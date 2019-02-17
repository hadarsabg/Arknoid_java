/**
 * Created by hadar on 25/05/2017.
 */
public class Counter {
    private int count = 0;

    /**
     * add number to current count.
     *
     * @param number an integer
     */
    public void increase(int number) {
        this.count += number;
    }


    /**
     * subtract number from current count.
     *
     * @param number an integer
     */
    public void decrease(int number) {
        this.count -= number;
    }

    /**
     * get current count.
     *
     * @return this.count
     */
    public int getValue() {
        return this.count;
    }
}

