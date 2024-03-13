package com.example.junior_portal.service.news;

import com.example.junior_portal.data.impl.inter.news.NewsRepoInter;
import com.example.junior_portal.data.mapper.NewsMapper;
import com.example.junior_portal.dtos.dto.NewsDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NewsService {
    private final NewsRepoInter newsRepoInter;

    private final NewsMapper newsMapper;

    public ResponseEntity<?> addNewNews(NewsDto newsDto){
        try {
            return ResponseEntity.ok(newsRepoInter.addNewNews(newsMapper.toModel(newsDto)));
        }
        catch (Exception e){
            return ResponseEntity.internalServerError().body("Something went wrong");
        }
    }
}
