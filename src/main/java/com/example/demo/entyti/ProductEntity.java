package com.example.demo.entyti;

import com.example.demo.entyti.equipment.TypeOfEquipment;
import com.example.demo.entyti.specification.*;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table (name = "products")
public class ProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String manufacturer;
    private String countryOfOrigin;
    private Boolean orderOnline;
    private Boolean installmentPlan;
    private Long serialNumber;
    private String color;
    private double price;
    @ManyToOne
    @JoinColumn(name = "size_id")
    private Size size;
    @ManyToOne
    @JoinColumn(name = "type")
    private TypeOfEquipment typeOfEquipment;
}
