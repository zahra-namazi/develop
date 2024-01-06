package ir.rahgozin.branch.user.domain;

import ir.rahgozin.branch.baseentity.BaseAuditEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Getter
@Setter
@Table(name = "BT_USER")
@EntityListeners(AuditingEntityListener.class)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "USERNAME", length = 150)
    private String userName;

    @Column(name = "NAME", length = 150)
    private String name;

    @Column(name = "ACTIVE")
    private boolean active;

    @Column(name = "DESCRIPTION", length = 300)
    private String description;

    @Embedded
    private BaseAuditEntity baseAuditEntity = new BaseAuditEntity();
}
