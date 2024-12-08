package com.api.BlackTechAPI.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_phones")
public class PhoneModel implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "phone_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer phoneId;

    @Column(nullable = false, unique = true, length = 9)
    private String number;

    @Column(nullable = false, length = 2)
    private String areaCode;

    @Column(nullable = false, length = 5)
    private String countryCode;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserModel userModel;
}
