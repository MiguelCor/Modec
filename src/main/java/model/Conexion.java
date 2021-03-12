package model;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Grupo 7
 */
public class Conexion {

    private static Conexion conexion;
    private static final String DBURL = "jdbc:mysql://localhost:3306/modec?serverTimezone=UTC";
    private static Connection conn = null;

    private Conexion() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            conn=DriverManager.getConnection(DBURL, "modec_user", "Prueba123_");
        } catch (ClassNotFoundException | SQLException | NoSuchMethodException | SecurityException
                | InstantiationException | IllegalArgumentException | IllegalAccessException
                | InvocationTargetException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /*Singleton Patron de Diseño*/
    public static synchronized Connection getConexion(){
        if (conexion==null) {
            conexion= new Conexion();
        }
        return conn;
    }
    
} // Fin Clase Conexion
