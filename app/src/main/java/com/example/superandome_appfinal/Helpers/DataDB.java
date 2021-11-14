package com.example.superandome_appfinal.Helpers;

import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;

import java.sql.SQLException;

public class DataDB {
    //Información de la BD
    public static String host="sql10.freesqldatabase.com";
    public static String port="3306";
    public static String nameBD="sql10450855";
    public static String user="sql10450855";
    public static String pass="GSlRt8BJ2m";

    //Información para la conexion
    public static String urlMySQL = "jdbc:mysql://" + host + ":" + port + "/"+nameBD;
    public static String driver = "com.mysql.jdbc.Driver";

    public static ConnectionSource connectionSource;

    public static ConnectionSource getConnectionSource() {
        try {
            if (connectionSource == null)
                connectionSource = new JdbcConnectionSource(urlMySQL, user, pass);
            return connectionSource;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
