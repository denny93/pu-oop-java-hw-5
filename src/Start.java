import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseListener;
import java.util.Random;

public class Start extends JPanel {

    public static Shema[][] shemaArray = new Shema[64][64];
    static JFrame frame;
    public static Graphics gs;
    static int maxcellInMatrix = 64 * 64;
    public static Controller control ;
    public static boolean finish = false;
    public  static void main(String[] args)
    {
        shemaArray= new Shema[64][64];
        createJframe();
        refreshPage();
    }

    public  void paint(Graphics g){
        gs = g;
        addElements(g , false );
    }

    /**
     *  paint items in jframe
     * @param g grafic parameter for add element
     * @param modes if modes is true that form repaint else fornt not repaint
     */
    public  static void addElements(Graphics g, boolean modes) {
        if (modes) {
            frame.repaint();
        }
        int x = 0 ;
        int y = 0;
        int kub = 10;
        for(int i = 0; i < 64; i++) {
            for (int j = 0; j < 64; j++) {
                if (shemaArray[i][j].baseModel.color != null) {
                    if (shemaArray[i][j].status == 1) {
                        g.setColor(Color.black);
                        g.fillRect(x, y, kub, kub);
                        g.setColor(Color.black);
                        g.drawRect(x, y, kub, kub);
                    } else {
                        g.setColor(shemaArray[i][j].baseModel.color);
                        g.fillRect(x, y, kub, kub);
                        g.setColor(Color.black);
                        g.drawRect(x, y, kub, kub);
                    }
                }
                shemaArray[i][j].startX = x;
                shemaArray[i][j].startY = y;
                shemaArray[i][j].endX = x+kub;
                shemaArray[i][j].endY = y+kub;
                x+=kub;
            }
            y+= kub;
            x = 0 ;
        }
        AddMenu(x +700, 100 , g);
    }

    /**
     * generate right menu for all phones
     * @param x position x of element
     * @param y position y of element
     * @param g grafic parameter for add element
     */
    private static void AddMenu(int x , int y, Graphics g) {
        for (int index = 0 ; index < control.phones.size() ; index++)
        {
            if (control.phones.get(index).finalStatus == 1) {
                g.setColor(Color.red);
            }
            else
            {
                g.setColor(Color.green);
            }
            g.fillRect(x, y, 100, 50);
            g.setColor(Color.black);
            g.drawRect(x, y, 100, 50);
            g.setColor(Color.black);
            g.drawString(control.phones.get(index).name, x + 20, y + 30);
            control.phones.get(index).startX = x;
            control.phones.get(index).startY = y;
            control.phones.get(index).endX = x+100;
            control.phones.get(index).endY = y+50;
            y+= 60;
        }
    }

    private static void createJframe() {
        frame = new JFrame();
        frame.setSize(1000,800);
        frame.getContentPane().add(new Start());
        frame.setLocationRelativeTo(null);
        frame.setBackground(Color.black);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        Component mouseClick = new MouseController()  ;
        frame.addMouseListener((MouseListener) mouseClick);
    }
    private static void refreshPage() {
        addPhones();
        addElements(gs, true);
    }

    /**
     * add infomation for position  of pixels for every pgone
     */
    private static void addINFoInMatrix() {
        createModels();
        enterBlue();
        enterRed();
        enterGreen();
        int maxPeace = 100;
        maxPeace = enterBurnedPixel(maxPeace);
        maxPeace = enterDAmagePixels(maxPeace);
        enterAllUndamagePixel();
    }

    /**
     * add information for every phone
     */
    private static void addPhones() {
        control = new Controller();
        addINFoInMatrix();
        control.setPhones(new Phones("Saamsung" , shemaArray));
        shemaArray = new Shema[64][64];
        addINFoInMatrix();
        control.setPhones(new Phones("Huawei" , shemaArray));
        shemaArray = new Shema[64][64];
        addINFoInMatrix();
        control.setPhones(new Phones("IPhone" , shemaArray));
        shemaArray = new Shema[64][64];
        addINFoInMatrix();
        control.setPhones(new Phones("Nokia" , shemaArray));
        shemaArray = new Shema[64][64];
        addINFoInMatrix();
        control.setPhones(new Phones("Siemens" , shemaArray));
        control.setPhones(new Phones("Finish" , new Shema[64][64]));
        shemaArray = new Shema[64][64];
        createModels();
    }

