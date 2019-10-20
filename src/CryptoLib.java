import java.util.Vector;


public class CryptoLib {

    public static String encrypt(String decrypted_data, Vector<Integer> levels, Position pos) {
        String[] result = encrypt(decrypted_data, levels, pos, false);
        return result[0];
    }

    public static String[] encrypt(String decrypted_data, Vector<Integer> levels, Position start_point, Boolean generate_visualization) {

        Vector<Integer> levels_vector = new Vector<>(levels);
        Vector<Pair<Integer, Character>> decrypted_levelled = new Vector<>();
        Vector<Integer> numbers_vector = new Vector<>();

        while (numbers_vector.size() < decrypted_data.length()) {
            Integer actual_height = levels_vector.firstElement();
            levels_vector.add(levels_vector.firstElement());
            levels_vector.remove(levels_vector.firstElement());

            numbers_vector.addAll(generate_level_numbers(actual_height, start_point));
        }


        for (int i = 0; i < numbers_vector.size(); i++) {
            if (i < decrypted_data.length()) {
                decrypted_levelled.add(new Pair<>(numbers_vector.elementAt(i), decrypted_data.charAt(i)));
            } else {
                decrypted_levelled.add(new Pair<>(numbers_vector.elementAt(i), ' '));
            }

        }

        String visual_output = "";
        if (generate_visualization) {
            visual_output = generate_visualization(decrypted_levelled, numbers_vector);
        }

        Vector<Pair<Integer, Character>> level_to_encrypt = new Vector<>();
        String encrypted_data = "";

        for (int i = 0; i <= Utilities.find_max_Int(levels_vector); i++) {
            for (Pair<Integer, Character> p : decrypted_levelled) {
                if (p.key == i) {
                    encrypted_data += p.value;
                    level_to_encrypt.add(p);
                }
            }
            decrypted_levelled.removeAll(level_to_encrypt);
            level_to_encrypt.removeAllElements();
        }

        String[] result = new String[2];
        result[0] = encrypted_data;
        result[1] = visual_output;

        return result;
    }

    public static String decrypt(String encrypted_data, Vector<Integer> levels, Position start_point) {

        Vector<Integer> numbers_vector = new Vector<>();
        Vector<Integer> levels_vector = new Vector<>(levels);

        Integer actual_height;
        while (numbers_vector.size() < encrypted_data.length()) {
            actual_height = levels_vector.firstElement();
            levels_vector.add(levels_vector.firstElement());
            levels_vector.remove(levels_vector.firstElement());

            numbers_vector.addAll(generate_level_numbers(actual_height, start_point));
        }

        char[] decrypted_data = new char[encrypted_data.length()];
        int max_height = Utilities.find_max_Int(levels_vector);

        for (int i = 1; i <= max_height; i++) {
            for (int j = 0; j < numbers_vector.size(); j++) {
                if (numbers_vector.elementAt(j) == i) {
                    decrypted_data[j] = encrypted_data.charAt(0);
                    encrypted_data = encrypted_data.substring(1);
                }
            }
        }

        return new String(decrypted_data);
    }


    static private Vector<Integer> generate_level_numbers(Integer height, Position start_point) {
        Vector<Integer> vec = new Vector<>();

        if (start_point == Position.BOTTOM) {
            for (Integer i = 1; i < height; i++) {
                vec.add(i);
            }
            for (Integer i = height; i > 1; i--) {
                vec.add(i);
            }
        } else {
            for (Integer i = height; i > 1; i--) {
                vec.add(i);
            }
            for (Integer i = 1; i < height; i++) {
                vec.add(i);
            }
        }

        return vec;
    }

    static String generate_visualization(Vector<Pair<Integer, Character>> levelled_data, Vector<Integer> numbers_vector) {
        char[] line_to_print = new char[levelled_data.size()];

        String output = "";

        for (int i = Utilities.find_max_Int(numbers_vector); i > 0; i--) {
            for (int j = 0; j < levelled_data.size(); j++) {
                Pair<Integer, Character> p = levelled_data.elementAt(j);
                if (p.key == i) {
                    line_to_print[j] = p.value;
                }
            }
            if (i<10){
                output += "0" + i + ":" + String.valueOf(line_to_print) + "\n";
            }
            else{
                output += i + ":" + String.valueOf(line_to_print) + "\n";
            }
            line_to_print = new char[levelled_data.size()];
        }
        return output;
    }
}
