package com.upiiz.salon.Services;



import com.upiiz.salon.Entities.VentasEntity;
import com.upiiz.salon.Repositories.VentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class VentaServiceImpl implements VentaService {

    @Autowired
    private VentaRepository ventaRepository;

    @Override
    public List<VentasEntity> listarVentas() {
        return ventaRepository.findAll();
    }

    @Override
    public VentasEntity guardarVenta(VentasEntity venta) {
        return ventaRepository.save(venta);
    }

    @Override
    public VentasEntity obtenerVentaPorId(Long id) {
        return ventaRepository.findById(id).orElse(null);
    }

    @Override
    public VentasEntity actualizarVenta(VentasEntity venta) {
        return ventaRepository.save(venta);
    }

    @Override
    public void eliminarVenta(Long id) {
        ventaRepository.deleteById(id);
    }
}