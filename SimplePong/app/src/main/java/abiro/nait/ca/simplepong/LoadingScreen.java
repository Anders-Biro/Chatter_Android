package abiro.nait.ca.simplepong;

import ca.youcode.nait.games.Game;
import ca.youcode.nait.games.Graphics;
import ca.youcode.nait.games.Screen;

/**
 * Created by abiro1 on 11/23/2018.
 */

public class LoadingScreen extends Screen
{
    public LoadingScreen (Game game)
    {
        super(game);
    }

    @Override
    public void update(float v)
    {
        Graphics g = game.getGraphics();
        Assets.ball = g.newPixmap("ball.png", Graphics.PixmapFormat.RGB565);
        Assets.background = g.newPixmap("background.jpg", Graphics.PixmapFormat.RGB565);
        Assets.gameover = g.newPixmap("gameover2.png", Graphics.PixmapFormat.RGB565);
        Assets.paddle = g.newPixmap("paddle2.png", Graphics.PixmapFormat.RGB565);

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
