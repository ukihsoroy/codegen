package com.luxshare.generator.core;

import freemarker.template.Template;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.Map;

/**
 * description: ServiceGenerator <br>
 * date: 2020/5/31 11:06 <br>
 *
 * @author K.O <br>
 * @version 1.0 <br>
 */
public class ServiceGenerator extends AbstractGenerator {

    private static final String SERVICE_PACKAGE = "/service/";

    private static final String SERVICE_IMPL_PACKAGE = "impl/";

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

            Map<String, Object> params = new java.util.HashMap<>();

            params.put("name", name);
            params.put("comment", comment);
            params.put("entityName", entityName);
            params.put("rootPackage", properties.getBackEnd().getRootPackage() + "." + packageName);

            String javaDir = (
                    moduleRoot + "/"
                            + properties.getBackEnd().getModule()
                            + ROOT_DIR + properties.getBackEnd().getRootPackage().replaceAll("\\.", "/") + "/"
                            + packageName + SERVICE_PACKAGE
            );

            String serviceFileName = javaDir + entityName + "Service.java";

            if (StringUtils.isNotEmpty(serviceFileName)) {
                File dir = new File(javaDir);
                if (!dir.exists()) dir.mkdirs();

                Template template = freemarker.getTemplate(properties.getBackEnd().getServiceTemplate());
                OutputStreamWriter out = new OutputStreamWriter(new FileOutputStream(
                        new File(serviceFileName)), StandardCharsets.UTF_8);
                template.process(params, out);
                out.close();
            }

            String serviceImplFileName = javaDir + SERVICE_IMPL_PACKAGE + entityName + "ServiceImpl.java";

            if (StringUtils.isNotEmpty(serviceImplFileName)) {
                File dir = new File(javaDir + SERVICE_IMPL_PACKAGE);
                if (!dir.exists()) dir.mkdirs();
                Template template = freemarker.getTemplate(properties.getBackEnd().getServiceImplTemplate());
                OutputStreamWriter out = new OutputStreamWriter(new FileOutputStream(
                        new File(serviceImplFileName)), StandardCharsets.UTF_8);
                template.process(params, out);
                out.close();
            }
        }
    }
}
