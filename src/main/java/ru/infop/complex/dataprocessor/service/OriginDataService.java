package ru.infop.complex.dataprocessor.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.fluent.Form;
import org.apache.http.client.fluent.Request;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Service;
import ru.infop.complex.dataprocessor.dto.CheckRequest;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;

@Service
public class OriginDataService {

    public static final String SET_COOKIE_HEADER = "Set-Cookie";
    private HttpClient client;
    private ObjectMapper objectMapper;

    public static final String SERVICE_DOMAIN = "xn--b1afk4ade.xn--90adear.xn--p1ai";
    private static final String SERVICE_BASE_URL = "https://" + SERVICE_DOMAIN + "/proxy/";
    private static final String CHECK_BASE_URL = SERVICE_BASE_URL + "check/auto/";

    @PostConstruct
    public void init() {
        client = HttpClientBuilder.create().build();
        objectMapper = new ObjectMapper();
    }

    public HttpResponse requestCaptcha(HttpServletResponse servletResponse) {
        HttpGet request = new HttpGet(SERVICE_BASE_URL + "/captcha.jpg");
        try {
            return client.execute(request);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public String sendCheckRequest(CheckRequest checkRequest, String sessionId) {
        try {
            HttpResponse httpResponse = Request.Post(CHECK_BASE_URL + checkRequest.getCheckType())
                    .bodyForm(
                            Form.form().add("vin", checkRequest.getVin())
                            .add("captchaWord", checkRequest.getCaptchaWord())
                            .add("checkType", checkRequest.getCheckType().toString())
                            .build()
                    )
                    .execute()
                    .returnResponse();
            return EntityUtils.toString(httpResponse.getEntity());
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
