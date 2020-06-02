package org.ko.generator.util;

import java.util.HashMap;
import java.util.Map;

/**
 * description: ConverterSQLTypeHandler <br>
 * date: 2020/5/23 23:09 <br>
 *
 * @author K.O <br>
 * @version 1.0 <br>
 */
public class ConverterSQLTypeHandler {

    private static final Map<String, String> container = new HashMap<String, String>();

    static {
        container.put("nvarchar", "string");
        container.put("char", "string");
        container.put("text", "string");
        container.put("int", "int");
        container.put("tinyint", "byte");
        container.put("smallint", "short");
        container.put("mediumint", "short");
        container.put("bigint", "long");
        container.put("float", "float");
        container.put("double", "double");
        container.put("decimal", "decimal");
        container.put("date", "date");
        container.put("datetime2", "date");
        container.put("timestamp", "date");
        container.put("json", "string");
    }

    public static String format(String key) {
        return container.get(key);
    }

}
