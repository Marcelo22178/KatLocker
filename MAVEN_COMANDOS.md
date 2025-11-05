# Comandos Básicos de Maven para KatLocker

## 📋 Índice
- [Comandos de Compilación](#compilación)
- [Comandos de Empaquetado](#empaquetado)
- [Comandos de Revisión y Análisis](#revisión-y-análisis)
- [Comandos de Limpieza](#limpieza)
- [Comandos de Ejecución](#ejecución)
- [Comandos Avanzados](#comandos-avanzados)

---

## 🔨 Compilación

### Compilar el proyecto
```bash
mvn compile
```
*Compila el código fuente del proyecto ubicado en `src/`*

### Compilar incluyendo tests
```bash
mvn test-compile
```
*Compila tanto el código fuente como los tests*

### Compilar sin ejecutar tests
```bash
mvn compile -DskipTests
```

---

## 📦 Empaquetado

### Crear JAR básico
```bash
mvn package
```
*Crea un JAR en la carpeta `target/`*

### Crear JAR ejecutable con todas las dependencias
```bash
mvn clean package
```
*Utiliza el plugin shade para crear un JAR "fat" con todas las dependencias incluidas*

### Crear JAR y saltar tests
```bash
mvn package -DskipTests
```
*Más rápido cuando no necesitas ejecutar tests*

### Instalar en repositorio local
```bash
mvn install
```
*Instala el JAR en tu repositorio local de Maven (~/.m2/repository)*

---

## 🔍 Revisión y Análisis

### Verificar dependencias
```bash
mvn dependency:tree
```
*Muestra el árbol completo de dependencias*

### Verificar dependencias actualizables
```bash
mvn versions:display-dependency-updates
```
*Muestra qué dependencias tienen versiones más nuevas disponibles*

### Verificar plugins actualizables
```bash
mvn versions:display-plugin-updates
```

### Análizar dependencias no utilizadas
```bash
mvn dependency:analyze
```
*Detecta dependencias declaradas pero no utilizadas*

### Verificar compilación y tests
```bash
mvn verify
```
*Ejecuta todas las fases hasta verify, incluyendo tests de integración*

### Ejecutar solo tests
```bash
mvn test
```
*Ejecuta los tests unitarios*

---

## 🧹 Limpieza

### Limpiar archivos generados
```bash
mvn clean
```
*Elimina la carpeta `target/`*

### Limpieza completa y recompilación
```bash
mvn clean compile
```

### Limpieza completa y empaquetado
```bash
mvn clean package
```
*El comando más común para una compilación limpia*

---

## ▶️ Ejecución

### Ejecutar la aplicación directamente
```bash
mvn exec:java
```
*Ejecuta la clase principal (katlocker.Main) configurada en el pom.xml*

### Ejecutar con argumentos específicos
```bash
mvn exec:java -Dexec.args="--enable-native-access=ALL-UNNAMED"
```

### Ejecutar JAR compilado
```bash
java -jar target/katlocker-1.0.0.jar
```
*Después de hacer `mvn package`*

---

## 🚀 Comandos Avanzados

### Ciclo completo de desarrollo
```bash
mvn clean compile test package
```
*Limpia, compila, ejecuta tests y empaqueta*

### Generar documentación
```bash
mvn javadoc:javadoc
```
*Genera documentación JavaDoc en `target/site/apidocs/`*

### Mostrar información del proyecto
```bash
mvn help:describe -Dplugin=compiler
```
*Muestra información sobre un plugin específico*

### Mostrar propiedades efectivas
```bash
mvn help:effective-pom
```
*Muestra el POM efectivo con todas las configuraciones heredadas*

### Generar archivos de proyecto para IDE
```bash
mvn eclipse:eclipse
# o
mvn idea:idea
```

---

## 🎯 Comandos Específicos para KatLocker

### Compilación rápida para desarrollo
```bash
mvn clean compile exec:java
```
*Limpia, compila y ejecuta en un solo comando*

### Empaquetado para distribución
```bash
mvn clean package -DskipTests
```
*Crea el JAR ejecutable listo para distribución*

### Verificación completa antes de release
```bash
mvn clean verify
```
*Ejecuta todas las verificaciones incluyendo tests*

---

## 📝 Notas Importantes

1. **Versión de Java**: Este proyecto usa Java 21, asegúrate de tenerlo instalado
2. **JAR Ejecutable**: El plugin shade crea un JAR con todas las dependencias incluidas
3. **Clase Principal**: Configurada como `katlocker.Main`
4. **Dependencias**: Usa JNativeHook para captura de eventos del sistema

## 🔧 Resolución de Problemas Comunes

### Si falla la compilación:
```bash
mvn clean compile -X
```
*El flag -X proporciona salida detallada para debugging*

### Si hay problemas con dependencias:
```bash
mvn dependency:resolve
mvn dependency:purge-local-repository
```

### Para forzar actualización de dependencias:
```bash
mvn clean compile -U
```
*El flag -U fuerza la actualización de snapshots y releases*

---

*Archivo generado para el proyecto KatLocker - Noviembre 2025*