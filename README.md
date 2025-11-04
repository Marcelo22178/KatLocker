# 🐱 KatLocker v1.0.0

## 🎉 Primera Release Oficial

**KatLocker** es una aplicación multiplataforma que bloquea completamente el teclado y el mouse para proteger tu computadora de las adorables pero traviesas patas de tu gato (o cualquier otra mascota).

---

## ✨ Características Principales

- 🔒 **Bloqueo Total Real** - Bloquea completamente teclado y mouse (no solo visual)
- 🎯 **Activación Simple** - Presiona `F1` para activar/desactivar
- 🖼️ **Interfaz Gráfica** - Icono intuitivo en la bandeja del sistema
- 🎨 **Indicadores Visuales** - Verde (desbloqueado) / Rojo (bloqueado)
- 🌍 **Multiplataforma** - Windows, macOS y Linux
- 🚀 **Portable** - Un solo archivo JAR, sin instalación
- ☕ **Java 21 LTS** - Tecnología moderna y estable

---

## 📥 Instalación

### Windows
1. Descarga `katlocker-1.0.0.jar`
2. Descarga `run-katlocker.bat`
3. Colócalos en la misma carpeta
4. Doble clic en `run-katlocker.bat`

### Linux / macOS
1. Descarga `katlocker-1.0.0.jar`
2. Descarga `run-katlocker.sh`
3. Colócalos en la misma carpeta
4. Dale permisos de ejecución: `chmod +x run-katlocker.sh`
5. Ejecuta: `./run-katlocker.sh`

### Ejecución Manual
```bash
java -jar katlocker-1.0.0.jar
```

---

## 🎮 Uso

### Activar/Desactivar Bloqueo
- **Método 1:** Presiona `F1`
- **Método 2:** Doble clic en el icono de la bandeja

### Menú del Icono (Clic Derecho)
- Ver estado actual
- Activar/Desactivar bloqueo
- Ver ayuda
- Salir

### Cuando está Bloqueado 🔒
- ❌ Teclado completamente bloqueado
- ❌ Mouse bloqueado en el centro
- ❌ Clics deshabilitados
- ✅ Solo F1 funciona para desbloquear
- 🔊 Sonido de alerta al presionar teclas

---

## 📋 Requisitos

- **Java Runtime:** Java 21 LTS o superior
  - Descarga: https://adoptium.net/
- **Sistema Operativo:** Windows 10/11, macOS 10.14+, Linux (cualquier distribución moderna)
- **Permisos:** Acceso a hooks globales del sistema

---

## 🔧 Tecnologías

- ☕ Java 21 LTS
- 🎣 JNativeHook 2.2.2 (hooks globales)
- 🖼️ Java Swing/AWT (interfaz gráfica)
- 🤖 Robot Class (control de hardware)
- 📦 Maven (gestión de dependencias)

---

## 📦 Archivos Incluidos

| Archivo | Descripción |
|---------|-------------|
| `katlocker-1.0.0.jar` | Ejecutable principal (incluye todas las dependencias) |
| `run-katlocker.bat` | Launcher para Windows |
| `run-katlocker.sh` | Launcher para Linux/macOS |
| `GUIA_USO.md` | Guía completa en español |

---

## 🐛 Problemas Conocidos

- En algunos sistemas Linux, puede requerir permisos adicionales de accesibilidad
- Los warnings de Java sobre "restricted methods" son normales y no afectan la funcionalidad
- En macOS, puede requerir permisos de accesibilidad en la primera ejecución

---

## 📝 Changelog

### v1.0.0 (2025-11-04)
- ✨ Release inicial
- ✅ Bloqueo completo de teclado y mouse
- ✅ Interfaz gráfica con System Tray
- ✅ Activación con F1
- ✅ Indicadores visuales de estado
- ✅ Soporte multiplataforma
- ✅ Notificaciones y menú contextual
- ✅ Documentación completa

---

## 🚀 Próximas Mejoras (Roadmap)

- [ ] Configuración de hotkey personalizado
- [ ] Modo temporizador (bloqueo automático después de X minutos)
- [ ] Whitelist de aplicaciones
- [ ] Tema oscuro/claro para el icono
- [ ] Estadísticas de uso

---

## 🤝 Contribuir

¿Encontraste un bug? ¿Tienes una sugerencia? 

1. Abre un [Issue](https://github.com/Marcelo22178/KatLocker/issues)
2. Envía un [Pull Request](https://github.com/Marcelo22178/KatLocker/pulls)

---

## 📄 Licencia

Este proyecto está bajo la licencia MIT. Ver archivo [LICENSE](LICENSE) para más detalles.

---

## ❤️ Agradecimientos

Gracias a todos los dueños de gatos que entienden la lucha de proteger su trabajo de sus adorables pero traviesas mascotas.

**¿Te gusta KatLocker?** ⭐ Dale una estrella en GitHub y comparte con otros dueños de gatos!

---

## 📸 Screenshots

_(El icono aparece en la bandeja del sistema con indicadores verde/rojo según el estado)_

**Estado Desbloqueado:**
- 🟢 Icono verde en la bandeja
- Mouse y teclado funcionan normalmente

**Estado Bloqueado:**
- 🔴 Icono rojo en la bandeja
- Mouse centrado y bloqueado
- Teclado completamente bloqueado
- Solo F1 funciona

---

**Descarga los archivos y protege tu trabajo hoy mismo!** 🐱💻


![Imagen de WhatsApp 2025-11-04 a las 11 50 27_09ae784e](https://github.com/user-attachments/assets/0e1b1813-b17e-4cdc-bb73-72e240d9b6bf)

