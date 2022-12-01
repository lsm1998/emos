package com.lsm1998.football.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class BettingDto
{
    private Long id;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createdTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updatedTime;

    private Long userId;

    private String content;

    private Integer version;

    private Integer amount;

    private String schedule;

    private Integer status;

    private String realName;
}
