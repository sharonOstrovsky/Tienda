/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tienda.servicios;

import java.util.Collection;
import java.util.Scanner;
import tienda.entidades.Producto;
import tienda.persistencia.productoDAO;


public class ProductoService {
    
    private productoDAO dao;
    
    public ProductoService(){
        this.dao = new productoDAO();
    }
    
    Scanner input = new Scanner(System.in);
    
    public void menu() throws Exception{
        boolean salir = false;
        do{
            
            System.out.println("MENU:");
            System.out.println("1. Lista el nombre de todos los productos que hay en la tabla producto.");
            System.out.println("2. Lista los nombres y los precios de todos los productos de la tabla producto.");
            System.out.println("3. Listar aquellos productos que su precio esté entre 120 y 202.");
            System.out.println("4. Buscar y listar todos los Portátiles de la tabla producto.");
            System.out.println("5. Listar el nombre y el precio del producto más barato.");
            System.out.println("6. Ingresar un producto a la base de datos.");
            System.out.println("7. Eliminar un producto de la base de datos.");
            System.out.println("8. Editar un producto con datos a elección.");
            System.out.println("9. Salir");
            System.out.print("Elija una opcion: ");
            
            switch(input.nextInt()){
                
                case 1:
                    System.out.println("NOMBRES PRODUCTOS:");
                    imprimirNombresProductos();
                    break;
                case 2:
                    System.out.println("NOMBRES Y PRECIO");
                    imprimirNombresPrecioProductos();
                    break;
                case 3:
                    System.out.println("PRODUCTOS ENTRE $120 Y $200");
                    imprimirProductosEnRango();
                    break;
                case 4:
                    System.out.println("PORTATILES:");
                    imprimirProductosPortatil();
                    break;
                case 5:
                    System.out.println("PRODUCTO MAS BARATO:");
                    imprimirProductoMasBarato();
                    break;
                case 6:
                    System.out.println("INGRESAR PRODUCTO:");
                    System.out.print("Ingrese el nombre del producto:");
                    String nombre = input.next();
                    System.out.print("Ingrese el precio: ");
                    double precio = input.nextDouble();
                    System.out.print("Ingerese el numero de codigo del fabricante: ");
                    int codigo = input.nextInt();
                    crearProducto(nombre, precio, codigo);
                    break;
                case 7: 
                    System.out.println("INGRESAR CODIGO DEL PRODUCTO A ELIMINAR");
                    eliminarCodigo(input.nextInt());
                    break;
                case 8:
                    System.out.println("en construccion");
                    break;
                case 9:
                    salir = true;
                    break;
            }
            
        }while(!salir);
    }
    
    public void crearProducto (String nombre, double precio, Integer codigoFabricante) throws Exception { 
        
        try{
            
            if(nombre == null || nombre.trim().isEmpty()){
                throw new Exception("Debe indicar el nombre");
            }
            if (precio < 0) {
                throw new Exception("Debe indicar el precio");
            }
            if (codigoFabricante == null) {
                throw new Exception("Debe indicar el codigo del fabricante");
            }
            
            Producto producto = new Producto();
            producto.setNombre(nombre);
            producto.setPrecio(precio);
            producto.setCodigoFabricante(codigoFabricante);
            dao.guardarProducto(producto);
            
        }catch(Exception e){
            throw e;
        }
        
    }
    
    
    
    public void eliminarCodigo(Integer codigo) throws Exception {

        try {
            
            if (codigo == null) {
                throw new Exception("Debe indicar el codigo");
            }
            if ( dao.buscarProductoPorCodigo(codigo)  == null){
                throw new Exception ("Codigo no encontrado");
            } 
            
            dao.eliminarProducto(Integer.toString(codigo));
        } catch (Exception e) {
            throw e;
        }
    }
    
    public Producto buscarProductoMasBarto() throws Exception {

        try {

            Producto producto = dao.buscarProductoMasBarato();

            return producto;
        } catch (Exception e) {
            throw e;
        }
    }
    
