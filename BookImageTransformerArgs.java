/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookimagetransformer;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import javax.imageio.ImageIO;

/**
 *
 * @author Jeremy G Diamond
 */
public class BookImageTransformer {

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException {
        ArrayList<String> images ;
        String dir = args[0];
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String choise = "0";
        File outputfile;
        BufferedImage img;
        boolean twoProfile = false;
        boolean copyFiles = false;
        int rotation = 0;
        int top = 0;
        int bot = 0;
        int lef = 0;
        int rig = 0;
        int rotation2 = 0;
        int top2 = 0;
        int bot2 = 0;
        int lef2 = 0;
        int rig2 = 0;
        
		if(args.length == 0){
			System.out.println("Add Help Text");
			return;
			}
			
		// set image list
        images = filesList(dir);
		
		
        // set bools
		if("2".equals(args[1]))
			twoProfile = true;
		
		if("y".equals(args[2]))
                copyFiles = true;
				
		System.out.println("Folder: " + dir);
		System.out.println("Two Profile: " + twoProfile); 
		System.out.println("Copy files: " + copyFiles); 
        
        if(copyFiles){ //copy all images
           staggerFileNames(images);
           images = filesList(dir);
           //profile2 = new int[4];
        }
        choise = "n"; // so that profile loading is not skipped
        
        while ("n".equals(choise)){
            System.out.println("Please enter the degrees of clockwise rotation (0 for none)");
            
            choise = reader.readLine();
            
            rotation = Integer.parseInt(choise);
            img = ImageIO.read(new File(images.get(0)));
            img = rotateImageByDegrees (img,rotation);
            outputfile = new File(dir + "\\test.png");
            ImageIO.write(img, "png", outputfile);
            
            System.out.println("Please enter the percent from the top to crop (0 for none)");
            choise = reader.readLine();
            top = Integer.parseInt(choise);
            
            System.out.println("Please enter the percent from the bottom to crop (0 for none)");
            choise = reader.readLine();
            bot = Integer.parseInt(choise);
            
            System.out.println("Please enter the percent from the left to crop (0 for none)");
            choise = reader.readLine();
            lef = Integer.parseInt(choise);
            
            System.out.println("Please enter the percent from the right to crop (0 for none)");
            choise = reader.readLine();
            rig = Integer.parseInt(choise);
            
            img = ImageIO.read(new File(dir+"\\test.png"));
            img = cropImage(img,top,lef,bot,rig);
            outputfile = new File(dir + "\\test.png");
            ImageIO.write(img, "png", outputfile);
            
            System.out.print("Check that image test.png\nIf this is wroung enter 'n' \nIf this is fine enter anything else\n" );
            
            choise = reader.readLine();
            
        }
        
        if (twoProfile)
            {
                choise = "n";
                 while ("n".equals(choise)){
                    System.out.println("Please enter the degrees of clockwise rotation for the even numbered pages (0 for none)");

                    choise = reader.readLine();
                    rotation2 = Integer.parseInt(choise);
                    img = ImageIO.read(new File(images.get(1)));
                    img = rotateImageByDegrees (img,rotation2);
                    outputfile = new File(dir + "\\test2.png");
                    ImageIO.write(img, "png", outputfile);
                    
                    System.out.println("Please enter the percent from the top to crop (0 for none)");
                    choise = reader.readLine();
                    top2 = Integer.parseInt(choise);

                    System.out.println("Please enter the percent from the bottom to crop (0 for none)");
                    choise = reader.readLine();
                    bot2 = Integer.parseInt(choise);

                    System.out.println("Please enter the percent from the left to crop (0 for none)");
                    choise = reader.readLine();
                    lef2 = Integer.parseInt(choise);

                    System.out.println("Please enter the percent from the right to crop (0 for none)");
                    choise = reader.readLine();
                    rig2 = Integer.parseInt(choise);

                    img = ImageIO.read(new File(dir+"\\test2.png"));
                    img = cropImage(img,top2,lef2,bot2,rig2);
                    outputfile = new File(dir + "\\test2.png");
                    ImageIO.write(img, "png", outputfile);


                    System.out.print("Check that image test2.png\nIf this is wroung enter 'n' \nIf this is fine enter anything else\n" );
                    
                    choise = reader.readLine();
                 }
            }
            
        
         if(twoProfile)
            run2profile(images,rotation,top,bot,lef,rig,rotation2,top2,bot2,lef2,rig2);
         else
            run1profile(images,rotation,top,bot,lef,rig);
    }
    
