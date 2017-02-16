/**
 * Created by admin on 16.02.2017.
 */
import DB.ConnectionToDB;
import DB.DBStudents;
import DB.ORMStudents;

import java.sql.*;
import java.util.HashMap;

public class Main {
    private static final String url = "jdbc:mysql://localhost:3306/java";
    private static final String user = "root";
    private static final String password = "";

    // JDBC variables for opening and managing connection
    private static Connection con;
    //private static Statement stmt;
    //private static ResultSet rs;

    public static void main(String[] args) throws SQLException {
        /*
        * Для каждой сущности- добавление, обновление, удаление, запрос по id,
        * запрос по имени, запрос всего списка
        * Вывести на консоль
        *
        * */

        ORMStudents orm = new DBStudents("students");

        HashMap<String, Object> hash = new HashMap<String, Object>();
        hash.put("name", "Andrey");
        hash.put("sex", 1);
        orm.insert(hash);

        hash.put("name", "Andrey2");
        hash.put("sex", 2);
        orm.insert(hash);

        ResultSet resultSet = orm.selectAll();

        Integer id = 0; // Запоминаем айди последней записи
        while (resultSet.next()) {
            System.out.println("name :" +  resultSet.getString("name") + " sex:" + resultSet.getInt("sex"));
            id = resultSet.getInt("id");
        }


        orm.delete("id", Integer.toString(id)); //Удаляем по значению поля

        resultSet = orm.selectAll();
        System.out.println("Проверяем корректность удаления (выводим всю таблицу)");
        while (resultSet.next()) {
            System.out.println("name :" +  resultSet.getString("name") + " sex:" + resultSet.getInt("sex"));
        }


        System.exit(1);

        //con = DriverManager.getConnection(url, user, password);

        //ResultSet rs = orm.getByName("chipulko");
        ResultSet rs = orm.getByName("chipulko");
        //ResultSet rs = orm.selectAll();
        while (rs.next()) {
            //int count = rs.getInt(1);
            System.out.println("name :" +  rs.getString("name"));
        }

        //System.exit(2);

        String sql = "INSERT INTO java.students (name, birth_date, sex) Values (?,?,?)";

        PreparedStatement preparedStatement = con.prepareStatement(sql);

        preparedStatement.setString(1,"ttt");
        preparedStatement.setInt(2,22);
        preparedStatement.setInt(3,2);

        //preparedStatement.executeUpdate();


    }
}
