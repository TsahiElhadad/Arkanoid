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
import Menu.HighScoreAnimation;
import Menu.MenuAnimation;
import Menu.ShowHiScoresTask;
import io.LevelSpecificationReader;

/**.
 * Main class for assigment 6.
 */
public class Ass6Game {
    /**.
     * Main function for ASS6, starting the game.
     * @param args - not get arguments from the user.
     */
    public static void main(String[] args) {
        // make animation runner and keyboard sensor for the game.
        AnimationRunner animationRunner = new AnimationRunner();
        KeyboardSensor keyboard = animationRunner.getKeyboard();
        //Menu<Task<Void>> menu = new MenuAnimation<Task<Void>>(keyboard);
//        menu.addSelection("s", "start game", "game flow");
        // make HighScoreFile with 0 score.
        HighScoreFile highScoreFile = new HighScoreFile(0);
        //HighScoreAnimation highScoreAnimation = new HighScoreAnimation(highScoreFile.getHightScore());
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
                    GameFlow game = new GameFlow(animationRunner, keyboard, 1);
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
            //((MenuAnimation) menu).resetMenu();
        }

//
//        // make 4 levels of game.
//        LevelInformation level1 = new DirectHit();
//        LevelInformation level2 = new WideEasy();
//        LevelInformation level3 = new Green3();
//        LevelInformation level4 = new FinalFour();
//        // make list of LevelInformation
//        List<LevelInformation> listLevels = new ArrayList<LevelInformation>();
//        // make a gameFlow with 7 lives.
//        GameFlow game = new GameFlow(animationRunner, keyboard, 1);
//        // if we d'ont get arguments from the user, play 4 levels.
//        if (args.length != 0) {
//            for (String s : args) {
//                // if pressed 1 for level 1.
//                if (s.equals("1")) {
//                    listLevels.add(level1);
//                } else if (s.equals("2")) { // if pressed 2 for level 2.
//                    listLevels.add(level2);
//                } else if (s.equals("3")) { // if pressed 3 for level 3.
//                    listLevels.add(level3);
//                } else if (s.equals("4")) { // if pressed 4 for level 4.
//                    listLevels.add(level4);
//                }
//            }
//        }
//        // if listLevels is empty
//            if (listLevels.isEmpty()) {
//                listLevels.add(level1);
//                listLevels.add(level2);
//                listLevels.add(level3);
//                listLevels.add(level4);
//                game.runLevels(listLevels);
//            } else { // we get arguments from user.
//                game.runLevels(listLevels);
//        }
    }

    public static List<LevelInformation> makeLevelsList() {
        // make 4 levels of game.
        LevelInformation level1 = new DirectHit();
        LevelInformation level2 = new WideEasy();
        LevelInformation level3 = new Green3();
        LevelInformation level4 = new FinalFour();
        // make list of LevelInformation
        List<LevelInformation> listLevels = new ArrayList<LevelInformation>();
//        listLevels.add(level1);
        listLevels.add(level2);
//        listLevels.add(level3);
//        listLevels.add(level4);
        return listLevels;
    }
}
