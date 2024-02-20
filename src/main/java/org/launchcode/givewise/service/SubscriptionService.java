package org.launchcode.givewise.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.launchcode.givewise.models.Subscription;
import org.launchcode.givewise.models.data.SubscriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Value;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@Service
public class SubscriptionService {
    @Autowired
    private SubscriptionRepository subscriptionRepository;

    @Value("${zerobounce.apikey}")
    private String zeroBounceApiKey;

    private static final String ZEROBOUNCE_API_URL = "https://api.zerobounce.net/v2/validate";

    public boolean validateAndSubscribe(String email) {
        // Call ZeroBounce API for email validation
        boolean isValid = validateEmailWithZeroBounce(email);

        // If email is valid, add to subscriptions table
        if (isValid) {
            Subscription subscription = new Subscription();
            subscription.setEmail(email);
            subscriptionRepository.save(subscription);
        }

        return isValid;
    }

    private boolean validateEmailWithZeroBounce(String email) {
        // Use Apache HttpClient to make a POST request to ZeroBounce API
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(ZEROBOUNCE_API_URL);

        try {
            List<NameValuePair> params = new ArrayList<>();
            params.add(new BasicNameValuePair("email", email));
            params.add(new BasicNameValuePair("api_key", zeroBounceApiKey));
            httpPost.setEntity(new UrlEncodedFormEntity(params));

            CloseableHttpResponse response = httpClient.execute(httpPost);

            try {
                ObjectMapper objectMapper = new ObjectMapper();
                JsonNode jsonResponse = objectMapper.readTree(response.getEntity().getContent());

                // Check if the email is valid based on the ZeroBounce API response
                return jsonResponse.has("status") && jsonResponse.get("status").asText().equalsIgnoreCase("valid");
            } finally {
                response.close();
            }
        } catch (IOException e) {
            e.printStackTrace(); // Handle exception as needed
            return false;
        } finally {
            try {
                httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
public List<Subscription> listAllSubscribers(){
    return subscriptionRepository.findAllSubscribers();
}
}
