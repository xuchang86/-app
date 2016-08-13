package com.rogrand.sys.service;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.rogrand.core.domain.Base;
import com.rogrand.core.domain.PageParam;
import com.rogrand.core.domain.PageResult;
import com.rogrand.core.service.BaseService;
import com.rogrand.core.util.DateUtil;
import com.rogrand.sys.domain.Log;

/**
 * 
 * 版权：融贯资讯 <br/>
 * 作者：jian.mei@rogrand.com <br/>
 * 生成日期：2013-10-24 <br/>
 * 描述：系统日志表管理逻辑
 */
@Service("sysLogService")
@Transactional(isolation = Isolation.READ_COMMITTED, readOnly = true, rollbackFor = Throwable.class)
public class LogService extends BaseService {
        private static final org.apache.commons.logging.Log logger = LogFactory.getLog(LogService.class);
    /**
     * 查询系统日志表对象
     *
     * @param pk 字符串型主键
     * @return Log
     * @throws Exception 异常
     */
    public Log query(String pk) throws Exception{
        return query(new Log(pk));
    }

    /**
     * 查询系统日志表对象
     *
     * @param pk long型主键
     * @return Log
     * @throws Exception 异常
     */
    public Log query(Long pk) throws Exception{
        return query(new Log(pk));
    }


    /**
     * 查询系统日志表对象
     *
     * @param param 系统日志表对象
     * @return Log
     * @throws Exception 异常
     */
    public Log query(Log param) throws Exception{
        return sqlDao.query("sys_log.query",param);
    }

    /**
     * 查询系统日志表对象集合
     *
     * @param param 系统日志表对象
     * @return List
     * @throws Exception 异常
     */
    public List<Log> list(Log param) throws Exception {
        return sqlDao.list("sys_log.query",param);
    }


    /**
     * 系统日志表翻页查询
     * @param param 分页条件对象
     * @return PageResult
     * @throws Exception 异常
     */
    public PageResult pageList(PageParam param) throws Exception {
        param.setCountSql("sys_log.pageCount");
        param.setRecordSql("sys_log.pageList");
        return pageService.pageQuery(param);
    }

