
package tienda;

import tienda.servicios.ProductoService;


public class Tienda {

    public static void main(String[] args) throws Exception {
        ProductoService ps = new ProductoService();
        ps.menu();
    }
    
}
