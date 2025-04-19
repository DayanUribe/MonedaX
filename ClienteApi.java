import com.google.gson.Gson;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ClienteApi {

    private final String API_KEY = ee54c59ff9bc2472e82198fa;
    public ResultadoCambio convertirMoneda(String de, String a, double cantidad) {
        try {
            String direccion = String.format("https://v6.exchangerate-api.com/v6/%s/pair/%s/%s/%f",
                    API_KEY, de, a, cantidad);
            URL url = new URL(direccion);
            HttpURLConnection conexion = (HttpURLConnection) url.openConnection();
            conexion.setRequestMethod("GET");
            conexion.connect();

            if (conexion.getResponseCode() == 200) {
                InputStreamReader reader = new InputStreamReader(conexion.getInputStream());
                Gson gson = new Gson();
                RespuestaApi respuesta = gson.fromJson(reader, RespuestaApi.class);
                return new ResultadoCambio(cantidad, respuesta.conversion_result);
            } else {
                System.out.println("⚠️ Error al obtener datos: " + conexion.getResponseCode());
            }
        } catch (Exception e) {
            System.out.println("🚨 Error: " + e.getMessage());
        }
        return null;
    }

    private class RespuestaApi {
        double conversion_result;
    }
}