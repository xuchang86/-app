/******************************************************************************
 * Copyright (C) 2016 ShenZhen xiaoyue Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为许畅个人开发研制。未经本人正式书面同意，其他任何个人、团体不得使用、
 * 复制、修改或发布本软件.
 *****************************************************************************/
package com.xiaoyao.base.service;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.xiaoyao.base.annotation.Id;
import com.xiaoyao.base.dao.BaseMapper;
import com.xiaoyao.base.model.BaseVO;

/**
 * 基础服务类
 * 
 * @author 许畅
 * @since JDK1.7
 * @version 2016年8月27日 许畅 新建
 */
public abstract class BaseService<T extends BaseVO> {

	/** Example类后缀名称 */
	private static final String SIMPLE_SUFFIX_NAME = "Example";

	/** queryVOByCondition调用查询接口的方法签名 */
	private static final String queryByCondition_method_name = "selectByExample";

	/** 插入接口方法签名 */
	private static final String INSERT_SELECTIVE = "insertSelective";

	/** 根据主键更新方法签名 */
	private static final String UPDATE_BY_PRIMARY_KEY = "updateByPrimaryKeySelective";

	/** 日志记录 */
	private static final Logger LOGGER = LoggerFactory
			.getLogger(BaseService.class);

	/**
	 * 通过VO组装的条件查询该VO对象集合
	 * <p>
	 * 调用该方法时Model模型对象需继承BaseVO, Mapper DAO接口需继承BaseMapper
	 * </p>
	 * 
	 * <pre>
	 * 该查询接口是根据普通方法进行抽象反射衍化而来:
	 * UserExample example = new UserExample();
	 * example.or().andPhoneEqualTo(user.getPhone());
	 * return userMapperExt.selectByExample(example);
	 * </pre>
	 * 
	 * @param condition
	 *            查询条件
	 * @param mapper
	 *            DAO接口
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<T> queryVOByCondition(T condition, BaseMapper mapper) {
		List<T> result = new ArrayList<T>();
		try {
			Object example = this.createExample(condition);

			Method target = mapper.getClass().getMethod(
					queryByCondition_method_name, example.getClass());
			result = (List<T>) target.invoke(mapper, example);
		} catch (IllegalArgumentException e) {
			LOGGER.error(e.getMessage(), e);
		} catch (IllegalAccessException e) {
			LOGGER.error(e.getMessage(), e);
		} catch (NoSuchMethodException e) {
			LOGGER.error("找不到方法:" + e.getMessage(), e);
		} catch (SecurityException e) {
			LOGGER.error(e.getMessage(), e);
		} catch (ClassNotFoundException e) {
			LOGGER.error("找不到对应的Class:" + e.getMessage(), e);
		} catch (InstantiationException e) {
			LOGGER.error(e.getMessage(), e);
		} catch (InvocationTargetException e) {
			LOGGER.error("Method invoke出错:" + e.getMessage(), e);
		}
		return result;
	}

	/**
	 * 保存VO对象
	 * 
	 * @param vo
	 *            保存的对象
	 * @param mapper
	 *            DAO接口
	 * @return
	 */
	public int saveVO(T vo, BaseMapper mapper) {
		try {
			Field field = getPrimarykey(vo.getClass());
			Object primarykey = field.get(vo);// 主键值
			if (primarykey == null) {
				// 新增
				Method method = mapper.getClass().getMethod(INSERT_SELECTIVE,
						vo.getClass());
				return (Integer) method.invoke(mapper, vo);
			} else {
				// 更新
				Method method = mapper.getClass().getMethod(
						UPDATE_BY_PRIMARY_KEY, vo.getClass());
				return (Integer) method.invoke(mapper, vo);
			}
		} catch (NoSuchFieldException e) {
			LOGGER.error("找不到对应的属性值:" + e.getMessage(), e);
		} catch (SecurityException e) {
			LOGGER.error(e.getMessage(), e);
		} catch (IllegalArgumentException e) {
			LOGGER.error(e.getMessage(), e);
		} catch (IllegalAccessException e) {
			LOGGER.error(e.getMessage(), e);
		} catch (NoSuchMethodException e) {
			LOGGER.error("找不到对应的方法 :" + e.getMessage(), e);
		} catch (InvocationTargetException e) {
			LOGGER.error("Method invoke错误:" + e.getMessage(), e);
		}
		return 0;
	}

