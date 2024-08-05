package com.inventarios.controlador;

import com.inventarios.excepcion.RecursoNoEncontradoExcepcion;
import com.inventarios.modelo.Producto;
import com.inventarios.servicio.ProductoServicio;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//http://localhost:8080/inventario-app
@RequestMapping("inventario-app") // Definir la URL de comienzo
@CrossOrigin(value = "http://localhost:4200") //Permitir peticiones desde Angular
public class ProductoControlador {
    // Permitir registrar mensajes
    private static final Logger logger = LoggerFactory.getLogger(ProductoControlador.class);

    @Autowired // Inyectar productoServicio
    private ProductoServicio productoServicio;

    //http://localhost:8080/inventario-app/productos
    //Obtener la lista de productos
    @GetMapping("/productos") // Acceder a los metodos con solicitudes HTTP GET
    public List<Producto> listarProductos() {
        List<Producto> productos = productoServicio.listarProductos();
        logger.info("Productos obtenidos: ");
        for (Producto producto : productos) {
            logger.info(producto.toString());
        }
        return productos;
    }

    @PostMapping("/productos")
    public Producto agregarProducto(@RequestBody Producto producto) {
        logger.info("Producto a agregar " + producto);
        return this.productoServicio.guardarProducto(producto);
    }

    @GetMapping("/productos/{id}")
    public ResponseEntity<Producto> obtenerProductoPorId(@PathVariable int id) {
       Producto producto =  this.productoServicio.buscarProductoPorId(id);
       if(producto !=null){
           return ResponseEntity.ok(producto);
       } else{
          throw new RecursoNoEncontradoExcepcion("No se encontro el id");
       }
    }

    @PutMapping("/productos/{id}")
    public ResponseEntity<Producto> actualizarProducto(
            @PathVariable int id, @RequestBody Producto productoRecibido) {
        // Buscar el producto en la base de datos
        Producto producto = this.productoServicio.buscarProductoPorId(id);

        // Verificar si el producto existe
        if (producto == null) {
            return ResponseEntity.notFound().build(); // Retorna un 404 si no se encuentra el producto
        }

        // Actualizar los datos del producto
        producto.setDescripcion(productoRecibido.getDescripcion());
        producto.setPrecio(productoRecibido.getPrecio());
        producto.setExistencia(productoRecibido.getExistencia());

        // Guardar el producto actualizado
        Producto productoActualizado = this.productoServicio.guardarProducto(producto);

        // Retornar el producto actualizado con un código 200 OK
        return ResponseEntity.ok(productoActualizado);
    }

    @DeleteMapping("/productos/{id}")
    public ResponseEntity<Producto> eliminarProducto(@PathVariable int id) {
        Producto producto = this.productoServicio.buscarProductoPorId(id);
        if (producto == null) {
            return ResponseEntity.notFound().build();
        }
        this.productoServicio.eliminarProducto(producto);
        return ResponseEntity.ok().build();
    }


}
