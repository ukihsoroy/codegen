package org.ko.generator.conf;


import com.microsoft.sqlserver.jdbc.SQLServerDataSource;

/**
 * description: GeneratorConf <br>
 * date: 2020/5/31 17:35 <br>
 *
 * @author K.O <br>
 * @version 1.0 <br>
 */
public class GeneratorConf {

    public static SQLServerDataSource dataSource() {
        SQLServerDataSource dataSource = new SQLServerDataSource();
        dataSource.setServerName("luxshare-sqlserver.database.windows.net");
        dataSource.setDatabaseName("luxshare_dev");
        dataSource.setUser("luxshare");
        dataSource.setPassword("ZAQ!2wsx");
        dataSource.setSSLProtocol("TLSv1");
        return dataSource;
    }
}
