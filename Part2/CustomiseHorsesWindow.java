
import java.awt.*;
import java.util.ArrayList;
import java.util.ListIterator;
import javax.swing.*;

public class CustomiseHorsesWindow{
    JFrame frame = new JFrame(); 
    JPanel panel;
    public CustomiseHorsesWindow(int HorseCount){
        panel = new JPanel(new GridLayout(HorseCount,2));
        ArrayList<JButton> horses = new ArrayList<>();
        ArrayList<Horse> horseList = new ArrayList<>();
        for(int i=0; i<HorseCount; i++){ horses.add(new JButton("Horse "+ (i+1))); }
        ListIterator<JButton> i = horses.listIterator();
        while(i.hasNext()){
            JButton b = i.next();
            b.addActionListener(e -> {
                if(e.getSource() == b){ 
                    //frame.dispose();
                    //make a horse page then use sliders and stuff to adjust horse stats.
                    HorseWindow window = new HorseWindow(b.getText());
                    horseList.add(window.horse);
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