/**
 * Created by hadar on 14/06/2017.
 */

/**
 * @param <T> the type of the return value of task
 */
public interface Task<T> {
    /**
     * run the task and return a T-type object.
     *
     * @return a return value of type T
     */
    T run();
}
