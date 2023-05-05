package lesson7;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DataBaseRepository {
    private String insertWeather = "insert into weather (city, dateDay,dateMonth,minTemperature, maxTemperature) values (?, ?, ? ,? ,?)";
//    private String getWeather = "select * from weather where city = ?";
    private static final String DB_PATH = "jdbc:sqlite:C:\\sqlite-tools-win32-x86-3410200\\sqlite.db";

    static {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    //Сохранить прогноз погоды в базу данных
    public boolean saveWeatherToDataBase(Weather weather) throws SQLException {
        try (Connection connection = DriverManager.getConnection(DB_PATH)) {
            System.out.println("выполняется сохранение прогноза в базу данных");
            PreparedStatement saveWeather = connection.prepareStatement(insertWeather);
            saveWeather.setString(1, weather.getCity());
            saveWeather.setString(2, weather.getDateDay());
            saveWeather.setString(3, weather.getDateMonth());
            saveWeather.setDouble(4, weather.getMinTemperature());
            saveWeather.setDouble(5, weather.getMaxTemperature());
            return saveWeather.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        throw new SQLException("Сохранение погоды в базу данных не выполнено!");
    }

    public void saveWeatherToDataBase(List<Weather> weatherList) throws SQLException {
        try (Connection connection = DriverManager.getConnection(DB_PATH)) {
            System.out.println("выполняется сохранение прогноза на 5 дней в базу данных");
            PreparedStatement saveWeather = connection.prepareStatement(insertWeather);
            for (Weather weather : weatherList) {
                saveWeather.setString(1, weather.getCity());
                saveWeather.setString(2, weather.getDateDay());
                saveWeather.setString(3, weather.getDateMonth());
                saveWeather.setDouble(4, weather.getMinTemperature());
                saveWeather.setDouble(5, weather.getMaxTemperature());
                saveWeather.addBatch();
            }
            saveWeather.executeBatch();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public List<Weather> getSavedToDBWeather(String selectedCity) {
        List<Weather> weathers = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(DB_PATH)) {
            //        //TODO: реализовать этот метод получения данных из таблицы погоды

            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery("SELECT * FROM weather WHERE city like \'%" + selectedCity + "%\'");
            while (resultSet.next()) {
                System.out.printf("В городе %s %s %s будет минимальная температура воздуха %s, максимальная %s \n", (resultSet.getString("city")), (resultSet.getString("dateDay")), (resultSet.getString("dateMonth")),(resultSet.getString("minTemperature")) , (resultSet.getString("maxTemperature")));
                weathers.add(new Weather(resultSet.getString("city"),
                        resultSet.getString("dateDay"),
                        resultSet.getString("dateMonth"),
                        resultSet.getDouble("minTemperature"),
                        resultSet.getDouble("maxTemperature")));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return weathers;
    }

   /* public List<Weather> getSavedToDBWeather() {
        List<Weather> weathers = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(DB_PATH)) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(getWeather);
            while (resultSet.next()) {
                System.out.print(resultSet.getInt("id"));
                System.out.println(" ");
                System.out.print(resultSet.getString("city"));
                System.out.println(" ");
                System.out.print(resultSet.getString("dateDay"));
                System.out.println(" ");
                System.out.print(resultSet.getString("dateMonth"));
                System.out.println(" ");
                System.out.print(resultSet.getDouble("minTemperature"));
                System.out.println(" ");
                System.out.print(resultSet.getDouble("maxTemperature"));
                System.out.println(" ");
                weathers.add(new Weather(resultSet.getString("city"),
                        resultSet.getString("dateDay"),
                        resultSet.getString("dateMonth"),
                        resultSet.getDouble("minTemperature"),
                        resultSet.getDouble("maxTemperature")));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return weathers;
    }*/

    public static void main(String[] args) throws SQLException {
        DataBaseRepository dataBaseRepository = new DataBaseRepository();

//        System.out.println(dataBaseRepository.getSavedToDBWeather("samara"));
    }
}

