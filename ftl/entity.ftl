/**
* @filename:${entityName} ${createTime}
* @project ${project}  ${version}
* Copyright(c) 2020 ${author} Co. Ltd.
* All right reserved.
*/
package ${entityUrl};

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
/**
* <p>代码自动生成，请勿修改</p>
*
* <p>说明： ${entityComment}实体类</P>
* @version: ${version}
* @author: ${author}
*
*/
@Data
@EqualsAndHashCode(callSuper = false)
public class ${entityName} implements Serializable {

private static final long serialVersionUID = ${agile}L;
<#assign x = "">
<#list cis as ci >
    <#if ci.property=="id">
    @TableId(value = "id", type = IdType.AUTO)
    </#if>
    @ApiModelProperty(name = "${ci.property}", value = "${ci.comment}")
    <#if ci.javaType=="Date">
        <#if ci.jdbcType=="date">
    @JSONField(format = "yyyy-MM-dd")
        <#elseif ci.jdbcType=="time">
    @JSONField(format = "HH:mm:ss")
        <#else>
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
        </#if>
    </#if>
    private ${ci.javaType} ${ci.property};
    <#assign x = x+"${ci.property}、">
</#list>

<#list cis as ci >
    <#if ci.property=="id">
    @ApiModelProperty(name = "idBatch", value = "批量主键处理")
    @JSONField(serialize = false)
    @TableField(exist = false)
    private List<${ci.javaType}> idBatch;
    </#if>
    <#if ci.javaType=="Date">
        <#if !"${ci.property}"?contains("start")&&!"${ci.property}"?contains("end")>
    @ApiModelProperty(name = "${ci.property}Start", value = "时间条件参数开始--${ci.comment}")
    @TableField(exist = false)
            <#if ci.jdbcType=="date">
    @JSONField(serialize = false, format = "yyyy-MM-dd")
            <#elseif ci.jdbcType=="time">
    @JSONField(serialize = false, format = "HH:mm:ss")
            <#else>
    @JSONField(serialize = false, format = "yyyy-MM-dd HH:mm:ss")
            </#if>
    private ${ci.javaType} ${ci.property}Start;
    @ApiModelProperty(name = "${ci.property}End", value = "时间条件参数结束--${ci.comment}")
    @TableField(exist = false)
            <#if ci.jdbcType=="date">
    @JSONField(serialize = false, format = "yyyy-MM-dd")
            <#elseif ci.jdbcType=="time">
    @JSONField(serialize = false, format = "HH:mm:ss")
            <#else>
    @JSONField(serialize = false, format = "yyyy-MM-dd HH:mm:ss")
            </#if>
    private ${ci.javaType} ${ci.property}End;
        </#if>
    </#if>
</#list>
    @ApiModelProperty(name = "paramAsc", value = "升序参数：${x}")
    @JSONField(serialize = false)
    @TableField(exist = false)
    private String[] paramAsc;
    @ApiModelProperty(name = "paramDesc", value = "降序参数：${x}")
    @JSONField(serialize = false)
    @TableField(exist = false)
    private String[] paramDesc;
}
	