package com.rootable.libraryservice2024.jwt.prop;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties("com.rootable.libraryservice2024") //com.rootable.libraryservice2024 경로 하위 속성들을 지정
public class JwtProp {

    private String secretKey; //인코딩된 secret key

}
