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
import java.awt.image.BufferedImage;
import javax.swing.*;
import javax.imageio.ImageIO;
import java.io.File;

public class InputBlocker implements NativeKeyListener, NativeMouseListener, NativeMouseMotionListener, KeyListener {
    private volatile boolean blocking = false;
    private Robot robot;
    private Thread blockerThread;
    private Point hidePoint; // Punto donde ocultar el cursor
    private JFrame blockingFrame;
    private JTextField dummyField;
    private Cursor blankCursor; // Cursor invisible

    public InputBlocker() {
        try {
            robot = new Robot();
            // Obtener dimensiones de pantalla
            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            // Punto para ocultar el cursor (esquina inferior derecha)
            hidePoint = new Point(screenSize.width - 1, screenSize.height - 1);
            
            // Crear cursor invisible
            BufferedImage cursorImg = new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB);
            blankCursor = Toolkit.getDefaultToolkit().createCustomCursor(
                cursorImg, new Point(0, 0), "blank cursor");
                
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
            System.out.println("🔑 Presiona Ctrl + Alt + L para desbloquear");
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
            // Mover el mouse a la esquina inmediatamente
            robot.mouseMove(hidePoint.x, hidePoint.y);
            
            blockerThread = new Thread(() -> {
                try {
                    while (blocking && !Thread.currentThread().isInterrupted()) {
                        // Mover el mouse a la esquina para ocultarlo
                        robot.mouseMove(hidePoint.x, hidePoint.y);
                        Thread.sleep(100); // Cada 100ms (menos frecuente)
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
            // Mover el mouse a la esquina para ocultarlo
            robot.mouseMove(hidePoint.x, hidePoint.y);
        }
    }

    @Override
    public void nativeMouseDragged(NativeMouseEvent e) {
        if (blocking && robot != null) {
            // Mover el mouse a la esquina para ocultarlo
            robot.mouseMove(hidePoint.x, hidePoint.y);
        }
    }

    // Crear ventana invisible que capture todo el input
    private void createBlockingWindow() {
        SwingUtilities.invokeLater(() -> {
            blockingFrame = new JFrame("🐱 KatLocker - Gato en Modo Guardia");
            
            // Configurar icono de la ventana usando gato.png
            Image windowIcon = loadGatoIcon();
            if (windowIcon != null) {
                blockingFrame.setIconImage(windowIcon);
            }
            
            // Hacer la ventana invisible pero activa
            blockingFrame.setUndecorated(true);
            blockingFrame.setSize(1920, 1080);
            blockingFrame.setLocation(0, 0);
            blockingFrame.setAlwaysOnTop(true);
            blockingFrame.setOpacity(0.01f); // Casi invisible
            
            // Aplicar cursor invisible si está disponible
            if (blankCursor != null) {
                blockingFrame.setCursor(blankCursor);
            }
            
            // Campo de texto dummy para capturar teclas
            dummyField = new JTextField();
            dummyField.addKeyListener(this);
            if (blankCursor != null) {
                dummyField.setCursor(blankCursor);
            }
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
            // Permitir todas las teclas ya que el desbloqueo se maneja por Ctrl+Alt+L globalmente
            // Solo bloquear para evitar que interfieran con el sistema
            e.consume(); // Bloquear la tecla completamente
            System.out.println("🚫 Tecla BLOQUEADA realmente: " + KeyEvent.getKeyText(e.getKeyCode()));
            Toolkit.getDefaultToolkit().beep();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (blocking) {
            e.consume();
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        if (blocking) {
            e.consume(); // Bloquear completamente el tipeo
        }
    }
    
    // Cargar el icono gato.png para la ventana
    private Image loadGatoIcon() {
        try {
            File iconFile = new File("gato.png");
            if (iconFile.exists()) {
                BufferedImage originalImage = ImageIO.read(iconFile);
                
                // Usar el tamaño original o escalarlo si es necesario
                // Para iconos de ventana, 32x32 o 48x48 es un buen tamaño
                int size = 32;
                if (originalImage.getWidth() <= size && originalImage.getHeight() <= size) {
                    return originalImage; // Usar tamaño original si ya es pequeño
                }
                
                // Escalar si es muy grande
                BufferedImage scaledImage = new BufferedImage(size, size, BufferedImage.TYPE_INT_ARGB);
                Graphics2D g = scaledImage.createGraphics();
                
                g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
                g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
                
                g.drawImage(originalImage, 0, 0, size, size, null);
                g.dispose();
                
                return scaledImage;
            } else {
                System.out.println("⚠️ Archivo gato.png no encontrado para icono de ventana");
                return null;
            }
        } catch (Exception e) {
            System.err.println("❌ Error cargando gato.png para ventana: " + e.getMessage());
            return null;
        }
    }
}
