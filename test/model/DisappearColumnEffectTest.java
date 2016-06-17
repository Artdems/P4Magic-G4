package model;

import static model.ChangeColorEffectTest.aGame;
import static model.DisappearEffectTest.aGame;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Alex
 */
public class DisappearColumnEffectTest {
    static Game aGame;
    public DisappearColumnEffectTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        //creation d'un jeu vide
        aGame = new Game();
        Board b = new Board(10, 10);
        aGame.setBoard(b);

    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test quand on ne joue pas sur la case
     */
    @Test
    public void testDisappearColumnEffectNormalGame() {

        // On pré-remplit le plateau pour les besoins de la simulation 
        Utils.simulateAGame(aGame);

        // Effet fixé sur une case (qui n'est pas encore remplie)
        int height = aGame.getBoard().getHeight();
        // height-3 correspond à la première case vide dans la colonne O, vu que l'on a déjà joué deux coups dans cette colonne
        aGame.getBoard().getTileIJ(height - 3, 0).setEffect(new DisappearColumnEffect());

        // Récupération de l'ID du joueur avant que le coup soit joué 
        int id_player = aGame.getCurrentPlayer().getId();

        // Récupération du nombre de pions présents 
        int nb_tokens_before = aGame.getBoard().getTotalTilesCount();

        // Coup joué sur une case ne contenant pas l'effet 
        aGame.playMove(1);

        // Récupération du nombre de pions après le coup 
        int nb_tokens_after = aGame.getBoard().getTotalTilesCount();

        // Vérifications :
        // - l'effet est bien appliqué sur la case 
        // - le tour de jeu a bien changé
        // - il y a bien un pion de plus sur le plateau
        assertTrue("Doit être d'effet disappear column", aGame.getBoard().getTileIJ(height - 3, 0).getEffect() instanceof DisappearColumnEffect);
        assertTrue(aGame.getCurrentPlayer().getId() != id_player);
        assertEquals(nb_tokens_before + 1, nb_tokens_after);
    }
    /**
     * Test quand le plateau est vide
     */
   @Test
    public void testDisappearColumnEffectEmptyGame() {

        // Effet fixé sur une case 
        int height = aGame.getBoard().getHeight();
        aGame.getBoard().getTileIJ(height - 1, 0).setEffect(new DisappearColumnEffect());

        // Récupération de l'ID du joueur courant 
        int id_player = aGame.getCurrentPlayer().getId();

        // Coup joué sur la case de l'effet 
        aGame.playMove(0);
        
        // Récupération de l'ID du joueur suivant 
        int id_next_player = aGame.getCurrentPlayer().getId();
        

        // Vérifications :
        // - le plateau est bien vide
        // - l'effet est bien appliqué sur la case 
        // - le tour de jeu a bien changé
        assertEquals(0, aGame.getBoard().getTotalTilesCount());
        assertTrue("Doit être d'effet disappear column", aGame.getBoard().getTileIJ(height - 1, 0).getEffect() instanceof DisappearColumnEffect);
        assertTrue(aGame.getCurrentPlayer().getId() != id_player);

    }
    /**
     * test apres plusieurs coups
     */
    @Test
    public void testDisappearColumnEffectFilledGame() {

        // On pré-remplit le plateau pour les besoins de la simulation 
        Utils.simulateAGame(aGame);

        // Effet fixé sur une case (qui n'est pas encore remplie)
        int height = aGame.getBoard().getHeight();
        // height-3 correspond à la première case vide dans la colonne O, vu que l'on a déjà joué deux coups dans cette colonne
        aGame.getBoard().getTileIJ(height - 3, 0).setEffect(new DisappearColumnEffect());

        // Récupération de l'ID du joueur avant que le coup soit joué 
        int id_player = aGame.getCurrentPlayer().getId();

        // Coup joué sur cette case 
        aGame.playMove(0);

        // Vérifications :
        // - la colonne est bien vide après le coup
        // - l'effet est bien appliqué sur la case 
        // - le tour de jeu a bien changé
        for (int i=0;i<aGame.getBoard().getHeight();i++){
            assertEquals(-1,aGame.getBoard().getTileIJ(i, 0).getStatus());
        }
        assertTrue("Doit être d'effet disappear column", aGame.getBoard().getTileIJ(height - 3, 0).getEffect() instanceof DisappearColumnEffect);
        assertTrue(aGame.getCurrentPlayer().getId() != id_player);

    }
    
}
