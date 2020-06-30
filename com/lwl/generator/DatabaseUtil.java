package com.lwl.generator;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DatabaseUtil {
    public DatabaseUtil() {
    }
    public static List<Map<String, String>> showTables(BasisInfo bi) {
        List<Map<String, String>> result = new ArrayList();
        Connection conn = null;
        Statement st = null;
        ResultSet rt = null;

        try {
            conn = DriverManager.getConnection(bi.getDbUrl(), bi.getDbName(), bi.getDbPassword());
            st = conn.createStatement();
            rt = st.executeQuery("SHOW TABLE STATUS ");

            while(rt.next()) {
                Map<String, String> map = new HashMap();
                map.put(rt.getString(1), rt.getString(18));
                result.add(map);
            }
        } catch (SQLException var22) {
            var22.printStackTrace();
        } finally {
            if (rt != null) {
                try {
                    rt.close();
                } catch (SQLException var21) {
                    var21.printStackTrace();
                }
            }

            if (st != null) {
                try {
                    st.close();
                } catch (SQLException var20) {
                    var20.printStackTrace();
                }
            }

            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException var19) {
                    var19.printStackTrace();
                }
            }

        }

        return result;
    }
}
