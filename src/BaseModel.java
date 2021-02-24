import java.awt.*;

public class BaseModel {
    Color color ;
    int finalStatus = 0;
    Pixel status  ;
    public  BaseModel()
    {}
    public BaseModel(Color color)
    {
        this.color = color;
    }
    public BaseModel(Color color, int status )
    {
        this.color = color;
    }

}
