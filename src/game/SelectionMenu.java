package game;

public class SelectionMenu<T> {
    private String messege;
    private String key;
    private T returnVal;

    public SelectionMenu(String key, String messege, T returnVal) {
        this.messege = messege;
        this.key = key;
        this.returnVal = returnVal;
    }

    public String getMessege() {
        return this.messege;
    }

    public String getKey() {
        return this.key;
    }

    public T getReturnVal() {
        return this.returnVal;
    }
}
