package com.upiiz.salon.Services;

import com.upiiz.salon.Entities.VentasEntity;
import java.util.List;

public interface VentaService {
    List<VentasEntity> listarVentas();
    VentasEntity guardarVenta(VentasEntity venta);
    VentasEntity obtenerVentaPorId(Long id);
    VentasEntity actualizarVenta(VentasEntity venta);
    void eliminarVenta(Long id);
}