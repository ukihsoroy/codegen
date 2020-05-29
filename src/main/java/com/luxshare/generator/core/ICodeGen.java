package com.luxshare.generator.core;

import freemarker.template.TemplateException;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;

/**
 * description: ICodeGen <br>
 * date: 2020/5/23 23:11 <br>
 *
 * @author K.O <br>
 * @version 1.0 <br>
 */
public interface ICodeGen {

    /**
     * 执行生成逻辑
     * @param names 数据库名称
     */
    void executor(String... names) throws URISyntaxException, IOException, TemplateException;

}
