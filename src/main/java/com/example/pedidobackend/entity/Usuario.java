package com.example.pedidobackend.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
@ToString
@Table(name = "usuario")
public class Usuario extends Auditable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private Long id;

    @Column(name = "nombre_usuario", length = 50)
    private String nombreUsuario;

    @Column(name = "contraseña_hash", length = 50)
    private String contraseniaHash;

    @Column(name = "rol", length = 50)
    private String rol;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_operario")
    private Operario operario;

}
