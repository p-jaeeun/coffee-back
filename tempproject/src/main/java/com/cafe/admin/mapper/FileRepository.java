package com.cafe.admin.mapper;

import com.cafe.admin.beans.Add;
import org.springframework.data.jpa.repository.JpaRepository;


public interface FileRepository extends JpaRepository<Add,Long> {
}
