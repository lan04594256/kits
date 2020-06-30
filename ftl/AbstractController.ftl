/**
 * @filename:${entityName}Controller ${createTime}
 * @project ${project}  ${version}
 * Copyright(c) 2020 ${author} Co. Ltd. 
 * All right reserved. 
 */
package ${abstractControllerUrl};

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import ${parentProject}.common.enums.ErrorCode;
import ${parentProject}.common.exception.SystemException;
import com.ydsh.generator.common.JsonResult;
import com.ydsh.generator.common.PageParam;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.util.Date;
import java.util.List;

/**   
 * <p>代码自动生成，请勿修改</p>
 * 
 * <p>说明： ${entityComment}API接口层</P>
 * @version: ${version}
 * @author: ${author}
 *
 */
public class AbstractController<S extends IService<T>, T>{
	
	@Autowired
    protected S baseService;

	/**
	 * @explain 查询对象  <swagger GET请求>
	 * @param   id
	 * @return  JsonResult
	 * @author  ${author}
	 * @time    ${createTime}
	 */
    @RequestMapping(value = "/getById/{id}",method = RequestMethod.GET)
	@ApiOperation(value = "获取对象", notes = "作者：${author}")
	@ApiImplicitParam(paramType="path", name = "id", value = "对象id", required = true, dataType = "Long")
	public JsonResult<T> getById(@PathVariable("id")Long id){
        JsonResult<T> result = new JsonResult<T>();
        T obj = baseService.getById(id);
        if (null != obj) {
            result.success(obj);
        } else {
            result.error("查询对象不存在！");
        }
        return result;
    }
	
	/**
	 * @explain 添加
	 * @param   entity
	 * @return  JsonResult
	 * @author  ${author}
	 * @time    ${createTime}
	 */
    @RequestMapping(value = "/insert",method = RequestMethod.POST)
	@ApiOperation(value = "添加", notes = "作者：${author}")
	public JsonResult<T> insert(@RequestBody T entity){
        JsonResult<T> result = new JsonResult<T>();
        if (null != entity) {
            boolean rsg = baseService.save(entity);
            if (rsg) {
                result.success("添加成功");
            } else {
                result.error("添加失败！");
            }
        } else {
            result.error("请传入正确参数！");
        }
        return result;
    }

	/**
	* @explain 批量添加
	* @param   entityList
	* @return  Boolean
	* @author  ${author}
	* @time    ${createTime}
	*/
	@RequestMapping(value = "/insertBatch",method = RequestMethod.POST)
	@ApiOperation(value = "批量添加", notes = "作者：${author}")
	public JsonResult<T> insertBatch(@RequestBody List<T> entityList){
        JsonResult<T> result = new JsonResult<T>();
        List<T> list = entityList;
        if (null != entityList) {
            boolean rsg = baseService.saveBatch(entityList);
            if (rsg) {
                result.success("添加成功");
            } else {
                result.error("添加失败！");
            }
        } else {
            result.error("请传入正确参数！");
        }
        return result;
    }
	
	/**
	 * @explain 修改
	 * @param   entity
	 * @return  JsonResult
	 * @author  ${author}
	 * @time    ${createTime}
	 */
	@RequestMapping(value = "/updateById",method = RequestMethod.POST)
	@ApiOperation(value = "根据id修改", notes = "作者：${author}")
    public JsonResult<T> updateById(@RequestBody T entity) {
        JsonResult<T> result = new JsonResult<T>();
        if (null != entity) {
            boolean rsg = baseService.updateById(entity);
            if (rsg) {
                result.success("修改成功");
            } else {
                result.error("修改失败！");
            }
        } else {
            result.error("请传入正确参数！");
        }
        return result;
    }
	
	/**
	* @explain 批量修改
	* @param   entityList
	* @return  JsonResult
	* @author  ${author}
	* @time    ${createTime}
	*/
    @RequestMapping(value = "/updateBatchById",method = RequestMethod.POST)
	@ApiOperation(value = "批量根据id修改", notes = "作者：${author}")
    public JsonResult<T> updateBatchById(@RequestBody List<T> entityList) {
        JsonResult<T> result = new JsonResult<T>();
        if (null != entityList) {
            boolean rsg = baseService.updateBatchById(entityList);
            if (rsg) {
                result.success("修改成功");
            } else {
                result.error("修改失败！");
            }
        } else {
            result.error("请传入正确参数！");
        }
        return result;
    }

	/**
	 * @explain 分页条件查询用户   
	 * @param   pageParam
	 * @return  JsonResult
	 * @author  ${author}
	 * @time    ${createTime}
	 */
    @RequestMapping(value = "/getPages",method = RequestMethod.POST)
	@ApiOperation(value = "分页查询", notes = "分页查询返回[IPage<T>],作者：${author}")
    public JsonResult<IPage<T>> getPages(@RequestBody PageParam<T> pageParam) {
        JsonResult<IPage<T>> returnPage = new JsonResult<IPage<T>>();
        Page<T> page = new Page<T>(pageParam.getPageNum(), pageParam.getPageSize());
        QueryWrapper<T> queryWrapper = new QueryWrapper<>();
        //条件构造
        createWrapper(pageParam.getParam(), queryWrapper);
        //分页数据
        IPage<T> pageData = baseService.page(page, queryWrapper);
        returnPage.success(pageData);
        return returnPage;
    }

