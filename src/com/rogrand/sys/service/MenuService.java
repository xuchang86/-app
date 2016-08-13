package com.rogrand.sys.service;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.rogrand.core.service.BaseService;
import com.rogrand.core.util.BeanUtil;
import com.rogrand.sys.domain.Action;
import com.rogrand.sys.domain.App;
import com.rogrand.sys.domain.Menu;
import com.rogrand.sys.domain.RoleAction;
import com.rogrand.sys.domain.RoleMenu;

/**
 * 
 * 版权：融贯资讯 <br/>
 * 作者：jian.mei@rogrand.com <br/>
 * 生成日期：2013-10-24 <br/>
 * 描述：菜单管理逻辑
 */
@Service("sysMenuService")
@Transactional(isolation = Isolation.READ_COMMITTED, readOnly = true, rollbackFor = Throwable.class)
public class MenuService extends BaseService {


    public Menu query(String sm_id) throws Exception {
        return query(new Menu(sm_id));
    }

    public Menu query(Menu param) throws Exception {
        return sqlDao.query("sys_menu.query", param);
    }

    public List<Menu> list(Menu param) throws Exception {
        return sqlDao.list("sys_menu.query", param);
    }

    /**
     * 查询菜单动作集合
     *
     * @param action 菜单动作
     * @return List
     * @throws Exception 异常
     */
    public List<Action> listAction(Action action) throws Exception {
        return sqlDao.list("sys_action.query", action);
    }


    public String tree(Menu menu) throws Exception {
        Menu param = (Menu) menu.clone();
        param.setOrder("a.sm_parentid,a.sm_order");
        List<Map<String, Object>> appArray = new ArrayList<Map<String, Object>>();
        List<App> appList = sqlDao.list("sys_app.query", new App("order", "sap_id"));
        for (App app : appList) {
            param.setSap_id(app.getSap_id());
            List<Menu> menuList = sqlDao.list("sys_menu.query", param);
            Map<String, Object> node = new HashMap<String, Object>();
            node.put("id", app.getSap_id());
            node.put("text", app.getSap_name());
            node.put("state", "open");
            Map<String, Object> attributes = new HashMap<String, Object>();
            attributes.put("sap_id", app.getSap_id());
            attributes.put("sm_type", "1");
            attributes.put("sm_admin", "0");
            attributes.put("sm_parentid", "0");
            node.put("attributes", attributes);
            List<Map<String, Object>> menuArray = createTree(menuList, "0");
            if (menuArray.size() > 0) {
                node.put("children", menuArray);
            }
            appArray.add(node);
        }
        return BeanUtil.toJsonString(appArray);
    }

    private List<Map<String, Object>> createTree(List<Menu> menuList, String sm_parentid) throws Exception {
        List<Map<String, Object>> menuArray = new ArrayList<Map<String, Object>>();
        boolean find = false;
        for (Menu menu : menuList) {
            if (menu.getSm_parentid().equals(sm_parentid)) {
                find = true;
                Map<String, Object> node = new HashMap<String, Object>();
                node.put("id", menu.getSm_id());
                node.put("text", menu.getSm_name());
                Map<String, Object> attributes = new HashMap<String, Object>();
                attributes.put("sap_id", menu.getSap_id());
                attributes.put("sm_type", menu.getSm_type());
                attributes.put("sm_parentid", menu.getSm_id());
                attributes.put("sm_admin", menu.getSm_admin());
                node.put("attributes", attributes);
                if (menu.getSm_child().equals("1")) {
                    List<Map<String, Object>> children = createTree(menuList, menu.getSm_id());
                    if (children.size() > 0) {
                        node.put("children", children);
                        node.put("state", "closed");
                    }
                }
                menuArray.add(node);
            } else if (find) {
                break;
            }
        }
        return menuArray;
    }


