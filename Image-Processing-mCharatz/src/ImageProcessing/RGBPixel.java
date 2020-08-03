package ImageProcessing;

/**
 *
 * @author MIchalis Charatzoglou , mCharatz
 */
public class RGBPixel {
    
    private int rgb;
	
    public RGBPixel(short red, short green, short blue) {
	rgb =  (red <<16) | (green<<8) | blue;
    }
	
    public RGBPixel(YUVPixel pixel) {
        
	int C = pixel.getY() - 16;
	int D = pixel.getU() - 128;
	int E = pixel.getV() - 128;

	int R = clip(( 298 * C + 409 * E + 128) >> 8);
	int G = clip(( 298 * C - 100 * D - 208 * E + 128) >> 8);
	int B = clip(( 298 * C + 516 * D + 128) >> 8);
		
	rgb =  (R <<16) | (G<<8) | B;
    }
	
    public RGBPixel(RGBPixel pixel) {
	this(pixel.getRed(), pixel.getGreen(), pixel.getBlue());
    }
	
    public short getRed() {
	Integer red = (rgb>>16) & 0xff;
	return red.shortValue();
    }
    
    public short getGreen() {
	Integer green = (rgb>>8) & 0xff;
	return green.shortValue();
    }
    
    public short getBlue() {
	Integer blue = rgb & 0xff;
	return blue.shortValue();
    }
	
    public void setRed(short red) {
	rgb =  (red <<16) | (getGreen()<<8) | getBlue();
    }
        
    public void setGreen(short green) {
	rgb =  (getRed() <<16) | (green<<8) | getBlue();
    }
    
    public void setBlue(short blue) {
	rgb =  (getRed() <<16) | (getGreen()<<8) | blue;
    }
	
    private int clip(int x) {
	if(x < 0) 
            return 0;
        
	else if(x>255)
            return 255;
        
        return x;
    }
    
    public int getRGB(){
        return rgb;
    }
    
    public void setRGB(int value){
        rgb = value;
    }
    
    final void setRGB(short red, short green, short blue){
        rgb =  (red <<16) | (green<<8) | blue;
    }
}
