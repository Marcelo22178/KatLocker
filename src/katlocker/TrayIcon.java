package katlocker;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;

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
        MenuItem toggleItem = new MenuItem("Activar Bloqueo (F1)");
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
                                   "Presiona F1 para activar/desactivar el bloqueo\nDoble clic en el icono también funciona", 
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
                toggleItem.setLabel(locked ? "Desactivar Bloqueo (F1)" : "Activar Bloqueo (F1)");
            }
        }
    }
    
    // Crear icono de candado
    private Image createLockIcon(boolean locked) {
        int size = 16;
        BufferedImage image = new BufferedImage(size, size, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = image.createGraphics();
        
        // Activar antialiasing
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        if (locked) {
            // Candado cerrado - ROJO
            g.setColor(new Color(220, 50, 50));
            
            // Cuerpo del candado
            g.fillRoundRect(4, 8, 8, 7, 2, 2);
            
            // Arco del candado (cerrado)
            g.setStroke(new BasicStroke(2));
            g.drawArc(6, 3, 4, 6, 0, 180);
            
            // Punto de cerradura
            g.setColor(Color.WHITE);
            g.fillOval(7, 10, 2, 2);
        } else {
            // Candado abierto - VERDE
            g.setColor(new Color(50, 200, 50));
            
            // Cuerpo del candado
            g.fillRoundRect(4, 8, 8, 7, 2, 2);
            
            // Arco del candado (abierto - desplazado)
            g.setStroke(new BasicStroke(2));
            g.drawArc(6, 3, 4, 6, 0, 180);
            g.drawLine(10, 5, 13, 3);
            
            // Punto de cerradura
            g.setColor(Color.WHITE);
            g.fillOval(7, 10, 2, 2);
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
               • F1 - Activar/Desactivar bloqueo
               • Doble clic en icono - Toggle bloqueo
            
            🔒 Cuando está Bloqueado:
               • Mouse bloqueado en el centro
               • Teclado completamente bloqueado
               • Solo F1 funciona para desbloquear
            
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
