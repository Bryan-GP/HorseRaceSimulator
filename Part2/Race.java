import java.util.LinkedHashMap;

import javax.swing.JTextArea;

import java.lang.Math;

/**
 * A three-horse race, each horse running in its own lane
 * for a given distance
 * 
 * @author McFarewell
 * @version 1.0
 */
public class Race
{
    private int raceLength;
    private LinkedHashMap<Integer, Horse> horses;
    private String Text;
    private JTextArea TextArea;
    public boolean finished;
    

    /**
     * Constructor for objects of class Race
     * Initially there are no horses in the lanes
     * 
     * @param distance the length of the racetrack (in metres/yards...)
     */
    public Race(int distance, LinkedHashMap<Integer, Horse> horses, JTextArea TextArea)
    {
        // initialise instance variables
        this.raceLength = distance;
        this.horses = horses;
        this.TextArea = TextArea; 

    }
    //removed addHorse Method
    
    
    /**
     * Start the race
     * The horse are brought to the start and
     * then repeatedly moved forward until the 
     * race is finished
     */

    public void startRace(){
        finished = false;
        for( int i=1; i<=horses.size(); i++ ){ horses.get(i).goBackToStart(); }
        while(!finished){
            for( int i=1; i<=horses.size(); i++ ){ 
                Horse h = horses.get(i);
                if(raceWonBy(h)){
                    Text += "And the winner is " + h.getName() ;
                    finished = true;
                }
            }
            for( int i=1; i<=horses.size(); i++ ){ moveHorse(horses.get(i)); } 
            updateRace();
            TextArea.setText(Text);
            try{ Thread.sleep(100);}
            catch(Exception e){}
        }
        //move each horse



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
        if (theHorse.getDistanceTravelled() == raceLength)
        {
            theHorse.setConfidence(Math.floor((theHorse.getConfidence()+0.1)*100)/100);
            return true;
        }
        else
        {
            return false;
        }
    }
    
    /***
     * Print the race on the terminal
     */
    private void updateRace()
    {
        //System.out.print('\u000C');  //clear the terminal window
        Text = "";
        
        multiplePrint('=',raceLength); //top edge of track
        Text +=  "\n" ; 

        for( int i=1; i<=horses.size(); i++ ){ 
            printLane(horses.get(i));
            Text += "\n" ; 
        }

        multiplePrint('=',raceLength); //bottom edge of track
        Text += "\n" ;
        //System.out.println();    
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
        int spacesAfter = raceLength - theHorse.getDistanceTravelled();
        //print a | for the beginning of the lane
        Text +=  "|" ; 
        //System.out.print('|');
        
        //print the spaces before the horse
        multiplePrint(' ',spacesBefore);
        
        //if the horse has fallen then print dead
        //else print the horse's symbol
        if(theHorse.hasFallen()){
            Text += "\b\u274C" ; 
            //System.out.print("\b\u274C");
        }else{
            Text += theHorse.getSymbol(); 
            //System.out.print(theHorse.getSymbol());
        }
        
        //print the spaces after the horse
        multiplePrint(' ',spacesAfter);
        
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
