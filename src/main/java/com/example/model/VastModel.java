package com.example.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
@Table(name = "vast")
@Getter
@Setter
public class VastModel {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Change to Long if needed
    private String VastId;
    private String Title;
    private String Description;

    private String version; // VAST version attribute

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "vast_id") // Foreign key
    private List<Ad> ads; // List of Ads

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "inline_id") // Foreign key
    private List<Creative> creatives; // List of Creative element
    
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "inline_id") // Foreign key
    private List<Impression> impressions; // List of Creative elements






   
   

    @Entity
    @JsonIgnoreProperties(ignoreUnknown = true)
    @Table(name = "ad")
    @Getter
    @Setter
    public static class Ad {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id; // Change to Long if needed

        @OneToOne(cascade = CascadeType.ALL)
        private InLine inLine; // Contains InLine details
    }

    @Entity
    @JsonIgnoreProperties(ignoreUnknown = true)
    @Table(name = "inline")
    @Getter
    @Setter
    public static class InLine {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id; // Change to Long if needed

        private String adSystem;
        private String adTitle; // Use String instead of CDATA
        private String description; // Use String instead of CDATA
        
        private String error; // Include the Error element if needed

        @OneToMany(cascade = CascadeType.ALL)
        @JoinColumn(name = "inline_id") // Foreign key
        private List<Impression> impressions; // Include Impression entities

        @OneToMany(cascade = CascadeType.ALL)
        @JoinColumn(name = "inline_id") // Foreign key
        private List<Creative> creatives;
    }

    @Entity
    @JsonIgnoreProperties(ignoreUnknown = true)
    @Table(name = "impression")
    @Getter
    @Setter
    public static class Impression {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private String id; // Change to Long if needed
        private String impressions;
        private String url; // URL for the impression tracking
       
       
    }

    @Entity
    @JsonIgnoreProperties(ignoreUnknown = true)
    @Table(name = "creative")
    @Getter
    @Setter
    public static class Creative {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private String id; // Change to Long if needed

        @OneToMany(cascade = CascadeType.ALL)
        @JoinColumn(name = "creative_id")
        private List<Companion> companions;

        @OneToOne(cascade = CascadeType.ALL)
        private Linear linear;
    
        @OneToMany(cascade = CascadeType.ALL)
        @JoinColumn(name = "creative_id") // Foreign key in the CompanionBanner table
        private List<CompanionBanner> companionBanners;

        @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "vast_id") // Foreign key
    private List<Creative> creatives;
        
    }

    @Entity
    @JsonIgnoreProperties(ignoreUnknown = true)
    @Table(name = "companion")
    @Getter
    @Setter
    public static class Companion {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id; // Change to Long if needed

        private int width;
        private int height;

        @OneToMany(cascade = CascadeType.ALL)
        @JoinColumn(name = "companion_id") // Foreign key
        private List<StaticResource> staticResources;

        private String clickThroughUrl; // URL for click-through
    }
    @Entity
    @JsonIgnoreProperties(ignoreUnknown = true)
    @Table(name = "companion_banner")
    @Getter
    @Setter
    public static class CompanionBanner {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private String id; // Changed to Long to match the database primary key strategy
        private int width;
        private String CompanionBanners;
        private int height;
        private String type;
        private String source;
        private String clickThroughUrl;


    }

    @Entity
    @JsonIgnoreProperties(ignoreUnknown = true)
    @Table(name = "static_resource")
    @Getter
    @Setter
    public static class StaticResource {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id; // Change to Long if needed

        private String creativeType;
        private String url; // URL of the static resource
    }

    @Entity
    @JsonIgnoreProperties(ignoreUnknown = true)
    @Table
    @Getter
    @Setter
    public static class Linear {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id; // Change to Long if needed

        private String duration;

        @OneToMany(cascade = CascadeType.ALL)
        @JoinColumn(name = "linear_id")
        private List<TrackingEvent> trackingEvents;

        @OneToMany(cascade = CascadeType.ALL)
        @JoinColumn(name = "linear_id")
        private List<MediaFile> mediaFiles;

        @OneToMany(cascade = CascadeType.ALL)
        @JoinColumn(name = "linear_id")
        private List<VideoClick> videoClicks; // Assuming you want to include this
    }

    @Entity
    @JsonIgnoreProperties(ignoreUnknown = true)
    @Table(name = "tracking_event")
    @Getter
    @Setter
    public static class TrackingEvent {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id; // Change to Long if needed

        private String eventType;
        private String url; // URL of the tracking event
    }

    @Entity
    @JsonIgnoreProperties(ignoreUnknown = true)
    @Table(name = "video_click")
    @Getter
    @Setter
    public static class VideoClick {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id; // Change to Long if needed

        private String url; // URL of the video click
    }

    @Entity
    @JsonIgnoreProperties(ignoreUnknown = true)
    @Table(name = "media_file")
    @Getter
    @Setter
    public static class MediaFile {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id; // Change to Long if needed

        private String type;
        private int bitrate;
        private int width;
        private int height;
        private String url; // URL of the media file
    }

   
}
