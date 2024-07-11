import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ConsultaDivisa {
    public static Moneda busquedaDivisa(String divisaBase, String divisaCambio) throws IOException {

        String url_str = "https://v6.exchangerate-api.com/v6/f6af95e487853dff4736825d/latest/" + divisaBase;//USD
// Realizando la Request
        URL url = new URL(url_str);
        HttpURLConnection request = (HttpURLConnection) url.openConnection();
        request.connect();
// Convirtiendo to JSON
        JsonParser jp = new JsonParser();
        JsonElement root = jp.parse(new InputStreamReader((InputStream) request.getContent()));
        JsonObject jsonobj = root.getAsJsonObject();
        Gson gson = new Gson();

        Moneda moneda = gson.fromJson(jsonobj, Moneda.class);

        return moneda;
    }
    public boolean ValidacionExistencia(String divisa) throws IOException {
        try {
            String url_str = "https://v6.exchangerate-api.com/v6/f6af95e487853dff4736825d/latest/" + divisa;//USD
            URL url = new URL(url_str);
            HttpURLConnection request = (HttpURLConnection) url.openConnection();
            request.connect();
            JsonParser jp = new JsonParser();
            JsonElement root = jp.parse(new InputStreamReader((InputStream) request.getContent()));
            JsonObject jsonobj = root.getAsJsonObject();
            Gson gson = new Gson();
            Moneda moneda = gson.fromJson(jsonobj, Moneda.class);
            if (moneda.conversion_rates().has(divisa.toUpperCase())) {
                // Obtener el valor correspondiente a la clave
                System.out.println("Divisa encontrada con exito: " + divisa);
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            System.out.println("No contamos con esa divisa ingrese nuevamente su divisa.");
            return false;
        }
    }
    public static void listaDivisaCambio(String divisa) throws IOException {
        String url_str = "https://v6.exchangerate-api.com/v6/f6af95e487853dff4736825d/latest/" + divisa;//USD
        URL url = new URL(url_str);
        HttpURLConnection request = (HttpURLConnection) url.openConnection();
        request.connect();
        JsonParser jp = new JsonParser();
        JsonElement root = jp.parse(new InputStreamReader((InputStream) request.getContent()));
        JsonObject jsonobj = root.getAsJsonObject();
        Gson gson = new Gson();

        //crenado objeto ExchanRate
        String divisaLista = jsonobj.get("base_code").getAsString();
        JsonObject valor = jsonobj.getAsJsonObject("conversion_rates");

        ListaDeDivisas listaDeDivisas = new ListaDeDivisas(divisaLista, valor);
        //Manipulacion del objeto monedaBase
        for (String currency : listaDeDivisas.valor().keySet()) {
            System.out.println(currency);
        }
    }
    public double calculo(double valorAConvertir, Moneda moneda, String divisaCambio, String divisaBase) {
        moneda.conversion_rates().has(divisaCambio);
        JsonObject convercion = moneda.conversion_rates();
        double tasa = convercion.get(divisaCambio).getAsDouble();
        System.out.println("La tasa de cambio por " + divisaBase + " es: " + tasa + " por " + divisaCambio);
        double resultado = valorAConvertir * tasa;//
        System.out.println("Resultado de la convercion " + resultado + " " + divisaCambio);
        return resultado;

    }
}
