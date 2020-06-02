package org.ko.generator.core;

import com.google.common.base.CaseFormat;
import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import freemarker.template.Configuration;
import org.apache.commons.lang3.StringUtils;
import org.ko.generator.constants.SQLConstants;
import org.ko.generator.constants.SchemaColumnNameConstants;
import org.ko.generator.entity.Column;
import org.ko.generator.properties.GeneratorProperties;
import org.ko.generator.util.ConverterSQLTypeHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.List;

/**
 * description: AbstractGenerator <br>
 * date: 2020/5/23 22:55 <br>
 *
 * @author K.O <br>
 * @version 1.0 <br>
 */
public abstract class AbstractGenerator implements ICodeGen {

    private final JdbcTemplate jDBCTemplate = new JdbcTemplate();

    @Autowired protected Configuration freemarker;

    @Autowired protected GeneratorProperties properties;

    private SQLServerDataSource sqlServerDataSource = null;

    public void dataSource (DataSource dataSource) {
        jDBCTemplate.setDataSource(dataSource);
        sqlServerDataSource = (SQLServerDataSource) dataSource;
    }

    private List<String> findTableNames(String database) {
        return jDBCTemplate.queryForList(SQLConstants.INFORMATION_SCHEMA_TABLES, String.class, database);
    }

    public List<Column> findColumnByTableName (String name) {
        return jDBCTemplate.query(SQLConstants.INFORMATION_SCHEMA_COLUMNS, (rs, i) -> {

            String columnName = rs.getString(SchemaColumnNameConstants.COLUMN_NAME);
            System.out.println("column name: " + columnName);

            String columnType = rs.getString(SchemaColumnNameConstants.DATA_TYPE).toLowerCase();
            System.out.println("column type: " + columnType);

            String propertyType = ConverterSQLTypeHandler.format(columnType);
            System.out.println("property type: " + propertyType);

            int length = rs.getInt(SchemaColumnNameConstants.CHARACTER_MAXIMUM_LENGTH);

            return new Column(
                    columnName,
                    columnType,
                    propertyType,
                    length
            );
        }, name);
    }

    /**
     *
     * @param value
     * @return
     */
    protected Integer toInt(String value) {
        int res = 0;
        if (StringUtils.isNotEmpty(value)) {
            res = Integer.parseInt(value);
        }
        return res;
    }

    /**
     * 下划线转驼峰
     * @param name
     * @return
     */
    protected String mapUnderscoreToCamelCase(String name) {
        return CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, name);
    }

    /**
     * 下划线去前缀转驼峰
     * @param name
     * @param prefix
     * @return
     */
    protected String reformatTable (String name, String prefix) {
        return name.replaceFirst(prefix, "");
    }
}
