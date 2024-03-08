package com.example.junior_portal.data.mapper;

import com.example.junior_portal.dtos.dto.NewsDto;
import com.example.junior_portal.model.News;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class NewsMapper {

    private final ProfileMapper profileMapper;

    public NewsDto toDto(News news){
        NewsDto newsDto = new NewsDto();
        newsDto.setId(news.getId());
        newsDto.setDescription(news.getDescription());
        newsDto.setTitle(news.getTitle());
        newsDto.setAuthor(profileMapper.toDto(news.getAuthor()));
        newsDto.setImageUrl(news.getImageUrl());
        newsDto.setCreatedTime(news.getCreatedTime());
        return newsDto;
    };

    public News toModel(NewsDto newsDto){
        News news = new News();
        news.setAuthor(profileMapper.toModel(newsDto.getAuthor()));
        news.setDescription(newsDto.getDescription());
        news.setId(newsDto.getId());
        news.setTitle(newsDto.getTitle());
        news.setImageUrl(newsDto.getImageUrl());
        news.setCreatedTime(newsDto.getCreatedTime());
        return news;
    };

    public List<NewsDto> toDtoList(List<News> news) {
        return news.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public List<News> toModelList(List<NewsDto> newsDtos) {
        return newsDtos.stream()
                .map(this::toModel)
                .collect(Collectors.toList());
    }
}
