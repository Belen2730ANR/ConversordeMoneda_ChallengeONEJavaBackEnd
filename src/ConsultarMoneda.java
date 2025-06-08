import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ConsultarMoneda {
    private static final String API_KEY = "1a600622b92cd6795c96a590";
    private static final String BASE_LATEST_URL = "https://v6.exchangerate-api.com/v6/" + API_KEY + "/latest/";

    public double obtenerTasaEspecifica(String monedaBase, String monedaDestino) throws IOException, InterruptedException {

        String apiUrl = BASE_LATEST_URL + monedaBase.toUpperCase();
        URI direccion = URI.create(apiUrl);

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(direccion)
                .build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200) {
                Gson gson = new Gson();
                JsonObject jsonResponse = gson.fromJson(response.body(), JsonObject.class);

                if (jsonResponse != null && jsonResponse.has("conversion_rates")) {
                    JsonObject conversionRates = jsonResponse.getAsJsonObject("conversion_rates");

                    if (conversionRates.has(monedaDestino.toUpperCase())) {
                        return conversionRates.get(monedaDestino.toUpperCase()).getAsDouble();
                    } else {
                        throw new RuntimeException("La moneda de destino '" + monedaDestino + "' no se encontró en las tasas de conversión para '" + monedaBase + "'.");
                    }
                } else {
                    throw new RuntimeException("Formato de respuesta de la API inesperado: no se encontró 'conversion_rates'.");
                }
            } else {
                String errorBody = response.body();
                throw new RuntimeException("Error al obtener las tasas de cambio. Código: " + response.statusCode() + ". Mensaje de la API: " + errorBody);
            }
        } catch (IOException | InterruptedException e) {
            throw new IOException("Error de conexión al buscar tasas para " + monedaBase + ": " + e.getMessage(), e);
        } catch (RuntimeException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("Error inesperado al procesar la respuesta de la API: " + e.getMessage(), e);
        }
    }
}