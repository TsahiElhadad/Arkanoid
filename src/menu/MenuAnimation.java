package menu;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import game.SelectionMenu;
import interfaces.Menu;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**.
 * Class of MenuAnimation.
 * @param <T>
 */
public class MenuAnimation<T> implements Menu<T> {
    private String titleMenu;
    private List<SelectionMenu<T>> selectionMenuList;
    private T valStatus;
    private KeyboardSensor keyboardSensor;
    private Boolean stop;

    /**.
     * Constructor for MenuAnimation.
     * @param keyboardSensor -
     */
    public MenuAnimation(KeyboardSensor keyboardSensor) {
//        this.titleMenu = menuTitle;
        this.keyboardSensor = keyboardSensor;
        this.selectionMenuList = new ArrayList<>();
        this.valStatus = null;
        this.stop = false;
    }


    @Override
    public void addSelection(String key, String message, T returnVal) {
        this.selectionMenuList.add(new SelectionMenu<>(key, message, returnVal));
    }

    /**
     * Return the current val.
     * @return the current val
     */
    @Override
    public T getStatus() {
        return this.valStatus;
    }

    /**.
     * Function that in charge of the logic frame of the game.
     * @param d - the draw surface.
     */
    @Override
    public void doOneFrame(DrawSurface d) {
        d.setColor(Color.BLUE);
        d.fillRectangle(0, 0, 800, 600);
        d.setColor(Color.RED);
        d.drawText(200, 200, "Arkanoid Game - Menu", 40);
        d.setColor(Color.BLACK);
        int i = 250;
        // show the options of the messeges we get.
        for (SelectionMenu<T> messege : this.selectionMenuList) {
            d.drawText(200, i, messege.getMessege(), 25);
            i += 50;
        }

        // loop over the choises of menu.
        for (SelectionMenu<T> choise : this.selectionMenuList) {
            // if this key was pressed.
            if (this.keyboardSensor.isPressed(choise.getKey())) {
                this.valStatus = choise.getReturnVal();
                this.stop = true;
            }
        }
    }

    public void resetMenu() {
        this.valStatus = null;
        this.stop = false;
    }

    /**.
     * Function that check if the game should stop or not.
     * @return - true for stopping the game and false continue the game.
     */
    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}
