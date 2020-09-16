import java.awt.*;
import java.awt.image.*;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class ScreenCapture {

    Robot robot;
    public ScreenCapture() {

        PixelColor.setMaxRGB(new int[] {255,255,0});
        PixelColor.setMinRGB(new int[] {0,0,0});
        PixelColor.setHighlightColor(new int[] {0,255,0});

        File file = new File("save.png");
        try {
            file.createNewFile();
            robot = new Robot();
        } catch(Exception e) {}
    }

    public void takeScreenShot() {

        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension sd = toolkit.getScreenSize();
        Rectangle imageSize = new Rectangle(0,0,(int)sd.getWidth(),(int)sd.getHeight());

        BufferedImage image = robot.createScreenCapture(imageSize);
        image = PixelColor.getPixelColor(image);

        overwriteFile(image);
    }

    private void overwriteFile(BufferedImage image) {
        File file = new File("save.png");
        try {
            ImageIO.write(image, "png", file);
        }catch(IOException e) {}
    }
}
