package wizardofyesno;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.util.Random;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.UIManager;

public class WizardOfYesNo extends JFrame{
    private static final java.lang.String[] ANSWER = {"Yes", "For sure", "Go for it", "Definitely", "Yeppers",
                                                        "No", "Never", "Don't do it", "Couldn't be worse", "Told you so"};
    private static final long serialVersionUID = 1L;
    private String disclaimer = "This is only a suggestion. Use your own good judgment. The Wizard of Yes/No is not responsible for the consequences of your decisions.";
    public WizardOfYesNo(){
        Random rand = new Random();
        int numberOfAnswers = ANSWER.length;
        int pick = rand.nextInt(numberOfAnswers);
        String answer = ANSWER[pick];
        JLabel label = new JLabel();
        label.setText(answer);
        Font font = new Font(Font.SERIF, Font.BOLD, 32);
        label.setFont(font);
        label.setHorizontalAlignment(JLabel.CENTER);
        setTitle("WizardOfYesOrNo");
        label.setOpaque(true);
        if(pick >= 5)
            label.setBackground(Color.RED);
        else
            label.setBackground(Color.GREEN);
        add(label, BorderLayout.CENTER);
        setVisible(true);
        setResizable(false);
        setLocationRelativeTo(null);
        //setSize(300,300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JTextArea disclaimerTextArea = new JTextArea(disclaimer);
        disclaimerTextArea.setLineWrap(true);
        disclaimerTextArea.setWrapStyleWord(true);
        disclaimerTextArea.setEditable(false);

        JScrollPane scrollPane = new JScrollPane(disclaimerTextArea);
        Dimension dimension = new Dimension(300, 50);
        scrollPane.setPreferredSize(dimension);
        add(scrollPane, BorderLayout.PAGE_END);

        pack();
    }

    public static void main(String[] args){
        try{
            String classname = UIManager.getCrossPlatformLookAndFeelClassName();
            UIManager.setLookAndFeel(classname);
        }catch(Exception e){}

        EventQueue.invokeLater(new Runnable() {public void run() {
            new WizardOfYesNo();
        }});
    }
}