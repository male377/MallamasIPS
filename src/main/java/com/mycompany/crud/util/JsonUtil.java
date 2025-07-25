package com.mycompany.crud.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletResponse;

public class JsonUtil {
    
    private static final ObjectMapper objectMapper = new ObjectMapper();
    
    public static void writeJsonResponse(HttpServletResponse response, Object object) throws IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        
        String jsonString = objectMapper.writeValueAsString(object);
        
        PrintWriter out = response.getWriter();
        out.print(jsonString);
        out.flush();
    }
    
    public static <T> T parseJsonRequest(String json, Class<T> clazz) throws IOException {
        return objectMapper.readValue(json, clazz);
    }
}