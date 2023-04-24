package lesson6;

import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

public class AccuweatherApp {
    private static final String PROTOCOL = "https";
    private static final String BASE_HOST = "dataservice.accuweather.com";
    private static final String FORECAST = "forecasts";
    private static final String VERSION = "v1";
    private static final String DAILY = "daily";
    private static final String FIVE_DAYS = "5day";
    private static final String API_KEY = "Ocu70sCixnaJBpxAPHwiDvGnBKGFAUet";
    private static final String API_KEY_QUERY_PARAM = "apikey";
    private static final String LOCATIONS = "locations";
    private static final String CITIES = "cities";
    private static final String AUTOCOMPLETE = "autocomplete";
    private static final OkHttpClient okHttpClient = new OkHttpClient();
    public static void main(String[] args) throws IOException {
      HttpUrl httpUrl = new HttpUrl.Builder()
              .scheme(PROTOCOL)
              .host(BASE_HOST)
              .addPathSegment(FORECAST)
              .addPathSegment(VERSION)
              .addPathSegment(DAILY)
              .addPathSegment(FIVE_DAYS)
              .addPathSegment("25100")
              .addQueryParameter(API_KEY_QUERY_PARAM,API_KEY)
              .build();
        Request request = new Request.Builder()
                .url(httpUrl)
                .build();
        Response response = okHttpClient.newCall(request).execute();
        String weatherResponse = response.body().string();
        System.out.println(weatherResponse);

    }
}
