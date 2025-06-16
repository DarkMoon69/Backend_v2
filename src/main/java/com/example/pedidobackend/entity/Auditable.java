package com.example.pedidobackend.entity;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@MappedSuperclass
@Getter
@Setter
public abstract class Auditable {

    @Column(name = "estado", nullable = false)
    private Boolean estado = Boolean.TRUE;

    @CreationTimestamp
    @Column(name = "fechacreacion")
    private LocalDateTime fechaCreacion;

    @UpdateTimestamp
    @Column(name = "fechaupdate")
    private LocalDateTime fechaUpdate;

}
