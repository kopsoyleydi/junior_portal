package com.example.junior_portal.data.repository;


import com.example.junior_portal.model.News;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional
public interface NewsRepository extends JpaRepository<News, Long> {

    @Query("select n from News n where (n.description like %:description%) and n.createdTime = :createDate and " +
            "n.title = :title")
    List<News> findAllByFilter(String title, String createDate, String description);
}