	/**
	 * 更新VO对象
	 * 
	 * @param vo
	 *            更新的对象
	 * @param condition
	 *            where条件
	 * @param mapper
	 *            DAO接口
	 * @return
	 */
	public int updateVOByCondition(T vo, T condition, BaseMapper mapper) {

		try {
			Object example = this.createExample(condition);
			Method mapperMethod = mapper.getClass().getMethod(
					"updateByExampleSelective", vo.getClass(),
					example.getClass());
			return (Integer) mapperMethod.invoke(mapper, vo, example);
		} catch (IllegalArgumentException e) {
			LOGGER.error(e.getMessage(), e);
		} catch (IllegalAccessException e) {
			LOGGER.error(e.getMessage(), e);
		} catch (NoSuchMethodException e) {
			LOGGER.error("找不到方法:" + e.getMessage(), e);
		} catch (SecurityException e) {
			LOGGER.error(e.getMessage(), e);
		} catch (ClassNotFoundException e) {
			LOGGER.error("找不到对应的Class:" + e.getMessage(), e);
		} catch (InstantiationException e) {
			LOGGER.error(e.getMessage(), e);
		} catch (InvocationTargetException e) {
			LOGGER.error("Method invoke出错:" + e.getMessage(), e);
		}
		return 0;
	}

	/**
	 * 创建Example条件语句(目前只针对equals To "=",这种条件后续在优化改进)
	 * 
	 * @param condition
	 *            查询条件值
	 * @return
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 * @throws ClassNotFoundException
	 * @throws InstantiationException
	 */
	private Object createExample(T condition) throws NoSuchMethodException,
			SecurityException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException,
			ClassNotFoundException, InstantiationException {
		// 条件值映射关系
		Map<String, Object> values = new HashMap<String, Object>();

		Field[] fields = condition.getClass().getDeclaredFields();
		for (Field field : fields) {
			field.setAccessible(true);
			if (field.get(condition) != null) {
				values.put(field.getName(), field.get(condition));
			}
		}

		Class<?> examleCls = Class
				.forName(wrapperExampleFullClassName(condition));

		// 创建example实例
		Object example = examleCls.newInstance();

		Method method = example.getClass().getMethod("or");
		Object criteria = method.invoke(example);

		Set<String> keys = values.keySet();
		for (String key : keys) {
			Object value = values.get(key);
			Method conditionMethod = criteria.getClass().getMethod(
					wrapperExampleName(key), value.getClass());
			criteria = conditionMethod.invoke(criteria, value);
		}

		return example;
	}

	/**
	 * 获取主键信息,如果没有Id注解则取"id"
	 * 
	 * @param cls
	 * @return
	 * @throws NoSuchFieldException
	 * @throws SecurityException
	 */
	private Field getPrimarykey(Class<?> cls) throws NoSuchFieldException,
			SecurityException {
		Field[] fields = cls.getDeclaredFields();
		for (Field field : fields) {
			if (field.isAnnotationPresent(Id.class))
				return field;
		}
		return cls.getDeclaredField("id");
	}

	/**
	 * 包装exmpale方法条件名称
	 * 
	 * @param propertyName
	 * @return
	 */
	private String wrapperExampleName(String propertyName) {
		StringBuffer sb = new StringBuffer();
		sb.append("and");
		sb.append(StringUtils.capitalize(propertyName));
		sb.append("EqualTo");
		return sb.toString();
	}

	/**
	 * 包装example的class全名称
	 * 
	 * @param vo
	 * @return
	 */
	private String wrapperExampleFullClassName(T vo) {
		return vo.getClass().getPackage().getName() + "."
				+ vo.getClass().getSimpleName() + SIMPLE_SUFFIX_NAME;
	}

	/**
	 * 包装返回值
	 * 
	 * @param count
	 *            DAO返回的值
	 * @return
	 */
	public boolean wrapperReturnVal(int count) {
		return count == 1 ? true : false;
	}

	/**
	 * 设置分页所需的参数
	 * 
	 * @param pageSize
	 *            页数
	 * @param pageNo
	 *            页码
	 * @param baseVO
	 */
	public void setPaging(String pageSize, String pageNo, BaseVO baseVO) {
		if (StringUtils.isNotBlank(pageSize)) {
			baseVO.setPageSize(Integer.parseInt(pageSize));
		}

		if (StringUtils.isNotBlank(pageNo)) {
			baseVO.setPageNo(Integer.parseInt(pageNo));
		}
	}

}
