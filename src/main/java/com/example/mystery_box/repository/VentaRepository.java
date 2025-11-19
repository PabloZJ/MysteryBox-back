package com.example.mystery_box.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.mystery_box.model.Venta;
import java.util.List;
import com.example.mystery_box.model.Estado;

@Repository
public interface VentaRepository extends JpaRepository<Venta, Long> {

    List<Venta> findByUsuarioId(Long usuarioId);
    List<Venta> findByEstadoId(Long estadoId);
    List<Venta> findByMetodoPagoId(Long metodoPagoId);
    List<Venta> findByMetodoEnvioId(Long metodoEnvioId);
    List<Venta> findByEstado (Estado estado);
}
