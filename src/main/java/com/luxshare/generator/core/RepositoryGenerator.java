package com.luxshare.generator.core;

import com.luxshare.generator.entity.Column;
import freemarker.template.Template;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * description: RepositoryGenerator <br>
 * date: 2020/5/31 10:58 <br>
 *
 * @author K.O <br>
 * @version 1.0 <br>
 */
public class RepositoryGenerator extends AbstractGenerator {

    private static final String REPOSITORY_PACKAGE = "/repository/";


    @Override
    public void executor(String... names) throws Exception {
        for (String name : names) {

            //表名字
            String entityName = reformatTable(name, properties.getPrefix());

            //包名称
            String packageName = name.replaceFirst(properties.getPrefix(), "").split("_")[0];

            Map<String, Object> params = new HashMap<>();
            params.put("name", name);
            params.put("entityName", entityName);
            params.put("rootPackage", properties.getBackEnd().getRootPackage() + "." + packageName);

            String dirStr = new File(Objects.requireNonNull(this.getClass().
                    getClassLoader().getResource(".")).toURI()).getAbsolutePath();

            int index = dirStr.indexOf("target");
            String moduleRoot = new File(dirStr.substring(0, index)).getParent();

            String javaDir = (
                    moduleRoot + "/"
                            + properties.getBackEnd().getModule()
                            + ROOT_DIR + properties.getBackEnd().getRootPackage().replaceAll("\\.", "/") + "/"
                            + packageName + REPOSITORY_PACKAGE
            );

            String repositoryFileName = javaDir + entityName + "Repository.java";

            if (StringUtils.isNotEmpty(repositoryFileName)) {
                File dir = new File(javaDir);
                if (!dir.exists()) dir.mkdirs();
                Template template = freemarker.getTemplate(properties.getBackEnd().getRepositoryTemplate());
                OutputStreamWriter out = new OutputStreamWriter(new FileOutputStream(
                        new File(repositoryFileName)), StandardCharsets.UTF_8);
                template.process(params, out);
                out.close();
            }

            //全部字段
            List<Column> columns = findColumnByTableName(name);
            params.put("columns", columns);

            //表缩写
            String addr = reformatAddr(name, properties.getPrefix());
            params.put("addr", addr);

            String repositoryXMLFileName = javaDir + entityName + "Repository.xml";

            if (StringUtils.isNotEmpty(repositoryXMLFileName)) {
                File dir = new File(javaDir);
                if (!dir.exists()) dir.mkdirs();
                Template template = freemarker.getTemplate(properties.getBackEnd().getRepositoryXMLTemplate());
                OutputStreamWriter out = new OutputStreamWriter(new FileOutputStream(
                        new File(repositoryXMLFileName)), StandardCharsets.UTF_8);
                template.process(params, out);
                out.close();
            }

        }
    }

    private String reformatAddr(String name, String prefix) {
        String[] splits = name.replaceFirst(prefix, "").split("_");
        StringBuilder addr = new StringBuilder();
        for (String x : splits) {
            addr.append(x.charAt(0));
        }
        return addr.toString();
    }
}
