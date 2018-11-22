package abiro.nait.ca.game001;

import ca.youcode.nait.games.Game;
import ca.youcode.nait.games.Screen;

/**
 * Created by abiro1 on 11/22/2018.
 */

public class LoadingScreen extends Screen
{
    public LoadingScreen(Game game)
    {
        super(game);
    }

    @Override
    public void update(float v)
    {
        game.setScreen(new GameScreen(game));
    }

    @Override
    public void present(float v)
    {

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
