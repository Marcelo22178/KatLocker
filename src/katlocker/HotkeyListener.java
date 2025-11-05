package katlocker;

import com.github.kwhat.jnativehook.GlobalScreen;
import com.github.kwhat.jnativehook.keyboard.NativeKeyEvent;
import com.github.kwhat.jnativehook.keyboard.NativeKeyListener;

public class HotkeyListener implements NativeKeyListener {

    private final LockManager lockManager;
    private boolean ctrlPressed = false;
    private boolean altPressed = false;

    public HotkeyListener(LockManager lockManager) {
        this.lockManager = lockManager;
    }

    public void startListening() {
        try {
            GlobalScreen.registerNativeHook();
            System.out.println("🟢 Escuchando combinación global: Ctrl + Alt + L");
            System.out.println("💡 Presiona Ctrl + Alt + L para activar/desactivar el bloqueo");
        } catch (Exception e) {
            e.printStackTrace();
        }

        GlobalScreen.addNativeKeyListener(this);
    }

    @Override
    public void nativeKeyPressed(NativeKeyEvent e) {
        int keyCode = e.getKeyCode();

        // Registrar teclas modificadoras
        if (keyCode == NativeKeyEvent.VC_CONTROL) {
            ctrlPressed = true;
        } else if (keyCode == NativeKeyEvent.VC_ALT) {
            altPressed = true;
        }

        // Detectar combinación: Ctrl + Alt + L
        if (ctrlPressed && altPressed && keyCode == NativeKeyEvent.VC_L) {
            System.out.println("🎯 Ctrl + Alt + L presionada - cambiando modo bloqueo...");
            lockManager.toggleLock();
        }
    }

    @Override
    public void nativeKeyReleased(NativeKeyEvent e) {
        int keyCode = e.getKeyCode();

        // Actualizar estado de teclas modificadoras
        if (keyCode == NativeKeyEvent.VC_CONTROL) {
            ctrlPressed = false;
        } else if (keyCode == NativeKeyEvent.VC_ALT) {
            altPressed = false;
        }
    }

    @Override
    public void nativeKeyTyped(NativeKeyEvent e) {
        // No se utiliza
    }
}
