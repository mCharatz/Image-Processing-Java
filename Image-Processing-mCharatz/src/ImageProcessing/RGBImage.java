package ImageProcessing;

/**
 *
 * @author Michalis Charatzoglou , mCharatz
 */
public class RGBImage implements Image{
    
    protected int colordepth;
    public static int MAX_COLORDEPTH = 255;
    protected RGBPixel myImage[][];
    
    public RGBImage(int width, int height, int colordepth){
        
        this.colordepth = colordepth;
        
        myImage = new RGBPixel[height][width];
        
        for (int i=0; i< height ; i++){
            for (int j=0; j< width; j++){
                            
                myImage[i][j] = new RGBPixel((short) 0, (short) 0,(short) 0);

            }               
        }
    }
    
    public RGBImage(RGBImage copyImg){
        
        this.colordepth = copyImg.colordepth;
        
        myImage = new RGBPixel[copyImg.myImage.length][myImage[0].length];
        
        for (int i=0; i< copyImg.myImage.length ; i++){
            for (int j=0; j< copyImg.myImage[0].length; j++){
                
                this.myImage[i][j] = copyImg.myImage[i][j];
            }
        }
    }
    
    public RGBImage(YUVImage YUVImg){
        
        this.colordepth = 255;
        
        
        myImage = new RGBPixel[YUVImg.myYUVImage.length][YUVImg.myYUVImage[0].length];
        
        for (int i=0; i< YUVImg.myYUVImage.length ; i++){
            for (int j=0; j< YUVImg.myYUVImage[0].length; j++){
                
                 myImage[i][j] = new RGBPixel(YUVImg.myYUVImage[i][j]);
            }
        }
        
        
    }
    
    @Override
    public void grayscale() {
        
        for (int i=0; i< this.myImage.length ; i++){
            for (int j=0; j< this.myImage[0].length; j++){
                
               short gray = (short)(this.myImage[i][j].getRed() * 0.3 + this.myImage[i][j].getGreen() * 0.59 + this.myImage[i][j].getBlue() * 0.11);
               
               this.myImage[i][j].setRed(gray);
               this.myImage[i][j].setGreen(gray);
               this.myImage[i][j].setBlue(gray);
                
            }
        }

    }

    @Override
    public void doublesize() {
        
        RGBPixel newImg[][] = new RGBPixel[this.myImage.length * 2][this.myImage[0].length * 2];

        for (int i=0; i< this.myImage.length  ; i++){
            for (int j=0; j< this.myImage[0].length; j++){

                newImg[2*i][2*j] = new RGBPixel(this.myImage[i][j]);

                newImg[2*i+1][2*j] = new RGBPixel(this.myImage[i][j]);

                newImg[2*i][2*j+1] = new RGBPixel(this.myImage[i][j]);

                newImg[2*i+1][2*j+1] = new RGBPixel(this.myImage[i][j]);

            } 
        }
        
        this.myImage = newImg;
    }

    @Override
    public void halfsize() {
        
        if(((this.myImage.length/2) == 1) || (this.myImage[0].length/2 == 1)){
            System.out.println("Image cannot decrease more!");
            return;
        }
        
        int height = (this.myImage.length/2);
        int width = (this.myImage[0].length/2);
    
        RGBPixel newImg[][] = new RGBPixel[height][width];
        

        for (int i=0; i< height; i++){
            for (int j=0; j< width; j++){
                
                short newRed = (short) ((this.myImage[2*i][2*j].getRed() + this.myImage[2*i+1][2*j].getRed() + this.myImage[2*i][2*j+1].getRed() + this.myImage[2*i+1][2*j+1].getRed())/4);
                short newGreen = (short) ((this.myImage[2*i][2*j].getGreen() + this.myImage[2*i+1][2*j].getGreen() + this.myImage[2*i][2*j+1].getGreen() + this.myImage[2*i+1][2*j+1].getGreen())/4);
                short newBlue = (short) ((this.myImage[2*i][2*j].getBlue() + this.myImage[2*i+1][2*j].getBlue() + this.myImage[2*i][2*j+1].getBlue() + this.myImage[2*i+1][2*j+1].getBlue())/4);
                
                newImg[i][j] = new RGBPixel(newRed,newGreen,newBlue);

            } 
        }
        
        this.myImage = newImg;
    }

    @Override
    public void rotateClockwise() {
        
        int M = this.getHeight();
        int N = this.getWidth();
        
        RGBPixel[][] rotatedImg = new RGBPixel[N][M];
        
        
        
        for (int r=0; r<M ; r++){
            for(int c=0 ; c<N ; c++){
                
                rotatedImg[c][M-r-1] = new RGBPixel(this.myImage[r][c]);
            }
        }
        
        myImage = rotatedImg.clone();
    }
    
    public int getWidth(){
       return myImage[0].length;
   }
   
      public int getHeight(){
       return myImage.length;
   }

    public RGBPixel getPixel(int y, int x) {
        return myImage[y][x];
    }

    void setPixel(int y, int x, RGBPixel rgbPixel) {
        myImage[y][x] = rgbPixel;
    }
    
    public int getColorDepth(){
        return colordepth;
    }
    
    
}
