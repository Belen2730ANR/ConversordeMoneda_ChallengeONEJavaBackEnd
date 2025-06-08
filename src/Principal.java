import java.io.IOException;
import java.util.Scanner;

public class Principal {
    public static void main(String[] args) {
        Scanner lectura = new Scanner(System.in);
        ConsultarMoneda consulta = new ConsultarMoneda();

        System.out.println("¡Bienvenido al Conversor de Monedas!");

        String opcion;
        do {
            System.out.println("\n-------------------------------------------------");
            System.out.println("1. Realizar una conversión");
            System.out.println("2. Salir");
            System.out.print("Por favor, seleccione una opción: ");
            opcion = lectura.nextLine().trim();

            switch (opcion) {
                case "1":
                    try {
                        System.out.print("Ingrese el código de la moneda que TIENE (ej. USD, EUR, MXN): ");
                        String monedaBase = lectura.nextLine().trim().toUpperCase();

                        if (!monedaBase.matches("^[A-Z]{3}$")) {
                            System.out.println("Error: Código de moneda base inválido. Debe ser de 3 letras (ej. USD).");
                            continue;
                        }

                        System.out.print("Ingrese el código de la moneda a la que desea CONVERTIR (ej. USD, EUR, MXN): ");
                        String monedaDestino = lectura.nextLine().trim().toUpperCase();

                        if (!monedaDestino.matches("^[A-Z]{3}$")) {
                            System.out.println("Error: Código de moneda destino inválido. Debe ser de 3 letras (ej. USD).");
                            continue;
                        }
                        double tasaDeConversion = consulta.obtenerTasaEspecifica(monedaBase, monedaDestino);

                        if (tasaDeConversion == -1.0) {
                            System.out.println("No se pudo obtener la tasa de cambio para " + monedaBase + " a " + monedaDestino + ". Intente con otras monedas o verifique su conexión.");
                            continue;
                        }

                        double cantidad = 0.0;
                        boolean entradaValida = false;
                        while (!entradaValida) {
                            System.out.print("Ingrese la cantidad que desea convertir: ");
                            try {
                                cantidad = Double.parseDouble(lectura.nextLine());
                                if (cantidad <= 0) {
                                    System.out.println("La cantidad debe ser un número positivo.");
                                } else {
                                    entradaValida = true;
                                }
                            } catch (NumberFormatException e) {
                                System.out.println("Entrada inválida. Por favor, ingrese un número.");
                            }
                        }

                        double resultado = cantidad * tasaDeConversion;

                        System.out.printf(" %.2f %s equivale a %.2f %s%n", cantidad, monedaBase, resultado, monedaDestino);
                        System.out.println("Tasa de cambio utilizada: 1 " + monedaBase + " = " + String.format("%.4f", tasaDeConversion) + " " + monedaDestino);

                    } catch (RuntimeException | IOException e) {
                        System.out.println("Error en la conversión: " + e.getMessage());
                    } catch (Exception e) {
                        System.out.println("Ocurrió un error inesperado: " + e.getMessage());
                    }
                    break;
                case "2":
                    System.out.println("Saliendo del programa. ¡Hasta luego!");
                    break;
                default:
                    System.out.println("Opción no válida. Por favor, intente de nuevo.");
            }
        } while (!opcion.equals("2"));

        lectura.close();
    }
}