package jdbcıntro;

import java.sql.*;
import java.util.ArrayList;

public class JdbcIntro {

    static String userName = "root";
    static String password = "Yusudhan11.99*";
    static String dbUrl = "jdbc:mysql://localhost:3306/world";

    public static void main(String[] args) throws SQLException {
        PreparedStatement statement = null;
        ResultSet resultSet;
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(dbUrl, userName, password);
            String sql = "update city set population = 1300000 where id = ?";
            statement = connection.prepareStatement(sql);
            statement.setInt(1, 3070);
            int result = statement.executeUpdate();
            System.out.println("Record updated.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            statement.close();
            connection.close();
        }
    }

    public static void selectDemo() throws SQLException {

        Statement statement = null;
        ResultSet resultSet;
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(dbUrl, userName, password);
            statement = connection.createStatement();
            resultSet = statement.executeQuery("select code,name,continent,region from country");
            ArrayList<Country> countries = new ArrayList<Country>();
            while (resultSet.next()) {
                countries.add(new Country(
                        resultSet.getString("code"),
                        resultSet.getString("name"),
                        resultSet.getString("continent"),
                        resultSet.getString("region")));
            }
            System.out.println(countries.size());
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            connection.close();
        }
    }

    public static void insertDemo() throws SQLException {
        PreparedStatement statement = null;
        ResultSet resultSet;
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(dbUrl, userName, password);
            String sql = "insert into city (name,countrycode,district,population) values(?,?,?,?)";
            statement = connection.prepareStatement(sql);
            statement.setString(1, "Düzce 2");
            statement.setString(2, "TUR");
            statement.setString(3, "Turkey");
            statement.setInt(4, 600000);
            int result = statement.executeUpdate();
            System.out.println("Record added.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            statement.close();
            connection.close();
        }
    }
}
