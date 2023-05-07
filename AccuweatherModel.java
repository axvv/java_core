package lesson7;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;

public class AccuweatherModel implements WeatherModel {
    private int howManyDays = 1;
    private static final String PROTOCOL = "https";
    private static final String BASE_HOST = "dataservice.accuweather.com";
    private static final String FORECAST = "forecasts";
    private static final String VERSION = "v1";
    private static final String DAILY = "daily";
    private static final String FIVE_DAYS = "5day";
    private static final String ONE_DAY = "1day";
    private static final String API_KEY = "Ocu70sCixnaJBpxAPHwiDvGnBKGFAUet";
    private static final String API_KEY_QUERY_PARAM = "apikey";
    private static final String LOCATIONS = "locations";
    private static final String CITIES = "cities";
    private static final String AUTOCOMPLETE = "autocomplete";
    private static final OkHttpClient okHttpClient = new OkHttpClient();
    private static final ObjectMapper objectMapper = new ObjectMapper();
    private DataBaseRepository dataBaseRepository = new DataBaseRepository();

    @Override
    public void getWeather(String selectedCity, Period period) throws IOException {
        switch (period) {
            case NOW:
                howManyDays = 1;
                HttpUrl oneDayForecastHttpUrl = new HttpUrl.Builder()
                        .scheme(PROTOCOL)
                        .host(BASE_HOST)
                        .addPathSegment(FORECAST)
                        .addPathSegment(VERSION)
                        .addPathSegment(DAILY)
                        .addPathSegment(ONE_DAY)
                        .addPathSegment(detectCityKey(selectedCity))
                        .addQueryParameter(API_KEY_QUERY_PARAM, API_KEY)
                        .addQueryParameter("Metric", "true")
                        .build();
                Request oneDayForecastRequest = new Request.Builder()
                        .url(oneDayForecastHttpUrl)
                        .build();
                Response oneDayForecastResponse = okHttpClient.newCall(oneDayForecastRequest).execute();
                String oneDayForecastWeatherResponse = oneDayForecastResponse.body().string();

//                читабельный вывод
//                узнаём для какого города прогноз
                try {
                    weatherInfo(oneDayForecastWeatherResponse);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }

                break;
            case FIVE_DAYS:
                howManyDays = 5;
                HttpUrl fiveDaysForecastHttpUrl = new HttpUrl.Builder()
                        .scheme(PROTOCOL)
                        .host(BASE_HOST)
                        .addPathSegment(FORECAST)
                        .addPathSegment(VERSION)
                        .addPathSegment(DAILY)
                        .addPathSegment(FIVE_DAYS)
                        .addPathSegment(detectCityKey(selectedCity))
                        .addQueryParameter(API_KEY_QUERY_PARAM, API_KEY)
                        .addQueryParameter("Metric", "true")
                        .build();
                Request fiveDaysForecastRequest = new Request.Builder()
                        .url(fiveDaysForecastHttpUrl)
                        .build();
                Response fiveDaysForecastResponse = okHttpClient.newCall(fiveDaysForecastRequest).execute();
                String fiveDaysForecastWeatherResponse = fiveDaysForecastResponse.body().string();
                try {
                    weatherInfo(fiveDaysForecastWeatherResponse);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }

                break;

        }

    }


    public List<Weather> getSavedToDBWeather(String selectedCity) {
        return dataBaseRepository.getSavedToDBWeather(selectedCity);
    }

