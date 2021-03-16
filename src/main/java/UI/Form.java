package UI;

import javax.swing.*;
import java.awt.*;

import static env.Constants.*;

/** Draws an URL input form and registers callback function responsible for parsing the page. */
public class Form extends JFrame {

    /** Input field for URL. */
    JTextArea tf;
    /** Container for UI elements. */
    JPanel panel;

    /** Callback. */
    Callback callback;

    /** Registers callback.
     * @param callback Callback.
     */
    public void registerCallBack(Callback callback){
        this.callback = callback;
    }

    /** Creates new form. */
    public Form() {
        setTitle("HTML Parser");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        create();
        add(panel);
        setSize(300, 250);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    /** Creates UI elements such as: label, input field and button - and adds an action listener. */
    private void create() {
        panel = new JPanel();
        panel.setPreferredSize(new Dimension(100, 100));
        panel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        BoxLayout boxlayout = new BoxLayout(panel, BoxLayout.Y_AXIS);
        panel.setLayout(boxlayout);

        JLabel label = new JLabel("Enter URL:");
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(label);
        panel.add(Box.createRigidArea(new Dimension(0, 5)));

        tf = tf = new JTextArea(PAGE_URL);
        tf.setMaximumSize(new Dimension(Integer.MAX_VALUE, 60));
        tf.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(tf);
        panel.add(Box.createRigidArea(new Dimension(0, 5)));

        JButton btn = new JButton("submit");
        btn.setAlignmentX(Component.CENTER_ALIGNMENT);
        btn.addActionListener(ae -> callback.callingBack(tf.getText()));

        panel.add(btn);
    }
}

