package com.rogrand.sys.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.rogrand.core.service.BaseService;
import com.rogrand.core.system.SystemParameter;
import com.rogrand.sys.domain.Action;
import com.rogrand.sys.domain.App;
import com.rogrand.sys.domain.Menu;
import com.rogrand.sys.domain.Role;
import com.rogrand.sys.domain.User;

/**
 * 
 * 版权：融贯资讯 <br/>
 * 作者：jian.mei@rogrand.com <br/>
 * 生成日期：2013-10-24 <br/>
 * 描述：用户登录逻辑
 */
@Service("loginService")
@Transactional(isolation = Isolation.READ_COMMITTED, readOnly = true, rollbackFor = Throwable.class)
public class LoginService extends BaseService {


    /**
     * 验证用户
     *
     * @param user
     * @return User
     * @throws Exception
     */
    public User checkLogin(User user) throws Exception {
        User loginUser = sqlDao.query("sys_login.queryUser", user);   //得到用户
        if (loginUser == null) return null;     //用户不存在
        if (!loginUser.getSu_password().equals(user.getSu_password())) return null; //密码错误
        App app = sqlDao.query("sys_login.querySystem",new App("sap_code",SystemParameter.get("systemCode")));
        loginUser.setSap_id(app.getSap_id());
        if (!loginUser.getSu_admin().equals("1")) {     //非管理员
            List<Role> roleList = sqlDao.list("sys_login.queryRole", loginUser);    //查询用户在子系统中的角色
            if (roleList != null && roleList.size() > 0) {
                String sr_id = "", sr_code = "", sr_name = "";
                for (Role role : roleList) {
                    sr_id += "," + role.getSr_id();
                    sr_code += "," + role.getSr_code();
                    sr_name += "," + role.getSr_name();
                }
                loginUser.setSr_id(sr_id.substring(1));
                loginUser.setSr_code(sr_code.substring(1));
                loginUser.setSr_name(sr_name.substring(1));
            }
        }
        return loginUser;
    }


    /**
     * 得到登录用户的菜单
     * @param user
     * @return
     * @throws Exception
     */
    public List<Menu> queryMenu(User user) throws Exception{
        List<Menu> menuList = sqlDao.list("sys_login.queryMenu", user);     //查询用户菜单
        return menuList;
    }


    public List<String> queryAction(User user) throws Exception{
        List<Action> actionList = sqlDao.list("sys_login.queryAction", user);
        List<String> list = new ArrayList<String>();

        for (Action action : actionList) {
            if (action.getSa_class() != null && action.getSa_group() != null) {
                list.add(action.getSa_class() + "." + action.getSa_group());
            }
        }

        return list; //查询用户访问权限
    }

}
