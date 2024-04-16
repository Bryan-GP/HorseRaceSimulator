import java.awt.*;

import javax.swing.*;

public class LaunchWindow {
    JFrame frame = new JFrame();
    JPanel panel2 = new JPanel();
    //JButton button = new JButton("Submit");
    public LaunchWindow(){
    
        panel2.setBackground(new Color(243, 235, 233)); // Light Gray
        panel2.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        JComboBox<Integer> nHorses = new JComboBox<>(new Integer[] {2,3,4,5,6,7,8,9,10});
        JLabel NumberOfHorsesLabel = new JLabel("Number of Horses: ");
        JButton submitButton = new JButton("Submit");
        submitButton.addActionListener(e -> { 
          if(e.getSource() == submitButton){ 
            frame.dispose();
            new CustomiseHorsesWindow(nHorses.getSelectedIndex()+2);
          }
        });
        
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel2.add(NumberOfHorsesLabel, gbc);
        gbc.gridx = 1;
        gbc.gridy = 0;
        panel2.add(nHorses, gbc);
        gbc.gridx = 2;
        gbc.gridy = 0;
        panel2.add(submitButton, gbc);

        frame.add(panel2, BorderLayout.CENTER);
        frame.setTitle("Horse Racing Simulation");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(420,420);
        frame.setVisible(true);
    }
}
