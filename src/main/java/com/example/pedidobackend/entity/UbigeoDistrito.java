package com.example.pedidobackend.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
@Table(name = "ubigeo_dist")
public class UbigeoDistrito extends Auditable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_ubigeo_dist")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_ubigeo_prov")
    private UbigeoProvincia ubigeoProvincia;

    @Column(name = "descripcion", length = 100)
    private String descripcion;

    @Column(name = "codigo", length = 2)
    private String codigo;

}
