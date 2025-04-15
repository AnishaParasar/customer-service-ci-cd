


package com.example.customer_services;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;



@RequestMapping("/api")
@RestController
public class CustomerController {

    RestTemplate rest;
    
    @GetMapping("/customers")
    public List<String> getProducts(){
        String[] names = {"Rina", "Mina", "Tina"};
                List<String> nameslist = Arrays.stream(names).collect(Collectors.toList());
        return nameslist;
    }

    @SuppressWarnings("unchecked")
    @GetMapping("/product")
    public List<String> getUserData() {
        rest=new RestTemplate();
        return rest.getForObject("http://localhost:5555/api/product", List.class);
    }
    
}
