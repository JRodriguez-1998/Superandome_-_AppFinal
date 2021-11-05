package com.example.superandome_appfinal.Daos;

public class DataDB {
    //Información de la BD
    public static String host="sql10.freesqldatabase.com";
    public static String port="3306";
    public static String nameBD="sql10447793";
    public static String user="sql10447793";
    public static String pass="w5qVWn22K9";

    //Información para la conexion
    public static String urlMySQL = "jdbc:mysql://" + host + ":" + port + "/"+nameBD;
    public static String driver = "com.mysql.jdbc.Driver";
}
