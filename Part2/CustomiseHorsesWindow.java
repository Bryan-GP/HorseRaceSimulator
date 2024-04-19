
import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;


import javax.swing.*;

public class CustomiseHorsesWindow  {
    JFrame frame = new JFrame(); 
    private static double DefaultConfidence = 0.5;
    private static char DefaultSymbol = '\u265E';
    private static ArrayList<JButton> horses;
    private static LinkedHashMap<Integer, Horse> ReadyHorses;

    public CustomiseHorsesWindow(int HorseCount){
        //CustomiseHorsesWindow.HorseCount = HorseCount;
        CustomiseHorsesWindow.horses = new ArrayList<>();
        CustomiseHorsesWindow.ReadyHorses = new LinkedHashMap<>();
        JPanel panel = new JPanel(new GridLayout(HorseCount+1,1)); //added 1 for the submit button
        for(int i=0; i<HorseCount; i++){
            CustomiseHorsesWindow.horses.add(new JButton("Horse"+(i+1)));
            CustomiseHorsesWindow.ReadyHorses.put(i,new Horse( DefaultSymbol, "Horse"+(i+1), DefaultConfidence) );
        }
        for(int i=0; i<CustomiseHorsesWindow.horses.size(); i++){
            JButton b = CustomiseHorsesWindow.horses.get(i);
            Horse h = CustomiseHorsesWindow.ReadyHorses.get(i);
            String windowName = "Horse "+(i+1);
            b.addActionListener(e -> {
                if(e.getSource() == b){ 
                    frame.setVisible(false);
                    new HorseWindow( this, windowName, b, h );
                }
            });
            panel.add(b);
        }
        JButton SubmitButton = new JButton("START RACING");
        SubmitButton.setBackground(new Color(333));
        SubmitButton.addActionListener(e->{
            if(e.getSource() == SubmitButton){
                //frame.dispose();
                new RaceGUI(ReadyHorses);
            }
        });
        panel.add(SubmitButton);
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