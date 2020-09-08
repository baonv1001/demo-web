package com.webapp.webdemo.entities.security;

import com.webapp.webdemo.constants.enums.RoleName;
import com.webapp.webdemo.entities.audit.Audit;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "ROLE")
public class Role extends Audit {
    private static final long serialVersionUID = -4304217258690879468L;

    @Id
    @SequenceGenerator(sequenceName = "ROLE_NO_SEQ", name = "SEQ_ROLE_NO_GEN", allocationSize = 1)
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "SEQ_ROLE_NO_GEN"
    )
    @Column(name = "ROLE_NO")
    private Long roleNo;

    @Column(name = "ROLE_NAME")
    @Enumerated(EnumType.STRING)
    private RoleName roleName;

    @Column(name = "DESCRIPTION", length = 2000)
    private String description;

    /*@ManyToMany(mappedBy = "roles")
    private List<User> users;*/
}
