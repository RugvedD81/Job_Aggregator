package com.example.Crawler_Service1.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "jobs")
public class Jobs {

    @Id
    private String id;

    private String site;
    private String position;
    private String company;
    private String url;
    private String location;

    @Column(length = 5000)
    private String tags;  //CSV tags
    
    private String date;

}
