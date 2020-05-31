package org.ko.generator.core;

import com.google.common.base.CaseFormat;
import org.ko.generator.constants.SQLConstants;
import org.ko.generator.entity.Column;
import org.ko.generator.util.ConverterSQLTypeHandler;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import org.apache.commons.lang3.StringUtils;
import org.ko.generator.constants.SchemaColumnNameConstants;
import org.ko.generator.properties.GeneratorProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import freemarker.template.Configuration;

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

    protected static final String ROOT_DIR = "/src/main/java/";

    private final JdbcTemplate jDBCTemplate = new JdbcTemplate();

    @Autowired protected Configuration freemarker;

    @Autowired protected GeneratorProperties properties;

    private MysqlDataSource mysqlDataSource = null;

    public void dataSource (DataSource dataSource) {
        jDBCTemplate.setDataSource(dataSource);
        if (dataSource instanceof MysqlDataSource) {
            mysqlDataSource = (MysqlDataSource) dataSource;
        }
    }

    private List<String> findTableNames(String database) {
        return jDBCTemplate.queryForList(SQLConstants.INFORMATION_SCHEMA_TABLES, String.class, database);
    }


    protected String findTableComment (String name) {
        String database = mysqlDataSource.getDatabaseName();
        return jDBCTemplate.queryForObject(SQLConstants.INFORMATION_SCHEMA_TABLE_DETAIL, String.class, database, name);
    }

    public List<Column> findColumnByTableName (String name) {
        String database = mysqlDataSource.getDatabaseName();
        return jDBCTemplate.query(SQLConstants.INFORMATION_SCHEMA_COLUMNS, (RowMapper<Column>) (rs, i) -> {

            String columnName = rs.getString(SchemaColumnNameConstants.COLUMN_NAME);
            String columnType = rs.getString(SchemaColumnNameConstants.DATA_TYPE).toLowerCase();
            Integer charLength = toInt(rs.getString(SchemaColumnNameConstants.CHARACTER_MAXIMUM_LENGTH));
            Integer precision = toInt(rs.getString(SchemaColumnNameConstants.NUMERIC_PRECISION));
            Integer scale = toInt(rs.getString(SchemaColumnNameConstants.NUMERIC_SCALE));
            int len = charLength + precision + scale;
            if (scale != 0) len = len + 1;
            return new Column(
                    columnName,
                    mapUnderscoreToCamelCase(columnName),
                    columnType,
                    ConverterSQLTypeHandler.format(columnType),
                    SchemaColumnNameConstants.PRI.equalsIgnoreCase(rs.getString(SchemaColumnNameConstants.COLUMN_KEY)),
                    len,
                    StringUtils.trimToEmpty(rs.getString(SchemaColumnNameConstants.COLUMN_COMMENT))
            );
        }, database, name);
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
        if (StringUtils.isNotBlank(prefix) && name.startsWith(prefix)) {
            return CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, name.replaceFirst(prefix, ""));
        }
        return CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, name);
    }
}
