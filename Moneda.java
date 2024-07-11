import com.google.gson.JsonObject;

public record Moneda(String result, String base_code, JsonObject conversion_rates) {

}
