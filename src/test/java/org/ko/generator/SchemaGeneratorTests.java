package org.ko.generator;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.ko.generator.conf.GeneratorConf;
import org.ko.generator.core.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * description: GeneratorTests <br>
 * date: 2020/5/31 17:36 <br>
 *
 * @author K.O <br>
 * @version 1.0 <br>
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class SchemaGeneratorTests {

    String[] tables = new String[]{
            "t_send_code_log"
    };

    @Test
    public void generatorSchema () throws Exception {
        schema.executor(tables);
    }

    @Autowired
    private SchemaGenerator schema;

    @Before
    public void before () {
        MysqlDataSource dataSource = GeneratorConf.dataSource();
        schema.dataSource(dataSource);
    }


}
