package com.api.BlackTechAPI.model;

import com.api.BlackTechAPI.enums.RoleName;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import java.io.Serial;
import java.io.Serializable;
import java.util.UUID;

@Data
@NoArgsConstructor
@Entity
@Table(name = "tb_roles")
public class RoleModel implements Serializable, GrantedAuthority {

    @Serial
    private static final long serialVersionUID = 1L;

    public RoleModel(RoleName roleName) {
        this.roleName = roleName;
    }

    @Id
    @Column(name = "role_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID roleId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, unique = true)
    private RoleName roleName;

    @Override
    public String getAuthority() {
        return roleName.toString();
    }
}
