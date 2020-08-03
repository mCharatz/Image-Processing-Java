/**
 *
 * @author Michalis Charatzoglou , mCharatz
 */


package ImageProcessing;

import java.io.FileNotFoundException;

import java.io.IOException;
import java.io.PrintWriter;

import java.util.Scanner;


public class PPMImage extends RGBImage {
    
    private String magicNumber;
    private int width;
    private int height;
    
    public PPMImage(java.io.File file) throws FileNotFoundException , UnsupportedFileFormatException{
        
        super(1,1,1);
        
        Scanner sc = new Scanner(file);
            
            if (sc.hasNext()){
                magicNumber = sc.next();
                if (magicNumber.compareTo("P3") != 0){
                    throw new UnsupportedFileFormatException();
                }
                else{
                    width = Integer.valueOf(sc.next());
                    height = Integer.valueOf(sc.next());
                    
                    
                    colordepth = Integer.valueOf(sc.next());
                    myImage = new RGBPixel[height][width];
                    
                    for (int i=0; i< height ; i++){
                        for (int j=0; j< width; j++){
                            
                            myImage[i][j] = new RGBPixel(Short.valueOf(sc.next()),Short.valueOf(sc.next()),Short.valueOf(sc.next()));

                        }               
                    }
                }
            }
            else
                System.out.println("Empty File , Nothing to Read..");
    }
    
    public PPMImage(RGBImage img){
        super(img.myImage[0].length,img.myImage.length,img.colordepth);
          magicNumber = "P3";
          height = img.myImage.length;
          width = img.myImage[0].length;
          
          for (int i=0; i< height ; i++){      
              for (int j=0; j< width ; j++){        
                  myImage[i][j] = new RGBPixel(img.myImage[i][j]);

               }               
           }
          
          System.out.println("OK");

    }
    
    public PPMImage(YUVImage img){
        super(img.myYUVImage[0].length,img.myYUVImage.length,255);
        
          height = img.myYUVImage.length;
          width = img.myYUVImage[0].length;
        
        for (int i=0; i< height ; i++){      
            for (int j=0; j< width ; j++){        
                super.myImage[i][j] = new RGBPixel(img.myYUVImage[i][j]);

             }               
         } 
        
    }
    
    @Override
    public String toString(){
        
        StringBuilder myStr=new StringBuilder();
        
        myStr.append("P3").append("\n");
        myStr.append(String.valueOf(this.getWidth())).append(" ");
        myStr.append(String.valueOf(this.getHeight())).append(" ");
        myStr.append(String.valueOf(colordepth)).append("\n");
        for (int i=0; i< getHeight() ; i++){
            for (int j=0; j< getWidth(); j++){
                
                myStr.append(String.valueOf(myImage[i][j].getRed())).append(" ").append(String.valueOf(myImage[i][j].getGreen())).append(" ").append(String.valueOf(myImage[i][j].getBlue())).append("\n");
            }               
        }

       return myStr.toString();
        
    }
    
    public void toFile(java.io.File file){
        
        PrintWriter out = null;

        try {
 
            if (file.createNewFile()){
                
            }
            else{
                file.delete();
                file.createNewFile();
                
            }
            
            out = new PrintWriter(file.getPath());
            out.println(this);
 
        }
         catch (IOException e) {
           System.out.println("IOException occured!");
       }
        finally{
            if (out != null){
                out.close();
                System.out.println("Done!");
            }
                
        }
        
   }
   
}