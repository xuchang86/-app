package com.rogrand.sys.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.rogrand.core.domain.PageParam;
import com.rogrand.core.domain.PageResult;
import com.rogrand.core.security.MD5;
import com.rogrand.core.service.BaseService;
import com.rogrand.core.util.DateUtil;
import com.rogrand.core.util.StringUtil;
import com.rogrand.sys.domain.Role;
import com.rogrand.sys.domain.User;
import com.rogrand.sys.domain.UserRole;

/**
 * 
 * 版权：融贯资讯 <br/>
 * 作者：jian.mei@rogrand.com <br/>
 * 生成日期：2013-10-24 <br/>
 * 描述：用户管理逻辑
 */
@Service("sysUserService")
@Transactional(isolation = Isolation.READ_COMMITTED, readOnly = true, rollbackFor = Throwable.class)
public class UserService extends BaseService {
    
    public User query(String su_id) throws Exception {
        return query(new User(su_id));
    }
    
    public User query(Long su_id) throws Exception {
        return query(new User(su_id));
    }
    
    public User query(User user) throws Exception {
        return sqlDao.query("sys_user.query", user);
    }
    
    //
    public PageResult pageList(PageParam param) throws Exception {
        param.setCountSql("sys_user.pageCount");
        param.setRecordSql("sys_user.pageList");
        Map<String, String> alias = new HashMap<String, String>();
        // alias.put("user_id", "a.user_id");
        // alias.put("user_name", "a.user_name");
        // alias.put("user_code", "a.user_code");
        // alias.put("user_status", "a.user_status");
        // alias.put("user_sex", "a.user_sex");
        // alias.put("user_contact", "a.user_contact");
        // alias.put("user_email", "a.user_email");
        // alias.put("user_admin", "a.user_admin");
        // alias.put("org_name", "a.org_id");
        return pageService.pageQuery(param);
    }
    
    public List<Role> listRole(User user) throws Exception {
        return sqlDao.list("sys_user_role.queryRole", user);
    }
    
    /**
     * 添加用户
     * 
     * @param user
     *            用户
     * @return String
     * @throws Exception
     *             异常
     */
    
    @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = Throwable.class)
    public String create(User user) throws Exception {
        user.setMode("su_code");
        User check = sqlDao.query("sys_user.query", user);
        if (check != null)
            return "2";
        user.setSu_admin("0");
        user.setSu_random(StringUtil.getRandomChar(10));
        user.setSu_password(MD5.getEncrypt("888888"));// 默认密码
        user.setSu_add_time(DateUtil.getNow());// 用户创建时间
        user.setSu_login_count(0);// 登录次数
        sqlDao.create("sys_user.create", user);
        String sr_id = user.getSr_id();
        if (sr_id != null) {
            UserRole userRole = new UserRole();
            userRole.setSu_id(user.getSu_id());
            String[] ids = sr_id.split(",");
            for (String id : ids) {
                userRole.setSr_id(id);
                sqlDao.create("sys_user_role.create", userRole);
            }
        }
        return "1";
    }
    
    /**
     * 更新用户
     * 
     * @param user
     *            用户
     * @return String
     * @throws Exception
     *             异常
     */
    
    @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = Throwable.class)
    public String update(User user) throws Exception {
        
        user.setMode("su_code");
        User check = sqlDao.query("sys_user.query", user);
        if (check != null && !check.getSu_id().equals(user.getSu_id()))
            return "2";
        sqlDao.update("sys_user.update", user);
        sqlDao.delete("sys_user_role.delete", new UserRole("su_id", user.getSu_id()));
        String sr_id = user.getSr_id();
        if (sr_id != null) {
            UserRole userRole = new UserRole();
            userRole.setSu_id(user.getSu_id());
            String[] ids = sr_id.split(",");
            for (String id : ids) {
                userRole.setSr_id(id);
                sqlDao.create("sys_user_role.create", userRole);
            }
        }
        return "1";
    }
    
    /**
     * 删除用户
     * 
     * @param su_id
     *            用户id
     * @return String
     * @throws Exception
     *             异常
     */
    
    @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = Throwable.class)
    public String delete(String su_id) throws Exception {
        return delete(new User(su_id));
    }
    
    @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = Throwable.class)
    public String delete(User user) throws Exception {
        User check = sqlDao.query("sys_user.query", user);
        if (check.getSu_admin().equals("1"))
            return "2";
        if (!check.getSu_status().equals("0"))
            return "3";
        sqlDao.delete("sys_user_role.delete", new UserRole("su_id", user.getSu_id())); // 删除角色
        sqlDao.delete("sys_user.delete", user);
        return "1";
    }
    
    @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = Throwable.class)
    public String updatePassword(User user) throws Exception {
        user.putFields("su_id,su_password");
        sqlDao.update("sys_user.update", user);
        return "1";
    }
    
    /**
     * 〈修改个人资料〉 <br/>
     * 
     * @param user
     *            个人资料信息
     * @return boolean 是否修改成功
     * @throws Exception
     */
    @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = Throwable.class)
    public boolean updatePersonInfo(User user) throws Exception {
        return sqlDao.update("sys_user.update", user) == 1;
    }
    
}
