package com.tdkj.RNS.properties;

import lombok.Data;
import org.springframework.boot.SpringBootConfiguration;

/**
 * @Description
 * @ClassName RnsProperties
 * @Author Chang
 * @date 2020.06.16 16:09
 */
@Data
@SpringBootConfiguration
public class RnsProperties {

    private ValidateCodeProperties code = new ValidateCodeProperties();
}
