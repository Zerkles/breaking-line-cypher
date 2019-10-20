import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.MessageFormat;
import java.util.Vector;

public class GUI extends JFrame implements ActionListener {
    JButton btn_encrypt, btn_decrypt;
    JTextArea txt_decrypted, txt_encrypted ;
    JTextArea txt_levelled;
    JLabel txt_input_info, txt_heights_info, txt_position_info;
    JTextField edt_input_decrypted, edt_input_heights, edt_input_position;
    JCheckBox chb_virtualization;
    JScrollPane sp_levelled;

    static String encrypted_string = "";

    GUI() {
        setSize(1080, 960);
        setTitle("Crypto");
        setLayout(null);

        Rectangle window = this.getBounds();

        txt_input_info = new JLabel("Enter Data to encrypt:");
        txt_input_info.setBounds(10, 10, 130, 20);

        int text_input_x = txt_input_info.getX() + txt_input_info.getWidth() + 10;
        edt_input_decrypted = new JTextField();
        edt_input_decrypted.setBounds(text_input_x, txt_input_info.getY(), Double.valueOf(window.getWidth()).intValue() - text_input_x - 20, txt_input_info.getHeight());

        txt_heights_info = new JLabel("Enter heights (divided by ','):");
        txt_heights_info.setBounds(txt_input_info.getX(), txt_input_info.getY() + txt_input_info.getHeight() + 5, 160, txt_input_info.getHeight());

        int heights_input_x = txt_heights_info.getX() + txt_heights_info.getWidth() + 10;
        edt_input_heights = new JTextField();
        edt_input_heights.setBounds(heights_input_x, txt_heights_info.getY(), Double.valueOf(window.getWidth()).intValue() - heights_input_x - 20, txt_heights_info.getHeight());

        txt_position_info = new JLabel("(optional) Enter start position (TOP/BOTTOM):");
        txt_position_info.setBounds(txt_heights_info.getX(), edt_input_heights.getY() + edt_input_heights.getHeight() + 5, 255, edt_input_heights.getHeight());

        int position_input_x = txt_position_info.getX() + txt_position_info.getWidth() + 10;
        edt_input_position = new JTextField();
        edt_input_position.setBounds(position_input_x, txt_position_info.getY(), Double.valueOf(window.getWidth()).intValue() - position_input_x - 20, txt_position_info.getHeight());

        chb_virtualization = new JCheckBox("Generate Virtualization");
        chb_virtualization.setBounds(txt_position_info.getX(), edt_input_position.getY() + edt_input_position.getHeight() + 5, 200, 20);

        btn_encrypt = new JButton("ENCRYPT");
        btn_encrypt.setBounds(window.width / 2, chb_virtualization.getY() + chb_virtualization.getHeight() + 10, 150, 20);
        btn_encrypt.addActionListener(this);

        txt_encrypted = new JTextArea("Encrypted data:");
        txt_encrypted.setBounds(txt_input_info.getX(), btn_encrypt.getY() + btn_encrypt.getHeight() + 10, Double.valueOf(window.getWidth()).intValue() - 30, 200);
        txt_encrypted.setLineWrap(true);

        btn_decrypt = new JButton("DECRYPT");
        btn_decrypt.setBounds(btn_encrypt.getX(), txt_encrypted.getY() + txt_encrypted.getHeight() + 10, btn_encrypt.getWidth(), btn_encrypt.getHeight());
        btn_decrypt.addActionListener(this);

        txt_decrypted = new JTextArea("Decrypted data:");
        txt_decrypted.setBounds(txt_input_info.getX(), btn_decrypt.getY() + btn_decrypt.getHeight() + 10, Double.valueOf(window.getWidth()).intValue() - 30, 200);
        txt_decrypted.setLineWrap(true);

        txt_levelled = new JTextArea();
        //txt_levelled.setBounds(txt_decrypted.getX(), txt_decrypted.getY() + txt_decrypted.getHeight() + 10, Double.valueOf(window.getWidth()).intValue() - 30, 200);
//        txt_levelled.setLineWrap(false);
        txt_levelled.setWrapStyleWord(true);

        sp_levelled = new JScrollPane(txt_levelled);
        sp_levelled.setBounds(txt_decrypted.getX(), txt_decrypted.getY() + txt_decrypted.getHeight() + 10, Double.valueOf(window.getWidth()).intValue() - 30, 200);


        add(btn_encrypt);
        add(btn_decrypt);

        add(txt_decrypted);
        add(txt_encrypted);
        add(txt_input_info);
        add(txt_heights_info);
        add(txt_position_info);

        add(edt_input_decrypted);
        add(edt_input_heights);
        add(edt_input_position);

        add(chb_virtualization);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object event_source = e.getSource();
        Vector<Integer> heights = Utilities.string_array_to_integer_vector(edt_input_heights.getText().split(","));;

        Position pos;
        if (edt_input_position.getText().equals("TOP")){
            pos = Position.TOP;
        }
        else{
            pos= Position.BOTTOM;
        }

        if (event_source == btn_encrypt) {

            String data = edt_input_decrypted.getText();

            if (chb_virtualization.isSelected()) {
                String[] result;

                result = CryptoLib.encrypt(data, heights, pos, true);

                encrypted_string = result[0];
                String visualization = result[1];
                txt_encrypted.setText("Encrypted data:\n" + encrypted_string);

                txt_levelled.setText("Visualization:\n"+ visualization);
                System.out.println(visualization);

                add(sp_levelled);
            } else {

                encrypted_string = CryptoLib.encrypt(data, heights, pos);
                txt_encrypted.setText("Encrypted data:\n" + encrypted_string);
            }
        } else if (event_source == btn_decrypt) {
            String decrypted_string = CryptoLib.decrypt(encrypted_string, heights, pos);
            txt_decrypted.setText("Decrypted data:\n" + decrypted_string);
        }

    }
}