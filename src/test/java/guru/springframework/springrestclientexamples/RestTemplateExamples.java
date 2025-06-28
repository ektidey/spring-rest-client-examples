package guru.springframework.springrestclientexamples;

import com.fasterxml.jackson.databind.JsonNode;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Created by jt on 9/22/17.
 */
public class RestTemplateExamples {

    public static final String API_ROOT = "https://fruitshop2-predic8.azurewebsites.net/shop/v2";

    @Test
    public void getProducts() throws Exception {
        String apiUrl = API_ROOT + "/products/";

        RestTemplate restTemplate = new RestTemplate();

        JsonNode jsonNode = restTemplate.getForObject(apiUrl, JsonNode.class);

        System.out.println("Response");
        System.out.println(jsonNode.toString());
    }

    @Test
    public void createProduct() throws Exception {
        String apiUrl = API_ROOT + "/products";

        RestTemplate restTemplate = new RestTemplate();

        //Java object to parse to JSON
        Map<String, Object> postMap = new HashMap<>();
        postMap.put("name", "Mangos");
        postMap.put("price", 2.79);

        JsonNode jsonNode = restTemplate.postForObject(apiUrl, postMap, JsonNode.class);

        System.out.println("Response");
        System.out.println(jsonNode.toString());
    }

    @Test
    public void updateProduct() throws Exception {

        //create customer to update
        String apiUrl = API_ROOT + "/products";

        RestTemplate restTemplate = new RestTemplate();

        //Java object to parse to JSON
        Map<String, Object> postMap = new HashMap<>();
        postMap.put("name", "Mangos");
        postMap.put("price", 2.79);

        JsonNode jsonNode = restTemplate.postForObject(apiUrl, postMap, JsonNode.class);

        System.out.println("Response");
        System.out.println(jsonNode.toString());

        String id = jsonNode.get("id").toString();

        System.out.println("Created product id: " + id);

        postMap.put("name", "Mangos");
        postMap.put("price", 2.89);

        restTemplate.put(apiUrl + "/" + id, postMap);

        JsonNode updatedNode = restTemplate.getForObject(apiUrl + "/" + id, JsonNode.class);

        System.out.println(updatedNode.toString());

    }

    @Test
    public void updateProductUsingPatch() throws Exception {

        //create customer to update
        String apiUrl = API_ROOT + "/products";

        RestTemplate restTemplate = new RestTemplate();

        //Java object to parse to JSON
        Map<String, Object> postMap = new HashMap<>();
        postMap.put("name", "Mangos");
        postMap.put("price", 2.79);

        JsonNode jsonNode = restTemplate.postForObject(apiUrl, postMap, JsonNode.class);

        System.out.println("Response");
        System.out.println(jsonNode.toString());

        String id = jsonNode.get("id").toString();

        System.out.println("Created product id: " + id);

        postMap.put("name", "Mangos 2");
        postMap.put("price", 2.85);

        //example of setting headers
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(postMap, headers);

        JsonNode updatedNode = restTemplate.patchForObject(apiUrl + "/" + id, entity, JsonNode.class);

        System.out.println(updatedNode.toString());
    }

    @Test
    public void deleteProduct() throws Exception {

        //create customer to update
        String apiUrl = API_ROOT + "/products";

        RestTemplate restTemplate = new RestTemplate();

        //Java object to parse to JSON
        Map<String, Object> postMap = new HashMap<>();
        postMap.put("name", "Mangos");
        postMap.put("price", 2.79);

        JsonNode jsonNode = restTemplate.postForObject(apiUrl, postMap, JsonNode.class);

        System.out.println("Response");
        System.out.println(jsonNode.toString());

        String id = jsonNode.get("id").toString();

        System.out.println("Created product id: " + id);

        restTemplate.delete(apiUrl + "/" + id); //expects 200 status

        System.out.println("Product deleted");

        //should go boom on 404
        assertThrows(HttpClientErrorException.class, () -> restTemplate.getForObject(apiUrl + "/" + id, JsonNode.class));
    }


}