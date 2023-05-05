package lesson7;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public interface WeatherModel {
    void getWeather(String selectedCity, Period period) throws IOException, SQLException;
//    Забираем прогноз погоды для выбранного города из базы данных
    public List<Weather> getSavedToDBWeather(String selectedCity);

}
