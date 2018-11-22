package abiro.nait.ca.game001;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import ca.youcode.nait.games.Screen;
import ca.youcode.nait.games.impl.AndroidGame;

public class MainActivity extends AndroidGame
{

    @Override
    public Screen getStartScreen()
    {
        return new LoadingScreen(this);
    }
}
