
/**
 * Write a description of class Horse here.
 * 
 * @author Bryan Piedra-Ojeda 230347627
 * @version 1
 */
public class Horse{
    //Fields of class Horse
    char horseSymbol;
    String horseName;
    double horseConfidence;
    int DistanceTravelled;
    boolean Fallen;

    //Constructor of class Horse
    public Horse(char horseSymbol, String horseName, double horseConfidence){
        this.horseSymbol = horseSymbol;
        this.horseName= horseName;
        this.horseConfidence = horseConfidence;
        this.DistanceTravelled = 0;
        this.Fallen = false;
    }
    
    /*Methods*/
    public void fall(){}
    public double getConfidence(){}
    public int getDistanceTravelled(){}
    public String getName(){}
    public char getSymbol(){}
    public void goBackToStart(){}
    public boolean hasFallen(){}
    public void moveForward(){}
    public void setConfidence(double newConfidence){}
    public void setSymbol(char newSymbol){}
}
