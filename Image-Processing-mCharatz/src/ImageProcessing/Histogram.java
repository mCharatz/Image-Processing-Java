/**
 *
 * @author Michalis Charatzoglou , mCharatz
 */

package ImageProcessing;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;


public class Histogram {
    
    private double probability[];
    private double cumulativeProbability[];
    private int numberOfPixels[]; 
    private short newValues[];     
    private int allPixels;
    
    private double numOfPixels;
    
    private static int MAX_Y = 236; //(0 - 235)
    
    public Histogram(YUVImage img){
        
        probability = new double[MAX_Y];
        cumulativeProbability = new double[MAX_Y];
        numberOfPixels = new int[MAX_Y];
        
        allPixels = img.myYUVImage.length * img.myYUVImage[0].length;
        
        for(int i = 0; i < MAX_Y ; i++)
            numberOfPixels[i] = 0;
        
        for(int i = 0; i< img.myYUVImage.length; i++){
            for(int j =0; j < img.myYUVImage[0].length; j++){
                numberOfPixels[img.myYUVImage[i][j].getY()] += 1;
            }
        }
    }
    
    
    public void equalize(){
        
        newValues = new short[MAX_Y];
        
        //probability
        for(int i = 0; i < MAX_Y ; i++)
            probability[i] = numberOfPixels[i]/(double)allPixels;
        
        
        for(int i = 0; i < MAX_Y ; i++)
            cumulativeProbability[i] = 0;
        
        
        //cumulativeProbability
        cumulativeProbability[0] = probability[0];
        
        for(int i = 1; i < MAX_Y ; i++)
                cumulativeProbability[i] = cumulativeProbability[i-1] + probability[i];
            
        
        //new values
        for(int i = 0; i < MAX_Y ; i++){
            newValues[i] = (short) (cumulativeProbability[i]*235);   
        }
            
    }
    
    public short getEqualizedLuminocity(int luminocity){
            
         return newValues[luminocity];
    }
    
    
public void toFile(File file){
    
        
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
      
    @Override
    public String toString(){
        
        int maxNumPixels = 0;
        double normalization;
        double NormalizedNumberOfPixels[];
        NormalizedNumberOfPixels =  new double[MAX_Y];
        
        for (int i = 0; i< MAX_Y; i++){
            if (numberOfPixels[i] > maxNumPixels)
                maxNumPixels = numberOfPixels[i];
        }
        
        //normalization
        normalization = 80/(double)maxNumPixels;
        
        for (int i=0; i< MAX_Y; i++)
             NormalizedNumberOfPixels[i] = numberOfPixels[i]*normalization;
           
            
        StringBuilder myStr=new StringBuilder();
        
        for (int i=0; i< MAX_Y ; i++){
              myStr.append(i);
              for(int j = 0; j < (int)NormalizedNumberOfPixels[i]; j++)
                  myStr.append("*");
              
              myStr.append("\n");
              
        }

       return myStr.toString();
    }
    
    private int limit(int luminocity){
        if (luminocity > 80)
            return 80;
        else
            return luminocity;
    }
    
}
