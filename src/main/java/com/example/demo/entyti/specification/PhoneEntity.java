package com.example.demo.entyti.specification;

import com.example.demo.entyti.ProductEntity;
import com.example.demo.entyti.equipment.TypeOfEquipment;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Getter
@Setter
public class PhoneEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "product_id")
    private ProductEntity product;
    private Integer memoryCapacity;
    private Integer numberOfCameras;

}
