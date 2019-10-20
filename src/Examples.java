import java.util.Vector;
import javax.swing.*;

public class Examples {
    public static void main(String[] args) {
        test();

//        GUI okno = new GUI();
//        okno.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        okno.setVisible(true);
    }

    static void test(){
        String text_to_encrypt = "Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.";
        System.out.println("TEKST:" + text_to_encrypt);

        String heights_string = "13,3,8";
        gui(text_to_encrypt, heights_string);
        console(text_to_encrypt, heights_string);

    }


    static void gui(String text, String heights) {
        GUI okno = new GUI();
        okno.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        okno.setVisible(true);
        okno.edt_input_decrypted.setText(text);
        okno.edt_input_heights.setText(heights);
    }

    static void console(String text, String heights_string) {
        Vector<Integer> heights = Utilities.string_array_to_integer_vector(heights_string.split(","));

        String[] result = CryptoLib.encrypt(text, heights, Position.BOTTOM, true);
        String encrypted_data = result[0];
        String visualization = result[1];
        System.out.println("RESULT_ENCRYPT:" + encrypted_data);
        System.out.println("VISUALIZATION:\n" + visualization);

        String decrypted_data = CryptoLib.decrypt(encrypted_data, heights, Position.BOTTOM);
        System.out.println("RESULT_DECRYPT:" + decrypted_data);
    }
}
