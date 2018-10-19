package abiro.nait.ca.week05;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

/**
 * Created by abiro1 on 10/19/2018.
 */

public class BaseActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu,menu);
        return true;
    }

    public boolean onMenuOpened(int featureId, Menu menu)
    {
        if(menu != null)
        {
            MenuItem toggleItem = menu.findItem(R.id.menu_item_toggle_service);
            if(GetterService.bRun == false)
            {
                toggleItem.setTitle("Go Online");
            }
            else
            {
                toggleItem.setTitle("Go Offline");
            }
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch(item.getItemId())
        {
            case R.id.menu_item_home:
            {
                startService(new Intent(this,Week05Activity.class));
                break;
            }
            case R.id.menu_item_toggle_service:
            {
                if(GetterService.bRun == false)
                {
                    startService(new Intent(this, GetterService.class));
                }
                else
                {
                    stopService(new Intent(this, GetterService.class));
                }
                break;
            }
            case R.id.menu_item_show_chatter:
            {
                startActivity(new Intent(this, ShowChatter.class));
                break;
            }
            case R.id.menu_item_cursor_list:
            {
                startActivity(new Intent(this, ChatCursorAdapterActivity.class));
                break;
            }
        }
        return true;
    }
}