    @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = Throwable.class)
    public String create(Menu menu, List<Action> actionList) throws Exception {
        menu.setSm_order(0l);
        menu.setSm_admin("0");
        sqlDao.create("sys_menu.create", menu);
        if (menu.getSm_type().equals("1") && actionList != null) {
            for (Action action : actionList) {
                action.setSm_id(menu.getSm_id());
                sqlDao.create("sys_action.create", action);
            }
        }
        sqlDao.delete("sys_role_action.delete", new RoleAction("sm_id", menu.getSm_parentid()));
        sqlDao.delete("sys_action.delete", new Action("sm_id", menu.getSm_parentid()));

        return "1";
    }

    @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = Throwable.class)
    public String update(Menu menu, List<Action> actionList) throws Exception {

        Menu result = query(menu.getSm_id());
        sqlDao.update("sys_menu.update", menu);
        if (!result.getSap_id().equals(menu.getSap_id()) && result.getSm_child().equals("1")) {
            update(result, menu.getSap_id());
        }

        if (menu.getSm_type().equals("0")) {//如果是分割线，则删除菜单功能和角色功能分配
            sqlDao.delete("sys_role_action.delete", new RoleAction("sm_id", menu.getSm_id()));   //删除角色功能
            sqlDao.delete("sys_action.delete", new Action("sm_id", menu.getSm_id()));      //删除菜单功能
        } else if (menu.getSm_type().equals("1")) {
            if (actionList != null) {
                for (Action action : actionList) {
                    if (action.getStatus().equals("0")) {//添加
                        action.setSm_id(menu.getSm_id());
                        sqlDao.create("sys_action.create", action);
                    } else if (action.getStatus().equals("1")) {//修改
                        action.setSm_id(menu.getSm_id());
                        sqlDao.update("sys_action.update", action);
                    } else if (action.getStatus().equals("2")) {//删除
                        sqlDao.delete("sys_role_action.delete", new RoleAction("sa_id", action.getSa_id()));
                        sqlDao.delete("sys_action.delete", new Action("sa_id", action.getSa_id()));
                    }
                }
            }
        }
        sqlDao.delete("sys_role_action.delete", new RoleAction("sm_id", menu.getSm_parentid()));
        sqlDao.delete("sys_action.delete", new Action("sm_id", menu.getSm_parentid()));
        return "1";

    }

    private void update(Menu menu, String sap_id) throws Exception {
        Menu param = new Menu("mode", "order");
        param.setSap_id(menu.getSap_id());
        param.setSm_parentid(menu.getSm_id());
        List<Menu> menuList = list(param);
        if (menuList != null && menuList.size() > 0) {
            Menu update = new Menu("sap_id", sap_id);
            for (Menu item : menuList) {
                update.setSm_id(item.getSm_id());
                sqlDao.update("sys_menu.update", update);
                if (item.getSm_child().equals("1")) {
                    update(item, sap_id);
                }
            }
        }
    }


    /**
     * 删除菜单表记录
     *
     * @param menu 菜单表对象
     * @return String
     * @throws Exception 异常
     */
    @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = Throwable.class)
    public String delete(Menu menu) throws Exception {

        Menu result = sqlDao.query("sys_menu.query", menu);
        if (result.getSm_child().equals("1")) return "2";
        if (result.getSm_admin().equals("1")) return "3";

        sqlDao.delete("sys_role_action.delete", new RoleAction("sm_id", menu.getSm_id()));
        sqlDao.delete("sys_role_menu.delete", new RoleMenu("sm_id", menu.getSm_id()));
        sqlDao.delete("sys_action.delete", new Action("sm_id", menu.getSm_id()));
        sqlDao.delete("sys_menu.delete", menu);
        return "1";
    }


    /**
     * 删除菜单表记录
     *
     * @param sm_id 字符串型主键
     * @return String
     * @throws Exception 异常
     */
    @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = Throwable.class)
    public String delete(String sm_id) throws Exception {
        return delete(new Menu(sm_id));
    }

    @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = Throwable.class)
    public String updateOrder(List<Menu> menuList) throws Exception {
        for (Menu menu : menuList) {
            sqlDao.update("sys_menu.update", menu);
        }
        return "1";
    }

}
