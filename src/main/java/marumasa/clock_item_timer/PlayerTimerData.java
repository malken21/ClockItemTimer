package marumasa.clock_item_timer;

public class PlayerTimerData {
    public long timer = 0;

    /* status
    1 タイマーが起動中 初期状態
    2 タイマーが一時停止
     */
    public int status = 1;

    public void remove() {
    }
}
