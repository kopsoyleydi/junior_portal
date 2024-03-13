package com.example.junior_portal.external;


import com.example.junior_portal.dtos.bodies.external.UniversityApiBody;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Component
public class UniversityAPI {

    public ResponseEntity<UniversityApiBody[]> findUniversitiesByNameAndCountry(String name, String country){

        String url = "http://universities.hipolabs.com?name=" + name + "&country=" + country;

        RestTemplate restTemplate = new RestTemplate();

        try {
            ResponseEntity<UniversityApiBody[]> response = restTemplate.getForEntity(url, UniversityApiBody[].class);
            if (response.getStatusCode() == HttpStatus.OK) {
                return response;
            } else {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } catch (RestClientException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
