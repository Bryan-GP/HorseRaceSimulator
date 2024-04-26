

/**
 * A three-horse race, each horse running in its own lane
 * for a given distance
 * 
 * @author McFarewell
 * @version 1.0
 */

import java.awt.*;
import java.io.*;
import java.util.*;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.filechooser.FileNameExtensionFilter;


public class Race {
    //main Frame and things connected to it
    private JFrame frame = new JFrame("HORSE RACING SIMULATION"); 
    private JTextArea RaceOutput;
    private JScrollPane scrollPane;
    private JLabel fontLabel;
    private JSpinner fontSizeSpinner;
    private JButton colourButton;
    private JComboBox<?> fontBox;
    private JMenuBar MenuBar;
    private JMenu SavesMenu;
    private JMenuItem openItem;
    private JMenuItem saveItem;
    private JMenuItem exitItem;

    //horse custumisation instance variables
    private JMenu RaceOptions;
    private JMenuItem NumberOfHorses;
    private int NumberOfHorsesInt = 2;
    private JMenuItem CostumiseHorses;
    private JMenuItem CostumiseTrack;

    //virtual betting instance variables
    private JMenu VirtualBetting;
    private boolean VirtualBettingUp;
    private Horse HorseBetted;
    private int BetAmount;
    private JMenuItem PlaceBet;
    private int Coins = 100; //TODO add Currency to Virtual betting system.

    //button to start the race
    private JButton StartButton;

    //instance variables needed throughout the class
    private double CurrentWinningTime;
    private LinkedHashMap<Integer, ArrayList<Double>> WiningTimesForCurrentHorses = new LinkedHashMap<>(); //TODO add Timer
    private LinkedHashMap<Integer, Horse> horses = new LinkedHashMap<>();
    private int TrackLength = 20;
    private char TrackChar = '=';
    private String Text;
    private boolean finished;
    private int numberFallen = 0;
    private final double DefaultConfidence = 0.5;
    private final char DefaultSymbol = '\u265E';
    private final String DefaultName = "HORSE";
    private Horse DefaultHorse1 = new Horse(DefaultSymbol,DefaultName+"1",DefaultConfidence);
    private Horse DefaultHorse2 = new Horse(DefaultSymbol,DefaultName+"2",DefaultConfidence);
    
    public Race(){

        //default values for Horses.
        horses.put(1,DefaultHorse1);
        horses.put(2,DefaultHorse2);
        WiningTimesForCurrentHorses.put(1, new ArrayList<Double>());
        WiningTimesForCurrentHorses.put(2, new ArrayList<Double>());

        //making the frame
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 600);
        frame.setLayout(new FlowLayout());
        frame.setBackground(Color.BLACK);
        frame.setLocationRelativeTo(null);
        frame.setMaximumSize(new Dimension(600,600));
        frame.setMinimumSize(new Dimension(600,600));

        //making the main output
        RaceOutput = new JTextArea();
        RaceOutput.setText("Step1: Choose Horses\nStep2: Customise Horses\n(Optional) Step3: Place Bet");
        RaceOutput.setSize(500,600);
        RaceOutput.setFont(new Font( "Arial", Font.PLAIN, 15));
        RaceOutput.setLineWrap(true);
        RaceOutput.setWrapStyleWord(true);
        RaceOutput.setForeground(new Color(0x10FF10));
        RaceOutput.setBackground(Color.BLACK);

        //allowing Scrolling
        scrollPane = new JScrollPane(RaceOutput);
        scrollPane.setPreferredSize(new Dimension(450,450));
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        //font label and 
        fontLabel = new JLabel("Font Size");

