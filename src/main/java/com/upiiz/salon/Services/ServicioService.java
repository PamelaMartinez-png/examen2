package com.upiiz.salon.Services;

import com.upiiz.salon.Entities.ServiciosEntity;
import java.util.List;

public interface ServicioService {
    List<ServiciosEntity> listarServicios();
    ServiciosEntity guardarServicio(ServiciosEntity servicio);
    ServiciosEntity obtenerServicioPorId(Long id);
    ServiciosEntity actualizarServicio(ServiciosEntity servicio);
    void eliminarServicio(Long id);
}