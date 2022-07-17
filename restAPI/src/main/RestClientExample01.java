package main;

public class RestClientExample01{
    public static void main(String[] args) {

        /*기본적으로 RestTemplate의 객체를 생성하기 위해서는
        RestTemplate의 생성자 파라미터로 HTTP Client 라이브러리의 구현 객체를 전달해야 한다*/

        RestTemplate restTemplate = new RestTemplate(

                /*HttpComponentsClientHttpRequestFactory 클래스를 통해 Apache HttpComponents를 전달한다*/

                new HttpComponentsClientHttpRequestFactory());
    }
}
