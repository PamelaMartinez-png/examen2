package com.upiiz.salon.Repositories;

import com.upiiz.salon.Entities.UsuarioEntity;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends ListCrudRepository<UsuarioEntity, Long> {
    @Query("SELECT * FROM usuarios WHERE email = :email")
    Optional<UsuarioEntity> findByEmail(@Param("email") String email);
}