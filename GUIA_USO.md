# 🐱 KatLocker - Guía de Uso

## 🚀 Cómo Ejecutar

### Método 1: Doble clic en KatLocker.bat (MÁS FÁCIL)
1. Busca el archivo `KatLocker.bat` en la carpeta
2. Haz doble clic en él
3. ¡Listo! La aplicación se ejecutará en segundo plano

### Método 2: Línea de comandos
```bash
java -jar target\katlocker-1.0.0.jar
```

### Método 3: Sin ventana de consola
```bash
javaw -jar target\katlocker-1.0.0.jar
```

## 🎯 Interfaz Gráfica

### Icono en la Bandeja del Sistema
Al iniciar KatLocker, verás un icono de candado en la bandeja del sistema (System Tray):

- **🟢 Candado Verde** = Desbloqueado (normal)
- **🔴 Candado Rojo** = Bloqueado (modo gato activado)

### Menú del Icono (Clic Derecho)
- **Estado actual** - Muestra si está bloqueado o desbloqueado
- **Activar/Desactivar Bloqueo** - Toggle del modo bloqueo
- **ℹ️ Ayuda** - Muestra información de uso
- **❌ Salir** - Cierra la aplicación

### Acciones Rápidas
- **Doble clic** en el icono = Activar/Desactivar bloqueo
- **Presionar Ctrl+Alt+L** = Activar/Desactivar bloqueo

## 🔒 Modo Bloqueado

Cuando activas el bloqueo (F1 o doble clic):

### ¿Qué se bloquea?
- ✅ **TODO el teclado** - No puedes escribir nada
- ✅ **TODO el mouse** - Se centra y no puedes moverlo
- ✅ **Clics del mouse** - No funcionan
- ⚠️ **EXCEPTO F1** - Para poder desbloquear

### Indicadores Visuales
1. El icono cambia a **rojo** 🔴
2. El tooltip dice "KatLocker - 🔒 Bloqueado"
3. El mouse se mueve al centro de la pantalla
4. Al presionar teclas, escuchas un sonido de alerta 🔊

### Para Desbloquear
Simplemente presiona **F1** de nuevo o haz doble clic en el icono

## 💡 Consejos de Uso

1. **Prueba primero** - Activa el bloqueo y verifica que funciona
2. **Recuerda F1** - Es la única forma de desbloquear
3. **Icono visible** - Asegúrate de ver el icono en la bandeja
4. **Gatos traviesos** - Perfecto para cuando tu gato salta al teclado

## 🎮 Ejemplos de Uso

### Escenario 1: Vas al baño
1. Presiona **F1** antes de levantarte
2. Tu gato puede saltar al teclado sin problemas
3. Al regresar, presiona **F1** para desbloquear

### Escenario 2: Contestas el teléfono
1. Doble clic en el icono de la bandeja
2. Hablas tranquilo sin preocuparte por tu gato
3. Terminas la llamada, doble clic de nuevo

### Escenario 3:Break/Descanso
1. **F1** para bloquear
2. Te alejas de la PC
3. Regresas y **F1** para continuar

## ⚠️ Importante

- Si olvidas que está bloqueado, **solo presiona F1**
- El bloqueo es REAL, no solo visual
- No uses esto en computadoras compartidas sin avisar
- Recuerda: **F1 = Desbloquear**

## 🐛 Problemas Comunes

### "No veo el icono"
- Mira en los iconos ocultos de la bandeja (flecha hacia arriba)
- En Windows 10/11: Configuración → Personalización → Barra de tareas

### "F1 no funciona"
- Verifica que la aplicación esté corriendo (busca el icono)
- Cierra y vuelve a abrir la aplicación
- Verifica la consola para mensajes de error

### "El icono desapareció"
- La aplicación puede haberse cerrado
- Ejecuta `KatLocker.bat` de nuevo

## 📞 Ayuda Adicional

Desde el menú del icono, selecciona **"ℹ️ Ayuda"** para ver:
- Atajos de teclado
- Estado del bloqueo
- Consejos de uso
- Información de versión

---

¡Disfruta de KatLocker y protege tu trabajo de las adorables patas de tu gato! 🐱💻
