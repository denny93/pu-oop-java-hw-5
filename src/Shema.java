public class Shema {
    int startX;
    int startY;
    int endX;
    int endY;
    BaseModel baseModel;
    int status  = 0;


    public Shema(int startX , int start_y , int endX , int endY , BaseModel baseModel)
    {
        this.startX = startX;
        this.startY = start_y;
        this.endX = endX;
        this.endY = endY;
        this.baseModel = baseModel;
    }
}