
import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;

public class CustomiseHorsesWindow  {
    //private static final char Symbol = 0
    //JPanel panel 
    //private JPanel panel = new JPanel(new GridLayout(horses.size(,2));
    //private static int HorseCount;
    JFrame frame = new JFrame(); 
    private static double DefaultConfidence = 0.5;
    private static char DefaultSymbol = '\u265E';
    private static ArrayList<JButton> horses; 

    public CustomiseHorsesWindow(int HorseCount){
        //CustomiseHorsesWindow.HorseCount = HorseCount;
        CustomiseHorsesWindow.horses = new ArrayList<>();
        JPanel panel = new JPanel(new GridLayout(HorseCount,2));
        for(int i=0; i<HorseCount; i++){ CustomiseHorsesWindow.horses.add(new JButton("Horse"+ (i+1))); }
        for(int i=0; i<CustomiseHorsesWindow.horses.size(); i++){
            JButton b = CustomiseHorsesWindow.horses.get(i);
            String windowName = "Horse "+(i+1);
            b.addActionListener(e -> {
                if(e.getSource() == b){ 
                    frame.setVisible(false);
                    //frame.dispose();
                    char Symbol = DefaultSymbol;
                    String DefaultName = b.getText();
                    double Confidence = DefaultConfidence;
                    new HorseWindow(this,windowName, b, new Horse( Symbol, DefaultName, Confidence));
                }
            });
            panel.add(b);
        }
        frame.add(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Horse Racing Simulation");
        frame.setSize(500, 420);
        frame.setVisible(true);
    }

    public void ChangeTextOnButton(char symbol, String name, double confidence, JButton Button){
        Button.setText( symbol + " " + name + " " + confidence );
        frame.setVisible(true);
    }
}

/* Testing Branch now*/ 