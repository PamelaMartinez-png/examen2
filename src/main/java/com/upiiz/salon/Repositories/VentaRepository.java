package com.upiiz.salon.Repositories;


import com.upiiz.salon.Entities.VentasEntity;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VentaRepository extends ListCrudRepository<VentasEntity, Long> {
}