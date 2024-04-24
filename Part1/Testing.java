// Source code is decompiled from a .class file using FernFlower decompiler.
public class Testing {
 
    public static void main(String[] var0) {
        part1_2();
    }
 
    public static void part1_2() {
       Race var0 = new Race(10);
       Horse var1 = new Horse('\u265e', "Test1", 0.6);
       var0.addHorse(var1, 1);
       Horse var2 = new Horse('\u265e', "Test2", 0.6);
       var0.addHorse(var2, 2);
       Horse var3 = new Horse('\u265d', "Test3", 0.4);
       var0.addHorse(var3, 3);
       var0.startRace();
    }
 
    public static void part1_1() {
       Horse var0 = new Horse('\u265e', "Test", 0.5);
       System.out.println("new Horse Confidence: " + var0.getConfidence());
       var0.setSymbol('\u265d');
       System.out.println("new Horse Symbol: " + var0.getSymbol());
       var0.fall();
       System.out.println("Has Horse Fallen?: " + var0.hasFallen());
 
       for(int var1 = 0; var1 < 10; ++var1) {
          var0.moveForward();
          System.out.println("Dist Travelled: " + var0.getDistanceTravelled());
       }
 
       var0.goBackToStart();
       System.out.println("Dist Travelled: " + var0.getDistanceTravelled());
    }
 }
 