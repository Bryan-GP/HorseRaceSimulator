// Source code is decompiled from a .class file using FernFlower decompiler.
public class Testing {
 
    public static void main(String[] var0) {
        testing_part1_1();
    }
 
    public static void testing_part1_2() {
       Race var0 = new Race(10);
       Horse var1 = new Horse('\u265e', "Test1", 0.6);
       var0.addHorse(var1, 1);
       Horse var2 = new Horse('\u265e', "Test2", 0.6);
       var0.addHorse(var2, 2);
       Horse var3 = new Horse('\u265d', "Test3", 0.4);
       var0.addHorse(var3, 3);
       var0.startRace();
    }
 
    public static void testing_part1_1() {
      Horse horse = new Horse('\u265E',"Test",0.5);
      System.out.println("confindence: " + horse.getConfidence());
      System.out.println("Dist Travelled: " + horse.getDistanceTravelled());
      System.out.println("Horse Name: " + horse.getName());
      System.out.println("Horse Symbol: " + horse.getSymbol());
      System.out.println("Has Horse Fallen?: " + horse.hasFallen());
      horse.setConfidence(0.6);
      System.out.println("new Horse Confidence: " + horse.getConfidence());
      horse.setSymbol('\u2658');
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
