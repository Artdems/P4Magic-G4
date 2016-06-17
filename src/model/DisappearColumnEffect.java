package model;

/**
 *
 * @author Alex
 */
public class DisappearColumnEffect extends Effect {
/**
 * this effet empties its column when it's played on
 * @param line
 * @param column
 * @param game 
 */
    @Override
    public void playEffect(int line, int column, Game game) {
        for (int i=0;i<game.getBoard().getHeight();i++){
            game.getBoard().getTileIJ(i, column).setStatus(-1);
        }
    }
    
}
