package com.example.demo;

import com.example.demo.Demo1.Student;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JSONTest {
    public static void main(String[] args) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonStr = "{\"id\":12,\"name\":\"zhangsan\",\"age\":33}";

        /*JSON-String--> Object*/
        Student student = objectMapper.readValue(jsonStr, Student.class);
        System.out.println(student);

        /*Object --> JSON-String*/
        String str = objectMapper.writeValueAsString(student);
        System.out.println(str);
    }
}
