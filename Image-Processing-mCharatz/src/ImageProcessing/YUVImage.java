/**
 *
 * @author Michalis Charatzoglou , mCharatz
 */

package ImageProcessing;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;


public class YUVImage {
    
    protected YUVPixel myYUVImage[][];
    protected String magicNumber;
    private int width;
    private int height;
    
    public YUVImage(int width, int height) {
        
        myYUVImage = new YUVPixel[height][width];
        magicNumber = "YUV3";
        
        for (int i=0; i< height ; i++){
            for (int j=0; j< width; j++){
                
                myYUVImage[i][j].setΥ((short) 16);
                myYUVImage[i][j].setV((short) 128);
                myYUVImage[i][j].setU((short) 128); 
            }
        }
    }
    
    public YUVImage(YUVImage copyImg){
        
        myYUVImage = new YUVPixel[copyImg.myYUVImage.length][copyImg.myYUVImage[0].length];
        
        magicNumber = copyImg.magicNumber;
        
        for (int i=0; i< copyImg.myYUVImage.length ; i++){
            for (int j=0; j< copyImg.myYUVImage[0].length; j++){
                
                myYUVImage[i][j] = copyImg.myYUVImage[i][j];
            }
        }
    }
    
    public YUVImage(RGBImage RGBImg){
        
        myYUVImage = new YUVPixel[RGBImg.myImage.length][RGBImg.myImage[0].length];
        
        magicNumber = "YUV3";
        
        for (int i=0; i< RGBImg.myImage.length ; i++){
            for (int j=0; j< RGBImg.myImage[0].length; j++){
                
               myYUVImage[i][j] = new YUVPixel(RGBImg.myImage[i][j]);
            }
        }        
        
    }
    
   public YUVImage(java.io.File file) throws FileNotFoundException , UnsupportedFileFormatException{
               
        
        Scanner sc = new Scanner(file);
            
            if (sc.hasNext()){
                magicNumber = sc.next();
                if (magicNumber.compareTo("YUV3") != 0){
                    throw new UnsupportedFileFormatException();
                }
                else{
                    width = Integer.valueOf(sc.next());
                    height = Integer.valueOf(sc.next());
                    
                    
                    myYUVImage = new YUVPixel[height][width];
                    
                    for (int i=0; i< height ; i++){
                        for (int j=0; j< width ; j++){
                            
                            myYUVImage[i][j] = new YUVPixel(Short.valueOf(sc.next()),Short.valueOf(sc.next()),Short.valueOf(sc.next()));

                        }               
                    }
                }
            }
            else
                System.out.println("Empty File , Nothing to Read..");
      
        }
   
    @Override
    public String toString(){
        StringBuilder myStr=new StringBuilder();
        
        myStr.append(magicNumber).append("\n");
        myStr.append(String.valueOf(myYUVImage[0].length)).append(" ");
        myStr.append(String.valueOf(myYUVImage.length)).append("\n");
        for (int i=0; i< myYUVImage.length ; i++){
            for (int j=0; j< myYUVImage[0].length; j++){
                
                myStr.append(String.valueOf(myYUVImage[i][j].getY())).append(" ");
                myStr.append(String.valueOf(myYUVImage[i][j].getU())).append(" ");
                myStr.append(String.valueOf(myYUVImage[i][j].getV())).append("\n");
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

    public void equalize() {
        Histogram myHistogram = new Histogram(this);
        myHistogram.equalize();
        
        for (int i=0;i<this.myYUVImage.length;i++){
            for(int j=0 ; j < this.myYUVImage[0].length; j++){
                
                int lum = (int) this.myYUVImage[i][j].getY();
                myYUVImage[i][j] = new YUVPixel(myHistogram.getEqualizedLuminocity(lum),this.myYUVImage[i][j].getU(),this.myYUVImage[i][j].getV());
                
            }
        }
        
        //procude a histogram in file MyHistogram
        //File myfile = new File("MyHisogram");
        //myHistogram.toFile(myfile);
    }      
}
