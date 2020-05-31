package org.ko.generator.properties;

/**
 * description: BackendProperties <br>
 * date: 2020/5/29 23:13 <br>
 *
 * @author K.O <br>
 * @version 1.0 <br>
 */
public class BackendProperties {

    private String module = "sigma-app";

    private String rootPackage = "com.luxshare.sigma.rest";

    private String controllerTemplate = "controller.java.ftl";

    private String serviceTemplate = "service.java.ftl";

    private String serviceImplTemplate = "serviceImpl.java.ftl";

    private String repositoryTemplate = "repository.java.ftl";

    private String repositoryXMLTemplate = "repository.xml.ftl";

    private String conditionTemplate = "condition.java.ftl";

    private String dtoTemplate = "dto.java.ftl";

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }

    public String getRootPackage() {
        return rootPackage;
    }

    public void setRootPackage(String rootPackage) {
        this.rootPackage = rootPackage;
    }

    public String getControllerTemplate() {
        return controllerTemplate;
    }

    public void setControllerTemplate(String controllerTemplate) {
        this.controllerTemplate = controllerTemplate;
    }

    public String getServiceTemplate() {
        return serviceTemplate;
    }

    public void setServiceTemplate(String serviceTemplate) {
        this.serviceTemplate = serviceTemplate;
    }

    public String getServiceImplTemplate() {
        return serviceImplTemplate;
    }

    public void setServiceImplTemplate(String serviceImplTemplate) {
        this.serviceImplTemplate = serviceImplTemplate;
    }

    public String getRepositoryTemplate() {
        return repositoryTemplate;
    }

    public void setRepositoryTemplate(String repositoryTemplate) {
        this.repositoryTemplate = repositoryTemplate;
    }

    public String getRepositoryXMLTemplate() {
        return repositoryXMLTemplate;
    }

    public void setRepositoryXMLTemplate(String repositoryXMLTemplate) {
        this.repositoryXMLTemplate = repositoryXMLTemplate;
    }

    public String getConditionTemplate() {
        return conditionTemplate;
    }

    public void setConditionTemplate(String conditionTemplate) {
        this.conditionTemplate = conditionTemplate;
    }

    public String getDtoTemplate() {
        return dtoTemplate;
    }

    public void setDtoTemplate(String dtoTemplate) {
        this.dtoTemplate = dtoTemplate;
    }
}
