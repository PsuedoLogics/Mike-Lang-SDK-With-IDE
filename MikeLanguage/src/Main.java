import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {

    public static String codeToProcess;
    public static JLabel outputLabel = new JLabel();
    static Parser parser = new Parser();
    public static void main(String[] args) {
        Window();

    }

    public static void Window()
    {

        JFrame frame = new JFrame();
        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        frame.add(panel);

        JTextArea languageInput = new JTextArea("Enter Code: ");


        languageInput.setPreferredSize(new Dimension(800, 300));
        panel.add(languageInput);

        JButton button = new JButton("RUN");
        panel.add(button);
        button.addActionListener(e -> {
            codeToProcess = languageInput.getText();
            parser.PrintCode(codeToProcess);
        });


        panel.add(outputLabel);

        frame.setVisible(true);

    }


}
