package com.example.pedidobackend.repository;

import com.example.pedidobackend.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Usuario findByNombreUsuarioAndEstadoTrue(String nombreUsuario);

}
