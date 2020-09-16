public class Launcher {

    static ScreenCapture screenShot;
    static int fps = 2;
    
    public static void main(String[] args) throws InterruptedException, Exception {

        screenShot = new ScreenCapture();
        while (true) {
            Thread.sleep(1000/fps);
            screenShot.takeScreenShot();
        }
    }
}