package com.webapp.webdemo.repository;

import com.webapp.webdemo.entities.Document;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DocumentRepository extends JpaRepository<Document, Long> {
}
