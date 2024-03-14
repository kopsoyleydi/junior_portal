package com.example.junior_portal.data.impl.news;

import com.example.junior_portal.data.impl.inter.news.NewsRepoInter;
import com.example.junior_portal.data.repository.NewsRepository;
import com.example.junior_portal.model.News;
import com.example.junior_portal.util.filter.FilterUtilForNews;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class NewsRepoInterImpl implements NewsRepoInter {

    private final NewsRepository newsRepository;
    @Override
    public News addNewNews(News news) {
        return newsRepository.save(news);
    }

    @Override
    public News updateNews(News news) {
        return newsRepository.save(news);
    }

    @Override
    public List<News> selectNewsByFilter(FilterUtilForNews filterUtilForNews) {
        return newsRepository.findAllByFilter(filterUtilForNews.getTitle(),
                filterUtilForNews.getCreated_at().toString(), filterUtilForNews.getDescription());
    }

    @Override
    public Page<News> getNewsWithPagination(Integer pageNumber, Integer pageSize, String sort) {
        Pageable pageable = null;
        if (sort != null) {
            pageable = PageRequest.of(pageNumber, pageSize, Sort.Direction.ASC, sort);
        } else {
            pageable = PageRequest.of(pageNumber, pageSize);
        }
        return newsRepository.findAll(pageable);
    }


}
