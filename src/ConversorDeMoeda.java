import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class ConversorDeMoeda {
    public static void main(String[] args) {
        Scanner sc1 = new Scanner(System.in);
        System.out.println("Seja bem vindo ao conversor de moeda!! \ndigite o que necessita de 1 até 7 ");
        System.out.println("1) Dólar => Peso argentino\n2) Peso argentino => Dólar\n3) Dólar => Real brasileiro\n4) Real brasileiro => Dólar\n5) Dólar => Peso colombiano\n6) Peso colombiano => Dólar\n7) Sair");

        int choice = sc1.nextInt();
        if (choice == 7) {
            System.out.println("Saindo...");
            return;
        }

        System.out.println("Digite a quantidade que deseja converter: ");
        double amount = sc1.nextDouble();
        
        String baseCurrency = "USD";
        String targetCurrency = "";
        
        switch (choice) {
            case 1:
                targetCurrency = "ARS";
                break;
            case 2:
                baseCurrency = "ARS";
                targetCurrency = "USD";
                break;
            case 3:
                targetCurrency = "BRL";
                break;
            case 4:
                baseCurrency = "BRL";
                targetCurrency = "USD";
                break;
            case 5:
                targetCurrency = "COP";
                break;
            case 6:
                baseCurrency = "COP";
                targetCurrency = "USD";
                break;
            default:
                System.out.println("Escolha inválida");
                return;
        }

        String url_str = "https://v6.exchangerate-api.com/v6/83cfb4840073a9d0329684c1/latest/" + baseCurrency;

        try {
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
                System.out.println("Erro na requisição da API.");
                return;
            }

            if (!jsonobj.has("conversion_rates")) {
                System.out.println("Dados de taxa de conversão não disponíveis.");
                return;
            }

            JsonObject conversionRates = jsonobj.getAsJsonObject("conversion_rates");
            if (!conversionRates.has(targetCurrency)) {
                System.out.println("Taxa de conversão para " + targetCurrency + " não disponível.");
                return;
            }

            double exchangeRate = conversionRates.get(targetCurrency).getAsDouble();

            // Calculate the converted amount
            double convertedAmount = amount * exchangeRate;
            System.out.printf("Quantidade convertida: %.2f %s%n", convertedAmount, targetCurrency);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
