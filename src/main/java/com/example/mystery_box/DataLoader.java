package com.example.mystery_box;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.example.mystery_box.model.Producto;
import com.example.mystery_box.model.Categoria;
import com.example.mystery_box.model.Usuario;
import com.example.mystery_box.model.Rol;
import com.example.mystery_box.model.MetodoPago;
import com.example.mystery_box.model.MetodoEnvio;

import com.example.mystery_box.repository.ProductoRepository;
import com.example.mystery_box.repository.CategoriaRepository;
import com.example.mystery_box.repository.UsuarioRepository;
import com.example.mystery_box.repository.RolRepository;
import com.example.mystery_box.repository.MetodoPagoRepository;
import com.example.mystery_box.repository.MetodoEnvioRepository;

import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.Arrays;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private RolRepository rolRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private MetodoPagoRepository metodoPagoRepository;

    @Autowired
    private MetodoEnvioRepository metodoEnvioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {

        // ----------- ROLES -----------
        List<String> roles = Arrays.asList("ADMIN", "USUARIO");
        for (String r : roles) {
            if (rolRepository.findByNombre(r) == null) {
                Rol rol = new Rol();
                rol.setNombre(r);
                rolRepository.save(rol);
            }
        }

        // ----------- USUARIOS -----------
        if (usuarioRepository.findByCorreo("admin@test.com") == null) {
            Usuario admin = new Usuario();
            admin.setUsername("Administrador");
            admin.setCorreo("admin@test.com");
            admin.setContrasena(passwordEncoder.encode("admin123"));
            admin.setRol(rolRepository.findByNombre("ADMIN"));
            usuarioRepository.save(admin);
        }

        if (usuarioRepository.findByCorreo("usuario@test.com") == null) {
            Usuario user = new Usuario();
            user.setUsername("Usuario");
            user.setCorreo("usuario@test.com");
            user.setContrasena(passwordEncoder.encode("usuario123"));
            user.setRol(rolRepository.findByNombre("USUARIO"));
            usuarioRepository.save(user);
        }

        // ----------- CATEGORIAS -----------
        List<String> categorias = Arrays.asList("Caja Pequeña", "Caja Grande");
        for (String c : categorias) {
            if (categoriaRepository.findByNombre(c) == null) {
                Categoria cat = new Categoria();
                cat.setNombre(c);
                categoriaRepository.save(cat);
            }
        }

        // ----------- PRODUCTOS -----------
        List<String> productos = Arrays.asList("Caja Básica", "Caja Premium", "Caja Deluxe");
        List<Categoria> todasCategorias = categoriaRepository.findAll();
        for (String p : productos) {
            if (productoRepository.findByNombre(p) == null) {
                Producto prod = new Producto();
                prod.setNombre(p);
                prod.setDescripcion(p + " - Contiene sorpresas geniales");
                prod.setPrecio(5000.0);
                prod.setCategoria(todasCategorias.get(0));
                productoRepository.save(prod);
            }
        }

        // ----------- METODOS DE PAGO -----------
        List<String> pagos = Arrays.asList("Débito", "Crédito", "Prepago");
        for (String t : pagos) {
            if (metodoPagoRepository.findByNombre(t) == null) {
                MetodoPago metodo = new MetodoPago();
                metodo.setNombre(t);
                metodoPagoRepository.save(metodo);
            }
        }

        // ----------- METODOS DE ENVIO -----------
        List<String> envios = Arrays.asList("Envío estándar", "Envío express");
        for (String e : envios) {
            if (metodoEnvioRepository.findByNombre(e) == null) {
                MetodoEnvio envio = new MetodoEnvio();
                envio.setNombre(e);
                metodoEnvioRepository.save(envio);
            }
        }

        System.out.println("DataLoader ejecutado: datos iniciales creados si no existían.");
    }
}
