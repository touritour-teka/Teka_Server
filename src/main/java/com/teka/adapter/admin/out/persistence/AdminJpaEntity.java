package com.teka.adapter.admin.out.persistence;

import com.teka.domain.admin.Admin;
import com.teka.domain.admin.AdminId;
import com.teka.domain.admin.Password;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "tbl_admin")
public class AdminJpaEntity {

    @Column(name = "admin_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Column(nullable = false, unique = true, length = 30)
    private String username;

    @Column(nullable = false)
    private String password;

    public AdminJpaEntity(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public Admin toDomain() {
        return new Admin(new AdminId(this.id), this.username, new Password(this.password));
    }

    public static AdminJpaEntity from(Admin admin) {
        return new AdminJpaEntity(admin.getUsername(), admin.getPassword().value());
    }
}