    /**
     * finish message
     */
    public static void finish()
    {
        int damage = 0 ;
        int helty = 0 ;

        for (int index = 0 ; index < control.phones.size() ; index++)
        {
            if(control.phones.get(index).finalStatus == 1){
                damage++;
            }
            else{
                helty++;
            }

        }
        JOptionPane.showMessageDialog(frame, "Helty phones " +helty +" , damage " + damage);
    }

    /**
     * enter undamaged pixel in shema array
     */
    private static void enterAllUndamagePixel() {
        for (int i = 0 ; i < 64 ; i++)
        {
            for(int j =0 ; j < 64 ; j++)
            {
                if (shemaArray[i][j].baseModel.status == null)
                {
                    shemaArray[i][j].baseModel.status = new UnharmedPixel();
                }
            }
        }
    }

    /**
     *  enter burned pixel in array
     * @param maxProcent this parameter is using licke peace of 100% of pixels
     * @return values of free peaces
     */

    private static int enterBurnedPixel(int maxProcent) {
        return enterAllStatus(maxProcent , "Burned" );
    }

    /**
     *  enter damage pixel in array
     * @param maxProcent this parameter is using licke peace of 100% of pixels
     * @return values of free peaces
     */
    private static int enterDAmagePixels(int maxProcent) {
         return enterAllStatus(maxProcent , "Damage" );
    }

    /**
     * this is main method that create damage or burned pixel in array
     * @param maxProcent this parameter is using licke peace of 100% of pixels
     * @param enterStatus name of pixel "Burned" or "Damage"
     * @return free procent for adding
     */
    private static int enterAllStatus(int maxProcent, String enterStatus) {
        Random random = new Random();
        double procent =  (double)(random.nextInt(maxProcent));
        int maxEnter =(int) (maxcellInMatrix / (1+(procent / 100)));
        if (procent <= 0 )
        {
            enterAllStatus(maxProcent, enterStatus);
        }
        for (int index = 0 ; index < maxEnter ; index++)
        {
            int x = random.nextInt(64) ;
            int y = random.nextInt(64)  ;
            if (shemaArray[x][y].baseModel.status == null)
            {
                switch (enterStatus)
                {
                    case "Damage" : shemaArray[x][y].baseModel.status = new DamagedPixel();break;
                    case "Burned" : shemaArray[x][y].baseModel.status = new BurnedPixel(); break;
                }
            }
            else
            {
                maxEnter++;
            }
        }
        return (int)procent * 100;
    }

    /**
     * enter greeen pixel
     */
    private static void enterGreen() {
        for (int i = 0 ; i < 64 ; i++)
        {
            for(int j =0 ; j < 64 ; j++)
            {
                if (shemaArray[i][j].baseModel.color == null)
                {
                    shemaArray[i][j].baseModel.color = Color.green;
                }
            }
        }
    }

    /**
     * enter red pixel
     */
    private static void enterRed() {
        SetColor(Color.red , maxcellInMatrix  / 3);
    }

    /**
     * enter red blue
     */
    private static void enterBlue() {
        SetColor(Color.blue , maxcellInMatrix /  3 );
    }

    /**
     * main method for adding color in shema array and visulize onmn frame
     * @param color color of pixel
     * @param max max adding pixels of color
     */
    private static void SetColor(Color color, int max) {
        Random random = new Random();
        for (int index= 0 ; index < max ; index ++)
        {
            int x = random.nextInt(64) ;
            int y = random.nextInt(64)  ;
            if (shemaArray[x][y].baseModel.color == null)
            {
                shemaArray[x][y].baseModel.color = color;
            }
            else
            {
                max++;
            }
        }
    }

    /**
     * main method for create array of elements and color
     */
    private static void createModels() {
        for (int i  = 0 ; i < 64 ; i++)
        {
            for (int j = 0 ; j < 64 ; j++)
            {
                shemaArray[i][j] = new Shema(0,0,0,0,new BaseModel(null));
            }
        }
    }
}
