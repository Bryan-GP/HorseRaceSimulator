import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

//needs to add all the horse adjustments;
public class HorseWindow  implements ActionListener{
    JFrame frame;
    Horse horse;
    JButton Button;

    JRadioButton WhiteHorseButton;
    JRadioButton BlackHorseButton;
    JTextField HorseName;
    JButton SubmitButton;
    JSlider ConfidenceSlider;
    JFrame CHWframe;
    
    public HorseWindow( JFrame CHWframe, String windowName, JButton button, Horse horse){
        this.frame = new JFrame("Horse Racing Simulation");
        this.horse = horse;
        this.Button = button;
        this.CHWframe = CHWframe;

        //panel1
        JPanel panel1 = new JPanel(new BorderLayout());
        JPanel InsidePanel = new JPanel(new GridBagLayout());
        JLabel ConfidenceLabel = new JLabel("Confidence: ");

        //confidence slider Setting;
        ConfidenceSlider = new JSlider(0,100,50);
        ConfidenceSlider.add(ConfidenceLabel);
        ConfidenceSlider.setPreferredSize(new Dimension(400,50));
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
        JLabel HorseNameLabel = new JLabel("Enter Horse Name: ");
        //HorseNameLabel.setBackground(new Color(50,40,90));
        HorseName = new JTextField(windowName,40);
        HorseName.addActionListener(this);

        //add submit button submit
        SubmitButton = new JButton("Submit");
        SubmitButton.addActionListener(this);

        
        ////Horse(char horseSymbol, String horseName, double horseConfidence)
        //// onsubmit->make a new page of the costumise horses window 

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        
        //adding slider
        gbc.gridx=0;
        gbc.gridy=0;
        InsidePanel.add(ConfidenceLabel,gbc);
        gbc.gridx=1;
        gbc.gridy=0;
        InsidePanel.add(ConfidenceSlider,gbc);

        //adding horse choosing buttons
        gbc.gridx=0;
        gbc.gridy=1;
        InsidePanel.add(ChooseHorseLabel,gbc);
        gbc.gridx=1;
        gbc.gridy=1;
        InsidePanel.add(WhiteHorseButton,gbc);
        gbc.gridx=2;
        gbc.gridy=1;
        InsidePanel.add(BlackHorseButton,gbc);

        //adding the input box
        gbc.gridx=0;
        gbc.gridy=2;
        InsidePanel.add(HorseNameLabel, gbc);
        gbc.gridx=1;
        gbc.gridy=2;
        gbc.anchor = GridBagConstraints.WEST;
        InsidePanel.add(HorseName, gbc);

        //adding the submit button to the  bottom right of the insidepanel
        gbc.gridx=2;
        gbc.gridy=3;
        InsidePanel.add(SubmitButton, gbc);

        //adding the title panel
        JPanel titlePanel = new JPanel();
        titlePanel.setBackground(new Color(50,100,150));
        titlePanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        JLabel TitlePart = new JLabel(windowName + " Settings");
        TitlePart.setForeground(Color.WHITE); // White text
        titlePanel.add(TitlePart);
        
        InsidePanel.setBackground(new Color(200,200,200));
        panel1.add(InsidePanel);
        frame.add(titlePanel, BorderLayout.NORTH);
        frame.add(panel1, BorderLayout.CENTER);
        frame.setMinimumSize(new Dimension(800, 500));;
        frame.setVisible(true);
    }

    @Override   
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == this.WhiteHorseButton){ this.horse.setSymbol('\u265E'); }
        else if(e.getSource() == this.BlackHorseButton){ this.horse.setSymbol('\u2658'); }
        else if(e.getSource() == this.SubmitButton){
            this.frame.dispose();
            this.horse.setName(HorseName.getText());
            this.horse.setConfidence( ((double) ConfidenceSlider.getValue())/100);
            Button.setText( this.horse.getSymbol()+" "+  this.horse.getName()+" "+ this.horse.getConfidence());
            CHWframe.setVisible(true);
        }
    }
}
