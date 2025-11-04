package katlocker;

public class Main {
      public static void main(String[] args) {
        System.out.println("😼 KatLock iniciado (modo universal)...");
        LockManager manager = new LockManager();
        HotkeyListener listener = new HotkeyListener(manager);
        listener.startListening();
    }
}
