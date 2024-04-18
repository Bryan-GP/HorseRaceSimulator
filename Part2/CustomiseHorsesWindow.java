
import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;

public class CustomiseHorsesWindow{
    //private static final char Symbol = 0;
    JFrame frame = new JFrame(); 
    //JPanel panel;
    public static int HorseCount;
    public static double DefaultConfidence = 0.5;
    public static char DefaultSymbol = '\u265E';
    public static ArrayList<JButton> horses; 

    public CustomiseHorsesWindow(){
        JPanel panel = new JPanel(new GridLayout(HorseCount,2));
        for(int i=0; i<horses.size(); i++){
            JButton b = horses.get(i);
            String windowName = "Horse "+(i+1);
            b.addActionListener(e -> {
                if(e.getSource() == b){ 
                    frame.dispose();
                    char Symbol = DefaultSymbol;
                    String DefaultName = windowName;
                    double Confidence = DefaultConfidence;
                    new HorseWindow(windowName, b, new Horse( Symbol, DefaultName, Confidence));
                    
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
    public CustomiseHorsesWindow(int HorseCount){
        CustomiseHorsesWindow.HorseCount = HorseCount;
        JPanel panel = new JPanel(new GridLayout(HorseCount,2));
        horses = new ArrayList<>();
        for(int i=0; i<HorseCount; i++){ horses.add(new JButton("Horse"+ (i+1))); }
        for(int i=0; i<horses.size(); i++){
            JButton b = horses.get(i);
            String windowName = "Horse "+(i+1);
            b.addActionListener(e -> {
                if(e.getSource() == b){ 
                    frame.dispose();
                    char Symbol = DefaultSymbol;
                    String DefaultName = b.getText();
                    double Confidence = DefaultConfidence;
                    new HorseWindow(windowName, b, new Horse( Symbol, DefaultName, Confidence));
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
}

/* Testing Branch now*/ 