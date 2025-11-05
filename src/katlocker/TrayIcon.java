package katlocker;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;

public class TrayIcon {
    
    private final LockManager lockManager;
    private java.awt.TrayIcon trayIcon;
    private boolean isLocked = false;
    
    public TrayIcon(LockManager lockManager) {
        this.lockManager = lockManager;
    }
    
    public void initialize() {
        // Verificar si el sistema soporta SystemTray
        if (!SystemTray.isSupported()) {
            System.err.println("❌ SystemTray no está soportado en este sistema");
            return;
        }
        
        try {
            // Configurar look and feel del sistema
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        // Crear el icono en la bandeja
        SwingUtilities.invokeLater(() -> {
            createTrayIcon();
        });
    }
    
    private void createTrayIcon() {
        SystemTray tray = SystemTray.getSystemTray();
        
        // Crear imagen para el icono (un candado simple)
        Image image = createLockIcon(false);
        
        // Crear menú popup
        PopupMenu popup = new PopupMenu();
        
        // Item de estado
        MenuItem statusItem = new MenuItem("🔓 Desbloqueado");
        statusItem.setEnabled(false);
        popup.add(statusItem);
        
        popup.addSeparator();
        
        // Item para activar/desactivar
        MenuItem toggleItem = new MenuItem("Activar Bloqueo (Ctrl+Alt+L)");
        toggleItem.addActionListener(e -> {
            lockManager.toggleLock();
            updateTrayIcon(!isLocked);
        });
        popup.add(toggleItem);
        
        popup.addSeparator();
        
        // Item de ayuda
        MenuItem helpItem = new MenuItem("ℹ️ Ayuda");
        helpItem.addActionListener(e -> showHelpDialog());
        popup.add(helpItem);
        
        // Item para salir
        MenuItem exitItem = new MenuItem("❌ Salir");
        exitItem.addActionListener(e -> {
            System.out.println("👋 Cerrando KatLocker...");
            System.exit(0);
        });
        popup.add(exitItem);
        
        // Crear el TrayIcon
        trayIcon = new java.awt.TrayIcon(image, "KatLocker - Desbloqueado", popup);
        trayIcon.setImageAutoSize(true);
        
        // Acción al hacer doble clic en el icono
        trayIcon.addActionListener(e -> {
            lockManager.toggleLock();
            updateTrayIcon(!isLocked);
        });
        
        // Agregar el icono a la bandeja
        try {
            tray.add(trayIcon);
            trayIcon.displayMessage("KatLocker Iniciado", 
                                   "Presiona Ctrl+Alt+L para activar/desactivar el bloqueo\nDoble clic en el icono también funciona", 
                                   java.awt.TrayIcon.MessageType.INFO);
        } catch (AWTException e) {
            System.err.println("❌ Error al agregar icono a la bandeja: " + e.getMessage());
        }
    }
    
    // Actualizar el icono según el estado
    public void updateTrayIcon(boolean locked) {
        if (trayIcon == null) return;
        
        isLocked = locked;
        Image image = createLockIcon(locked);
        trayIcon.setImage(image);
        
        String status = locked ? "🔒 Bloqueado" : "🔓 Desbloqueado";
        trayIcon.setToolTip("KatLocker - " + status);
        
        // Actualizar menú
        PopupMenu popup = trayIcon.getPopupMenu();
        if (popup != null && popup.getItemCount() > 0) {
            MenuItem statusItem = popup.getItem(0);
            statusItem.setLabel(status);
            
            if (popup.getItemCount() > 2) {
                MenuItem toggleItem = popup.getItem(2);
                toggleItem.setLabel(locked ? "Desactivar Bloqueo (Ctrl+Alt+L)" : "Activar Bloqueo (Ctrl+Alt+L)");
            }
        }
    }
    
    // Crear icono usando gato.png
    private Image createLockIcon(boolean locked) {
        try {
            // Cargar el icono gato.png desde el directorio raíz del proyecto
            File iconFile = new File("gato.png");
            if (iconFile.exists()) {
                BufferedImage originalImage = ImageIO.read(iconFile);
                
                // Redimensionar para el tray (16x16 píxeles)
                int size = 16;
                BufferedImage scaledImage = new BufferedImage(size, size, BufferedImage.TYPE_INT_ARGB);
                Graphics2D g = scaledImage.createGraphics();
                
                // Configurar para mejor calidad de escalado
                g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
                g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
                
                // Dibujar la imagen escalada
                g.drawImage(originalImage, 0, 0, size, size, null);
                
                // Aplicar un filtro de color según el estado
                if (locked) {
                    // Aplicar un tinte rojizo sutil cuando está bloqueado
                    g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3f));
                    g.setColor(new Color(255, 0, 0, 100));
                    g.fillRect(0, 0, size, size);
                } else {
                    // Aplicar un tinte verdoso sutil cuando está desbloqueado
                    g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.2f));
                    g.setColor(new Color(0, 255, 0, 80));
                    g.fillRect(0, 0, size, size);
                }
                
                g.dispose();
                return scaledImage;
            } else {
                System.out.println("⚠️ Archivo gato.png no encontrado, usando icono por defecto");
                return createDefaultIcon(locked);
            }
        } catch (Exception e) {
            System.err.println("❌ Error cargando gato.png: " + e.getMessage());
            return createDefaultIcon(locked);
        }
    }
    
    // Icono de respaldo si no se puede cargar gato.png
    private Image createDefaultIcon(boolean locked) {
        int size = 16;
        BufferedImage image = new BufferedImage(size, size, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = image.createGraphics();
        
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        if (locked) {
            // Estado bloqueado - Punto rojo discreto
            g.setColor(new Color(200, 60, 60, 200));
            g.fillOval(5, 5, 6, 6);
            g.setColor(new Color(150, 40, 40, 255));
            g.setStroke(new BasicStroke(1));
            g.drawOval(5, 5, 6, 6);
            g.setColor(new Color(255, 255, 255, 150));
            g.fillOval(7, 7, 2, 2);
        } else {
            // Estado desbloqueado - Punto verde discreto
            g.setColor(new Color(60, 180, 60, 180));
            g.fillOval(5, 5, 6, 6);
            g.setColor(new Color(40, 130, 40, 255));
            g.setStroke(new BasicStroke(1));
            g.drawOval(5, 5, 6, 6);
            g.setColor(new Color(255, 255, 255, 150));
            g.fillOval(7, 7, 2, 2);
        }
        
        g.dispose();
        return image;
    }
    
    // Mostrar diálogo de ayuda
    private void showHelpDialog() {
        String message = """
            🐱 KatLocker - Bloqueo de Teclado y Mouse
            
            ━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
            
            🔑 Atajos de Teclado:
               • Ctrl+Alt+L - Activar/Desactivar bloqueo
               • Doble clic en icono - Toggle bloqueo
            
            🔒 Cuando está Bloqueado:
               • Mouse bloqueado en esquina
               • Teclado completamente bloqueado
               • Solo Ctrl+Alt+L funciona para desbloquear
            
            💡 Consejos:
               • El icono cambia de color según el estado
               • Verde = Desbloqueado
               • Rojo = Bloqueado
            
            ━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
            
            Versión 1.0.0 | Java 21 LTS
            """;
        
        JOptionPane.showMessageDialog(null, 
                                     message, 
                                     "KatLocker - Ayuda", 
                                     JOptionPane.INFORMATION_MESSAGE);
    }
}
