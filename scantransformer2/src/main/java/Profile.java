import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Profile {


    private float degree;
    private int top;
    private int bottom;
    private int left;
    private int right;

    public Profile (int degree, int top, int bottom, int left, int right)
    {

        this.degree = degree % 360;
        this.top = top % 100;
        this.bottom = bottom % 100;
        this.left = left % 100;
        this.right = right % 100;
    }

    public float getDegree() {
        return degree;
    }

    public int getTop() {
        return top;
    }

    public int getBottom() {
        return bottom;
    }

    public int getLeft() {
        return left;
    }

    public int getRight() {
        return right;
    }

    public void setDegree(float degree) {
        this.degree = degree;
    }

    public void setTop(int top) {
        this.top = top;
    }

    public void setBottom(int bottom) {
        this.bottom = bottom;
    }

    public void setLeft(int left) {
        this.left = left;
    }

    public void setRight(int right) {
        this.right = right;
    }

    private BufferedImage cropImage(BufferedImage src) {

        //define pixel size of crops
        float td = src.getHeight() * (top/100);
        float ld = src.getWidth() * (left/100);
        float bd = src.getHeight() * (bottom/100);
        float rd = src.getWidth() * (right/100);

        //cast sizes to floats
        int x = (int) ld;
        int y = (int) td;
        int xsize = src.getWidth() - (int) ld - (int) rd;
        int ysize = src.getHeight() - (int) td - (int) bd;

        //get sub image of src
        BufferedImage dest = src.getSubimage(x,y,xsize,ysize);

        return dest;
    }

    public BufferedImage rotateImageByDegrees(BufferedImage img) {

        //set degrees to radians, take the sine and cosine, get img size
        double rads = Math.toRadians(degree);
        double sin = Math.abs(Math.sin(rads)), cos = Math.abs(Math.cos(rads));
        int w = img.getWidth();
        int h = img.getHeight();

        // use sign and cosine to find the new size of the img after rotation
        int newWidth = (int) Math.floor(w * cos + h * sin);
        int newHeight = (int) Math.floor(h * cos + w * sin);

        // creates transformation objects
        BufferedImage rotated = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = rotated.createGraphics();
        AffineTransform at = new AffineTransform();
        at.translate((newWidth - w) / 2, (newHeight - h) / 2);

        //define center point
        int x = w / 2;
        int y = h / 2;

        //preform rotation
        at.rotate(rads, x, y);
        g2d.setTransform(at);
        //g2d.drawImage(img, 0, 0, (ImageObserver) this);
        ImageObserver io = new ImageObserver() {
            @Override
            public boolean imageUpdate(Image image, int i, int i1, int i2, int i3, int i4) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        };
        g2d.drawImage(img, 0, 0, io);
        //g2d.setColor(Color.RED);
        g2d.drawRect(0, 0, newWidth - 1, newHeight - 1);
        g2d.dispose();

        return rotated;
    }

    public void fullTransform(String image) throws IOException
    {
        BufferedImage img;
        File outputfile;

        img = ImageIO.read(new File(image));

        if(!(degree == 0)){

            //img = ImageIO.read(new File(image));
            img = rotateImageByDegrees (img);
            //outputfile = new File(image);
            //ImageIO.write(img, "png", outputfile);

        }

        img = cropImage(img);

        outputfile = new File(image);
        ImageIO.write(img, "png", outputfile);

    }
}
