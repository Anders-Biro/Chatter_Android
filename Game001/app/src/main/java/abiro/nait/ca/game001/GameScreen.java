package abiro.nait.ca.game001;

import android.graphics.Color;

import java.util.List;

import ca.youcode.nait.games.Game;
import ca.youcode.nait.games.Graphics;
import ca.youcode.nait.games.Input;
import ca.youcode.nait.games.Screen;

/**
 * Created by abiro1 on 11/22/2018.
 */

public class GameScreen extends Screen
{
    public final static String TAG = "Game001_GameScreen";
    int x = 10, y = 10, xInc = 5, yInc = 4;

    public GameScreen(Game game)
    {
        super(game);
    }

    @Override
    public void update(float deltatime)
    {
        List<Input.TouchEvent> touchEvents = game.getInput().getTouchEvents();
        int length = touchEvents.size();

        xInc += length;
        if(xInc > 20)
            xInc = 0;

        x += xInc;
        y += yInc;

        if(x < 10 || x > 310)
        {
            xInc *= -1;
        }
        if(y < 10 || y > 470)
        {
            yInc *= -1;
        }
    }

    @Override
    public void present(float deltatime)
    {
        Graphics g = game.getGraphics();
        g.drawCircle(x,y,10, Color.parseColor("#990000"));
    }

    @Override
    public void pause()
    {

    }

    @Override
    public void resume()
    {

    }

    @Override
    public void dispose()
    {

    }
}
