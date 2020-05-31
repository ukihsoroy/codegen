package com.luxshare.generator.properties;

/**
 * description: EntityProperties <br>
 * date: 2020/5/29 23:16 <br>
 *
 * @author K.O <br>
 * @version 1.0 <br>
 */
public class EntityProperties {

    private String module = "sigma-mysql";

    private final String rootPackage = "com.luxshare.sigma.data";

    private final String entityTemplate = "entity.java.ftl";

    private final String constantsTemplate = "constants.java.ftl";

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }

    public String getRootPackage() {
        return rootPackage;
    }

    public String getEntityTemplate() {
        return entityTemplate;
    }

    public String getConstantsTemplate() {
        return constantsTemplate;
    }
}
