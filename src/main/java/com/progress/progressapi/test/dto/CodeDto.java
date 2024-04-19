package com.progress.progressapi.test.dto;

import lombok.Data;

/**
 * description
 *
 * @author xiaoning.wang
 * @date 2024-04-18 9:40
 */
@Data
public class CodeDto {

    private String code;

    public CodeDto (String code) {
        this.code = code;
    }

}
