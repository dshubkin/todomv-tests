package utils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class CompareTwoImages {
    File actualFile;
    private static BufferedImage image1, image2, imageResult;
    private static boolean isIdentic;
    private static int compareX, compareY;
    private static double sensitivity = 0.10;

    public CompareTwoImages() throws IOException {
        File defaultFile = new File("default.png");
        File actualFileObject = ((TakesScreenshot) ChromeWebDriver.getInstance()).getScreenshotAs(OutputType.FILE);
        byte[] actual = new FileInputStream(actualFileObject).readAllBytes();
        try (FileOutputStream fos = new FileOutputStream("actual.png")) {
            fos.write(actual);
        }
        actualFile = new File("actual.png");

        image1 = loadPNG(actualFile.getName());
        image2 = loadPNG(defaultFile.getName());
    }

    public void setParameters(int compareX, int compareY) {
        CompareTwoImages.compareX = compareX;
        CompareTwoImages.compareY = compareY;
    }

    public void compare() {
        // setup change display image
        imageResult = new BufferedImage(image2.getWidth(null), image2.getHeight(null), BufferedImage.TYPE_INT_RGB);
        Graphics2D g2 = imageResult.createGraphics();
        g2.drawImage(image2, null, null);
        g2.setColor(Color.RED);
        // assign size of each section
        int blocksX = (int)(image1.getWidth()/compareX);
        int blocksY = (int)(image1.getHeight()/compareY);
        CompareTwoImages.isIdentic = true;
        for (int y = 0; y < compareY; y++) {
            for (int x = 0; x < compareX; x++) {
                int result1 [][] = convertTo2D(image1.getSubimage(x*blocksX, y*blocksY, blocksX - 1, blocksY - 1));
                int result2 [][] = convertTo2D(image2.getSubimage(x*blocksX, y*blocksY, blocksX - 1, blocksY - 1));
                for (int i = 0; i < result1.length; i++) {
                    for (int j = 0; j < result1[0].length; j++) {
                        int diff = Math.abs(result1[i][j] - result2[i][j]);
                        if (diff/Math.abs(result1[i][j]) > sensitivity) {
                            // draw an indicator on the change image to show where change was detected.
                            g2.drawRect(x*blocksX, y*blocksY, blocksX - 1, blocksY - 1);
                            isIdentic = false;
                        }
                    }
                }
            }
        }
        actualFile.delete();
    }

    public BufferedImage getImageResult() {
        return imageResult;
    }

    public int[][] convertTo2D(BufferedImage subimage) {
        int width = subimage.getWidth();
        int height = subimage.getHeight();
        int[][] result = new int[height][width];

        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                result[row][col] = subimage.getRGB(col, row);
            }
        }
        return result;
    }

    public static BufferedImage loadPNG(String filename) throws IOException {
        BufferedImage img = null;
        try {
            img = ImageIO.read(new File(filename));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return img;

    }

    public static void savePNG(BufferedImage bimg, String filename) {
        try {
            File outputfile = new File(filename);
            ImageIO.write(bimg, "png", outputfile);
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    public boolean isIdentic() {
        return isIdentic;
    }
}