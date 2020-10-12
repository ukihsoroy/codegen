package org.ko.generator.core;


import org.ko.generator.entity.Column;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SchemaGenerator extends AbstractGenerator {
    @Override
    public void executor(String... names) throws Exception {
        for (String name : names) {
            //全部字段
            List<Column> columns = findColumnByTableName(name);
            //表名字
            String entityName = reformatTable(name, properties.getPrefix());
            System.out.print(entityName);
            columns.forEach(x -> System.out.print("|" + x.getColumnName()));
        }
    }
}
