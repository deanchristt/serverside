package com.multipro.serverside.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class JobDto {

    private UUID id;
    private String type;
    private String url;
    private String created_at;
    private String company;
    private String company_url;
    private String location;
    private String title;
    private String description;
    private String how_to_apply;
    private String company_logo;
}
