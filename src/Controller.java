import java.util.ArrayList;
import java.util.List;

public class Controller {
    List<Phones> phones;


    public Controller()
    {
        phones = new ArrayList<>();
    }

    public void setPhones(Phones phones) {
        this.phones.add(phones);
    }

    public Phones getPhones(int index ) {
        if (index < 0)
        {
            return null;
        }
        if (index == phones.size())
        {
            return null;
        }
        return phones.get(index);
    }
}
