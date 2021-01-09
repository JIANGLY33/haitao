package com.jalinyiel.haitao.haitao.model.vo;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class LogInResponse implements Serializable {

    private static final long serialVersionUID = -3243519221994039357L;

    public String logInTag;

    public String username;
}
