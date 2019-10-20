import java.util.Vector;

public class Utilities {
    static Integer find_max_Int(Vector<Integer> vector) {
        Integer max = 0;
        for (Integer x : vector) {
            if (x > max) {
                max = x;
            }
        }
        return max;
    }

    static Vector<Integer> string_array_to_integer_vector(String [] array){
        Vector<Integer> integers_vector = new Vector<>();
        for(String s:array){
            integers_vector.add(Integer.valueOf(s));
        }
        return integers_vector;
    }

    static void string_to_file(String data, String file_name){

    }

    static void file_to_string(String data, String file_name){

    }
}