    public void weatherInfo(String dayForecastWeatherResponse) throws JsonProcessingException, MalformedURLException, SQLException {
        String url = objectMapper.readTree(dayForecastWeatherResponse).get("Headline").at("/Link").asText();
        URL urlForCity = new URL(url);
        String[] segments = urlForCity.getPath().split("/");
        String city = segments[3].substring(0, 1).toUpperCase() + segments[3].substring(1).toLowerCase();


//                Текст прогноза
        String weatherText = objectMapper.readTree(dayForecastWeatherResponse).get("Headline").at("/Text").asText();
//        Цикл для вывода информации для каждого дня прогноза
        for (int i = 0; i < howManyDays; i++) {
            System.out.println(howManyDays);
            //                узнаём для какого дня прогноз
            String date = objectMapper.readTree(dayForecastWeatherResponse)
                    .at("/DailyForecasts")
                    .get(i)
                    .at("/Date").asText();
            String[] segmentsDate = date.split("-");
            String dateDay = segmentsDate[2].substring(0, 2);
//                узнаём для какого месяца прогноз
            String dateMonth = segmentsDate[1];
            switch (dateMonth) {
                case ("01"):
                    dateMonth = "января";
                    break;
                case ("02"):
                    dateMonth = "февраля";
                    break;
                case ("03"):
                    dateMonth = "марта";
                    break;
                case ("04"):
                    dateMonth = "апреля";
                    break;
                case ("05"):
                    dateMonth = "мая";
                    break;
                case ("06"):
                    dateMonth = "июня";
                    break;
                case ("07"):
                    dateMonth = "июля";
                    break;
                case ("08"):
                    dateMonth = "августа";
                    break;
                case ("09"):
                    dateMonth = "сентября";
                    break;
                case ("10"):
                    dateMonth = "октября";
                    break;
                case ("11"):
                    dateMonth = "ноября";
                    break;
                case ("12"):
                    dateMonth = "декабря";
                    }


//                Температура воздуха из прогноза (минимальная и максимальная)
                    String minTemperature = objectMapper
                            .readTree(dayForecastWeatherResponse)
                            .at("/DailyForecasts")
                            .get(i)
                            .at("/Temperature/Minimum/Value").asText();
                    String maxTemperature = objectMapper
                            .readTree(dayForecastWeatherResponse)
                            .at("/DailyForecasts")
                            .get(i)
                            .at("/Temperature/Maximum/Value").asText();
//                единицы измерения температуры
                    String unitTemperature = objectMapper
                            .readTree(dayForecastWeatherResponse)
                            .at("/DailyForecasts")
                            .get(i)
                            .at("/Temperature/Maximum/Unit").asText();

                    System.out.printf("В городе %s %s %s будет %s, минимальная температура воздуха %s%s, максимальная %s%s \n", city, dateDay, dateMonth, weatherText, minTemperature, unitTemperature, maxTemperature, unitTemperature);
//            сохранить прогноз погоды на 1 день в базу данных
                    try {
                        dataBaseRepository.saveWeatherToDataBase(new Weather(city, dateDay, dateMonth, Double.parseDouble(minTemperature), Double.parseDouble(minTemperature)));
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    } catch (NumberFormatException e) {
                        throw new RuntimeException(e);
                    }
            }
        }


        private String detectCityKey (String selectedCity) throws IOException {
            HttpUrl httpUrl = new HttpUrl.Builder()
                    .scheme(PROTOCOL)
                    .host(BASE_HOST)
                    .addPathSegment(LOCATIONS)
                    .addPathSegment(VERSION)
                    .addPathSegment(CITIES)
                    .addPathSegment(AUTOCOMPLETE)
                    .addQueryParameter(API_KEY_QUERY_PARAM, API_KEY)
                    .addQueryParameter("q", selectedCity)
                    .build();
            Request request = new Request.Builder()
                    .url(httpUrl)
                    .get()
                    .addHeader("accept", "application/json")
                    .build();

            Response response = okHttpClient.newCall(request).execute();
            String responseString = response.body().string();
            String cityKey = objectMapper.readTree(responseString).get(0).at("/Key").asText();

            return cityKey;

        }


        public static void main (String[]args) throws IOException {
            AccuweatherModel accuweatherModel = new AccuweatherModel();
            UserInterfaceView userInterfaceView = new UserInterfaceView();

            userInterfaceView.runInterface();
        }
    }
