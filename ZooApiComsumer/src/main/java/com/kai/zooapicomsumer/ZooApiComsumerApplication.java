package com.kai.zooapicomsumer;

import org.json.simple.JSONObject;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class ZooApiComsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZooApiComsumerApplication.class, args);

        RestTemplate restTemplate = new RestTemplate();

        //POST
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        JSONObject dogJsonObject = new JSONObject();
        dogJsonObject.put("breeds", "bull dog");
        dogJsonObject.put("name", "John");
        dogJsonObject.put("age", "11");
        ResponseEntity<String> response1 = restTemplate.postForEntity("http://127.0.0.1:8080/api/v1/dog", dogJsonObject, String.class);
        System.out.println(response1.getStatusCode());
        System.out.println("===================");

        //GET
        ResponseEntity<String> response2 = restTemplate.getForEntity("http://127.0.0.1:8080/api/v1/dog/name/John", String.class);
        System.out.println(response2.getStatusCode());
        System.out.println(response2.getBody());
        System.out.println("===================");

        ResponseEntity<String> response3 = restTemplate.getForEntity("http://127.0.0.1:8080/api/v1/dog/breeds/bull%20dog", String.class);
        System.out.println(response2.getStatusCode());
        System.out.println(response2.getBody());
        System.out.println("===================");

    }

}
