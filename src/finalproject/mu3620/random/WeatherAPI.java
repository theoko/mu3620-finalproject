package finalproject.mu3620.random;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import static finalproject.mu3620.helpers.Constants.*;

public class WeatherAPI {

    private static WeatherAPI INSTANCE = null;

    private WeatherAPI() {
        // Do this once
        initializeWeather();
    }

    public static WeatherAPI getInstance()
    {
        if (INSTANCE == null) {
            INSTANCE = new WeatherAPI();
        }

        return INSTANCE;
    }

    private final String[] weatherLinks = new String[] {
            "https://api.openweathermap.org/data/2.5/weather?q=Sahara&appid=0021f7b296482eecb5c71c4f138cf484",
            "https://api.openweathermap.org/data/2.5/weather?q=Worcester&appid=0021f7b296482eecb5c71c4f138cf484",
            "https://api.openweathermap.org/data/2.5/weather?q=Boston&appid=0021f7b296482eecb5c71c4f138cf484"
    };
    private final String weatherURL = "https://api.openweathermap.org/data/2.5/weather?q=Boston&appid=0021f7b296482eecb5c71c4f138cf484";
    private int count = 0;
    private int temperature = 0;
    private int temperatureKelvin = 273;

    public int getCount() {
        return count;
    }

    public int getTemperature() {
        return temperature;
    }

    public void initializeWeather() {
        try {
            StringBuilder result = new StringBuilder();
            URL url = new URL(weatherURL);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                result.append(line);
            }
            reader.close();

            String type = this.getKey(this.getKeyFromArray(this.getKey(result.toString(), "weather"), 0), "main");
            switch (type) {
                case "\"Clear\"":
                    count += CLEAR_COUNT;
                    break;
                case "\"Clouds\"":
                    count += CLOUDS_COUNT;
                    break;
                case "\"Drizzle\"":
                    count += DRIZZLE_COUNT;
                    break;
                case "\"Rain\"":
                    count += RAIN_COUNT;
                    break;
                case "\"Thunderstorm\"":
                    count += THUNDERSTORM_COUNT;
                    break;
                case "\"Snow\"":
                    count += SNOW_COUNT;
                    break;
                default:
                    // Do nothing
            }
            double tempK = Double.parseDouble(this.getKey(this.getKey(result.toString(), "main"), "temp"));
            int tempF = (int) ((tempK - 273) * 1.8 + 32);
            temperature = tempF;
            temperatureKelvin = (int) tempK;
            count += tempF;
            System.out.println("Temperature in F: " + tempF);
            System.out.println("Current count: " + count);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String getKey(String jsonString, String key) {
        Gson gson = new Gson();
        return gson.fromJson(jsonString, JsonObject.class).get(key).toString();
    }

    private String getKeyFromArray(String jsonString, int index) {
        Gson gson = new Gson();
        JsonArray jsonObject = gson.fromJson(jsonString, JsonArray.class);
        return jsonObject.get(index).toString();
    }

}
