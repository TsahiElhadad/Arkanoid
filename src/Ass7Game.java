/**.
 * @author Tzahi elhadad
 * ID 206214165
 */
import animation.AnimationRunner;
import animation.KeyPressStoppableAnimation;
import biuoop.KeyboardSensor;
import game.GameFlow;
import interfaces.Menu;
import interfaces.Task;
import io.HighScoreFile;
import levels.DirectHit;
import levels.Green3;
import levels.LevelInformation;
import levels.WideEasy;
import levels.FinalFour;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import menu.HighScoreAnimation;
import menu.MenuAnimation;
import menu.ShowHiScoresTask;
import io.LevelSpecificationReader;

/**.
 * Main class for assigment 7.
 */
public class Ass7Game {
    /**.
     * Main function for ASS7, starting the game.
     * @param args - not get arguments from the user.
     */
    public static void main(String[] args) {
        // make animation runner and keyboard sensor.
        AnimationRunner animationRunner = new AnimationRunner();
        KeyboardSensor keyboard = animationRunner.getKeyboard();
        // make HighScoreFile with 0 score.
        HighScoreFile highScoreFile = new HighScoreFile(0);
        // MAKE MENU SELECTION.
        try {
            List<LevelInformation> listLevelInfo = new ArrayList<>();
            File fileLevel = new File("level_definition.txt");
            Reader reader = new FileReader(fileLevel);
            LevelSpecificationReader lSp = new LevelSpecificationReader();
            listLevelInfo = lSp.fromReader(reader);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        // run the game menu.
        while (true) {
            Menu<Task<Void>> menu = new MenuAnimation<Task<Void>>(keyboard);
            // add start game option.
            menu.addSelection("s", "Start Game - press s", new Task<Void>() {
                @Override
                public Void run() {
                    GameFlow game = new GameFlow(animationRunner, keyboard, 3);
                    game.runLevels(makeLevelsList());
                    return null;
                }
            });
            // add high score option.
            menu.addSelection("h", "High Score Of The Game - press h",
                    new ShowHiScoresTask(animationRunner,
                            new KeyPressStoppableAnimation(keyboard, KeyboardSensor.SPACE_KEY,
                                    new HighScoreAnimation(highScoreFile.getHightScore()))));
            // add quit game option.
            menu.addSelection("q", "Quit Game - press q", new Task<Void>() {
                        // anonymus Class for quit from game.
                        @Override
                        public Void run() {
                            System.exit(0);
                            return null;
                        }
                    });
            animationRunner.run(menu);
            Task<Void> task = menu.getStatus();
            // DO ACOORDING TO STATUS.
            task.run();
        }
    }

    /*
    Return list of levels of game.
     */
    public static List<LevelInformation> makeLevelsList() {
        // make 4 levels of game.
        LevelInformation level1 = new DirectHit();
        LevelInformation level2 = new WideEasy();
        LevelInformation level3 = new Green3();
        LevelInformation level4 = new FinalFour();
        // make list of LevelInformation
        List<LevelInformation> listLevels = new ArrayList<LevelInformation>();
        listLevels.add(level1);
        listLevels.add(level2);
        listLevels.add(level3);
        listLevels.add(level4);
        return listLevels;
    }
}
