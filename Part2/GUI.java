


public class GUI {
    public GUI(){
        new LaunchWindow();
    }
}
/* 
    public JFrame frame;
    public JPanel panel1;
    public JPanel panel2;

    public GUI() {
        //panel1
        this.panel1 = new JPanel();
        this.panel1.setBackground(new Color(29, 112, 180)); // Dark Blue
        this.panel1.setLayout(new FlowLayout(FlowLayout.CENTER));
        JLabel Main = new JLabel("Choose Horses");
        Main.setForeground(Color.WHITE); // White text
        this.panel1.add(Main);

        //panel2
        this.panel2 = new JPanel();
        this.panel2.setBackground(new Color(243, 235, 233)); // Light Gray
        this.panel2.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        JComboBox<Integer> nHorses = new JComboBox<>(new Integer[] {2,3,4,5,6,7,8,9,10});
        JLabel NumberOfHorsesLabel = new JLabel("Number of Horses: ");
        JButton submitButton = new JButton("Submit");
        //giving the submit button funtionality
        submitButton.addActionListener(e -> { 
            this.panel1.remove(NumberOfHorsesLabel);
            CustomiseHorses(nHorses.getSelectedIndex()+2); 
        });
        
        gbc.gridx = 0;
        gbc.gridy = 0;
        this.panel2.add(NumberOfHorsesLabel, gbc);
        gbc.gridx = 1;
        gbc.gridy = 0;
        this.panel2.add(nHorses, gbc);
        gbc.gridx = 2;
        gbc.gridy = 0;
        this.panel2.add(submitButton, gbc);


        this.frame.add(this.panel1, BorderLayout.NORTH);
        this.frame.add(this.panel2, BorderLayout.CENTER);

        this.frame = new JFrame();
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.setTitle("Horse Racing Simulation");
        this.frame.setSize(500, 420);
        this.frame.setVisible(true);
    }

    private void CustomiseHorses(int horses) {
        
    }
    */

