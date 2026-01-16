# 🎭 Playwright Automation - Prueba Tecnica Playwright - Diego Reyes

Este proyecto contiene la automatización de pruebas para el formulario de registro de **DemoQA**, desarrollada en **Java** utilizando **Playwright** y siguiendo el patrón de diseño **Page Object Model (POM)**.

## 🛠️ Tecnologías Utilizadas

* **Lenguaje:** Java 11
* **Framework de Automatización:** Playwright
* **Gestor de Dependencias:** Maven
* **Reportes:** Allure Report
* **Generación de Datos:** JavaFaker
* **Framework de Pruebas:** JUnit 5

---

## 📋 Estrategia de Pruebas y Justificación

Para este proyecto se aplicaron técnicas de diseño de **Caja Negra** con el fin de maximizar la cobertura de riesgos con el menor número de escenarios:

1.  **Análisis de Valores Límite (BVA):** Se aplicó en el campo `Mobile Number`. El sistema requiere exactamente 10 dígitos. Se diseñaron pruebas para validar que 9 dígitos (límite inferior - 1) fallen y 10 dígitos funcionen.
2.  **Partición de Equivalencia:** Se utilizó en el campo `Email` para validar formatos correctos vs. incorrectos, y en los campos obligatorios para asegurar que el formulario no procese registros nulos.
3.  **Pruebas Combinatorias:** Se validó la interacción entre los selectores dependientes de `State` y `City`, asegurando que la lógica de negocio se mantenga (la ciudad debe pertenecer al estado seleccionado).
4.  **Manejo de Elementos Dinámicos:** Se implementaron esperas explícitas y estrategias de localización robustas para el *Datepicker* y *React-Selects*, que son elementos complejos de automatizar.

---

## 🚀 Instalación y Ejecución

### Requisitos Previos
* JDK 11 o superior.
* Maven instalado.
* Navegador Chromium (Playwright lo descargará automáticamente).

### Pasos para ejecutar:

1.  **Clonar el repositorio:**
    ```bash
    git clone https://github.com/diegoalejoreyes/PruebaTecnica_Playwright_Formulario.git
    ```

2.  **Ejecutar las pruebas:**
    ```bash
    mvn clean test
    ```

3.  **Generar el reporte de Allure:**
    ```bash
    mvn allure:serve
    ```

---

## 📊 Reportes y Evidencias

El proyecto genera evidencias automáticamente:
* **Screenshots:** Se capturan al finalizar cada test (exitoso o fallido) y se adjuntan al reporte de Allure.
* **Logs de Pasos:** Anotaciones y tests claros para generar reportes claros y organizados.
* **Allure Report:** Proporciona un dashboard interactivo con estadísticas y detalles de ejecución.

Los casos de prueba se adjuntan en un archivo excel aparte por correo

---
