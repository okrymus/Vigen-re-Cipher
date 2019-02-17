// GUI
// 
// Last Modified: 3/15/16

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class GUI extends JFrame {

    private JTextField text;
    private JTextField key;
    private JTextField finalText;
    private JRadioButton encrypt;
    private JRadioButton decrypt;
    private ButtonGroup mode;
    private JButton process;

    private VigenereCipher cipher = new VigenereCipher(VigenereCipher.ENCRYPT);

    GUI () {
        super("Vigemere Cipher");
        setLayout(new FlowLayout());
        text = new JTextField("type your plain/cypher text here", 20);
        add(text);

        key = new JTextField("type your key word here", 20);
        add(key);

        encrypt = new JRadioButton("encryption", true);
        decrypt = new JRadioButton("decryption", false);
        add(encrypt);
        add(decrypt);
        mode = new ButtonGroup();
        mode.add(encrypt);
        mode.add(decrypt);

        process = new JButton("get final text");
        add(process);

        finalText = new JTextField("final text", 20);
        finalText.setEditable(false);
        add(finalText);

        encrypt.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                cipher.setMode(cipher.ENCRYPT);
            }
        });

        decrypt.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                cipher.setMode(cipher.DECRYPT);
            }
        });

       /* text.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cipher.setText(e.getActionCommand());
            }
        });*/

        process.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cipher.setText(text.getText());
                cipher.setKeyWord(key.getText());
                finalText.setText(cipher.getFinalText());


                //System.out.print(text.getText());
            }
        });
    }
}