        //font size changer
        fontSizeSpinner = new JSpinner();
        fontSizeSpinner.setPreferredSize(new Dimension(50,25));
        fontSizeSpinner.setValue(15);
        fontSizeSpinner.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) { RaceOutput.setFont( new Font(RaceOutput.getFont().getFamily() , Font.PLAIN, (int) fontSizeSpinner.getValue() )); }
        });

        //colour changer
        colourButton = new JButton("Colour");
        colourButton.addActionListener(e->{
            new JColorChooser();
            Color color = JColorChooser.showDialog(null, "Choose a color", Color.black);
            RaceOutput.setForeground(color);
        });

        //font changer
        String[] fonts = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
        fontBox = new JComboBox<>(fonts);
        fontBox.addActionListener(e->{
            RaceOutput.setFont(new Font((String)fontBox.getSelectedItem(), Font.PLAIN, RaceOutput.getFont().getSize()));
        });
        fontBox.setSelectedItem("Arial");

        //menu Bar
        MenuBar = new JMenuBar();

        //Saves
        SavesMenu = new JMenu("Saves");
        openItem = new JMenuItem("Open");
        saveItem = new JMenuItem("Save");
        exitItem = new JMenuItem("Exit");

        saveItem.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setCurrentDirectory(new File("."));
            int response = fileChooser.showSaveDialog(null);
            if(response == JFileChooser.APPROVE_OPTION){
                File file = new File(fileChooser.getSelectedFile().getAbsolutePath());
                try (PrintWriter writer = new PrintWriter(file)) {
                    //writer.print(WiningTimesForCurrentHorses.toString())//TODO save the times and stuff
                    //writer.println(RaceOutput.getText());
                } 
                catch (FileNotFoundException e1) { }
            }
        });
        openItem.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setCurrentDirectory(new File("."));
            FileNameExtensionFilter filter = new FileNameExtensionFilter("Text files", "txt");
            fileChooser.setFileFilter(filter);
            int response = fileChooser.showOpenDialog(null);
            if(response == JFileChooser.APPROVE_OPTION){
                File file = new File(fileChooser.getSelectedFile().getAbsolutePath());
                Scanner fileIn = null;
                try{
                    fileIn = new Scanner(file);
                    if(file.isFile()){
                        while(fileIn.hasNextLine()){
                            String line = fileIn.nextLine() + "\n";
                            RaceOutput.append(line);
                        }
                    }
                }catch(IOException e2){}

            }
        });
        exitItem.addActionListener(e -> {
            System.exit(0);
        });

        RaceOptions = new JMenu("RaceOptions");
        NumberOfHorses = new JMenuItem("NumberOfHorses");
        CostumiseHorses = new JMenuItem("CostumiseHorses");
        CostumiseTrack = new JMenuItem("CostumiseTrack");

        NumberOfHorses.addActionListener(e->{ ChooseNumberOfHorses(); });
        CostumiseHorses.addActionListener(e->{ CustomiseHorsesWindow(); });
        CostumiseTrack.addActionListener(e->{ CustomiseTrack(); });

        VirtualBetting = new JMenu("VirtualBetting");
        PlaceBet = new JMenuItem("Place Bet");

        PlaceBet.addActionListener(e->{ PlaceBet(); });

        VirtualBetting.add(PlaceBet);

        RaceOptions.add(NumberOfHorses);
        RaceOptions.add(CostumiseHorses);
        RaceOptions.add(CostumiseTrack);
        SavesMenu.add(openItem);
        SavesMenu.add(saveItem);
        SavesMenu.add(exitItem);

        MenuBar.add(SavesMenu);
        MenuBar.add(RaceOptions);
        MenuBar.add(VirtualBetting);

        StartButton = new JButton("START");
        StartButton.addActionListener(e->{ startRaceGUI(); });

        frame.setJMenuBar(MenuBar);
        frame.add(fontLabel);
        frame.add(fontSizeSpinner);
        frame.add(colourButton);
        frame.add(fontBox);
        frame.add(StartButton);
        frame.add(scrollPane);
        frame.setVisible(true);
    }

    public void ChooseNumberOfHorses(){
        JFrame ChooseNumberOfHorsesFrame = new JFrame();
        JPanel panel2 = new JPanel();
        panel2.setBackground(new Color(243, 235, 233)); // Light Gray
        panel2.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        JComboBox<Integer> nHorses = new JComboBox<>(new Integer[] {2,3,4,5,6,7,8,9,10});
        nHorses.setSelectedItem(NumberOfHorsesInt);
        JLabel NumberOfHorsesLabel = new JLabel("Number of Horses: ");
        JButton submitButton = new JButton("Submit");
        submitButton.addActionListener(e -> { 
            NumberOfHorsesInt = nHorses.getSelectedIndex()+2;
            //make it so that the number of horses
            horses = new LinkedHashMap<>();
            for(int i=0; i<this.NumberOfHorsesInt; i++){
                horses.put((i+1), new Horse( DefaultSymbol, "HORSE"+(i+1), DefaultConfidence) );
                WiningTimesForCurrentHorses.put(i+1, new ArrayList<>());
            }
            ChooseNumberOfHorsesFrame.dispose();
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

        ChooseNumberOfHorsesFrame.add(panel2, BorderLayout.CENTER);
        ChooseNumberOfHorsesFrame.setTitle("Horse Racing Simulation");
        ChooseNumberOfHorsesFrame.setSize(420,420);
        ChooseNumberOfHorsesFrame.setVisible(true);
    }

    public void CustomiseHorsesWindow(){
        JFrame CustomiseHorsesFrame = new JFrame("Customise Horses");
        ArrayList<JButton> HorseButtons = new ArrayList<>();;
        JPanel panel = new JPanel(new GridLayout(this.NumberOfHorsesInt+2,1)); //added 1 for the submit button
        for(int i=0; i<this.NumberOfHorsesInt; i++){ HorseButtons.add(new JButton("HORSE"+(i+1))); }
        for(int i=0; i<HorseButtons.size(); i++){
            JButton b = HorseButtons.get(i);
            Horse h = horses.get(i+1);
            String windowName = "HORSE"+(i+1);
            b.addActionListener(e -> {
                if(e.getSource() == b){ 
                    CustomiseHorsesFrame.setVisible(false);
                    new HorseWindow( CustomiseHorsesFrame, windowName, b, h );
                }
            });
            panel.add(b);
        }
        JButton RandomiseButton = new JButton("RANDOMISE");
        RandomiseButton.setBackground(new Color(333));
        RandomiseButton.addActionListener(e->{
            System.out.println(horses.toString());
            for (int i = 0; i < NumberOfHorsesInt; i++) {
                Horse h = horses.get(i+1);
                Random rand = new Random();
                int random = rand.nextInt(2)+1;
                char randomSymbol = random == 1? DefaultSymbol: '\u2658';
                double randomConfidence = Math.floor((Math.random())*100)/100;
                //System.out.println(horses.toString());
                h.setConfidence(randomConfidence);
                h.setSymbol(randomSymbol);
                //, new Horse(randomSymbol, ""+DefaultName+i, randomConfidence));
            }
            CustomiseHorsesFrame.dispose();
        });
        JButton SubmitButton = new JButton("SUBMIT ");
        SubmitButton.setBackground(new Color(333));
        SubmitButton.addActionListener(e->{
            CustomiseHorsesFrame.dispose();
        });
        panel.add(SubmitButton);
        panel.add(RandomiseButton);
        CustomiseHorsesFrame.add(panel);
        CustomiseHorsesFrame.setTitle("Horse Racing Simulation");
        CustomiseHorsesFrame.setSize(500, 420);
        CustomiseHorsesFrame.setVisible(true);
    }

    public void CustomiseTrack(){
        JFrame CustomiseTrackFrame = new JFrame("Customise The Track");
        JPanel panel2 = new JPanel();
        panel2.setBackground(new Color(243, 235, 233)); // Light Gray
        panel2.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        //confidence slider Setting;
        JLabel NumberOfHorsesLabel = new JLabel("Length Of Track: ");
        JSlider ConfidenceSlider = new JSlider(0,100,50);
        ConfidenceSlider.setValue(20);
        ConfidenceSlider.setPreferredSize(new Dimension(400,50));
        ConfidenceSlider.setPaintTicks(true);
        ConfidenceSlider.setMinorTickSpacing(2);
        ConfidenceSlider.setPaintTrack(true);
        ConfidenceSlider.setMajorTickSpacing(25);
        ConfidenceSlider.setPaintLabels(true);
        ConfidenceSlider.setSnapToTicks(true);

        JLabel TrackCharacterLabel = new JLabel("type a character for the Track: ");
        JTextField TrackCharacter = new JTextField(10);
        TrackCharacter.setText(TrackChar+"");
        TrackCharacter.setSize(20, 10);

        JButton submitButton = new JButton("Submit");
        submitButton.addActionListener(e -> {
            TrackChar = TrackCharacter.getText().charAt(0);
            TrackChar = TrackChar != '\u0000'?TrackChar:'=';
            TrackLength = ConfidenceSlider.getValue();
            CustomiseTrackFrame.dispose();
        });
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel2.add(NumberOfHorsesLabel, gbc);
        gbc.gridx = 1;
        gbc.gridy = 0;
        panel2.add(ConfidenceSlider, gbc);
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel2.add(TrackCharacterLabel, gbc);
        gbc.gridx = 1;
        gbc.gridy = 1;
        panel2.add(TrackCharacter, gbc);
        gbc.gridx = 1;
        gbc.gridy = 2;
        panel2.add(submitButton, gbc);

        CustomiseTrackFrame.add(panel2, BorderLayout.CENTER);
        //CustomiseTrackFrame.setTitle("Horse Racing Simulation");
        //ChooseNumberOfHorsesFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        CustomiseTrackFrame.setSize(1000,420);
        CustomiseTrackFrame.setVisible(true);
    }

    public void PlaceBet(){
        JFrame PlaceBetFrame = new JFrame("Place A Bet");
        PlaceBetFrame.setLayout(new FlowLayout());
        JPanel panel = new JPanel();
        panel.setBackground(new Color(243, 235, 233)); // Light Gray
        panel.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, horses.size()+2, horses.size()+2);

        //confidence slider Setting;
        JLabel BetAmountSliderLabel = new JLabel("Number of Coins Betted: ");
        JSlider BetAmountSlider = new JSlider(0,100,50);
        BetAmountSlider.setValue(20);
        BetAmountSlider.setPreferredSize(new Dimension(400,50));
        BetAmountSlider.setPaintTicks(true);
        BetAmountSlider.setMinorTickSpacing(2);
        BetAmountSlider.setPaintTrack(true);
        BetAmountSlider.setMajorTickSpacing(25);
        BetAmountSlider.setPaintLabels(true);
        BetAmountSlider.setSnapToTicks(true);

        //add Radio options for the type of horse
        ButtonGroup group = new ButtonGroup();
        JLabel ChooseHorseLabel = new JLabel("Choose Horse to Bet on:");
        LinkedHashMap<Integer,JRadioButton> horseBetButtons = new LinkedHashMap<>();
        for(int i=1; i<=horses.size(); i++){
            Horse h = horses.get(i);
            JRadioButton horseBetButton = new JRadioButton(h.getName()+" ("+h.getConfidence()+")");
            group.add(horseBetButton);
            horseBetButton.addActionListener(e->{
                VirtualBettingUp = true;
                HorseBetted = h;
            });
            horseBetButtons.put(i,horseBetButton);
        }

        //Add SumbitButton
        JButton submitButton = new JButton("Submit");
        submitButton.setBackground(Color.GREEN);
        submitButton.addActionListener(e -> {
            BetAmount = BetAmountSlider.getValue();
            HorseBetted = HorseBetted != null?HorseBetted:horses.get(1);
            PlaceBetFrame.dispose();
        });

        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(BetAmountSliderLabel, gbc);
        gbc.gridx = 1;
        gbc.gridy = 0;
        panel.add(BetAmountSlider, gbc);
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(ChooseHorseLabel);
        for(int i=1; i<=horseBetButtons.size(); i++){
            gbc.gridx = 1;
            gbc.gridy = i+1;
            panel.add(horseBetButtons.get(i), gbc);
        }
        
        gbc.gridx = 2;
        gbc.gridy = 2;
        panel.add(submitButton, gbc);

        PlaceBetFrame.add(panel , BorderLayout.CENTER);
        PlaceBetFrame.setSize(1000,700);
        PlaceBetFrame.setVisible(true);
    }


    private void checkIfBetWon(Horse winner){
        if(winner.equals(HorseBetted)){ 
            Text += "\nYOUR "  + BetAmount + " BET WINS!"; 
        }else{
            Text += "\nYour bet of " + BetAmount + " has been lost.";
        }
    }


    private void startRaceGUI(){
        finished = false;
        CurrentWinningTime = 0;
        for( int i=1; i<=horses.size(); i++ ){ 
            Horse h = horses.get(i);
            h.pickUpHorse();
            h.goBackToStart();
        }
        new Thread(()->{
            while(!finished){  
                for( int i=1; i<=horses.size(); i++ ){ 
                    Horse h = horses.get(i);
                    if(h.hasFallen()){ numberFallen++; }
                    if(raceWonBy(h)){
                        finished = true;
                        updateRace();
                        Text += "And the winner is " + h.getName();
                        if(VirtualBettingUp) { 
                            Text += "\n\nYOUR BET: "+HorseBetted.getName();
                            checkIfBetWon(h); 
                        }
                        WiningTimesForCurrentHorses.get(i).add(CurrentWinningTime);
                        System.out.println(WiningTimesForCurrentHorses.toString());
                        
                        RaceOutput.setText(Text);
                        return;
                    }
                } 
                if(numberFallen == horses.size()){
                    finished = true;
                    updateRace();
                    Text += "All the horses have fallen.";
                    RaceOutput.setText(Text);
                    numberFallen = 0;
                    return;
                }else{ numberFallen = 0; }
                for( int i=1; i<=horses.size(); i++ ){ moveHorse(horses.get(i)); } 
                try{
                    updateRace();
                    if(VirtualBettingUp){ Text += "\n\nYOUR BET: "+HorseBetted.getName();}
                    RaceOutput.setText(Text);
                    Thread.sleep(100);
                }catch(InterruptedException e){
                    System.out.println("Interrupted");
                }
            }
        }).start();
    }
    
    
    /**
     * Randomly make a horse move forward or fall depending
     * on its confidence rating
     * A fallen horse cannot move
     * 
     * @param theHorse the horse to be moved
     */
    private void moveHorse(Horse theHorse)
    {
        //if the horse has fallen it cannot move, 
        //so only run if it has not fallen
        
        if  (!theHorse.hasFallen())
        {
            //the probability that the horse will move forward depends on the confidence;
            if (Math.random() < theHorse.getConfidence())
            {
               theHorse.moveForward();
            }
            
            //the probability that the horse will fall is very small (max is 0.1)
            //but will also will depends exponentially on confidence 
            //so if you double the confidence, the probability that it will fall is *2
            if (Math.random() < (0.1*theHorse.getConfidence()*theHorse.getConfidence()))
            {
                theHorse.fall();
                theHorse.setConfidence( Math.floor((theHorse.getConfidence() - 0.1 )*100)/100);
            }
        }
    }
        
    /** 
     * Determines if a horse has won the race
     *
     * @param theHorse The horse we are testing
     * @return true if the horse has won, false otherwise.
     */
    private boolean raceWonBy(Horse theHorse)
    {
        if (theHorse.getDistanceTravelled() == TrackLength)
        {
            theHorse.setConfidence(Math.floor((theHorse.getConfidence()+0.1)*100)/100);
            return true;
        }
        else
        {
            return false;
        }
    }
    
    //updates the TextVariable to be shown on the screen
    private void updateRace()
    {
        
        Text = "\n\n\n\n";
        
        multiplePrint(TrackChar,TrackLength); //top edge of track
        Text +=  "\n" ; 

        for( int i=1; i<=horses.size(); i++ ){ 
            printLane(horses.get(i));
            Text += "\n" ; 
        }

        multiplePrint(TrackChar,TrackLength); //bottom edge of track
        CurrentWinningTime = Math.floor((CurrentWinningTime+0.1)*100)/100;
        Text += "\nTIMER: "+CurrentWinningTime+"\n";
        CalculateLeadingProbablity();
    }

    //the following calaclulates the Leading horses' probablilty to win.
    private void CalculateLeadingProbablity(){
        String addText = "";
        double biggestProb = 0;
        double DistSum = 0;
        for (int i = 1; i <= horses.size(); i++) { 
            DistSum += horses.get(i).hasFallen()? 0 : horses.get(i).getDistanceTravelled(); 
        }
        for (int i = 1; i <= horses.size(); i++) {
            if (horses.get(i).hasFallen()) { continue; }
            Horse h = horses.get(i);
            double currentProb = numberFallen!=horses.size()?Math.floor(((h.getDistanceTravelled()*((NumberOfHorsesInt-numberFallen))*h.getConfidence())/(DistSum*NumberOfHorsesInt-numberFallen)*(100)))/100:1;
            if ( currentProb > biggestProb ){
                biggestProb = currentProb;
                addText =  h.getName() + " is currently in the lead with a probability of " + biggestProb + "\n";
            }
        }
        //System.out.println(addText);
        Text += addText;
    }

    
    /**
     * print a horse's lane during the race
     * for example
     * |           X                      |
     * to show how far the horse has run
     */
    private void printLane(Horse theHorse)
    {
        //calculate how many spaces are needed before
        //and after the horse
        int spacesBefore = theHorse.getDistanceTravelled();
        int spacesAfter = TrackLength - theHorse.getDistanceTravelled();
        //print a | for the beginning of the lane
        Text +=  "|" ; 
        //System.out.print('|');
        
        //print the spaces before the horse
        multiplePrint(' ',spacesBefore*2);
        
        //if the horse has fallen then print dead
        //else print the horse's symbol
        if(theHorse.hasFallen()){
            Text += "\b\u274C" ; 
            //System.out.print("\b\u274C");
        }else{
            Text += " "+theHorse.getSymbol(); 
            //System.out.print(theHorse.getSymbol());
        }
        
        //print the spaces after the horse
        multiplePrint(' ',spacesAfter*2);
        
        //print the | {horseName} ({current confidence}) for the end of the track
        Text += "| "+ theHorse.getName()+ " (Current confidence "+ theHorse.getConfidence() +")" ;
        //System.out.print("| "+ theHorse.getName()+ " (Current confidence "+ theHorse.getConfidence() +")" );
    }
        
    
    /***
     * print a character a given number of times.
     * e.g. printmany('x',5) will print: xxxxx
     * 
     * @param aChar the character to Print
     */
    private void multiplePrint(char aChar, int times){
        int i = 0;
        while (i < times){
            Text +=  aChar ; 
            //System.out.print(aChar);
            i = i + 1;
        }
    }
}
