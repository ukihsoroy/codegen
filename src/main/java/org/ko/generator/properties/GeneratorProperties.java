package org.ko.generator.properties;

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

    private String path;

    private String prefix = "t_";

    //controller生成配置
    private BackendProperties backEnd = new BackendProperties();

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public BackendProperties getBackEnd() {
        return backEnd;
    }

    public void setBackEnd(BackendProperties backEnd) {
        this.backEnd = backEnd;
    }
}
