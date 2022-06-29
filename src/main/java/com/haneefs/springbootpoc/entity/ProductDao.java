package com.haneefs.springbootpoc.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Table(name = "SBG_PRODUCT")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductDao {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long id;
    String name;
}
