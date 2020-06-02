package org.ko.generator.constants;

/**
 * description: SQLConstants <br>
 * date: 2020/5/23 22:54 <br>
 *
 * @author K.O <br>
 * @version 1.0 <br>
 */
public final class SQLConstants {

    public static final String INFORMATION_SCHEMA_TABLES = "SELECT table_name FROM information_schema.tables WHERE table_schema = ?";

    public static final String INFORMATION_SCHEMA_COLUMNS = "SELECT * FROM information_schema.columns WHERE table_name = ? and COLUMN_NAME not in ('Guid','IsDeleted','CreatedOn','CreatedByUserGuid','CreatedByUserName','ModifiedOn','ModifiedByUserGuid','ModifiedByUserName')";

}
