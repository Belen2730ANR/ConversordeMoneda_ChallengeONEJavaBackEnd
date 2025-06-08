# Conversor de Monedas en Java

¡Hola! Soy **Belén González Villeda** y he desarrollado este sencillo conversor de monedas en Java. Este programa te permite convertir cantidades entre diferentes divisas, obteniendo las tasas de cambio en tiempo real a través de una API.

---

## 📌 Descripción del Proyecto

Este proyecto es una aplicación de consola en Java que facilita la conversión de monedas. El usuario interactúa mediante un menú simple: puede ingresar la moneda que tiene, la moneda a la que desea convertir y la cantidad, y el programa le mostrará el resultado utilizando las tasas de cambio más recientes obtenidas de una API externa.

### 🗂️ Estructura del Proyecto

El proyecto está diseñado con dos archivos principales para mantener una estructura clara y modular:

- **`Principal.java`**: Maneja la interfaz de usuario, el menú y la lógica de entrada/salida de datos.
- **`ConsultarMoneda.java`**: Se encarga de toda la comunicación con la API externa para obtener las tasas de cambio.

---

## ✨ Características

- 🔁 **Conversión de Monedas**: Convierte una cantidad de una moneda a otra utilizando tasas de cambio actualizadas.
- 💻 **Interacción por Consola**: Interfaz de usuario sencilla y directa a través de la consola.
- 🌐 **API Externa**: Utiliza la API de [ExchangeRate-API](https://www.exchangerate-api.com/) para obtener datos de tasas de cambio en tiempo real.
- ⚠️ **Manejo de Errores**: Incluye validaciones básicas para las entradas del usuario y manejo de errores en la comunicación con la API.
- 🧩 **Estructura Modular**: Código dividido en clases lógicas para una mejor organización.

---
