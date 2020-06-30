package generator;

import lombok.Data;

import java.util.List;

@Data
public class BasisInfo {
    private static final long serialVersionUID = 123123L;
    private String project;
    private String parentProject;
    private String author;
    private String version;
    private String dbUrl;
    private String dbName;
    private String dbPassword;
    private String database;
    private String table;
    private String entityName;
    private String objectName;
    private String entityComment;
    private String createTime;
    private String agile;
    private String entityUrl;
    private String daoUrl;
    private String mapperUrl;
    private String serviceUrl;
    private String serviceImplUrl;
    private String abstractControllerUrl;
    private String controllerUrl;
    private String swaggerConfigUrl;
    private String idType;
    private String idJdbcType;
    private List<PropertyInfo> cis;

    public BasisInfo(String project, String parentProject, String author, String version, String dbUrl, String dbName, String dbPassword, String database, String entityUrl, String daoUrl, String mapperUrl, String serviceUrl, String serviceImplUrl, String controllerUrl) {
        this.project = project;
        this.parentProject = parentProject;
        this.author = author;
        this.version = version;
        this.dbUrl = dbUrl;
        this.dbName = dbName;
        this.dbPassword = dbPassword;
        this.database = database;
        this.entityUrl = entityUrl;
        this.daoUrl = daoUrl;
        this.mapperUrl = mapperUrl;
        this.serviceUrl = serviceUrl;
        this.serviceImplUrl = serviceImplUrl;
        this.controllerUrl = controllerUrl;
        this.abstractControllerUrl = controllerUrl + ".base";
    }
    public BasisInfo(String project, String parentProject, String author, String version, String dbUrl, String dbName, String dbPassword, String database, String table, String entityName, String objectName, String entityComment, String createTime, String agile, String entityUrl, String daoUrl, String mapperUrl, String serviceUrl, String serviceImplUrl, String abstractControllerUrl, String controllerUrl, String swaggerConfigUrl, String idType, String idJdbcType, List<PropertyInfo> cis) {
        this.project = project;
        this.parentProject = parentProject;
        this.author = author;
        this.version = version;
        this.dbUrl = dbUrl;
        this.dbName = dbName;
        this.dbPassword = dbPassword;
        this.database = database;
        this.table = table;
        this.entityName = entityName;
        this.objectName = objectName;
        this.entityComment = entityComment;
        this.createTime = createTime;
        this.agile = agile;
        this.entityUrl = entityUrl;
        this.daoUrl = daoUrl;
        this.mapperUrl = mapperUrl;
        this.serviceUrl = serviceUrl;
        this.serviceImplUrl = serviceImplUrl;
        this.abstractControllerUrl = abstractControllerUrl;
        this.controllerUrl = controllerUrl;
        this.swaggerConfigUrl = swaggerConfigUrl;
        this.idType = idType;
        this.idJdbcType = idJdbcType;
        this.cis = cis;
    }
    public BasisInfo() {
    }
}
