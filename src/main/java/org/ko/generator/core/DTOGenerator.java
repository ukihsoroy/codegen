package org.ko.generator.core;

import freemarker.template.Template;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * description: DTOGenerator <br>
 * date: 2020/5/31 10:37 <br>
 *
 * @author K.O <br>
 * @version 1.0 <br>
 */
@Component
public class DTOGenerator extends AbstractGenerator {

    private static final String DTO_PACKAGE = "/dto/";

    @Override
    public void executor(String... names) throws Exception {
        for (String name : names) {

            //表名字
            String entityName = reformatTable(name, properties.getPrefix());

            //包名称
            String packageName = name.replaceFirst(properties.getPrefix(), "").split("_")[0];

            String dirStr = new File(Objects.requireNonNull(this.getClass().getClassLoader().getResource("."))
                    .toURI()).getAbsolutePath();
            int index = dirStr.indexOf("target");
            String moduleRoot = new File(dirStr.substring(0, index)).getParent();

            String javaDir = (
                    moduleRoot + "/"
                            + properties.getBackEnd().getModule()
                            + ROOT_DIR + properties.getBackEnd().getRootPackage().replaceAll("\\.", "/") + "/"
                            + packageName + DTO_PACKAGE
            );

            String repositoryFileName = javaDir + entityName + "DTO.java";

            Map<String, Object> params = new HashMap<>();

            params.put("name", name);
            params.put("entityName", entityName);
            params.put("rootPackage", properties.getBackEnd().getRootPackage() + "." + packageName);

            if (StringUtils.isNotEmpty(repositoryFileName)) {
                File dir = new File(javaDir);
                if (!dir.exists()) dir.mkdirs();
                Template template = freemarker.getTemplate(properties.getBackEnd().getDtoTemplate());
                OutputStreamWriter out = new OutputStreamWriter(new FileOutputStream(
                        new File(repositoryFileName)), "UTF-8");
                template.process(params, out);
                out.close();
            }
        }
    }
}
