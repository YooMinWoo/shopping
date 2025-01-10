package com.example.shopping.file.repository;

import com.example.shopping.file.entity.File;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileRepository extends JpaRepository<Long, File> {
    
}
