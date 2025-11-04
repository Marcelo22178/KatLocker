# 📦 Guía para Publicar Release en GitHub

## 🚀 Pasos para Crear un Release en GitHub

### 1️⃣ Preparar los Archivos

Ya tienes todo listo en la carpeta `release/`:
- ✅ `katlocker-1.0.0.jar` - Ejecutable principal
- ✅ `run-katlocker.bat` - Launcher Windows
- ✅ `run-katlocker.sh` - Launcher Linux/macOS
- ✅ `README.md` - Documentación
- ✅ `GUIA_USO.md` - Guía en español
- ✅ `RELEASE_NOTES.md` - Notas del release
- ✅ `LICENSE` - Licencia

**También tienes:** `KatLocker-v1.0.0.zip` - Todo en un archivo ZIP

---

### 2️⃣ Subir Código a GitHub (si no lo has hecho)

```bash
# Inicializar repositorio Git (si no existe)
git init

# Agregar archivos
git add .

# Commit inicial
git commit -m "Initial release v1.0.0"

# Crear repositorio en GitHub y conectar
git remote add origin https://github.com/Marcelo22178/KatLocker.git

# Subir código
git branch -M main
git push -u origin main
```

---

### 3️⃣ Crear Release en GitHub

#### Opción A: Desde la Web (Recomendado)

1. **Ve a tu repositorio:**
   - https://github.com/Marcelo22178/KatLocker

2. **Clic en "Releases"** (en el menú derecho)

3. **Clic en "Create a new release"**

4. **Configurar el Release:**
   - **Tag version:** `v1.0.0`
   - **Release title:** `🐱 KatLocker v1.0.0 - Primera Release`
   - **Description:** Copia el contenido de `RELEASE_NOTES.md`

5. **Subir Archivos:**
   
   Arrastra y suelta estos archivos en "Attach binaries":
   - `KatLocker-v1.0.0.zip` (PRINCIPAL - contiene todo)
   - `katlocker-1.0.0.jar` (para descarga individual)
   - `run-katlocker.bat` (para descarga individual)
   - `run-katlocker.sh` (para descarga individual)

6. **Opciones:**
   - ✅ Marca "Set as the latest release"
   - (Opcional) Marca "Create a discussion for this release"

7. **Clic en "Publish release"**

#### Opción B: Desde la Línea de Comandos (con GitHub CLI)

```bash
# Instalar GitHub CLI si no lo tienes
# https://cli.github.com/

# Crear release
gh release create v1.0.0 \
  --title "🐱 KatLocker v1.0.0 - Primera Release" \
  --notes-file RELEASE_NOTES.md \
  KatLocker-v1.0.0.zip \
  release/katlocker-1.0.0.jar \
  release/run-katlocker.bat \
  release/run-katlocker.sh
```

---

### 4️⃣ Verificar el Release

1. **Ve a:** https://github.com/Marcelo22178/KatLocker/releases
2. **Verifica que aparezca:** v1.0.0
3. **Prueba descargar** los archivos
4. **Comparte el link:** 
   - Directo al release: `https://github.com/Marcelo22178/KatLocker/releases/tag/v1.0.0`
   - Última versión: `https://github.com/Marcelo22178/KatLocker/releases/latest`

---

### 5️⃣ Promocionar tu Release

#### Actualizar README.md

Agrega un badge al inicio del README:

```markdown
[![Latest Release](https://img.shields.io/github/v/release/Marcelo22178/KatLocker)](https://github.com/Marcelo22178/KatLocker/releases/latest)
[![Downloads](https://img.shields.io/github/downloads/Marcelo22178/KatLocker/total)](https://github.com/Marcelo22178/KatLocker/releases)
```

#### Crear un Post

Puedes compartir en:
- Twitter/X
- Reddit (r/java, r/cats)
- Dev.to
- LinkedIn

Ejemplo de mensaje:
```
🐱 Acabo de lanzar KatLocker v1.0.0!

Una app Java que bloquea tu teclado y mouse para proteger 
tu trabajo de gatos traviesos.

✨ Características:
- Bloqueo real con F1
- Icono en System Tray
- Multiplataforma (Windows/Mac/Linux)
- Java 21 LTS

🔗 https://github.com/Marcelo22178/KatLocker

#Java #OpenSource #Cats
```

---

## 📋 Checklist de Publicación

Antes de publicar, verifica:

- [ ] Todo el código compiló sin errores
- [ ] El JAR funciona correctamente
- [ ] Los scripts de launcher funcionan
- [ ] La documentación está actualizada
- [ ] LICENSE está incluido
- [ ] README está completo
- [ ] RELEASE_NOTES tiene toda la info
- [ ] Has probado en tu sistema operativo
- [ ] El archivo ZIP contiene todo lo necesario

---

## 🎯 URLs Importantes

Después de publicar, tendrás:

- **Repositorio:** `https://github.com/Marcelo22178/KatLocker`
- **Releases:** `https://github.com/Marcelo22178/KatLocker/releases`
- **Última versión:** `https://github.com/Marcelo22178/KatLocker/releases/latest`
- **Download ZIP:** `https://github.com/Marcelo22178/KatLocker/releases/download/v1.0.0/KatLocker-v1.0.0.zip`

---

## 🔄 Para Futuras Actualizaciones

Cuando quieras publicar v1.1.0:

1. Actualiza la versión en `pom.xml`
2. Ejecuta `build-release.bat`
3. Actualiza `RELEASE_NOTES.md`
4. Commit y push cambios
5. Crea nuevo release con tag `v1.1.0`

---

## 💡 Consejos

1. **Usa Semantic Versioning:** v1.0.0, v1.1.0, v2.0.0
2. **Escribe buenos release notes:** Explica qué cambió
3. **Incluye screenshots:** Ayuda a los usuarios
4. **Responde issues:** Mantén tu comunidad activa
5. **Celebra:** ¡Tu primera release es especial! 🎉

---

¡Listo para publicar! 🚀
