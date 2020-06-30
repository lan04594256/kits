package generator;


public class MySqlToJavaUtil {
    public MySqlToJavaUtil() {
    }

    public static String getClassName(String table) {
        table = changeToJavaFiled(table);
        StringBuilder sbuilder = new StringBuilder();
        char[] cs = table.toCharArray();
        cs[0] = (char) (cs[0] - 32);
        sbuilder.append(String.valueOf(cs));
        return sbuilder.toString();
    }

    public static String changeToJavaFiled(String field) {
        String[] fields = field.split("_");
        StringBuilder sbuilder = new StringBuilder(fields[0]);

        for (int i = 1; i < fields.length; ++i) {
            char[] cs = fields[i].toCharArray();
            cs[0] = (char) (cs[0] - 32);
            sbuilder.append(String.valueOf(cs));
        }

        return sbuilder.toString();
    }

    public static String jdbcTypeToJavaType(String sqlType) {
        MySqlTypeConvert typeConvert = new MySqlTypeConvert();
        return typeConvert.processTypeConvert(DateType.ONLY_DATE, sqlType).getType();
    }
}
