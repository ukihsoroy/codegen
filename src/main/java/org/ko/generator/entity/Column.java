package org.ko.generator.entity;

public class Column {

    public Column(String columnName, String columnType, String propertyType, int length) {
        this.columnName = columnName;
        this.columnType = columnType;
        this.propertyType = propertyType;
        this.length = length;
    }

    private String columnName;
    private String columnType;
    private String propertyType;
    private int length;

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public String getColumnType() {
        return columnType;
    }

    public void setColumnType(String columnType) {
        this.columnType = columnType;
    }

    public String getPropertyType() {
        return propertyType;
    }

    public void setPropertyType(String propertyType) {
        this.propertyType = propertyType;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }
}
