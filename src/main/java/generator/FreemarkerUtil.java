package generator;

import common.ResultJson;
import freemarker.template.Configuration;
import freemarker.template.Template;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * @author Administrator
 */
public class FreemarkerUtil {
    public FreemarkerUtil() {
    }
    public static ResultJson createFile(BasisInfo dataModel, String templateName, String filePath, boolean isOverrride) {
        ResultJson result = new ResultJson();
        FileWriter out = null;
        String fileName = dataModel.getEntityName() + messageStr(templateName);

        try {
            Configuration configuration = new Configuration(Configuration.VERSION_2_3_28);
            configuration.setClassForTemplateLoading(FreemarkerUtil.class, "/generator/ftl");
            configuration.setDefaultEncoding("utf-8");
            Template template = configuration.getTemplate(templateName);
            File file = new File(filePath);
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }

            ResultJson var10;
            if (file.exists() && !isOverrride) {
                result.setCode(-1);
                result.setMessage("已经存在" + fileName);
                var10 = result;
                return var10;
            }

            file.createNewFile();
            out = new FileWriter(file);
            template.process(dataModel, out);
            result.setCode(1);
            result.setMessage("创建" + fileName + "成功");
            var10 = result;
            return var10;
        } catch (Exception var21) {
            var21.printStackTrace();
        } finally {
            if (null != out) {
                try {
                    out.close();
                } catch (IOException var20) {
                    var20.printStackTrace();
                }
            }

        }

        result.setCode(-1);
        result.setMessage("创建" + fileName + "失败");
        return result;
    }

    public static String messageStr(String name) {
        if (name.equals("entity.ftl")) {
            name = ".java";
        } else if (name.equals("dao.ftl")) {
            name = "Dao.java";
        } else if (name.equals("mapper.ftl")) {
            name = "Mapper.xml";
        } else if (name.equals("service.ftl")) {
            name = "Service.java";
        } else if (name.equals("serviceImpl.ftl")) {
            name = "ServiceImpl.java";
        } else if (name.equals("controller.ftl")) {
            name = "Controller.java";
        }

        return name;
    }
}
