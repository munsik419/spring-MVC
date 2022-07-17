package com.dreamfactory.restAPI;

import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

public class RestClientExample01{
    public static void main(String[] args) {

        /*기본적으로 RestTemplate의 객체를 생성하기 위해서는
        RestTemplate의 생성자 파라미터로 HTTP Client 라이브러리의 구현 객체를 전달해야 한다*/

        RestTemplate restTemplate = new RestTemplate(

                /*HttpComponentsClientHttpRequestFactory 클래스를 통해 Apache HttpComponents를 전달한다*/

                new HttpComponentsClientHttpRequestFactory());

        //URI 생성
        /*
        UriComponentsBuilder 클래스를 이용해서 UriComponents 객체를 생성하고
        UriComponents 객체를 이용해서 HTTP Request를 요청할 엔드포인트의 URI를 생성한다
        */
        UriComponents uriComponents = UriComponentsBuilder

                .newInstance() //UriComponentsBuilder 객체를 생성한다
                .scheme("http") //URI의 scheme을 설정한다
                .host("worldtimeapi.org") //호스트 정보를 입력한다
                .port(80)  //디폴트 값은 80이므로 80 포트를 사용하는 호스트라면 생략 가능하다
                .path("api/timezone/{continents}/{city}")
                /*
                URI의 경로(path)를 입력한다
                URI의 path에서 {continents}, {city} 두 개의 템플릿 변수를 사용하고 있다
                두 개의 템플릿 변수는 uriComponents.expand("Asia", "Seoul").toUri(); 에서
                expand() 메서드 파라미터의 문자열로 채워진다
                빌드 타임에 {continents}는 ‘Asia’, {city}는 ‘Seoul’로 변환됩니다.
                 */
                .encode()
                /*URI에 사용된 템플릿 변수들을 인코딩 한다
                 non-ASCII 문자와 URI에 적절하지 않은 문자를 Percent Encoding 한다는 의미이다
                 */
                .build();
                //UriComponents 객체를 생성한다

        URI uri = uriComponents.expand("Asia","Seoul").toUri();
        /* expand 메서드는 파라미터로 입력한 값을 URI 템플릿 변수의 값으로 대체한다
           toUri 메서드는 객체를 생성한다
         */

        //Request 전송

        //getForObject() 메서드로 HTTP Get 요청을 통해 서버의 리소스를 조회한다
        //응답 데이터를 문자열로 받을 수 있도록 String.class로 지정한다
       String result = restTemplate.getForObject(uri, String.class);
        System.out.println(result);

    }
}
