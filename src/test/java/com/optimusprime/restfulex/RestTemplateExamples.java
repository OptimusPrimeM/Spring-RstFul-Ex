package com.optimusprime.restfulex;

import com.fasterxml.jackson.databind.JsonNode;
import org.junit.Test;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

public class RestTemplateExamples {

    private static final String API_ROOT = "https://api.predic8.de/shop";

    @Test
    public void getCategories() throws Exception {
        String apiUrl = API_ROOT+"/categories/";

        RestTemplate restTemplate = new RestTemplate();

        JsonNode jsonNode=  restTemplate.getForObject(apiUrl,JsonNode.class);

        System.out.println("Response");
        if (jsonNode != null) {
            System.out.println(jsonNode.toString());
        }
    }

    @Test
    public void getCustomer() throws Exception {
        String apiUrl = API_ROOT+"/customers/";

        RestTemplate restTemplate = new RestTemplate();

        JsonNode jsonNode = restTemplate.getForObject(apiUrl,JsonNode.class);

        System.out.println("Response");
        if (jsonNode != null) {
            System.out.println(jsonNode.toString());
        }
    }

    @Test
    public void createCustomer() throws Exception {
        String apiUrl = API_ROOT+"/customers/";

        RestTemplate restTemplate= new RestTemplate();

        Map<String , Object> postMap =  new HashMap<>();
        postMap.put("firstname","Menik");
        postMap.put("lastname","Tennakoon");

        JsonNode jsonNode = restTemplate.postForObject(apiUrl, postMap,JsonNode.class);
        System.out.println("Response");
        if (jsonNode != null) {
            System.out.println(jsonNode.toString());
        }
    }

    @Test
    public void updateCustomer() throws Exception {
        String apiUrl =  API_ROOT+"/customers/";

        RestTemplate restTemplate = new RestTemplate();

        Map<String, Object> postMap =  new HashMap<>();
        postMap.put("firstname","John");
        postMap.put("lastname","Nilatha");

        JsonNode jsonNode = restTemplate.postForObject(apiUrl, postMap, JsonNode.class);
        System.out.println("Response");
        if (jsonNode != null) {
            System.out.println(jsonNode.toString());
        }

        if (jsonNode != null) {
            String customerUrl =jsonNode.get("customer_url").textValue();
            String id =customerUrl.split("/")[3];

            System.out.println("Created customer ID = " +id);
            postMap.put("firstname","Optimus");
            postMap.put("lastname","Prime");

            restTemplate.put(apiUrl+id,postMap);

            JsonNode updatedNode =restTemplate.getForObject(apiUrl+id,JsonNode.class);
            if (updatedNode != null) {
                System.out.println(updatedNode.toString());
            }
        }

    }

    @Test
    public void deleteCustomer() throws Exception {

        String apiUrl =  API_ROOT+"/customers/";

        RestTemplate restTemplate = new RestTemplate();

        Map<String, Object> postMap =  new HashMap<>();
        postMap.put("firstname","Iron");
        postMap.put("lastname","Hide");

        JsonNode jsonNode = restTemplate.postForObject(apiUrl, postMap, JsonNode.class);
        System.out.println("Response");
        if (jsonNode != null) {
            System.out.println(jsonNode.toString());

            String customerUrl =jsonNode.get("customer_url").textValue();
            String id =customerUrl.split("/")[3];

            System.out.println("Created customer ID = " +id);

            restTemplate.delete(apiUrl+id);

            System.out.println("Customer deleted");

            restTemplate.getForObject(apiUrl+id, JsonNode.class);
        }


    }
}
