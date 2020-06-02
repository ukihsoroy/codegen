package org.ko.generator.properties;

/**
 * description: BackendProperties <br>
 * date: 2020/5/29 23:13 <br>
 *
 * @author K.O <br>
 * @version 1.0 <br>
 */
public class BackendProperties {

    private String controllerTemplate = "Controller.cs.ftl";

    private String entitiesTemplate = "Entities.cs.ftl";

    private String jsonModelTemplate = "JsonModel.cs.ftl";

    private String requestPayloadTemplate = "RequestPayload.cs.ftl";

    private String createViewModelTemplate = "CreateViewModel.cs.ftl";

    public String getControllerTemplate() {
        return controllerTemplate;
    }

    public void setControllerTemplate(String controllerTemplate) {
        this.controllerTemplate = controllerTemplate;
    }

    public String getEntitiesTemplate() {
        return entitiesTemplate;
    }

    public void setEntitiesTemplate(String entitiesTemplate) {
        this.entitiesTemplate = entitiesTemplate;
    }

    public String getJsonModelTemplate() {
        return jsonModelTemplate;
    }

    public void setJsonModelTemplate(String jsonModelTemplate) {
        this.jsonModelTemplate = jsonModelTemplate;
    }

    public String getRequestPayloadTemplate() {
        return requestPayloadTemplate;
    }

    public void setRequestPayloadTemplate(String requestPayloadTemplate) {
        this.requestPayloadTemplate = requestPayloadTemplate;
    }

    public String getCreateViewModelTemplate() {
        return createViewModelTemplate;
    }

    public void setCreateViewModelTemplate(String createViewModelTemplate) {
        this.createViewModelTemplate = createViewModelTemplate;
    }
}
