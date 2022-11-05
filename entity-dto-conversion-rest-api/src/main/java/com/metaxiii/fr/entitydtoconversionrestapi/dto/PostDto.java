package com.metaxiii.fr.entitydtoconversionrestapi.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

@Getter
@Setter
@RequiredArgsConstructor
public class PostDto {
    private Long id;

    private String title;

    private String url;

    private String date;

    private UserDto user;

    public void setSubmissionDate(Date date, String timezone) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone(timezone));
        this.date = simpleDateFormat.format(date);
    }
}
