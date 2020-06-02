package org.ko.generator.core;

import org.ko.generator.entity.Column;
import freemarker.template.Template;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * description: EntityGenerator <br>
 * date: 2020/5/31 10:47 <br>
 *
 * @author K.O <br>
 * @version 1.0 <br>
 */
@Component
public class EntityGenerator extends AbstractGenerator {

    private static final String ENTITY_PACKAGE = "/Entities/Report/";

    @Override
    public void executor(String... names) throws Exception {
        for (String name : names) {
            //全部字段
            List<Column> columns = findColumnByTableName(name);
            //表名字
            String entityName = reformatTable(name, properties.getPrefix());

            String path = properties.getPath() + ENTITY_PACKAGE;

            String entityFileName = path + entityName + ".cs";

            Map<String, Object> params = new HashMap<>();

            params.put("name", name);
            params.put("entityName", entityName);
            params.put("columns", columns);

            if (StringUtils.isNotEmpty(entityFileName)) {
                File dir = new File(path);
                if (!dir.exists()) dir.mkdirs();
                Template template = freemarker.getTemplate(properties.getBackEnd().getEntitiesTemplate());
                OutputStreamWriter out = new OutputStreamWriter(new FileOutputStream(
                        new File(entityFileName)), StandardCharsets.UTF_8);
                template.process(params, out);
                out.close();
            }

        }
    }
}
