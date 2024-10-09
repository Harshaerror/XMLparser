# XMLparser
# VAST XML Parser and Exporter

## Overview

This project is a VAST (Video Ad Serving Template) XML parser and exporter built using **Java**, **Spring Boot**, and **MySQL**. It provides the ability to parse VAST XML from both local files and URLs, convert the data into Java objects, and export the parsed data to JSON format.

### Features:
- **VAST Parsing**: Parse VAST XML data from files or URLs.
- **JSON Export**: Convert parsed VAST XML data to JSON.
- **Persistence**: Store parsed VAST data in a MySQL database using JPA.
- **REST API**: Provide a RESTful API for parsing and exporting VAST data

#Make sure you are using correct Dependencies in my case i have used this XML parsing for above java 21 and spring boot 3.3.4
<!-- XML Parsing (DOM Parsing) -->
    <dependency>
    <groupId>xerces</groupId>
    <artifactId>xercesImpl</artifactId>
    <version>2.12.0</version>
</dependency>
