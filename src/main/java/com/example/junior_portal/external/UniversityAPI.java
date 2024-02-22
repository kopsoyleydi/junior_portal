package com.example.junior_portal.external;


import com.example.junior_portal.dtos.bodies.external.UniversityApiBody;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class UniversityAPI {

    public ResponseEntity<UniversityApiBody> getUniversities(String parameters){
        String url = "http://universities.hipolabs.com/search?name=middle";
        RestTemplate universities = new RestTemplate();
        ResponseEntity<UniversityApiBody> jsonParser = universities.getForEntity(url, UniversityApiBody.class);

        return jsonParser;
    }
}
