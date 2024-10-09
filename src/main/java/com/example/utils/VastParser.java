package com.example.utils;

import com.example.model.VastModel;


import org.springframework.stereotype.Component;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.*;
import org.xml.sax.InputSource;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;


import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Component
public class VastParser {

    // Read XML from File
    public String readXmlFromFile(String filePath) throws IOException {
        return new String(Files.readAllBytes(Paths.get(filePath)));
    }

    // Read XML from URL
    public String readXmlFromUrl(String urlString) throws IOException {
        @SuppressWarnings("deprecation")
        URL url = new URL(urlString);
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()))) {
            StringBuilder content = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line);
            }
            return content.toString();
        }
    }

    // Parse the XML content to the VastModel object
    public VastModel parseVastXml(String xmlContent) throws Exception {
        VastModel vast = new VastModel();
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse(new InputSource(new StringReader(xmlContent)));

        // Parse VAST version
        // (tag means element)
        Element vastElement = (Element) doc.getElementsByTagName("VAST").item(0);
        String version = vastElement.getAttribute("version");
        vast.setVersion(version);

        // Parse ID
        vast.setVastId(doc.getElementsByTagName("Ad").item(0).getAttributes().getNamedItem("id").getNodeValue());

        // Ensure you have getters and setters for the `vastId` in the VastModel
        // Parse Title and Description
        vast.setTitle(doc.getElementsByTagName("AdTitle").item(0).getTextContent());
        vast.setDescription(doc.getElementsByTagName("Description").item(0).getTextContent());

        // Parse Impressions
        NodeList impressionNodes = doc.getElementsByTagName("Impression");
        List<VastModel.Impression> impressions = new ArrayList<>();
        for (int i = 0; i < impressionNodes.getLength(); i++) {
            Element impressionElement = (Element) impressionNodes.item(i);
            VastModel.Impression impression = new VastModel.Impression();
            impression.setId(impressionElement.getAttribute("id")); // Some may not have an ID
            impression.setUrl(impressionElement.getTextContent().trim());
            impressions.add(impression);
        }
        vast.setImpressions(impressions);

        // Parse Creatives
        NodeList creativeNodes = doc.getElementsByTagName("Creative");
        List<VastModel.Creative> creatives = new ArrayList<>();

        for (int i = 0; i < creativeNodes.getLength(); i++) {
            Element creativeElement = (Element) creativeNodes.item(i);
            VastModel.Creative creative = new VastModel.Creative();

            creative.setId(creativeElement.getAttribute("id"));

            // Parse Companion Banners
            NodeList companionAdNodes = creativeElement.getElementsByTagName("Companion");
            List<VastModel.CompanionBanner> companionBanners = new ArrayList<>();
            for (int j = 0; j < companionAdNodes.getLength(); j++) {
                Element companionAdElement = (Element) companionAdNodes.item(j);
                VastModel.CompanionBanner companionBanner = new VastModel.CompanionBanner();
                companionBanner.setId(companionAdElement.getAttribute("id"));
                companionBanner.setWidth(Integer.parseInt(companionAdElement.getAttribute("width")));
                companionBanner.setHeight(Integer.parseInt(companionAdElement.getAttribute("height")));
                companionBanner.setSource(
                        companionAdElement.getElementsByTagName("StaticResource").item(0).getTextContent().trim());
                     // Parse ClickThrough URL (optional)
            if (companionAdElement.getElementsByTagName("CompanionClickThrough").getLength() > 0) {
                companionBanner.setClickThroughUrl(companionAdElement.getElementsByTagName("CompanionClickThrough").item(0).getTextContent().trim());
            }
                companionBanners.add(companionBanner);
            }
            creative.setCompanionBanners(companionBanners);

            creatives.add(creative);
        }
        vast.setCreatives(creatives);

        return vast;

    }
}
