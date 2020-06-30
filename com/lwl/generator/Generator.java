package com.lwl.generator;


import common.ResultJson;

import java.util.Iterator;
import java.util.List;

public class Generator {
    public static final String ENTITY = "entity";
    public static final String DAO = "dao";
    public static final String DAO_IMPL = "daoImpl";
    public static final String SERVICE = "service";
    public static final String SERVICE_IMPL = "serviceImpl";
    public static final String CONTROLLER = "controller";
    public static final String SWAGGER_CONFIG = "swaggerConfig";

    public Generator() {
    }

    public static ResultJson createEntity(String url, BasisInfo bi, boolean isOverrride) {
        String fileUrl = getGeneratorFileUrl(url, bi.getEntityUrl(), bi.getEntityName(), "entity");
        System.out.println(url + "entity.ftl");
        return FreemarkerUtil.createFile(bi, "entity.ftl", fileUrl, isOverrride);
    }

    public static ResultJson createDao(String url, BasisInfo bi, boolean isOverride) {
        String fileUrl = getGeneratorFileUrl(url, bi.getDaoUrl(), bi.getEntityName(), "dao");
        return FreemarkerUtil.createFile(bi, "dao.ftl", fileUrl, isOverride);
    }

    public static ResultJson createDaoImpl(String url, BasisInfo bi, boolean isOverride) {
        String fileUrl = getGeneratorFileUrl(url, bi.getMapperUrl(), bi.getEntityName(), "daoImpl");
        List<PropertyInfo> list = bi.getCis();
        String agile = "";

        PropertyInfo propertyInfo;
        for (Iterator var6 = list.iterator(); var6.hasNext(); agile = agile + propertyInfo.getColumn() + ", ") {
            propertyInfo = (PropertyInfo) var6.next();
        }

        agile = agile.substring(0, agile.length() - 2);
        bi.setAgile(agile);
        return FreemarkerUtil.createFile(bi, "mapper.ftl", fileUrl, isOverride);
    }

    public static ResultJson createService(String url, BasisInfo bi, boolean isOverride) {
        String fileUrl = getGeneratorFileUrl(url, bi.getServiceUrl(), bi.getEntityName(), "service");
        return FreemarkerUtil.createFile(bi, "service.ftl", fileUrl, isOverride);
    }

    public static ResultJson createServiceImpl(String url, BasisInfo bi, boolean isOverride) {
        String fileUrl = getGeneratorFileUrl(url, bi.getServiceImplUrl(), bi.getEntityName(), "serviceImpl");
        return FreemarkerUtil.createFile(bi, "serviceImpl.ftl", fileUrl, isOverride);
    }

    public static ResultJson createController(String url, BasisInfo bi, boolean isOverride) {
        createAbstractController(url, bi, isOverride);
        String fileUrl = getGeneratorFileUrl(url, bi.getControllerUrl(), bi.getEntityName(), "controller");
        return FreemarkerUtil.createFile(bi, "controller.ftl", fileUrl, isOverride);
    }

    public static ResultJson createAbstractController(String url, BasisInfo bi, boolean isOverride) {
        if (!isOverride) {
            return new ResultJson();
        } else {
            String fileUrl = getGeneratorFileUrl(url, bi.getAbstractControllerUrl(), "Abstract", "controller");
            return FreemarkerUtil.createFile(bi, "AbstractController.ftl", fileUrl, isOverride);
        }
    }

    public static ResultJson createSwaggerConfig(String url, BasisInfo bi) {
        String fileUrl = getGeneratorFileUrl(url, bi.getSwaggerConfigUrl(), "Swagger", "swaggerConfig");
        return FreemarkerUtil.createFile(bi, "SwaggerConfig.ftl", fileUrl, false);
    }

    public static String getGeneratorFileUrl(String url, String packageUrl, String entityName, String type) {
        if (type.equals("entity")) {
            return url + pageToUrl(packageUrl) + entityName + ".java";
        } else if (type.equals("dao")) {
            return url + pageToUrl(packageUrl) + entityName + "Dao.java";
        } else if (type.equals("daoImpl")) {
            return url + pageToUrl(packageUrl) + entityName + "Mapper.xml";
        } else if (type.equals("service")) {
            return url + pageToUrl(packageUrl) + entityName + "Service.java";
        } else if (type.equals("serviceImpl")) {
            return url + pageToUrl(packageUrl) + entityName + "ServiceImpl.java";
        } else if (type.equals("controller")) {
            return url + pageToUrl(packageUrl) + entityName + "Controller.java";
        } else {
            return type.equals("swaggerConfig") ? url + pageToUrl(packageUrl) + entityName + "Config.java" : null;
        }
    }

    public static String pageToUrl(String url) {
        return url.replace(".", "/") + "/";
    }
}
