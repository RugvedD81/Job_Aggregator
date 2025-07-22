package com.example.Auth_Service.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Jobs {

    private String site;
    private String position;
    private String company;
    private String url;
    private String location;

    @Column(length = 5000)
    private String tags;  //CSV tags

    private String date;
}
