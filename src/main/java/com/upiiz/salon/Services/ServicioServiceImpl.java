package com.upiiz.salon.Services;

import com.upiiz.salon.Entities.ServiciosEntity;
import com.upiiz.salon.Repositories.ServiciosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ServicioServiceImpl implements ServicioService {

    @Autowired
    private ServiciosRepository serviciosRepository;

    @Override
    public List<ServiciosEntity> listarServicios() {
        return serviciosRepository.findAll();
    }

    @Override
    public ServiciosEntity guardarServicio(ServiciosEntity servicio) {
        return serviciosRepository.save(servicio);
    }

    @Override
    public ServiciosEntity obtenerServicioPorId(Long id) {
        return serviciosRepository.findById(id).orElse(null);
    }

    @Override
    public ServiciosEntity actualizarServicio(ServiciosEntity servicio) {
        return serviciosRepository.save(servicio);
    }

    @Override
    public void eliminarServicio(Long id) {
        serviciosRepository.deleteById(id);
    }
}