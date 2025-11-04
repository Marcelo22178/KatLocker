package katlocker;

import com.github.kwhat.jnativehook.GlobalScreen;
import com.github.kwhat.jnativehook.keyboard.NativeKeyEvent;
import com.github.kwhat.jnativehook.keyboard.NativeKeyListener;

public class HotkeyListener implements NativeKeyListener {

    private final LockManager lockManager;

    public HotkeyListener(LockManager lockManager) {
        this.lockManager = lockManager;
    }

    public void startListening() {
        try {
            GlobalScreen.registerNativeHook();
            System.out.println("🟢 Escuchando tecla global: F1");
            System.out.println("💡 Presiona F1 para activar/desactivar el bloqueo");
        } catch (Exception e) {
            e.printStackTrace();
        }

        GlobalScreen.addNativeKeyListener(this);
    }

    @Override
    public void nativeKeyPressed(NativeKeyEvent e) {
        // Solo F1 para activar/desactivar
        if (e.getKeyCode() == NativeKeyEvent.VC_F1) {
            System.out.println("🎯 F1 presionada - cambiando modo bloqueo...");
            lockManager.toggleLock();
        }
    }

    @Override
    public void nativeKeyReleased(NativeKeyEvent e) {
        // No necesitamos manejar liberación de teclas para F1
    }

    @Override
    public void nativeKeyTyped(NativeKeyEvent e) {}
}
