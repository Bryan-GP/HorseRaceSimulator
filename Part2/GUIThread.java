
public class GUIThread implements Runnable {
    @Override
    public void run() {
        GUI GUI = new GUI();
        while(!GUI.StartHorseRace){
            try { Thread.sleep(1000);} 
            catch (InterruptedException e) { e.printStackTrace(); }
        }
    }
}