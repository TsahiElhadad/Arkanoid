package io;

import java.io.*;

public class HighScoreFile {
    private int scoreMessage;
    private File fileObj;
    private int hightScore;

    /**.
     * Cons
     * @param score - score.
     */
    public HighScoreFile(int score) {
            this.scoreMessage = score;
            this.fileObj = null;
            this.hightScore = 0;
    }

    /**.
     * Function that return the HighScore.
     * @return
     */
    public int getHightScore() {
        addNewScore();
        return this.hightScore;
    }

    /**
     * Function that in charge of the logic frame of the game.
     */
    public void addNewScore() {
        try {
            // make path to File.
                this.fileObj = new File("highScore.txt");
                // check if file not created allready
                if (this.fileObj.createNewFile()) {
                    PrintWriter printWriter = new PrintWriter(this.fileObj);
                    printWriter.write("The highest score so far is: " + this.scoreMessage);
                    this.hightScore = this.scoreMessage;
                    printWriter.close();
                } else {
                    // read from line
                    BufferedReader bufferedReader = new BufferedReader(
                            new InputStreamReader(
                                    new FileInputStream("highScore.txt")));
                    // get the first line from file.
                    String line = bufferedReader.readLine();
                    // get the score from the line.
                    String scoreFromfileStr = line.substring(line.indexOf(":") + 2);
                    // convert the score to integer.
                    int scoreToInt = Integer.parseInt(scoreFromfileStr);
                    // load the hightscore.
                    this.hightScore = scoreToInt;
                    // check if the new score is bigger than last one.
                    if (this.scoreMessage > scoreToInt) {
                        PrintWriter printWriter = new PrintWriter(this.fileObj);
                        printWriter.write("The highest score so far is: " + this.scoreMessage);
                        this.hightScore = this.scoreMessage;
                        printWriter.close();
                    }
                    bufferedReader.close();
                }
        } catch (IOException e) {
            System.out.println("Error in creating file");
            e.printStackTrace();
        }
    }
}