    /**
     * 正序
     */
    private static final String paramAsc = "paramAsc";
    /**
     * 倒序
     */
    private static final String paramDesc = "paramDesc";
    /**
     * 开始--驼峰
     */
    private static final String Start = "Start";
    /**
     * 开始
     */
    private static final String start = "start";
    /**
     * 结束--驼峰
     */
    private static final String End = "End";
    /**
     * 结束
     */
    private static final String end = "end";

    /**
     * 条件构造
     *
     * @param obj
     * @param queryWrapper
	 * @author  ${author}
	 * @time    ${createTime}
     * @return
     */
    private void createWrapper(Object obj, QueryWrapper<T> queryWrapper) {
        if (obj == null) {
            return;
        }
        Class<? extends Object> clazz = obj.getClass();
        Field[] fields = clazz.getDeclaredFields();
        if (fields != null) {
            for (Field field : fields) {
                if (!field.isAccessible()) {
                    field.setAccessible(true);
                }
                if (Modifier.isFinal(field.getModifiers()) || Modifier.isStatic(field.getModifiers())) {
                    continue;
                }
                try {
                    Object dstObject = new PropertyDescriptor(field.getName(), clazz).getReadMethod().invoke(obj);
                    if (dstObject == null || "".equals(dstObject)) {
                        continue;
                    }
                    boolean flag = field.isAnnotationPresent(TableField.class);
                    if (flag) {
                        TableField tableField = field.getAnnotation(TableField.class);
                        if (!tableField.exist()) {
                            // 非数据库参数
                            if (paramAsc.equals(field.getName())) {
                                // 正序参数
                                if (dstObject != null && dstObject instanceof String[]) {
                                    String[] strings = (String[]) dstObject;
                                    for (int i = 0; i < strings.length; i++) {
                                        strings[i] = com.baomidou.mybatisplus.core.toolkit.StringUtils.camelToUnderline(strings[i]);
                                    }
                                    queryWrapper.orderByAsc(strings);
                                }
                            }
                            if (paramDesc.equals(field.getName())) {
                                // 倒序参数
                                if (dstObject != null && dstObject instanceof String[]) {
                                    String[] strings = (String[]) dstObject;
                                    for (int i = 0; i < strings.length; i++) {
                                        strings[i] = com.baomidou.mybatisplus.core.toolkit.StringUtils.camelToUnderline(strings[i]);
                                    }
                                    queryWrapper.orderByDesc(strings);
                                }
                            }
                            // 区间时间判断
                            if (dstObject instanceof Date) {
                                if (field.getName().contains(Start)) {
                                    queryWrapper.ge(com.baomidou.mybatisplus.core.toolkit.StringUtils.camelToUnderline(field.getName().replace(Start, "")), (Date) dstObject);
                                }
                                if (field.getName().contains(End)) {
                                    queryWrapper.le(com.baomidou.mybatisplus.core.toolkit.StringUtils.camelToUnderline(field.getName().replace(End, "")), (Date) dstObject);
                                }
                            }
                            continue;
                        }
                    }
                    if (dstObject instanceof Date) {
                        // 处理时间条件
                        if (field.getName().contains(start) || field.getName().contains(Start)) {
                            if (dstObject != null && !"".equals(dstObject)) {
                                queryWrapper.ge(com.baomidou.mybatisplus.core.toolkit.StringUtils.camelToUnderline(field.getName()), (Date) dstObject);
                            }
                        } else if (field.getName().contains(end) || field.getName().contains(End)) {
                            if (dstObject != null && !"".equals(dstObject)) {
                                queryWrapper.le(com.baomidou.mybatisplus.core.toolkit.StringUtils.camelToUnderline(field.getName()), (Date) dstObject);
                            }
                        }
                        continue;
                    }
                    queryWrapper.eq(com.baomidou.mybatisplus.core.toolkit.StringUtils.camelToUnderline(field.getName()), dstObject);
                } catch (IntrospectionException e) {
                    throw new SystemException(ErrorCode.SYS_EXCEPTION.getCode(), "通用接口，条件构造封装报错！", e);
                } catch (IllegalAccessException e) {
                    throw new SystemException(ErrorCode.SYS_EXCEPTION.getCode(), "通用接口，条件构造封装报错！", e);
                } catch (InvocationTargetException e) {
                    throw new SystemException(ErrorCode.SYS_EXCEPTION.getCode(), "通用接口，条件构造封装报错！", e);
                }
            }
        }
    }
	
}
