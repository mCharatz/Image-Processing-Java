/**
 *
 * @author Michalis Charatzoglou , mCharatz
 */


package ImageProcessing;


public class YUVPixel {
    
    private int yuv;
    
    public YUVPixel(short Y, short U, short V){
        yuv =  (Y <<16) | (U<<8) | V;
    }
    
    public YUVPixel(YUVPixel pixel){
       yuv = pixel.yuv;
    }
    
    
    public YUVPixel(RGBPixel pixel){
        
      int Y = ( (  66 * pixel.getRed() + 129 * pixel.getGreen() +  25 * pixel.getBlue() + 128) >> 8) +  16;
      int U = ( ( -38 * pixel.getRed() -  74 * pixel.getGreen() + 112 * pixel.getBlue() + 128) >> 8) + 128;
      int V = ( ( 112 * pixel.getRed() -  94 * pixel.getGreen() -  18 * pixel.getBlue() + 128) >> 8) + 128;
      
      yuv =  (Y <<16) | (U<<8) | V;

    }
    
    public short getY(){
        Integer Y = (yuv>>16) & 0xff;
	return Y.shortValue();
    }
    
    public short getU(){
        Integer U = (yuv>>8) & 0xff;
	return U.shortValue();
    }
    
    public short getV(){
        Integer V = yuv & 0xff;
	return V.shortValue();
    }
    
    public void setΥ(short Y) {
	yuv =  (Y <<16) | (getU()<<8) | getV();
    }
        
    public void setU(short U) {
	yuv =  (getY() <<16) | (U<<8) | getV();
    }
    
    public void setV(short V) {
	yuv =  (getY() <<16) | (getU()<<8) | V;
    }
}
