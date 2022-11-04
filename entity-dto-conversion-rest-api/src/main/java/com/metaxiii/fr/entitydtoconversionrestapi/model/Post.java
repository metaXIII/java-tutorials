package com.metaxiii.fr.entitydtoconversionrestapi.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity
@Getter
@Setter
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String title;

    private String url;

    private String date;

    private String redditID;

    private Date submissionDate;

    private boolean sent;

    private String userName;
}
