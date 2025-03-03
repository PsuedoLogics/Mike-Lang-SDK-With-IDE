import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {

    public static String codeToProcess;
    public static JLabel outputLabel = new JLabel();
   // static Parser parser = new Parser();
    static ParserV2 parser = new ParserV2();
    public static void main(String[] args) {

        Window();

    }

    public static void Window()
    {

        try {
            // Set System L&F
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel");
        }
        catch (UnsupportedLookAndFeelException e) {
            // handle exception
        }
        catch (ClassNotFoundException e) {
            // handle exception
        }
        catch (InstantiationException e) {
            // handle exception
        }
        catch (IllegalAccessException e) {
            // handle exception
        }

        JFrame frame = new JFrame();
        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        frame.add(panel);
        
        JTextArea languageInput = new JTextArea("");
        languageInput.setFont(new Font("Roboto", Font.PLAIN, 14 ));

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
