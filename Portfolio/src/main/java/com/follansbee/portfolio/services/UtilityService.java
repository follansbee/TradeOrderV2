package com.follansbee.portfolio.services;

import com.follansbee.portfolio.models.Security;
import com.follansbee.portfolio.models.SecurityDetail;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
public class UtilityService {
    public SecurityDetail getSymbolInfo (String security){
        String url = "http://18.219.170.38:8081/symbols/";
        RestTemplate restTemplate = new RestTemplate();
        SecurityDetail sd = new SecurityDetail();
        try{
            sd = restTemplate.getForObject(url + security + "/", SecurityDetail.class);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            sd.setCompanyName("UNKNOWN SYMBOL");
        }

        System.out.println(sd.toString());
        return sd;
    }

    public List<Security> getSymbolInfo() {
        String url = "http://18.219.170.38:8081/symbols/";
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<List<Security>> rateResponse =
                restTemplate.exchange(url,
                        HttpMethod.GET, null, new ParameterizedTypeReference<List<Security>>() {
                        });
        List<Security> symbols = rateResponse.getBody();

//        SecurityDetail[] forNow = restTemplate.getForObject(url, SecurityDetail[].class);
//        List<SecurityDetail>  symbols= Arrays.asList(forNow);
        return symbols;
    }

    //    private SecurityDetail getSecurityDetails(String security) {
//
//        //USE MICROSERVICE
//        RestTemplate restTemplate = new RestTemplate();
//
//        SecurityDetail sd = restTemplate.getForObject("https://cloud.iexapis.com/stable/stock/" + security + "/quote?token=pk_effdc9a83841481482be9bba56ea31e1", SecurityDetail.class);
//
//        return sd;
//    }


}
