package com.example.demo.entyti.specification;

import com.example.demo.entyti.ProductEntity;
import com.example.demo.entyti.equipment.TypeOfEquipment;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
@Setter
public class VacuumCleanerEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "product_id")
    private ProductEntity product;
    private Integer numberOfModes;
    private Integer dustContainerVolume;
}

