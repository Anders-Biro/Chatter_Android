package abiro.nait.ca.checkboxlist;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by abiro1 on 11/8/2018.
 */

public class CheckAdapter extends ArrayAdapter<Item>
{
    Context context;
    LayoutInflater inflater;
    ArrayList<Item> aItems;

    public CheckAdapter(Context context, int rowLayoutId, List items)
    {
        super(context, rowLayoutId, items);
        this.context = context;

        MainActivity activity = (MainActivity)context;
        inflater = activity.getLayoutInflater();
        aItems = (ArrayList<Item>) items;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        LinearLayout view = (LinearLayout)convertView;
        if (view == null)
        {
            view = (LinearLayout)inflater.inflate(R.layout.listview_row, parent, false);
        }
        TextView tv = (TextView)view.findViewById(R.id.row_description);
        tv.setText(getItem(position).getItemDescription());
        CheckBox cb = (CheckBox)view.findViewById(R.id.row_checkbox);
        cb.setOnCheckedChangeListener(null);
        cb.setChecked(getItem(position).isChecked());
        cb.setTag(Integer.valueOf(position));
        cb.setOnCheckedChangeListener(listener);
        return view;
    }

    public int getCount()
    {
        return aItems.size();
    }

    public Item getItem(int pos)
    {
        return aItems.get(pos);
    }

    CompoundButton.OnCheckedChangeListener listener = new CompoundButton.OnCheckedChangeListener()
    {
        @Override
        public void onCheckedChanged(CompoundButton compoundButton, boolean b)
        {
            aItems.get((Integer)compoundButton.getTag()).setChecked(b);
        }
    };
}
