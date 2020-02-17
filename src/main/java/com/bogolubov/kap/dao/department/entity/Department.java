package com.bogolubov.kap.dao.department.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "department")
public class Department implements Serializable {

    private static final long serialVersionUID = -8454023672688893914L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="department_id_seq")
    @SequenceGenerator(name="department_id_seq",sequenceName="department_id_seq",allocationSize=1)
    @Column(name = "department_id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "name_short")
    private String nameShort;

    @Column(name = "code")
    private String code;
}
