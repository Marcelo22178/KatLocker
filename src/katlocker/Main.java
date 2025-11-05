package katlocker;

import javax.swing.*;
import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;

public class Main {
    public static void main(String[] args) {
        System.out.println("� KatLocker iniciado...");
        
        // Configurar el icono por defecto de la aplicación Java
        setApplicationIcon();
        
        // Crear el administrador de bloqueo
        LockManager manager = new LockManager();
        
        // Crear e inicializar el icono de la bandeja
        TrayIcon trayIcon = new TrayIcon(manager);
        manager.setTrayIcon(trayIcon);
        trayIcon.initialize();
        
        // Iniciar el listener de teclas globales
        HotkeyListener listener = new HotkeyListener(manager);
        listener.startListening();
        
        System.out.println("✅ KatLocker listo - Revisa el icono en la bandeja del sistema");
    }
    
    // Configurar el icono por defecto de todas las ventanas de la aplicación
    private static void setApplicationIcon() {
        try {
            File iconFile = new File("gato.png");
            if (iconFile.exists()) {
                Image gatoIcon = ImageIO.read(iconFile);
                
                // En sistemas más modernos de Java, se puede configurar el icono de la aplicación
                if (Taskbar.isTaskbarSupported()) {
                    Taskbar taskbar = Taskbar.getTaskbar();
                    if (taskbar.isSupported(Taskbar.Feature.ICON_IMAGE)) {
                        taskbar.setIconImage(gatoIcon);
                        System.out.println("🐱 Icono del gato configurado en la barra de tareas");
                    }
                }
                
                // También configurar para futuras ventanas
                System.setProperty("java.awt.Window.locationByPlatform", "true");
                
                System.out.println("🖼️ Icono de gato.png cargado como icono principal de la aplicación");
            } else {
                System.out.println("⚠️ Archivo gato.png no encontrado - usando iconos por defecto");
            }
        } catch (Exception e) {
            System.err.println("❌ Error configurando icono de aplicación: " + e.getMessage());
        }
    }
}
