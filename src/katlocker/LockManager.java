package katlocker;

public class LockManager {
    private boolean locked = false;
    private final InputBlocker inputBlocker = new InputBlocker();

    public void toggleLock() {
        locked = !locked;
        if (locked) {
            System.out.println("🔒 Modo Gato ACTIVADO (entrada bloqueada)");
            inputBlocker.startBlocking();
        } else {
            System.out.println("🔓 Modo Gato DESACTIVADO (entrada restaurada)");
            inputBlocker.stopBlocking();
        }
    }

    public boolean isLocked() {
        return locked;
    }
}
