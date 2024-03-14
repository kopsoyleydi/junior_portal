package com.example.junior_portal.data.impl.inter.news;

import com.example.junior_portal.model.News;
import com.example.junior_portal.util.filter.FilterUtilForNews;
import org.springframework.data.domain.Page;

import java.util.List;


public interface NewsRepoInter {

    News addNewNews(News news);

    News updateNews(News news);

    List<News> selectNewsByFilter(FilterUtilForNews filterUtilForNews);

    Page<News> getNewsWithPagination(Integer pageNumber, Integer pageSize, String sort);

}
