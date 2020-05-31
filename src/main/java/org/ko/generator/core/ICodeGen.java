package org.ko.generator.core;

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
    void executor(String... names) throws Exception;

}
