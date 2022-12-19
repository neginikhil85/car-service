package com.learning.entities;

import com.learning.enums.InventoryType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "inventory")
public class Inventory {
    @Id
    private Long id;
    private String location;
    @Enumerated(EnumType.STRING)
    private InventoryType type;
}
