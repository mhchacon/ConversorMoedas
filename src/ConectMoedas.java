import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Currency;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class ConectMoedas{
    private static final String API_KEY = "83cfb4840073a9d0329684c1";

    public double getExchangeRate(Currency baseCurrency, Currency targetCurrency) throws Exception {
        String url_str = "https://v6.exchangerate-api.com/v6/" + API_KEY + "/latest/" + baseCurrency;

        // Making Request
        URL url = new URL(url_str);
        HttpURLConnection request = (HttpURLConnection) url.openConnection();
        request.connect();

        // Convert to JSON
        InputStreamReader reader = new InputStreamReader((InputStream) request.getContent());
        JsonElement root = JsonParser.parseReader(reader);
        JsonObject jsonobj = root.getAsJsonObject();

        // Accessing exchange rate
        if (!jsonobj.has("result") || !jsonobj.get("result").getAsString().equals("success")) {
            throw new Exception("Erro na requisição da API.");
        }

        if (!jsonobj.has("conversion_rates")) {
            throw new Exception("Dados de taxa de conversão não disponíveis.");
        }

        JsonObject conversionRates = jsonobj.getAsJsonObject("conversion_rates");
        if (!conversionRates.has(targetCurrency.toString())) {
            throw new Exception("Taxa de conversão para " + targetCurrency + " não disponível.");
        }

        return conversionRates.get(targetCurrency.toString()).getAsDouble();
    }
}

