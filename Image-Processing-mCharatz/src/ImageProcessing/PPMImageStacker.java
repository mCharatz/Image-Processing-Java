package ImageProcessing;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
 *
 * @author Michalis Charatzoglou , mCharatz
 */

public class PPMImageStacker{
    
    List<PPMImage> FileList = new LinkedList<PPMImage>();
    PPMImage finalImage;
    
    public PPMImageStacker(java.io.File dir) throws FileNotFoundException, UnsupportedFileFormatException {
        
        
        if(dir.exists()){
            if(dir.isDirectory()){
                for (final File fileEntry : dir.listFiles()) 
                    FileList.add(new PPMImage(fileEntry));
            }      
            else
                throw new UnsupportedFileFormatException("[ERROR] "+ dir +" is not a directory!");
                
        }
        else
            throw new FileNotFoundException("[ERROR] Directory "+ dir +" does not exist!");
    }

    public void stack() {
        
        int height = FileList.get(0).myImage.length;
        int width = FileList.get(0).myImage[0].length;
        
        finalImage = new PPMImage(new RGBImage(width,height,FileList.get(0).colordepth));

        
        
        int sumRED[][] = new int[height][width];
        int sumGREEN[][] = new int[height][width];
        int sumBLUE[][] = new int[height][width];
        
        int size = FileList.size();
        
        for (int i = 0; i<FileList.size(); i++){
            for(int r = 0; r< height ; r++){
                for(int c = 0; c< width  ; c++){
                    
                    sumRED[r][c] += FileList.get(i).myImage[r][c].getRed();
                    sumGREEN[r][c] += FileList.get(i).myImage[r][c].getGreen();
                    sumBLUE[r][c] += FileList.get(i).myImage[r][c].getBlue();
                }
            }
        }
        
        for(int r = 0; r< height ; r++){
            for(int c = 0; c< width ; c++){
                    
                sumRED[r][c] = sumRED[r][c]/size;
                sumGREEN[r][c] = sumGREEN[r][c]/size;
                sumBLUE[r][c] = sumBLUE[r][c]/size;
                
                }
            }
        
        for(int r = 0; r< height ; r++){
           for(int c = 0; c< width  ; c++){
               finalImage.myImage[r][c].setRGB((short) (sumRED[r][c]), (short) (sumGREEN[r][c]), (short) (sumBLUE[r][c]));
               
            }
        }
        
        
    }

    public PPMImage getStackedImage() {
        return finalImage;
    }
}
