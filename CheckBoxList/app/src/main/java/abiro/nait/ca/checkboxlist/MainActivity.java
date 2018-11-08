package abiro.nait.ca.checkboxlist;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
{
    ArrayList<Item> aItems;
    ListView listView;
    CheckAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        aItems = new ArrayList<Item>();
        loadArray();
        adapter = new CheckAdapter(this, android.R.layout.simple_list_item_1, aItems);

        listView = (ListView)findViewById(R.id.listview_checked);
        listView.setAdapter(adapter);
    }

    public void loadArray()
    {
//        this is where you would load from the database, use while next

        Item one = new Item(1, "Item one", false);
        Item two = new Item(1, "Item two", false);
        Item three = new Item(1, "Item three", false);
        Item four = new Item(1, "Item four", false);
        Item five = new Item(1, "Item five", false);
        Item six = new Item(1, "Item six", false);
        Item seven = new Item(1, "Item seven", false);
        Item eight = new Item(1, "Item eight", false);
        Item nine = new Item(1, "Item nine", false);
        Item ten = new Item(1, "Item ten", false);

        aItems.add(one);
        aItems.add(two);
        aItems.add(three);
        aItems.add(four);
        aItems.add(five);
        aItems.add(six);
        aItems.add(seven);
        aItems.add(eight);
        aItems.add(nine);
        aItems.add(ten);
    }
}
