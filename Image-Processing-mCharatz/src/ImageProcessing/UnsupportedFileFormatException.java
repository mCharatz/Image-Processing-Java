package ImageProcessing;

/**
 *
 * @author Michalis Charatzoglou , mCharatz
 */

public class UnsupportedFileFormatException extends java.lang.Exception{
    
    public UnsupportedFileFormatException(){
        super();
    }
    
    public UnsupportedFileFormatException(String msg){
        super(msg);
    }
    
    @Override
    public String getMessage(){
        return "UnsupportedFileFormatException occured!";
    }
    
    
}