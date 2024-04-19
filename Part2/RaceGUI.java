import java.util.LinkedHashMap;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class RaceGUI {
    JFrame frame = new JFrame("HORSE RACING SIMULATOR");
    public RaceGUI(LinkedHashMap<Integer,Horse> horses ){
        JPanel panel = new JPanel();

        JTextField RaceOutput = new JTextField();
        RaceOutput.setEditable(false);
        

        frame.add(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 420);
        frame.setVisible(true);
    }
}
//make this calss show the race with all the horses.
//in RacingGUI branch now
//make a textfield and use that as the terminal.
