package com.bilgeadam.repository.entity;

import com.bilgeadam.enums.ESpecType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tbl_computer_spec")
public class ComputerSpec {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Enumerated(EnumType.STRING)
    private ESpecType specType;

    private Long computerId;
    private Long userId;

    @Embedded
    private BaseEntity baseEntity;
}
