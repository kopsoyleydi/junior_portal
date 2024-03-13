package com.example.junior_portal.data.impl.inter.news;

import com.example.junior_portal.model.News;
import org.springframework.data.domain.Page;


public interface NewsRepoInter {

    News addNewNews(News news);

    News updateNews(News news);

    Page<News> getNewsWithPagination(Integer pageNumber, Integer pageSize, String sort);

}
