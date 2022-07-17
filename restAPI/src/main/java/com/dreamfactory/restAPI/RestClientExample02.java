package com.dreamfactory.restAPI;

import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

public class RestClientExample02 {
    public static void main(String[] args) {
        RestTemplate restTemplate = new RestTemplate(
                new HttpComponentsClientHttpRequestFactory()
        );
        UriComponents uriComponents = UriComponentsBuilder
                .newInstance()
                .scheme("http")
                .host("worldtimeapi.org")
                        .port(80)
                        .path("/api/timezone/{continents}/{city}")
                        .encode()
                        .build();
        URI uri = uriComponents.expand("Asia", "Seoul").toUri();
        //WorldTime 클래스를 사용해서 전체 응답 데이터가 아니라 datetime과 timezone 정보만 전달 받는다
//        WorldTime worldTime = restTemplate.getForObject(uri, WorldTime.class);

//        System.out.println("# datatime: " + worldTime.getDatetime());
//        System.out.println("# timezone: " + worldTime.getTimezone());
//        System.out.println("# day_of_week: " + worldTime.getDay_of_week());

        //getForEntity()를 사용한 Response Body(바디, 컨텐츠) + Header(헤더) 정보 전달 받기
//        ResponseEntity<WorldTime> response = restTemplate.getForEntity(uri, WorldTime.class);
//        System.out.println("# datatime: " + response.getBody().getDatetime());
//        System.out.println("# timezone: " + response.getBody().getTimezone());
//        System.out.println("# day_of_week: " + response.getBody().getDay_of_week());
//        System.out.println("# HTTP Status Code: " + response.getStatusCode());
//        System.out.println("# HTTP Status Value: " + response.getStatusCodeValue());
//        System.out.println("# Content Type: " + response.getHeaders().getContentType());
//        System.out.println(response.getHeaders().entrySet());

        //exchange() 를 사용한 응답 데이터 받기
        ResponseEntity<WorldTime> response = restTemplate.exchange(
                uri, HttpMethod.GET, null, WorldTime.class);
        System.out.println("# datatime: " + response.getBody().getDatetime());
        System.out.println("# timezone: " + response.getBody().getTimezone());
        System.out.println("# day_of_week: " + response.getBody().getDay_of_week());
        System.out.println("# HTTP Status Code: " + response.getStatusCode());
        System.out.println("# HTTP Status Value: " + response.getStatusCodeValue());
    }
}
