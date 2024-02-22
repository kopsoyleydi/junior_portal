package com.example.junior_portal.api;

import com.example.junior_portal.dtos.bodies.external.UniversityApiBody;
import com.example.junior_portal.external.UniversityAPI;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("basic/test")
public class TestController {

    private final UniversityAPI universityAPI;

    @GetMapping("/test")
    public UniversityApiBody[] getUniversities(@RequestParam("name") String name,
                                               @RequestParam("country") String country){
        return universityAPI.getUniversities(name, country).getBody();
    }
}
