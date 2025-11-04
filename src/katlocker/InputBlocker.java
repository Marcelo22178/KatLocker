package katlocker;

import java.awt.*;

public class InputBlocker {
    private Robot robot;
    private Thread blockerThread;
    private volatile boolean running = false;

    public InputBlocker() {
        try {
            robot = new Robot();
        } catch (AWTException e) {
            System.err.println("Error inicializando Robot: " + e.getMessage());
        }
    }

    public void startBlocking() {
        running = true;
        blockerThread = new Thread(() -> {
            System.out.println("Bloqueando teclado y mouse...");
            while (running) {
                // Fijar el cursor en una posición para "bloquear" el touchpad
                Point lockPos = MouseInfo.getPointerInfo().getLocation();
                robot.mouseMove(lockPos.x, lockPos.y);
                // Pequeño delay para no saturar CPU
                try {
                    Thread.sleep(20);
                } catch (InterruptedException ignored) {}
            }
        });
        blockerThread.start();
    }

    public void stopBlocking() {
        running = false;
        System.out.println("Entradas desbloqueadas.");
    }
}