    public Producto buscarProductoPorCodigo(Integer codigo) throws Exception {
        
        try{
            
            if (codigo == null) {
                throw new Exception("Debe indicar el codigo");
            }
            Producto producto = dao.buscarProductoPorCodigo(codigo);

            return producto;
        }catch (Exception e) {
            throw e;
        }
        
    }
    
    public void imprimirProductoMasBarato() throws Exception {
        
        try {
            
            Producto producto = buscarProductoMasBarto();
            
            if(producto == null){
                throw new Exception("No existen productos para imprimir");
            }else{
                System.out.println(producto.getNombre()+"\t"+producto.getPrecio());
            }
           
        } catch (Exception e) {
            throw e;
        }
    }
    
    
    public Collection<Producto> listarProducto() throws Exception {

        try {

            Collection<Producto> productos = dao.listarProductos();

            return productos;
        } catch (Exception e) {
            throw e;
        }
    }
    
    public Collection<Producto> listarNombresProducto() throws Exception {

        try {

            Collection<Producto> productos = dao.listarNombresProductos();

            return productos;
        } catch (Exception e) {
            throw e;
        }
    }
    
    public Collection<Producto> listarNombresPrecioProducto() throws Exception {

        try {

            Collection<Producto> productos = dao.listarNombresPrecioProductos();

            return productos;
        } catch (Exception e) {
            throw e;
        }
    }
    
    public Collection<Producto> listarProductoPorRangoPrecio() throws Exception {

        try {

            Collection<Producto> productos = dao.listarProductosEnRangoPrecio();

            return productos;
        } catch (Exception e) {
            throw e;
        }
    }
    
    public Collection<Producto> listarProductosPortatil() throws Exception {

        try {

            Collection<Producto> productos = dao.listarProductosPortatil();

            return productos;
        } catch (Exception e) {
            throw e;
        }
    }

    public void imprimirProductos() throws Exception {

        try {

            //Listamos los productos
            Collection<Producto> productos = listarProducto();

            //Imprimimos los productos
            if (productos.isEmpty()) {
                throw new Exception("No existen productos para imprimir");
            } else {
                for (Producto p : productos) {
                    System.out.println(p);
                }
            }
        } catch (Exception e) {
            throw e;
        }
    }
    
    public void imprimirNombresProductos() throws Exception {

        try {

            //Listamos los productos
            Collection<Producto> productos = listarNombresProducto();

            //Imprimimos los productos
            if (productos.isEmpty()) {
                throw new Exception("No existen productos para imprimir");
            } else {
                for (Producto p : productos) {
                    System.out.println(p.getNombre());
                }
            }
        } catch (Exception e) {
            throw e;
        }
    }
    
    public void imprimirNombresPrecioProductos() throws Exception {

        try {

            //Listamos los productos
            Collection<Producto> productos = listarNombresPrecioProducto();

            //Imprimimos los productos
            if (productos.isEmpty()) {
                throw new Exception("No existen productos para imprimir");
            } else {
                
                for (Producto p : productos) {
                    System.out.println(p.getNombre() + "\t\t" + p.getPrecio());
                }
            }
        } catch (Exception e) {
            throw e;
        }
    }
    
    public void imprimirProductosEnRango() throws Exception {

        try {

            //Listamos los productos
            Collection<Producto> productos = listarProductoPorRangoPrecio();

            //Imprimimos los productos
            if (productos.isEmpty()) {
                throw new Exception("No existen productos para imprimir");
            } else {
                for (Producto p : productos) {
                    System.out.println(p);
                }
            }
        } catch (Exception e) {
            throw e;
        }
    }
    
    public void imprimirProductosPortatil() throws Exception {

        try {

            //Listamos los productos
            Collection<Producto> productos = listarProductosPortatil();

            //Imprimimos los productos
            if (productos.isEmpty()) {
                throw new Exception("No existen productos para imprimir");
            } else {
                for (Producto p : productos) {
                    System.out.println(p);
                }
            }
        } catch (Exception e) {
            throw e;
        }
    }
    
}
