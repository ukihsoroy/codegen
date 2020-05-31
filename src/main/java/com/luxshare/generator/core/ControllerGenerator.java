package com.luxshare.generator.core;

import freemarker.template.Template;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.HashMap;

/**
 * description: ControllerGenerator <br>
 * date: 2020/5/29 23:41 <br>
 *
 * @author K.O <br>
 * @version 1.0 <br>
 */
public class ControllerGenerator extends AbstractGenerator {

    private static final String  CONTROLLER_PACKAGE = "/controller/";

    @Override
    public void executor(String... names) throws Exception {
        for (String name : names) {
            //表名字
            String entityName = reformatTable(name, properties.getPrefix());

            //表注释
            String comment = findTableComment(name);

            //包名称
            String packageName = name.replaceFirst(properties.getPrefix(), "").split("_")[0];

            String dirStr = new File(this.getClass().getClassLoader().getResource(".").toURI()).getAbsolutePath();

            int index = dirStr.indexOf("target");
            String moduleRoot = new File(dirStr.substring(0, index)).getParent();


            HashMap<String, Object> params = new HashMap<>();
            params.put("name", name);
            params.put("comment", comment);
            params.put("entityName", entityName);
            params.put("rootPackage", properties.getBackEnd().getRootPackage() + "." + packageName);

            String javaDir = (
                    moduleRoot + "/"
                            + properties.getBackEnd().getModule()
                            + ROOT_DIR + properties.getBackEnd().getRootPackage().replaceAll("\\.", "/") + "/"
                            + packageName + CONTROLLER_PACKAGE
            );

            String controllerFileName = javaDir + entityName + "Controller.java";

            if (StringUtils.isNotEmpty(controllerFileName)) {
                File dir = new File(javaDir);
                if (!dir.exists()) {
                    dir.mkdirs();
                }
                Template template = freemarker.getTemplate(properties.getBackEnd().getControllerTemplate());
                OutputStreamWriter out = new OutputStreamWriter(new FileOutputStream(new File(controllerFileName)), "UTF-8");
                template.process(params, out);
                out.close();
            }
        }


    }
}
