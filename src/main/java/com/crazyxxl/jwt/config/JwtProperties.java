package com.crazyxxl.jwt.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties("jwt")
public class JwtProperties {

    /**加密秘钥**/
    private String jwtKey;

    private String urlPatterns;

    private String authFailPath;

    private String headerStartTag;

    private String claimsInfo;
}
