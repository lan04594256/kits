package com.lwl.generator;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EntityInfoUtil {
    public EntityInfoUtil() {
    }

    public static BasisInfo getInfo(BasisInfo bi) throws SQLException {
        List<PropertyInfo> columns = new ArrayList();
        Connection con = null;
        PreparedStatement pstemt = null;
        ResultSet rs = null;
        String sql = "select column_name,data_type,column_comment from information_schema.columns where table_schema='" + bi.getDatabase() + "' and table_name='" + bi.getTable() + "'";

        try {
            con = DriverManager.getConnection(bi.getDbUrl(), bi.getDbName(), bi.getDbPassword());
            pstemt = con.prepareStatement(sql);

            PropertyInfo ci;
            for(rs = pstemt.executeQuery(); rs.next(); columns.add(ci)) {
                String column = rs.getString(1);
                String jdbcType = rs.getString(2);
                String comment = rs.getString(3);
                ci = new PropertyInfo();
                ci.setColumn(column);
                if (jdbcType.equalsIgnoreCase("int")) {
                    ci.setJdbcType("Integer");
                } else if (jdbcType.equalsIgnoreCase("datetime")) {
                    ci.setJdbcType("timestamp");
                } else {
                    ci.setJdbcType(jdbcType);
                }

                ci.setComment(comment);
                ci.setProperty(MySqlToJavaUtil.changeToJavaFiled(column));
                ci.setJavaType(MySqlToJavaUtil.jdbcTypeToJavaType(jdbcType));
                if (column.equalsIgnoreCase("id")) {
                    bi.setIdType(ci.getJavaType());
                    bi.setIdJdbcType(ci.getJdbcType());
                }
            }

            bi.setCis(columns);
            rs.close();
            pstemt.close();
            con.close();
            BasisInfo var25 = bi;
            return var25;
        } catch (Exception var23) {
            var23.printStackTrace();
            throw new RuntimeException("自动生成实体类错误：" + var23.getMessage());
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException var22) {
            }

            try {
                if (pstemt != null) {
                    pstemt.close();
                }
            } catch (SQLException var21) {
            }

            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException var20) {
                var20.printStackTrace();
            }

        }
    }
}
