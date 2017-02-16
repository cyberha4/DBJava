package DB;

import java.sql.*;
import java.util.HashMap;

/**
 * Created by admin on 16.02.2017.
 */
public abstract class ORMStudents {
    protected String tableName;

    public ORMStudents(String tableName){
        this.tableName = tableName;
    }

    private Connection getConnection(){
        Connection query =  ConnectionToDB.getInstance();
        return query;
    }
    private ResultSet getResultSet(String sql) throws SQLException {
        return getConnection().createStatement().executeQuery(sql);
    }

    private Statement getStatement() throws SQLException {
        return getConnection().createStatement();
    }

    public String select(String[] fields, String tableName){
        return "";
    }

    public String selectAll(String[] fields ) throws SQLException {
        String  fields_ = "";

        for(String field:
                fields){
            fields_+=field+",";
        }
        if(fields_.contains(","))
            fields_ = fields_.substring(0, fields_.length()-1);;
        String sql = "SELECT "+fields_+" FROM "+this.tableName;
        System.out.println(sql);
        //return sql;

        return "";

    }

    public ResultSet selectAll() throws SQLException {
        String sql = "SELECT * FROM "+this.tableName;
        return getResultSet(sql);
    }

    public ResultSet getById(int id) throws SQLException {
        String strId = Integer.toString(id);
        String sql = "SELECT * FROM "+this.tableName+" WHERE id = "+strId;
        System.out.println(sql);
        return getResultSet(sql);
    }

    public ResultSet getByName(String name) throws SQLException {
        String sql = "SELECT * FROM "+this.tableName+" WHERE (name) = (?)";
        PreparedStatement preparedStatement = getConnection().prepareStatement(sql);
        preparedStatement.setString(1, name);

        System.out.println(sql);
        return preparedStatement.executeQuery();
    }

    public boolean insert(HashMap<String, Object> params) {
        String fields_ = "(";
        Object[] values = new Object[params.size()];
        int i =0;

        //params.keySet();
        for (String key : params.keySet()) {
            System.out.println("Key: " + key + " Value: ");
            fields_ += " "+key+",";
            values[i++] = params.get(key);
        }

        if(fields_.contains(","))
            fields_ = fields_.substring(0, fields_.length()-1);
        fields_ += ")";
        //System.out.println(fields_);
        //System.out.println(values[0]);
        //System.out.println(values[1]);

        String sql = "INSERT INTO java.students "+fields_+" Values (?,?)";
        System.out.println(sql);
        try {
            PreparedStatement preparedStatement = getConnection().prepareStatement(sql);
            i = 1;
            for (Object value : values) {
                if (value instanceof String)
                    preparedStatement.setString(i++, (String) value);
                if (value instanceof Integer)
                    preparedStatement.setInt(i++, (Integer) value);
            }
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;

    }

    public boolean delete(String field, String value){
        if(value.equals("0"))
            return false;
        try {
            getStatement().execute("DELETE FROM "+this.tableName+" WHERE "+field+" = '"+value+"'");
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
