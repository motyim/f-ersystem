package me.ersystem.service;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * @author MotYim mohamed.motyim@gmail.com
 * @since 22-Mar-2019
 */
@Service
public class MailService {

    @Value("${mailgun.app-base-url}")
    private String API_BAES_URL;

    @Value("${mailgun.app-key}")
    private String API_KEY;


    public void sendMail(String mail,String subject,String text){
        HttpResponse<JsonNode> request = null;
        try {
            request = Unirest.post(API_BAES_URL)
                    .basicAuth("api", API_KEY)
                    .field("from", "Admin <USER@YOURDOMAIN.COM>")
                    .field("to", mail)
                    .field("subject", subject)
                    .field("text", text)
                    .asJson();
            request.getBody();
        } catch (UnirestException e) {
            e.printStackTrace();
        }

    }
}
