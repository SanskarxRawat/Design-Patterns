/**
 * Adapter Pattern: Design a "Weather App" that adapts data from multiple weather services (API, XML, CSV) to provide a unified weather forecast.
 */

// Target Interface
interface WeatherService {
    String getWeatherForecast();
}

// Adaptee Classes
class APIWeatherService {
    public String fetchWeatherData() {
        return "Weather data from API: Sunny, 25°C";
    }
}

class XMLWeatherService {
    public String getXMLWeatherData() {
        return "<weather><condition>Cloudy</condition><temperature>22°C</temperature></weather>";
    }
}

class CSVWeatherService {
    public String readCSVWeatherData() {
        return "Condition,Rainy,Temperature,18°C";
    }
}

// Adapter Classes
class APIWeatherAdapter implements WeatherService {
    private APIWeatherService apiWeatherService;

    public APIWeatherAdapter(APIWeatherService apiWeatherService) {
        this.apiWeatherService = apiWeatherService;
    }

    @Override
    public String getWeatherForecast() {
        return apiWeatherService.fetchWeatherData();
    }
}

class XMLWeatherAdapter implements WeatherService {
    private XMLWeatherService xmlWeatherService;

    public XMLWeatherAdapter(XMLWeatherService xmlWeatherService) {
        this.xmlWeatherService = xmlWeatherService;
    }

    @Override
    public String getWeatherForecast() {
        String xmlData = xmlWeatherService.getXMLWeatherData();
        // Parsing XML data (simplified for this example)
        return "Weather data from XML: Cloudy, 22°C";
    }
}

class CSVWeatherAdapter implements WeatherService {
    private CSVWeatherService csvWeatherService;

    public CSVWeatherAdapter(CSVWeatherService csvWeatherService) {
        this.csvWeatherService = csvWeatherService;
    }

    @Override
    public String getWeatherForecast() {
        String csvData = csvWeatherService.readCSVWeatherData();
        // Parsing CSV data (simplified for this example)
        return "Weather data from CSV: Rainy, 18°C";
    }
}

// Client Code
public class WeatherApp {
    public static void main(String[] args) {
        WeatherService apiWeather = new APIWeatherAdapter(new APIWeatherService());
        WeatherService xmlWeather = new XMLWeatherAdapter(new XMLWeatherService());
        WeatherService csvWeather = new CSVWeatherAdapter(new CSVWeatherService());

        System.out.println(apiWeather.getWeatherForecast());
        System.out.println(xmlWeather.getWeatherForecast());
        System.out.println(csvWeather.getWeatherForecast());
    }
}

/**
Explanation:
1. `WeatherService` is the target interface that defines a unified method `getWeatherForecast()` for getting weather data.
2. `APIWeatherService`, `XMLWeatherService`, and `CSVWeatherService` are the adaptee classes that provide weather data in different formats.
3. `APIWeatherAdapter`, `XMLWeatherAdapter`, and `CSVWeatherAdapter` are the adapter classes that adapt the interface of the adaptees to the target `WeatherService` interface.
4. `WeatherApp` is the client that uses the `WeatherService` interface to get weather forecasts from different services in a unified way.
*/
