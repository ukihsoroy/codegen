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
public class GeneratorTests {

    String[] tables = new String[]{
            "t_send_code_log"
    };



    @Test
    public void generatorEntity () throws Exception {
        entity.executor(tables);
    }

    @Test
    public void generatorRepository () throws Exception {
        repository.executor(tables);
    }

    @Test
    public void generatorCondition () throws Exception {
        condition.executor(tables);
    }

    @Test
    public void generatorDTO () throws Exception {
        dto.executor(tables);
    }

    @Test
    public void generatorService () throws Exception {
        service.executor(tables);
    }

    @Test
    public void generatorController () throws Exception {
        controller.executor(tables);
    }

    @Autowired
    private EntityGenerator entity;

    @Autowired
    private RepositoryGenerator repository;

    @Autowired
    private ConditionGenerator condition;

    @Autowired
    private DTOGenerator dto;

    @Autowired
    private ServiceGenerator service;

    @Autowired
    private ControllerGenerator controller;

    @Before
    public void before () {
        MysqlDataSource dataSource = GeneratorConf.dataSource();
        entity.dataSource(dataSource);
        repository.dataSource(dataSource);
        condition.dataSource (dataSource);
        dto.dataSource(dataSource);
        service.dataSource(dataSource);
        controller.dataSource(dataSource);
    }


}
