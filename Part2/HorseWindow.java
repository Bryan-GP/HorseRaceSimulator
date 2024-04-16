import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

//needs to add all the horse adjustments;
public class HorseWindow  implements ActionListener{
    JFrame frame;//  = new JFrame();
    Horse horse;
    JRadioButton WhiteHorseButton;
    JRadioButton BlackHorseButton;

    public HorseWindow( String s ){

        frame = new JFrame(s);

        //panel1
        JPanel panel1 = new JPanel(new GridBagLayout());
        JLabel ConfidenceLabel = new JLabel("Confidence: ");
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        //confidence Setting;
        JSlider ConfidenceSlider = new JSlider(0,100,50);
        ConfidenceSlider.add(ConfidenceLabel);
        ConfidenceSlider.setPreferredSize(new Dimension(400,200));
        ConfidenceSlider.setPaintTicks(true);
        ConfidenceSlider.setMinorTickSpacing(2);
        ConfidenceSlider.setPaintTrack(true);
        ConfidenceSlider.setMajorTickSpacing(25);
        ConfidenceSlider.setPaintLabels(true);
        ConfidenceSlider.setSnapToTicks(true);

        //add Radio options for the type of horse
        ButtonGroup group = new ButtonGroup();
        JLabel ChooseHorseLabel = new JLabel("Choose Horse Type:");
        WhiteHorseButton = new JRadioButton("\u265E");
        BlackHorseButton = new JRadioButton("\u2658");
        group.add(WhiteHorseButton);
        group.add(BlackHorseButton);
        WhiteHorseButton.addActionListener(this);
        BlackHorseButton.addActionListener(this);
        

        //add input box
        //add submit button 
        ////Horse(char horseSymbol, String horseName, double horseConfidence)
        //// onsubmit->make a new page of the costumise horses window 

        gbc.gridx = 0;
        gbc.gridy = 0;
        panel1.add(ConfidenceLabel, gbc);
        gbc.gridx = 1;
        gbc.gridy = 0;
        panel1.add(ConfidenceSlider, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        panel1.add(ChooseHorseLabel, gbc);
        gbc.gridx = 1;
        gbc.gridy = 1;
        panel1.add(WhiteHorseButton, gbc);
        gbc.gridx = 2;
        gbc.gridy = 1;
        panel1.add(BlackHorseButton, gbc);




        //frame.add(panel0);
        frame.add(panel1);
        frame.setMinimumSize(new Dimension(600, 450));;
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == WhiteHorseButton){}
        else if(e.getSource() == BlackHorseButton){}
    }
}
