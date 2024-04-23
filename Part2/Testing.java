///import javax.swing.SwingUtilities;

public class Testing {
    public static void main(String[] args){
        new Race();
        //new GUI();
    }

    public static void part1_2(){
        /* 
        Race race = new Race(10);
        Horse horse1 = new Horse('\u265E',"Test1",0.6);
        race.addHorse(horse1, 1);
        Horse horse2 = new Horse('\u2658',"Test2",0.6);
        race.addHorse(horse2, 2);
        Horse horse3 = new Horse('\u265E',"Test3",0.4);
        race.addHorse(horse3, 3);
        race.startRace();*/
    }

    public static void part1_1(){
        Horse horse = new Horse('\u265E',"Test",0.5);
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
