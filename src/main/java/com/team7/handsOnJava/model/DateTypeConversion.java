package com.team7.handsOnJava.model;

import lombok.extern.slf4j.Slf4j;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.sql.Date;


@Slf4j
public class DateTypeConversion {

    public Date StringToDateType(String inputDate) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-M-yyyy");
        try {
            return new Date(sdf.parse(inputDate).getTime());
        } catch (ParseException e) {
            log.error("Unable to parse the given date.", e);
        }
        return null;
    }
}
