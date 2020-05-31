package org.ko.generator.conf;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

/**
 * description: GeneratorConf <br>
 * date: 2020/5/31 17:35 <br>
 *
 * @author K.O <br>
 * @version 1.0 <br>
 */
public class GeneratorConf {

    public static MysqlDataSource dataSource() {
        MysqlDataSource dataSource = new MysqlDataSource();
        dataSource.setDatabaseName("sigma_server");
        dataSource.setPort(3306);
        dataSource.setUser("root");
        dataSource.setPassword("tiger");
        return dataSource;
    }
}