    public static ArrayList<String> filesList(String dir) throws IOException {

		File f = new File(dir); // current directory
                ArrayList<String> listOFiles = new ArrayList();
		File[] files = f.listFiles();
		for (File file : files) {
			
			listOFiles.add(file.getCanonicalPath());
		}

                return listOFiles;
	}
    private static BufferedImage cropImage(BufferedImage src, float tp,float lp,float bp,float rp) {
        
        float td = src.getHeight() * (tp/100);
        float ld = src.getWidth() * (lp/100);
        float bd = src.getHeight() * (bp/100);
        float rd = src.getWidth() * (rp/100);
        
        int x = (int) ld;
        int y = (int) td;
        int xsize = src.getWidth() - (int) ld - (int) rd;
        int ysize = src.getHeight() - (int) td - (int) bd;
        
      BufferedImage dest = src.getSubimage(x,y,xsize,ysize);
      
      return dest; 
   }
    
    private static void staggerFileNames(ArrayList<String> images) throws IOException {
      
        File outputfile;
        BufferedImage img;
        
        for (int i = 0; i < images.size(); ++i){
            img = ImageIO.read(new File(images.get(i)));

            outputfile = new File(images.get(i).substring(0, images.get(i).lastIndexOf("."))+" copy.jpg");
            ImageIO.write(img, "jpg", outputfile);

            System.out.println(images.get(i));
        }
   }
    
    
    
    public static BufferedImage rotateImageByDegrees(BufferedImage img, double angle) {

            double rads = Math.toRadians(angle);
            double sin = Math.abs(Math.sin(rads)), cos = Math.abs(Math.cos(rads));
            int w = img.getWidth();
            int h = img.getHeight();
            int newWidth = (int) Math.floor(w * cos + h * sin);
            int newHeight = (int) Math.floor(h * cos + w * sin);

            BufferedImage rotated = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2d = rotated.createGraphics();
            AffineTransform at = new AffineTransform();
            at.translate((newWidth - w) / 2, (newHeight - h) / 2);

            int x = w / 2;
            int y = h / 2;

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
    
    public static void fullTransform(String image, int rot,int top,int bot,int lef,int rig) throws IOException
    {
        BufferedImage img;
        File outputfile;
        
        if(!(rot == 0)){
             
             
                img = ImageIO.read(new File(image));
                img = rotateImageByDegrees (img,rot);
                outputfile = new File(image);
                ImageIO.write(img, "png", outputfile);
             
         }
        
        img = ImageIO.read(new File(image));
        
        img = cropImage(img,top,lef,bot,rig);
        
        outputfile = new File(image);
        ImageIO.write(img, "png", outputfile);
        
    }
    
    public static void run1profile(ArrayList<String> images, int rot,int top,int bot,int lef,int rig) throws IOException
    {
         for (int i = 0; i < images.size(); ++i)
         {
             fullTransform(images.get(i),rot,top,bot,lef,rig);
         }
    }
    
    public static void run2profile(ArrayList<String> images, int rot1,int top1,int bot1,int lef1,int rig1,int rot2,int top2,int bot2,int lef2,int rig2) throws IOException
    {
        for (int i = 0; i < images.size(); ++i)
         {
             if (!(i%2 == 0))
                fullTransform(images.get(i),rot2,top2,bot2,lef2,rig2);
             else
                fullTransform(images.get(i),rot1,top1,bot1,lef1,rig1);
         }
    }
    
}
