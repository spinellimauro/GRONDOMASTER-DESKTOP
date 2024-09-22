# Grondomaster

**Grondomaster** es una aplicación de escritorio diseñada para facilitar la gestión de ligas deportivas. Este documento te guiará paso a paso para generar el archivo ejecutable (`.exe`) a partir del código fuente Java utilizando **Eclipse** y **Launch4j**.

## Requisitos

Antes de comenzar, asegúrate de tener instalados los siguientes programas:

- [Eclipse](https://www.eclipse.org/downloads/) (Entorno de Desarrollo Integrado - IDE)
- [Launch4j](http://launch4j.sourceforge.net/) (Herramienta para empaquetar `.jar` en `.exe`)
- JDK (Java Development Kit) versión 1.8 o superior

---

## Pasos para generar el `.exe`

### 1. Exportar proyecto desde Eclipse

Sigue estos pasos para exportar tu proyecto desde Eclipse en formato `.jar`:

1. **Abrir Eclipse** y cargar el proyecto de **Grondomaster**.
2. Haz **clic derecho** sobre el proyecto en el **Explorador de Proyectos**.
3. Selecciona **Export** → **Java** → **Runnable JAR file**.
4. En el campo **Launch configuration**, selecciona `Liga App - LigaMaster` (la clase principal de la aplicación).
5. Marca la opción **Copy required libraries into a sub-folder**.
6. Elige la ubicación de destino para exportar el archivo `.jar`.
7. Haz clic en **Finish**.

Tu archivo `.jar` ya está exportado correctamente.

---

### 2. Convertir el `.jar` a `.exe` con Launch4j

Ahora, utilizaremos **Launch4j** para generar el archivo `.exe` a partir del `.jar` exportado:

1. **Abrir Launch4j**.
2. Ve a la pestaña **Basic**:
   - En **Output file**, selecciona la ubicación donde quieres guardar el archivo `.exe`.
   - En **Jar**, selecciona el archivo `.jar` que exportaste desde Eclipse.
3. Ve a la pestaña **JRE**:
   - En **Min JRE version**, introduce `1.8.0`.
   - En la opción **Bundled JRE Path**, elige **First 64 bits, then 32 bits**.
   - En **Jvm options**, añade lo siguiente:
     ```bash
     -Djava.system.class.loader=org.uqbar.apo.APOClassLoader
     -Dfile.encoding=UTF8
     ```
4. Haz clic en **Build** para generar el archivo `.exe`.

---

## Consideraciones adicionales

- Asegúrate de que la **versión mínima de JRE** esté configurada correctamente en Launch4j para evitar problemas de compatibilidad.
- Los **Jvm options** añadidos son importantes para la correcta configuración del **ClassLoader** y la codificación de archivos.

---

## Créditos

Desarrollado por el equipo de **Grondomaster**.

---


