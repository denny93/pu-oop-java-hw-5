public class Phones {
    String name;
    Shema[][] shemaArray ;
    int startX;
    int startY;
    int endX;
    int endY;
    int countDamagePixel = 0 ;
    int countBurrnedPixels = 0 ;
    int couintUnharmedPixel = 0 ;
    int finalStatus = 0 ;
    int checkedPixels = 0 ;
    public Phones(String name , Shema[][] shemaArray)
    {
        this.shemaArray = new Shema[64][64];
        this.name = name;
        this.shemaArray = shemaArray;
    }
    public void CheckFinalStatus()
    {
        int max = 64 *64 ;
        if ((countBurrnedPixels + countDamagePixel) > (max / 2))
        {
            finalStatus = 1;
        }
    }
}
