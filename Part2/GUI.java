//import java.io.BufferedReader;
//import java.io.FileReader;
//import java.io.IOException;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.swing.*;


public class GUI{
    JFrame frame = new JFrame("HORSE RACING SIMULATION"); 
    JTextArea RaceOutput;
    JScrollPane scrollPane;
    JSpinner fontSizeSpinner;
    JLabel fontLabel;
    JButton colourButton;
    JButton StartButton;
    JComboBox<?> fontBox;
    JMenuBar MenuBar;
    JMenu SavesMenu;
    JMenuItem openItem;
    JMenuItem saveItem;
    JMenuItem exitItem;



    public GUI(){ 

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
        //JFrame frame = new JFrame();

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
            System.out.println("in the Clock while loop");
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
}
    


