package com.webapp.webdemo.entities.audit;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.time.LocalDateTime;

@Setter
@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class Audit implements Serializable {
    private static final long serialVersionUID = 239211924032207430L;

    @CreatedBy
    @Column(name = "CREATED_BY", length = 15, nullable = false)
    private String createdBy;

    @CreatedDate
    @Column(name = "CREATED_ON", nullable = false)
    private LocalDateTime createdOn;

    @LastModifiedBy
    @Column(name = "MODIFIED_BY", length = 15, nullable = false)
    private String modifiedBy;

    @LastModifiedDate
    @Column(name = "MODIFIED_ON", nullable = false)
    private LocalDateTime modifiedOn;
}
