package com.webapp.webdemo.entities;

import com.webapp.webdemo.entities.audit.Audit;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Nationalized;

import javax.persistence.*;

@Entity
@Setter
@Getter
@Table(name = "DOCUMENT")
public class Document extends Audit {
    private static final long serialVersionUID = -5056032453468583578L;

    @Id
    @SequenceGenerator(sequenceName = "DOCUMENT_NO_SEQ", name = "SEQ_DOCUMENT_NO_GEN", allocationSize = 1)
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "SEQ_DOCUMENT_NO_GEN"
    )
    @Column(name = "DOCUMENT_NO")
    private Long documentNo;

    @Column(name = "FILE_NAME")
    @Nationalized
    private String fileName;

    @Column(name = "URL")
    private String url;
}
