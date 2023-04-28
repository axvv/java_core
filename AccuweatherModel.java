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

public class AccuweatherModel implements WeatherModel {
    private int howManyDays = 1;
    private static final String PROTOCOL = "https";
    private static final String BASE_HOST = "dataservice.accuweather.com";
    private static final String FORECAST = "forecasts";
    private static final String VERSION = "v1";
    private static final String DAILY = "daily";
    private static final String FIVE_DAYS = "5day";
    private static final String ONE_DAY = "1day";
    private static final String API_KEY = "ZgwUhpA1ZnAJwF5bZK35j3FFaFMcotGM";
    private static final String API_KEY_QUERY_PARAM = "apikey";
    private static final String LOCATIONS = "locations";
    private static final String CITIES = "cities";
    private static final String AUTOCOMPLETE = "autocomplete";
    private static final OkHttpClient okHttpClient = new OkHttpClient();
    private static final ObjectMapper objectMapper = new ObjectMapper();

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
                weatherInfo(oneDayForecastWeatherResponse);
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
                weatherInfo(fiveDaysForecastWeatherResponse);
                break;
        }

    }

    private void weatherInfo(String dayForecastWeatherResponse) throws JsonProcessingException, MalformedURLException {
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
            if (dateMonth.equals("01")) {
                dateMonth = "января";
            }
            if (dateMonth.equals("02")) {
                dateMonth = "февраля";
            }
            if (dateMonth.equals("03")) {
                dateMonth = "марта";
            }
            if (dateMonth.equals("04")) {
                dateMonth = "апреля";
            }
            if (dateMonth.equals("05")) {
                dateMonth = "мая";
            }
            if (dateMonth.equals("06")) {
                dateMonth = "июня";
            }
            if (dateMonth.equals("07")) {
                dateMonth = "июля";
            }
            if (dateMonth.equals("08")) {
                dateMonth = "августа";
            }
            if (dateMonth.equals("09")) {
                dateMonth = "сентября";
            }
            if (dateMonth.equals("10")) {
                dateMonth = "октября";
            }
            if (dateMonth.equals("11")) {
                dateMonth = "ноября";
            }
            if (dateMonth.equals("12")) {
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
        }
    }


    private String detectCityKey(String selectedCity) throws IOException {
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


    public static void main(String[] args) throws IOException {
        AccuweatherModel accuweatherModel = new AccuweatherModel();
        UserInterfaceView userInterfaceView = new UserInterfaceView();

        userInterfaceView.runInterface();
    }
}
