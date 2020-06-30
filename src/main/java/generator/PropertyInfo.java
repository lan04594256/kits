package generator;

import lombok.Data;

@Data
public class PropertyInfo {
    private static final long serialVersionUID = 123124L;
    private String column;
    private String jdbcType;
    private String comment;
    private String property;
    private String javaType;
    protected boolean canEqual(Object other) {
        return other instanceof PropertyInfo;
    }
    @Override
    public String toString() {
        return "PropertyInfo(column=" + this.getColumn() + ", jdbcType=" + this.getJdbcType() + ", comment=" + this.getComment() + ", property=" + this.getProperty() + ", javaType=" + this.getJavaType() + ")";
    }

    public PropertyInfo(String column, String jdbcType, String comment, String property, String javaType) {
        this.column = column;
        this.jdbcType = jdbcType;
        this.comment = comment;
        this.property = property;
        this.javaType = javaType;
    }

    public PropertyInfo() {
    }
}
