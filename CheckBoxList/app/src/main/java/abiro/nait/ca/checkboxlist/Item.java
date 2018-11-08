package abiro.nait.ca.checkboxlist;

/**
 * Created by abiro1 on 11/8/2018.
 */

public class Item
{
    private String itemDescription;
    private int id;
    private boolean checked;

    public Item(int id, String itemDescription, boolean checked)
    {
        this.itemDescription = itemDescription;
        this.id = id;
        this.checked = checked;
    }

    public String getItemDescription()
    {
        return itemDescription;
    }

    public void setItemDescription(String itemDescription)
    {
        this.itemDescription = itemDescription;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

//    use logic to make sure a checkbox value can be saved in sqlite database as an int
    public boolean isChecked()
    {
        return checked;
    }

    public void setChecked(boolean checked)
    {
        this.checked = checked;
    }
}
