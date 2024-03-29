
/**
 * Write a description of class Horse here.
 * 
 * @author Bryan Piedra-Ojeda 230347627
 * @version 1
 */
public class Horse{
    //Fields of class Horse
    private char horseSymbol;//has: get, set
    private String horseName;//has: get
    private double horseConfidence;//has: get, set
    private int DistanceTravelled;//has: 
    private boolean Fallen;//has: 

    //Constructor of class Horse
    public Horse(char horseSymbol, String horseName, double horseConfidence){
        this.horseSymbol = horseSymbol;
        this.horseName = horseName;
        this.horseConfidence = horseConfidence;
        this.DistanceTravelled = 0;
        this.Fallen = false;
    }
    
    /*Methods*/
    public double getConfidence(){ return this.horseConfidence; }
    public int getDistanceTravelled(){ return this.DistanceTravelled; }
    public String getName(){ return this.horseName; }
    public char getSymbol(){ return this.horseSymbol; }
    public boolean hasFallen(){ return this.Fallen; }
    public void setConfidence(double newConfidence){ this.horseConfidence = newConfidence; }
    public void setSymbol(char newSymbol){ this.horseSymbol = newSymbol; }
    public void fall(){ this.Fallen = true; }
    public void goBackToStart(){ this.DistanceTravelled = 0; }
    public void moveForward(){ this.DistanceTravelled += 1; }

}
