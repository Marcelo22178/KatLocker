package katlocker;

import com.github.kwhat.jnativehook.GlobalScreen;
import com.github.kwhat.jnativehook.keyboard.NativeKeyEvent;
import com.github.kwhat.jnativehook.keyboard.NativeKeyListener;
import com.github.kwhat.jnativehook.mouse.NativeMouseEvent;
import com.github.kwhat.jnativehook.mouse.NativeMouseListener;
import com.github.kwhat.jnativehook.mouse.NativeMouseMotionListener;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.*;

public class InputBlocker implements NativeKeyListener, NativeMouseListener, NativeMouseMotionListener, KeyListener {
    private volatile boolean blocking = false;
    private Robot robot;
    private Thread blockerThread;
    private Point centerPoint;
    private JFrame blockingFrame;
    private JTextField dummyField;

    public InputBlocker() {
        try {
            robot = new Robot();
            // Obtener el centro de la pantalla para "anclar" el mouse
            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            centerPoint = new Point(screenSize.width / 2, screenSize.height / 2);
        } catch (AWTException e) {
            System.err.println("⚠️ Error inicializando Robot: " + e.getMessage());
            System.err.println("El bloqueo de mouse puede no funcionar correctamente.");
        }
    }

    public void startBlocking() {
        if (!blocking) {
            blocking = true;
            GlobalScreen.addNativeKeyListener(this);    
            GlobalScreen.addNativeMouseListener(this);
            GlobalScreen.addNativeMouseMotionListener(this);
            
            // Crear ventana invisible para capturar input
            createBlockingWindow();
            
            // Iniciar thread para bloqueo activo del mouse
            startMouseBlocker();
            
            System.out.println("🔒 MODO GATO ACTIVADO");
            System.out.println("📱 Teclado y mouse bloqueados REALMENTE");
            System.out.println("🔑 Presiona F1 para desbloquear");
        }
    }

    public void stopBlocking() {
        if (blocking) {
            blocking = false;
            GlobalScreen.removeNativeKeyListener(this);
            GlobalScreen.removeNativeMouseListener(this);
            GlobalScreen.removeNativeMouseMotionListener(this);
            
            // Cerrar ventana de bloqueo
            if (blockingFrame != null) {
                blockingFrame.setVisible(false);
                blockingFrame.dispose();
                blockingFrame = null;
            }
            
            // Detener el thread de bloqueo del mouse
            if (blockerThread != null) {
                blockerThread.interrupt();
            }
            
            System.out.println("🔓 MODO GATO DESACTIVADO");
            System.out.println("✅ Teclado y mouse liberados");
        }
    }

    private void startMouseBlocker() {
        if (robot != null) {
            blockerThread = new Thread(() -> {
                try {
                    while (blocking && !Thread.currentThread().isInterrupted()) {
                        // Mover constantemente el mouse al centro para "bloquearlo"
                        robot.mouseMove(centerPoint.x, centerPoint.y);
                        Thread.sleep(50); // Cada 50ms
                    }
                } catch (InterruptedException e) {
                    // Thread interrumpido, salir silenciosamente
                }
            });
            blockerThread.setDaemon(true);
            blockerThread.start();
        }
    }

    public boolean isBlocking() {
        return blocking;
    }

    private int keyBlockCount = 0;
    private int mouseBlockCount = 0;

    // Interceptar todas las teclas y mostrar que están siendo bloqueadas
    @Override
    public void nativeKeyPressed(NativeKeyEvent e) {
        if (blocking) {
            keyBlockCount++;
            // Solo mostrar cada 5 teclas para no spam
            if (keyBlockCount % 5 == 1) {
                System.out.println("🚫 [" + keyBlockCount + "] Tecla bloqueada: " + 
                    NativeKeyEvent.getKeyText(e.getKeyCode()));
            }
            
            // Generar un beep para indicar bloqueo
            if (robot != null) {
                try {
                    Toolkit.getDefaultToolkit().beep();
                } catch (Exception ex) {
                    // Ignorar si no se puede hacer beep
                }
            }
        }
    }

    @Override
    public void nativeKeyReleased(NativeKeyEvent e) {
        if (blocking) {
            // Solo contar, no hacer nada más para evitar spam
        }
    }

    @Override
    public void nativeKeyTyped(NativeKeyEvent e) {
        if (blocking) {
            // Interceptar eventos de tipeo
        }
    }

    // Interceptar y bloquear clics del mouse
    @Override
    public void nativeMouseClicked(NativeMouseEvent e) {
        if (blocking) {
            mouseBlockCount++;
            if (mouseBlockCount % 3 == 1) {
                System.out.println("🚫 [" + mouseBlockCount + "] Clic bloqueado");
            }
            
            // Beep para clic bloqueado
            try {
                Toolkit.getDefaultToolkit().beep();
            } catch (Exception ex) {
                // Ignorar
            }
        }
    }

    @Override
    public void nativeMousePressed(NativeMouseEvent e) {
        if (blocking) {
            // Interceptar presión de botones
        }
    }

    @Override
    public void nativeMouseReleased(NativeMouseEvent e) {
        if (blocking) {
            // Interceptar liberación de botones
        }
    }

    // El movimiento del mouse se maneja por el thread separado
    @Override
    public void nativeMouseMoved(NativeMouseEvent e) {
        if (blocking && robot != null) {
            // Forzar el mouse de vuelta al centro inmediatamente
            robot.mouseMove(centerPoint.x, centerPoint.y);
        }
    }

    @Override
    public void nativeMouseDragged(NativeMouseEvent e) {
        if (blocking && robot != null) {
            // Forzar el mouse de vuelta al centro
            robot.mouseMove(centerPoint.x, centerPoint.y);
        }
    }

    // Crear ventana invisible que capture todo el input
    private void createBlockingWindow() {
        SwingUtilities.invokeLater(() -> {
            blockingFrame = new JFrame("KatLocker Block");
            
            // Hacer la ventana invisible pero activa
            blockingFrame.setUndecorated(true);
            blockingFrame.setSize(1920, 1080);
            blockingFrame.setLocation(0, 0);
            blockingFrame.setAlwaysOnTop(true);
            blockingFrame.setOpacity(0.01f); // Casi invisible
            
            // Campo de texto dummy para capturar teclas
            dummyField = new JTextField();
            dummyField.addKeyListener(this);
            blockingFrame.add(dummyField);
            
            blockingFrame.setVisible(true);
            blockingFrame.setFocusableWindowState(true);
            dummyField.requestFocus();
            
            // Timer para mantener el foco constantemente
            Timer focusTimer = new Timer(100, e -> {
                if (blocking && blockingFrame != null) {
                    blockingFrame.toFront();
                    dummyField.requestFocus();
                }
            });
            focusTimer.start();
        });
    }

    // Implementar KeyListener para capturar y bloquear teclas realmente
    @Override
    public void keyPressed(KeyEvent e) {
        if (blocking) {
            // Solo permitir F1 para desbloquear
            if (e.getKeyCode() != KeyEvent.VK_F1) {
                e.consume(); // Bloquear la tecla completamente
                System.out.println("🚫 Tecla BLOQUEADA realmente: " + KeyEvent.getKeyText(e.getKeyCode()));
                Toolkit.getDefaultToolkit().beep();
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (blocking && e.getKeyCode() != KeyEvent.VK_F1) {
            e.consume();
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        if (blocking) {
            e.consume(); // Bloquear completamente el tipeo
        }
    }
}
