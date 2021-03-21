package gestion;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Blob;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import model.Producto;
import model.Conexion;

/**
 *
 * @author Grupo 7
 */
public class ProductoGestion {

    private static final String SQL_GETPRODUCTOS = "select * from producto where id_tipoproducto =?";

    //Metodo encargado de traer los productos
    public static ArrayList<Producto> getProductos(int tipoProducto) {
        ArrayList<Producto> list = new ArrayList<>();
        try {
            PreparedStatement sentencia = Conexion.getConexion().prepareStatement(SQL_GETPRODUCTOS);
            sentencia.setInt(1, tipoProducto);
            ResultSet rs = sentencia.executeQuery();
            while (rs != null && rs.next()) {
                list.add(new Producto(
                        rs.getInt(1), // ID
                        rs.getString(2), // NOMBRE
                        rs.getString(3), // DESCRIPCION
                        rs.getString(4), // IMAGEN
                        rs.getFloat(5), // PRECIO
                        rs.getString(6).charAt(0), // GENERO
                        rs.getString(7), // TALLA
                        rs.getInt(8), // CANTIDAD
                        rs.getInt(9), // ID ENTREGA
                        rs.getInt(10) // ID TIPOPRODUCTO
                ));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductoGestion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public void listarImg(int id, HttpServletResponse response) {
        String sql = "select * from producto where id_producto="+id;
        InputStream inputStream = null;
        OutputStream outputStream = null;
        BufferedInputStream bufferedInputStream = null;
        BufferedOutputStream bufferedOutputStream = null;
        try {
            outputStream = response.getOutputStream();
            PreparedStatement sentencia = Conexion.getConexion().prepareStatement(sql);
            ResultSet rs = sentencia.executeQuery();
            if (rs != null && rs.next()) {
                inputStream = rs.getBinaryStream("imagen");
            }
            bufferedInputStream = new BufferedInputStream(inputStream);
            bufferedOutputStream = new BufferedOutputStream(outputStream);
            int i = 0;
            while ((i = bufferedInputStream.read()) != -1) {
                bufferedOutputStream.write(i);
            }
        } catch (Exception e) {
        }
    }

} // Fin Clase CamisasHombreGestion
