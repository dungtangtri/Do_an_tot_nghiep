package com.dung.spring.application.payload;


import java.sql.Date;
import java.time.LocalDateTime;

public interface UserProfileInterface {
    Long getUser_id();
    Integer getOrder_id();
    String getUsername();
    String getEmail();
    String getCustomer_name();
    LocalDateTime getCreate_time();
    String getOrder_description();
    Float getAmount();
    String getProduct_name();

}
