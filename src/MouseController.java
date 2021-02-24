import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 *This is class for control all game by clicking over one of the graphics buckets
 * in front end
 * we use mouse press to handle the event from clicking over jframe
 */

public class MouseController extends JComponent implements MouseListener {
    int x = 0;
    int y = 0;
    int maxClick = 0;
    int xXlixked = 0;
    int yxlixked = 0;
    @Override
    public void mouseClicked(MouseEvent e) {

    }

    int phoneSelectedIndex = 0;
    @Override
    public void mousePressed(MouseEvent e) {
        int x = e.getX() - 10;
        int y = e.getY() - 30;
        if (x > 650 )
        {
            CheckClickOverMenu(x , y );
            return;
        }
        boolean isresulve = false;
        for (int i = 0 ; i < 64 ; i++){
            for (int j = 0 ; j < 64 ; j++){
                if (Start.shemaArray[i][j].startX <= x && Start.shemaArray[i][j].startY <= y &&
                        Start.shemaArray[i][j].endX >= x && Start.shemaArray[i][j].endY >= y) {
                    if (isresulve) {
                        break;
                    }
                    switch (Start.shemaArray[i][j].baseModel.status.getClass().getName()) {
                        case "UnharmedPixel":
                            isresulve = true;
                            Start.control.phones.get(phoneSelectedIndex).couintUnharmedPixel++;
                            maxClick = 0;
                            break;
                        case "BurnedPixel":
                            Start.shemaArray[i][j].status = 1;
                            isresulve = true;
                            Start.control.phones.get(phoneSelectedIndex).countBurrnedPixels++;
                            maxClick = 0 ;
                            break;
                        case "DamagedPixel":
                            CheckDamagePixel(i,j);
                            isresulve = true;
                            break;
                    }
                    Start.control.phones.get(phoneSelectedIndex).checkedPixels++;
                }
            }
            if(isresulve)
            {
                break;
            }
        }
        Start.control.phones.get(phoneSelectedIndex).CheckFinalStatus();
        Start.addElements(Start.gs , true);
    }

    /**
     *  check for damage pixel and change main array if we have 3 click over pixel
     * @param i position row in matrix
     * @param j position col in matrix
     */
    private void CheckDamagePixel(int i , int j) {
        xXlixked = i;
        yxlixked = j;
        if (xXlixked == i && yxlixked == j) {
            maxClick++;
        } else {
            maxClick = 0;
        }
        if (maxClick > 2) {
            Start.shemaArray[i][j].status = 1;
            Start.control.phones.get(phoneSelectedIndex).countDamagePixel++;
        }
    }

    /**
     * chekc if click by mouse is over menu
     */
    private void CheckClickOverMenu(int x  , int y) {
        boolean isAdded = false;
        for (int index = 0 ; index < Start.control.phones.size() ; index++)
        {
            if (Start.control.phones.get(index).startX <= x && Start.control.phones.get(index).startY <= y &&
                    Start.control.phones.get(index).endX >= x && Start.control.phones.get(index).endY >= y)
            {
                if (Start.control.phones.get(index).name == "Finish")
                {
                    if(CHeckIfFiveFoneIsChecked()) {
                        Start.finish();
                    }
                }
                Start.shemaArray = Start.control.phones.get(index).shemaArray;
                phoneSelectedIndex = index;
                isAdded = true;
                break;
            }
        }
        if (isAdded == false)
        {
            return;
        }
        Start.addElements(Start.gs , true);
    }

    /**
     *  check is five phones is checked or not
     * @return tru if five phone is checked ot false if isn`t
     */
    private boolean CHeckIfFiveFoneIsChecked() {
        int count = 0 ;
        for (int index  = 0 ; index < Start.control.phones.size() ; index++)
        {
            if (Start.control.phones.get(index).checkedPixels > 0 )
            {
                count++;
            }
        }
        if (count == 5)
        {
            return true;
        }
        return false;
    }


    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
