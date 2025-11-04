package katlocker;

import java.awt.*;
import java.awt.event.KeyEvent;
import javax.swing.*;

public class HotkeyListener {
    private final LockManager lockManager;

    public HotkeyListener(LockManager lockManager) {
        this.lockManager = lockManager;
    }

    public void startListening() {
        setupTrayIcon();

        JOptionPane.showMessageDialog(null,
            "KatLock ejecutándose.\nPresiona CTRL + ALT + K para activar/desactivar el bloqueo.",
            "KatLock", JOptionPane.INFORMATION_MESSAGE);

        KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(e -> {
            if (e.getID() == KeyEvent.KEY_PRESSED &&
                e.isControlDown() && e.isAltDown() && e.getKeyCode() == KeyEvent.VK_K) {
                lockManager.toggleLock();
            }
            return false;
        });

        while (true) {
            try { Thread.sleep(1000); } catch (InterruptedException ignored) {}
        }
    }

    private void setupTrayIcon() {
        if (!SystemTray.isSupported()) return;
        try {
            SystemTray tray = SystemTray.getSystemTray();
            TrayIcon icon = new TrayIcon(
                Toolkit.getDefaultToolkit().createImage(""),
                "KatLock - Protección anti gatos 😼"
            );
            icon.setImageAutoSize(true);
            tray.add(icon);
        } catch (Exception e) {
            System.err.println("No se pudo agregar al área de notificación.");
        }
    }
}
