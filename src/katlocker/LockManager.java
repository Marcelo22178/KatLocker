package katlocker;

public class LockManager {
    private boolean locked = false;
    private final InputBlocker inputBlocker = new InputBlocker();
    private TrayIcon trayIcon;

    public void setTrayIcon(TrayIcon trayIcon) {
        this.trayIcon = trayIcon;
    }

    public void toggleLock() {
        locked = !locked;
        if (locked) {
            System.out.println("🔒 Modo Gato ACTIVADO (entrada bloqueada)");
            inputBlocker.startBlocking();
        } else {
            System.out.println("🔓 Modo Gato DESACTIVADO (entrada restaurada)");
            inputBlocker.stopBlocking();
        }
        
        // Notificar al icono de la bandeja
        if (trayIcon != null) {
            trayIcon.updateTrayIcon(locked);
        }
    }

    public boolean isLocked() {
        return locked;
    }
}

