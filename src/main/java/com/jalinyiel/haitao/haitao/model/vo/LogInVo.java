package com.jalinyiel.haitao.haitao.model.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class LogInVo implements Serializable {

    private static final long serialVersionUID = 2308143499420817190L;

    private String username;

    private String password;

    public LogInVo(String username, String password) {
    }
}
