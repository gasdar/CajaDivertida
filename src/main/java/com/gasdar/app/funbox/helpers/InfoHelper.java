package com.gasdar.app.funbox.helpers;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class InfoHelper {

    public static Map<String, Object> infoResponse(String message, Integer code) {
        Map<String, Object> json = new HashMap<>();
        json.put("responseMessage", message);
        json.put("responseCode", code);
        json.put("date", new Date().toString());
        return json;
    }

}
