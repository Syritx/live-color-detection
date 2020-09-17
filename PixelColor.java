import java.awt.image.*;

public class PixelColor {

    static int[] maxRGB = {255,255,255};
    static int[] minRGB = {0,0,0};
    static int[] highlightColor = {255,0,0};

    static void setMaxRGB(int[] maxRGBv) {
        maxRGB = maxRGBv;
    }

    static void setMinRGB(int[] minRGBv) {
        minRGB = minRGBv;
    }

    static void setHighlightColor(int[] rgb) {
        highlightColor = rgb;
    }

    public static BufferedImage getPixelColor(BufferedImage image, boolean canDesaturate) {

        int width = image.getWidth(), height = image.getHeight();
        isSelected = new ArrayList<Boolean>();

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {

                int rgb = image.getRGB(x, y);

                int red = (rgb>>16)&0xff;
                int green = (rgb>>8)&0xff;
                int blue = rgb&0xff;

                int selectedRGB = (255<<24) | (255<<16) | (255<<8) | 255;
                if (checkColor(red,0) && checkColor(green,1) && checkColor(blue,2)) {

                    selectedRGB = (255<<24) | (highlightColor[0]<<16) | (highlightColor[1]<<8) | highlightColor[2];
                    isSelected.add(true);
                    image.setRGB(x, y, selectedRGB);
                }
                else isSelected.add(false);
            }
        }

        if (canDesaturate) {
            ColorConvertOp convert = new ColorConvertOp(
                                         ColorSpace.getInstance(ColorSpace.CS_GRAY),null);
            convert.filter(image, image);

            int id = 0;
            for (int x = 0; x < width; x++) {
                for (int y = 0; y < height; y++) {

                    if (isSelected.get(id)) {
                        int selectedRGB = (255<<24) | (highlightColor[0]<<16) | (highlightColor[1]<<8) | highlightColor[2];
                        image.setRGB(x, y, selectedRGB);
                    }
                    id++;
                }
            }
        }
        return image;
    }

    static boolean checkColor(int color, int place) {
        if (color > minRGB[place]-1 && color <= maxRGB[place]) return true;
        return false;
    }
}
