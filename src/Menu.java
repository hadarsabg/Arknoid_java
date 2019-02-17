/**
 * Created by hadar on 14/06/2017.
 *
 * @param <T> the type of the return values(status)
 */
public interface Menu<T> extends Animation {
    /**
     * add an optional selection to the menu.
     *
     * @param key       - key that represent the selection
     * @param message   a string-what is the option
     * @param returnVal return value after pressing key
     */
    void addSelection(String key, String message, T returnVal);

    /**
     * return the returnVal of the option chosen.
     *
     * @return current menu status
     */
    T getStatus();

    /**
     * add a sub menu.
     * @param key symbol
     * @param message massage
     * @param subMenu sub menu animation
     */
    void addSubMenu(String key, String message, Menu<T> subMenu);
}
