package com.upiiz.salon.Repositories;


import com.upiiz.salon.Entities.ServiciosEntity;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiciosRepository extends ListCrudRepository<ServiciosEntity, Long> {
}