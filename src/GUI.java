import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Vector;

public class GUI extends JFrame implements ActionListener {
    JButton btn_encrypt, btn_decrypt, btn_encrypt_file, btn_decrypt_file;
    JTextArea txt_decrypted, txt_encrypted;
    JTextArea txt_levelled;
    JLabel txt_input_info, txt_heights_info, txt_position_info;
    JTextField edt_input_decrypted, edt_input_heights, edt_input_position;
    JCheckBox chb_Visualization, chb_encrypt_text_to_file, chb_decrypt_text_to_file, chb_encrypt_file, chb_decrypt_file;
    JScrollPane sp_levelled;
    JFileChooser jfc_encrypt, jfc_decrypt;

    static String last_encrypted_string = "";

    GUI() {
        setSize(1080, 845);
        setTitle("Crypto");
        setLayout(null);

        Rectangle window = this.getBounds();

        txt_input_info = new JLabel("Enter text to encrypt:");
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

        chb_Visualization = new JCheckBox("Generate Visualization");
        chb_Visualization.setBounds(txt_position_info.getX(), edt_input_position.getY() + edt_input_position.getHeight() + 5, 200, 20);

        btn_encrypt = new JButton("ENCRYPT");
        btn_encrypt.setBounds(window.width / 2, chb_Visualization.getY() + chb_Visualization.getHeight() + 10, 150, 20);
        btn_encrypt.addActionListener(this);

        btn_encrypt_file = new JButton("Choose File...");
        btn_encrypt_file.setBounds(btn_encrypt.getX() - btn_encrypt.getWidth() - 5, btn_encrypt.getY(), btn_encrypt.getWidth(), btn_encrypt.getHeight());
        btn_encrypt_file.addActionListener(this);

        chb_encrypt_file = new JCheckBox("Encrypt file");
        chb_encrypt_file.setBounds(btn_encrypt_file.getX() - 100 - 5, btn_encrypt.getY(), 100, btn_encrypt_file.getHeight());

        chb_encrypt_text_to_file = new JCheckBox("Write text encryption to file");
        chb_encrypt_text_to_file.setBounds(btn_encrypt.getX() + btn_encrypt.getWidth() + 5, btn_encrypt.getY(), 200, btn_encrypt.getHeight());

        txt_encrypted = new JTextArea("Encrypted data:");
        txt_encrypted.setBounds(txt_input_info.getX(), btn_encrypt.getY() + btn_encrypt.getHeight() + 10, Double.valueOf(window.getWidth()).intValue() - 30, 200);
        txt_encrypted.setLineWrap(true);

        btn_decrypt = new JButton("DECRYPT");
        btn_decrypt.setBounds(btn_encrypt.getX(), txt_encrypted.getY() + txt_encrypted.getHeight() + 10, btn_encrypt.getWidth(), btn_encrypt.getHeight());
        btn_decrypt.addActionListener(this);

        btn_decrypt_file = new JButton("Choose File...");
        btn_decrypt_file.setBounds(btn_decrypt.getX() - btn_decrypt.getWidth() - 5, btn_decrypt.getY(), btn_decrypt.getWidth(), btn_decrypt.getHeight());
        btn_decrypt_file.addActionListener(this);

        chb_decrypt_file = new JCheckBox("Decrypt file");
        chb_decrypt_file.setBounds(btn_decrypt_file.getX() - 100 - 5, btn_decrypt.getY(), 100, btn_decrypt_file.getHeight());

        chb_decrypt_text_to_file = new JCheckBox("Write text decryption to file");
        chb_decrypt_text_to_file.setBounds(btn_decrypt.getX() + btn_decrypt.getWidth() + 5, btn_decrypt.getY(), 200, 20);

        txt_decrypted = new JTextArea("Decrypted data:");
        txt_decrypted.setBounds(txt_input_info.getX(), btn_decrypt.getY() + btn_decrypt.getHeight() + 10, Double.valueOf(window.getWidth()).intValue() - 30, 200);
        txt_decrypted.setLineWrap(true);

        txt_levelled = new JTextArea();
        txt_levelled.setWrapStyleWord(true);

        sp_levelled = new JScrollPane(txt_levelled);
        sp_levelled.setBounds(txt_decrypted.getX(), txt_decrypted.getY() + txt_decrypted.getHeight() + 10, Double.valueOf(window.getWidth()).intValue() - 30, 200);

        jfc_encrypt = new JFileChooser();
        jfc_encrypt.setCurrentDirectory(new File(System.getProperty("user.dir")));
        jfc_decrypt = new JFileChooser();
        jfc_decrypt.setCurrentDirectory(new File(System.getProperty("user.dir")));

        add(btn_encrypt);
        add(btn_decrypt);
        add(btn_encrypt_file);
        add(btn_decrypt_file);

        add(txt_decrypted);
        add(txt_encrypted);
        add(txt_input_info);
        add(txt_heights_info);
        add(txt_position_info);

        add(edt_input_decrypted);
        add(edt_input_heights);
        add(edt_input_position);

        add(chb_Visualization);
        add(chb_encrypt_text_to_file);
        add(chb_decrypt_text_to_file);
        add(chb_encrypt_file);
        add(chb_decrypt_file);

        add(jfc_encrypt);
        add(jfc_decrypt);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object event_source = e.getSource();
        Vector<Integer> heights = new Vector<>();
        if (!edt_input_heights.getText().isEmpty()) {
            if (heights.size() == 1) {
                heights.add(Integer.valueOf(edt_input_heights.getText()));
            } else {
                heights = Utilities.string_array_to_integer_vector(edt_input_heights.getText().split(","));
            }
        }

        Position pos;
        if (edt_input_position.getText().equals("TOP")) {
            pos = Position.TOP;
        } else {
            pos = Position.BOTTOM;
        }

        if (event_source == btn_encrypt) {

            String visualization = "";
            String encrypted_string = "";

            if (chb_encrypt_file.isSelected()) {
                File f = jfc_encrypt.getSelectedFile();
                String data_from_file = Utilities.file_to_string(f.getPath());

                if (chb_Visualization.isSelected()) {
                    String[] result = CryptoLib.encrypt(data_from_file, heights, pos, true);
                    encrypted_string = result[0];
                    visualization = result[1];

                    Utilities.write_string_to_file(visualization, "visualization.txt");

                } else {
                    encrypted_string = CryptoLib.encrypt(data_from_file, heights, pos);

                }

                Utilities.write_string_to_file(encrypted_string, f.getName() + ".blc");
            } else {
                String data_from_input = edt_input_decrypted.getText();

                if (chb_Visualization.isSelected()) {
                    String[] result = CryptoLib.encrypt(data_from_input, heights, pos, true);
                    encrypted_string = result[0];
                    last_encrypted_string = encrypted_string;
                    visualization = result[1];

                    txt_levelled.setText("Visualization:\n" + visualization);
                    add(sp_levelled);
                    Utilities.write_string_to_file(visualization, "visualization.txt");
                } else {
                    encrypted_string = CryptoLib.encrypt(data_from_input, heights, pos);
                    last_encrypted_string = encrypted_string;
                }
                if (chb_encrypt_text_to_file.isSelected()) {
                    Utilities.write_string_to_file(encrypted_string, "encryption.blc");
                }
                txt_encrypted.setText("Encrypted data:\n" + encrypted_string);
            }

            Utilities.write_string_to_file("Start Position: " + pos + "\nLevels List: " + heights, "latest_key.txt");

        } else if (event_source == btn_decrypt) {
            String decrypted_string = "Decrypted data:\n";
            if ((!chb_decrypt_file.isSelected())) {
                decrypted_string = CryptoLib.decrypt(last_encrypted_string, heights, pos);
                txt_decrypted.setText("Decrypted data:\n" + decrypted_string);
            }

            if (chb_decrypt_text_to_file.isSelected()) {
                Utilities.write_string_to_file(decrypted_string, "decryption.txt");
            }

            if (chb_decrypt_file.isSelected()) {
                File f = jfc_decrypt.getSelectedFile();
                String data_from_file = Utilities.file_to_string(f.getPath());
                decrypted_string = CryptoLib.decrypt(data_from_file, heights, pos);
                Utilities.write_string_to_file(decrypted_string, "decrypted_" + f.getName().replace(".blc", ""));
            }
        } else if (event_source == btn_encrypt_file) {
            jfc_encrypt.showOpenDialog(null);
            btn_encrypt_file.setText(jfc_encrypt.getSelectedFile().getName());
        } else if (event_source == btn_decrypt_file) {
            jfc_decrypt.showOpenDialog(null);
            btn_decrypt_file.setText(jfc_decrypt.getSelectedFile().getName());
        }

    }
}