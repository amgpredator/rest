package com.bogolubov.kap.dao.position.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "position")
public class Position implements Serializable {
    private static final long serialVersionUID = -8454023672688893914L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="position_id_seq")
    @SequenceGenerator(name="position_id_seq",sequenceName="position_id_seq",allocationSize=1)
    @Column(name = "position_id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "code")
    private String code;

}
