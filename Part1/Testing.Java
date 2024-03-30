

public class Testing{
    public static void main(String[] args){
        Horse horse = new Horse('\u265E',"Test",0.5);
        System.out.println("confindence: " + horse.getConfidence());
        System.out.println("Dist Travelled: " + horse.getDistanceTravelled());
        System.out.println("Horse Name: " + horse.getName());
        System.out.println("Horse Symbol: " + horse.getSymbol());
        System.out.println("Has Horse Fallen?: " + horse.hasFallen());
        horse.setConfidence(0.6);
        System.out.println("new Horse Confidence: " + horse.getConfidence());
        horse.setSymbol('\u265D');
        System.out.println("new Horse Symbol: " + horse.getSymbol());
        horse.fall();
        System.out.println("Has Horse Fallen?: " + horse.hasFallen());
        for (int i=0; i<10; i++){
            horse.moveForward();
            System.out.println("Dist Travelled: " + horse.getDistanceTravelled());
        }
        horse.goBackToStart();
        System.out.println("Dist Travelled: " + horse.getDistanceTravelled());
    }
}