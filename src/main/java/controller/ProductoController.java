package controller;

import gestion.ProductoGestion;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import model.Producto;

/**
 *
 * @author maria
 */
@Named(value = "productoController")
@SessionScoped
public class ProductoController implements Serializable {

    /**
     * Creates a new instance of CamisasHombreController
     */
    public ProductoController() {
    }
    
    public List<Producto> getProductos(int tipoProducto) {
        return ProductoGestion.getProductos(tipoProducto);
    }
    
}
