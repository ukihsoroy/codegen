package org.ko.generator;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
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
            "DncDic"
    };

    @Test
    public void generatorEntity () throws Exception {
        entity.executor(tables);
    }

    @Test
    public void generatorController () throws Exception {
        controller.executor(tables);
    }

    @Test
    public void generatorJsonModel() throws Exception {
        jsonModel.executor(tables);
    }

    @Test
    public void generatorRequestPayload() throws Exception {
        requestPayload.executor(tables);
    }

    @Test
    public void generatorCreateViewModel() throws Exception {
        createViewModel.executor(tables);
    }

    @Autowired
    private EntityGenerator entity;

    @Autowired
    private ControllerGenerator controller;

    @Autowired
    private JsonModelGenerator jsonModel;

    @Autowired
    private RequestPayloadGenerator requestPayload;

    @Autowired
    private CreateViewModelGenerator createViewModel;

    @Before
    public void before () {
        SQLServerDataSource dataSource = GeneratorConf.dataSource();
        entity.dataSource(dataSource);
        controller.dataSource(dataSource);
        jsonModel.dataSource(dataSource);
        requestPayload.dataSource(dataSource);
        createViewModel.dataSource(dataSource);
    }

}
