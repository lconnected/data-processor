package ru.infop.complex.dataprocessor.controller;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.entity.ContentType;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.infop.complex.dataprocessor.dto.CheckRequest;
import ru.infop.complex.dataprocessor.dto.CheckType;
import ru.infop.complex.dataprocessor.service.OriginDataService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;

import static ru.infop.complex.dataprocessor.service.OriginDataService.SERVICE_DOMAIN;
import static ru.infop.complex.dataprocessor.service.OriginDataService.SET_COOKIE_HEADER;

/**
 * Created by lconnected on 11/06/2018.
 */
@RestController
@RequestMapping("/carservice")
public class EntranceController {

    @Autowired
    private OriginDataService originDataService;

    @GetMapping("/captcha")
    public void showCaptcha(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // todo forward to service url
        response.setContentType(ContentType.IMAGE_PNG.toString());
        HttpResponse httpResponse = originDataService.requestCaptcha(response);
        httpResponse.getEntity().writeTo(response.getOutputStream());
        Header headerSetCookie = httpResponse.getHeaders(SET_COOKIE_HEADER)[0];
        response.setHeader(headerSetCookie.getName(), headerSetCookie.getValue() + "; Domain=" + SERVICE_DOMAIN);
    }

    @PostMapping("/check")
    public void checkAuto(@RequestBody CheckRequest checkRequest, HttpServletRequest request, HttpServletResponse response) throws IOException {
        String result = originDataService.sendCheckRequest(checkRequest, request.getHeader("JSESSIONID"));
        response.setContentType(ContentType.APPLICATION_JSON.toString());
        response.getWriter().write(result);
    }
}
