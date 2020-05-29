package com.luxshare.generator.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * description: GeneratorProperties <br>
 * date: 2020/5/29 23:18 <br>
 *
 * @author K.O <br>
 * @version 1.0 <br>
 */
@ConfigurationProperties(prefix = "codegen")
public class GeneratorProperties {

    public static final String prefix = "t_";

    //实体类生成配置
    public static final EntityProperties entity = new EntityProperties();

    //controller生成配置
    public static final BackendProperties backEnd = new BackendProperties();

}
