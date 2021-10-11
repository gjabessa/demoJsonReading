package com.example.demo.controller;

import com.example.demo.CustomObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/")
public class demoController {
    ObjectMapper om = new ObjectMapper();
    @GetMapping
    public CustomObject returnFile() {
        CustomObject co = null;
        try {
            co = new CustomObject(fetchFileMap(),"success");
        } catch (IOException e) {
            System.out.println("error");
            throw new NoSuchElementException();
        }

        return co;
    }

    public Map fetchFileMap() throws IOException{
        File file;
        file = ResourceUtils.getFile("classpath:apidata.json");
        Map<?, ?> map = om.readValue(file, Map.class);
        return map;

    }


    private String readFromInput(InputStream inputStream) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
        String line;
        while((line= br.readLine()) != null){
            sb.append(line).append("\n");
        }

        return sb.toString();
    }

}
