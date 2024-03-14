package com.example.junior_portal.util.filter;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
@Getter
public class FilterUtilForNews {

    private LocalDateTime created_at;

    private String description;

    private String title;

}
