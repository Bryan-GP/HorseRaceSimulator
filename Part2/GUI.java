//import java.io.BufferedReader;
//import java.io.FileReader;
//import java.io.IOException;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedHashMap;
import javax.swing.*;

public class GUI{
    JTextArea RaceOutput;
    Race race;
    LinkedHashMap<Integer, Horse> horses;
    boolean StartHorseRace;
    public GUI(){ 
        this.StartHorseRace = false;
        LaunchWindow(); 
    }
    public void LaunchWindow(){
        JFrame frame = new JFrame();
        JPanel panel2 = new JPanel();
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
            CustomiseHorsesWindow(nHorses.getSelectedIndex()+2);
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

    public void CustomiseHorsesWindow(int HorseCount){
        JFrame frame = new JFrame(); 
        double DefaultConfidence = 0.5;
        char DefaultSymbol = '\u265E';
        ArrayList<JButton> Horses = new ArrayList<>();;
        LinkedHashMap<Integer, Horse> ReadyHorses = new LinkedHashMap<>();
        //CustomiseHorsesWindow.HorseCount = HorseCount;
        JPanel panel = new JPanel(new GridLayout(HorseCount+1,1)); //added 1 for the submit button
        for(int i=0; i<HorseCount; i++){
            Horses.add(new JButton("HORSE"+(i+1)));
            ReadyHorses.put((i+1), new Horse( DefaultSymbol, "HORSE"+(i+1), DefaultConfidence) );
        }
        for(int i=0; i<Horses.size(); i++){
            JButton b = Horses.get(i);
            Horse h = ReadyHorses.get(i+1);
            String windowName = "HORSE"+(i+1);
            b.addActionListener(e -> {
                if(e.getSource() == b){ 
                    frame.setVisible(false);
                    new HorseWindow( frame, windowName, b, h );
                }
            });
            panel.add(b);
        }
        JButton SubmitButton = new JButton("START RACING");
        SubmitButton.setBackground(new Color(333));
        SubmitButton.addActionListener(e->{
            if(e.getSource() == SubmitButton){
                frame.dispose();
                this.horses = ReadyHorses;
                this.StartHorseRace = true;
            }
        });
        panel.add(SubmitButton);
        frame.add(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Horse Racing Simulation");
        frame.setSize(500, 420);
        frame.setVisible(true);
    }

    public void RaceGUI(){
        JFrame frame = new JFrame();
        frame.setLayout(new FlowLayout());
        //JPanel panel = new JPanel(new BorderLayout());
        RaceOutput = new JTextArea();
        RaceOutput.setEditable(false);
        RaceOutput.setText(horses.toString());

        //panel.add(RaceOutput, BorderLayout.CENTER);
        frame.add(RaceOutput);
        frame.setBackground(Color.BLACK);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setMinimumSize(new Dimension(500,500));
        frame.setLocationRelativeTo(null);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setVisible(true);
        //changeText();
        race = new Race(10, this.horses, this.RaceOutput);
        race.startRace();
    }

    public void Clock(){
        //Calendar calendar;
        JLabel timeLabel;
        JLabel dayLabel;
        JLabel dateLabel;
        SimpleDateFormat timeFormat;
        SimpleDateFormat dayFormat;
        SimpleDateFormat dateFormat;
        String time;
        String day;
        String date;
        JFrame frame = new JFrame();

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("My Clock Program");
        frame.setLayout(new FlowLayout());
        frame.setSize(350,200);
        frame.setResizable(false);

        timeFormat = new SimpleDateFormat("hh:mm:ss a");
        dayFormat = new SimpleDateFormat("EEEE");
        dateFormat = new SimpleDateFormat("MMMMM dd, yyyy");

        timeLabel = new JLabel();
        timeLabel.setFont(new Font("Verdana",Font.PLAIN,50));
        timeLabel.setForeground(new Color(0x00FF00));
        timeLabel.setBackground(Color.black);
        timeLabel.setOpaque(true);

        dayLabel = new JLabel();
        dayLabel.setFont(new Font("Ink Free",Font.PLAIN,35));

        dateLabel = new JLabel();
        dateLabel.setFont(new Font("Ink Free",Font.PLAIN,25));


        frame.add(timeLabel);
        frame.add(dayLabel);
        frame.add(dateLabel);
        frame.setVisible(true);

        //setTime(timeLabel,dayLabel,dateLabel,timeFormat,dayFormat,dateFormat);
    
        while(true) {
            time = timeFormat.format(Calendar.getInstance().getTime());
            timeLabel.setText(time);

            day = dayFormat.format(Calendar.getInstance().getTime());
            dayLabel.setText(day);

            date = dateFormat.format(Calendar.getInstance().getTime());
            dateLabel.setText(date);
            
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    
    public LinkedHashMap<Integer, Horse> getTextInHorses(){
        horses = new LinkedHashMap<>();
        try(BufferedReader reader = new BufferedReader(new FileReader("Horses.txt"))){
            String[] horse;
            while(reader != null){
                horse = reader.readLine().split(",");
                int laneNumber = Integer.parseInt(horse[0]);
                String HorseName = horse[1];
                double HorseConfidence = Double.parseDouble(horse[2]);
                char HorseChar = horse[3].charAt(0);
                horses.put(laneNumber, new Horse(HorseChar, HorseName,HorseConfidence));
            }
        }catch(IOException e){}
        return horses;
    }
}
    /*
    }*/



