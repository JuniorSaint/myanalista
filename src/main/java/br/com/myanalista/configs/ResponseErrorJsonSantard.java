package br.com.myanalista.configs;

import org.json.JSONObject;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class ResponseErrorJsonSantard {
    public static String json(String status, String error, String message) {
        long date = new Date().getTime();
        JSONObject json = new JSONObject();
        json.put("timestamp", date + "");
        json.put("status", status);
        json.put("error", error);
        json.put("message", message);
        json.put("path", "/login");
        return json.toString();
    }
}
