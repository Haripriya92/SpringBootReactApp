package org.launchcode.givewise.service;

import com.mailjet.client.MailjetClient;
import com.mailjet.client.MailjetRequest;
import com.mailjet.client.MailjetResponse;
import com.mailjet.client.resource.Emailv31;
import org.json.JSONArray;
import org.json.JSONObject;
import org.launchcode.givewise.models.Product;
import org.launchcode.givewise.models.Subscription;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class MailjetService {
    @Autowired
    private SubscriptionService subscriptionService;
    @Value("${mailjet.api-key}")
    private String apiKey;

    @Value("${mailjet.api-secret}")
    private String apiSecret;

    public void sendEmail(String to, String subject, String productName, String productDescription,  BigDecimal productPrice) {
        try {
            MailjetClient client = new MailjetClient(apiKey, apiSecret);
            String htmlContent = "<html><body>" +
                    "<h2>A new product has been added. Check it out!</h2>" +
                    "<p><strong>Name:</strong> " + productName + "</p>" +
                    "<p><strong>Description:</strong> " + productDescription + "</p>" +
                    "<p><strong>Price:</strong> " + productPrice + "</p>" +
                    "</body></html>";

            MailjetRequest request = new MailjetRequest(Emailv31.resource)
                    .property(Emailv31.MESSAGES, new JSONArray()
                            .put(new JSONObject()
                                    .put("From", new JSONObject()
                                            .put("Email", "givewise92@gmail.com"))
                                    .put("To", new JSONArray()
                                            .put(new JSONObject()
                                                    .put("Email", to)))
                                    .put("Subject", subject)
                                    .put("HTMLPart", htmlContent)));

            MailjetResponse response = client.post(request);
            System.out.println(response.getStatus());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void notifySubscriberOnNewProduct(Product product){
List<Subscription> subscribers=subscriptionService.listAllSubscribers();
        for (Subscription subscriber : subscribers) {
            String email = subscriber.getEmail();
            sendEmail(email, "New Product Added", product.getProductName(), product.getProductDescription(), product.getPrice());
        }
    }
}