package com.example.service;

import com.example.utils.VastParser;
import com.example.exception.VastParsingException;
import com.example.model.VastModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class VastService {

    @Autowired
    private VastParser vastParser;

    public VastModel parseFromFile(String filePath)  {
       try{ String xmlContent = vastParser.readXmlFromFile(filePath);
        VastModel vastModel = vastParser.parseVastXml(xmlContent);
        System.out.println(vastModel.getVersion());
        return vastModel;
       }
        catch (Exception e) {
            throw new VastParsingException("Error parsing VAST XML from file: " + e.getMessage(), e);
        }
    }

    public VastModel parseFromUrl(String url)  {
        try{
        String xmlContent = vastParser.readXmlFromUrl(url);
        return vastParser.parseVastXml(xmlContent);
        }
     catch (Exception e) {
        throw new VastParsingException("Error parsing VAST XML from URL: " + e.getMessage(), e);
    }
    }
}