    /**
     * 插入系统日志表记录
     *
     * @param param 系统日志表对象
     * @return String
     * @throws Exception 异常
     */
    @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = Throwable.class)
    public String create(Log param) throws Exception  {
        sqlDao.create("sys_log.create",param);
        return "1";
    }

    /**
     * 更新系统日志表记录
     *
     * @param param 系统日志表对象
     * @return String
     * @throws Exception 异常
     */
    @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = Throwable.class)
    public String update(Log param) throws Exception {
        sqlDao.update("sys_log.update", param);
        return "1";
    }

    /**
     * 删除系统日志表记录
     *
     * @param param 系统日志表对象
     * @return String
     * @throws Exception 异常
     */
    @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = Throwable.class)
    public String delete(Log param) throws Exception {
        sqlDao.delete("sys_log.delete", param);
        return "1";
    }


    /**
     * 删除系统日志表记录
     *
     * @param pk 字符串型主键
     * @return String
     * @throws Exception 异常
     */
    @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = Throwable.class)
    public String delete(String pk) throws Exception {
        return delete(new Log(pk));
    }


    /**
     * 删除系统日志表记录
     *
     * @param pks 字符串型主键数组
     * @return String
     * @throws Exception 异常
     */
    @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = Throwable.class)
    public String delete(String[] pks) throws Exception {
        for(String pk:pks){
            delete(pk);
        }
        return "1";
    }

    @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = Throwable.class)
    public String delete(Date date) throws Exception {
        Log log = new Log("mode","sl_date");
        log.setSl_date(date);
        sqlDao.delete("sys_log.delete", log);
        return "1";
    }

    @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = Throwable.class)
    public String delete() throws Exception{
        sqlDao.delete("sys_log.delete",null);
        return "1";
    }



      public static String getString(Object obj) {
        if (obj == null) return null;
        Class cls = obj.getClass();
        String str = "";
        if (cls == String.class) return (String) obj;
        else if (cls == String[].class) {
            for (String s : (String[]) obj) str += "," + s;
            return str.equals("") ? "" : str.substring(1);
        } else if (cls == BigDecimal.class) return ((BigDecimal) obj).toPlainString();
        else if (cls == BigDecimal[].class) {
            for (BigDecimal s : (BigDecimal[]) obj) str += "," + s.toPlainString();
            return str.equals("") ? "" : str.substring(1);
        } else if (cls == BigInteger.class) return obj.toString();
        else if (cls == BigInteger[].class) {
            for (BigInteger s : (BigInteger[]) obj) str += "," + s.toString();
            return str.equals("") ? "" : str.substring(1);
        } else if (cls == Boolean.class) return obj.toString();
        else if (cls == Boolean[].class) {
            for (Boolean s : (Boolean[]) obj) str += "," + s.toString();
            return str.equals("") ? "" : str.substring(1);
        } else if (cls == Double.class) return (new BigDecimal((Double) obj)).toPlainString();
        else if (cls == Double[].class) {
            for (Double s : (Double[]) obj) str += "," + (new BigDecimal(s)).toPlainString();
            return str.equals("") ? "" : str.substring(1);
        } else if (cls == Float.class) return (new BigDecimal((Float) obj)).toPlainString();
        else if (cls == Float[].class) {
            for (Float s : (Float[]) obj) str += "," + (new BigDecimal(s)).toPlainString();
            return str.equals("") ? "" : str.substring(1);
        } else if (cls == Integer.class) return obj.toString();
        else if (cls == Integer[].class) {
            for (Integer s : (Integer[]) obj) str += "," + s.toString();
            return str.equals("") ? "" : str.substring(1);
        } else if (cls == Long.class) return obj.toString();
        else if (cls == Long[].class) {
            for (Long s : (Long[]) obj) str += "," + s.toString();
            return str.equals("") ? "" : str.substring(1);
        } else if (cls == Short.class) return obj.toString();
        else if (cls == Short[].class) {
            for (Short s : (Short[]) obj) str += "," + s.toString();
            return str.equals("") ? "" : str.substring(1);
        } else if (cls == Date.class) return DateUtil.formatDate((Date) obj);
        else if (cls == Date[].class) {
            for (Date s : (Date[]) obj) str += "," + DateUtil.formatDate(s);
            return str.equals("") ? "" : str.substring(1);
        } else if (obj instanceof List) {
            for (Object o : (List) obj) {
                str += "," + getString(o);
            }
            return str.equals("") ? "" : str.substring(1);
        } else if (obj instanceof Map) {
            for (Object o : ((Map) obj).entrySet()) {
                Map.Entry entry = (Map.Entry) o;
                String key = (String) entry.getKey();
                Object value = entry.getValue();
                str += key + ":" + getString(value) + "\n";
            }
            return str;
        } else if (obj instanceof Base) {
            while (!cls.equals(Base.class)) {
                String table = ((Base) obj).getComment("tableComment");
                if (table != null) str += table + "\n";
                else str += cls.getName() + "\n";
                Field[] fields = cls.getDeclaredFields();
                for (Field field : fields) {
                    try {
                        PropertyDescriptor pd = new PropertyDescriptor(field.getName(), cls);
                        Method method = pd.getReadMethod();
                        if (method != null) {
                            Object o = method.invoke(obj);
                            String v = getString(o);
                            String n = ((Base) obj).getComment(field.getName());
                            if (v != null) {
                                str += (n != null ? n : field.getName()) + ": " + getString(o) + "\n";
                            }
                        }
                    } catch (IntrospectionException e) {
                        logger.error(e.toString());
                    } catch (InvocationTargetException e) {

                        logger.error(e.toString());
                    } catch (IllegalAccessException e) {

                        logger.error(e.toString());
                    }
                }
                cls = cls.getSuperclass();
            }
            return str;
        }
        return null;
    }
}