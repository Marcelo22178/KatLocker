package katlocker;

public class Main {
    public static void main(String[] args) {
        System.out.println("😼 KatLocker iniciado...");
        
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
}
