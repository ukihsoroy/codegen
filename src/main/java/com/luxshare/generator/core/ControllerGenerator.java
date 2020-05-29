package com.luxshare.generator.core;

import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.apache.commons.lang3.StringUtils;

import java.io.*;
import java.net.URISyntaxException;
import java.util.Arrays;
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
    public void executor(String... names) throws URISyntaxException, IOException, TemplateException {
        for (String name : names) {
            //表名字
            String entityName = reformatTable(name, properties.prefix);

            //表注释
            String comment = findTableComment(name);

            //包名称
            String packageName = name.replaceFirst(properties.prefix, "").split("_")[0];

            String dirStr = new File(this.getClass().getClassLoader().getResource(".").toURI()).getAbsolutePath();

            int index = dirStr.indexOf("target");
            String moduleRoot = new File(dirStr.substring(0, index)).getParent();


            HashMap<String, Object> params = new HashMap<>();
            params.put("name", name);
            params.put("comment", comment);
            params.put("entityName", entityName);
            params.put("rootPackage", properties.backEnd.rootPackage + "." + packageName);

            String javaDir = (
                    moduleRoot + "/"
                            + properties.backEnd.module
                            + ROOT_DIR + properties.backEnd.rootPackage.replaceAll("\\.", "/") + "/"
                            + packageName + CONTROLLER_PACKAGE
            );

            String controllerFileName = javaDir + entityName + "Controller.java";

            if (StringUtils.isNotEmpty(controllerFileName)) {
                File dir = new File(javaDir);
                if (!dir.exists()) {
                    dir.mkdirs();
                }
                Template template = freemarker.getTemplate(properties.backEnd.controllerTemplate);
                OutputStreamWriter out = new OutputStreamWriter(new FileOutputStream(new File(controllerFileName)), "UTF-8");
                template.process(params, out);
                out.close();
            }
        }


    }
}
