package com.rogrand.sys.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.rogrand.core.domain.PageParam;
import com.rogrand.core.domain.PageResult;
import com.rogrand.core.service.BaseService;
import com.rogrand.core.util.BeanUtil;
import com.rogrand.sys.domain.Action;
import com.rogrand.sys.domain.App;
import com.rogrand.sys.domain.Menu;
import com.rogrand.sys.domain.Role;
import com.rogrand.sys.domain.RoleAction;
import com.rogrand.sys.domain.RoleMenu;
import com.rogrand.sys.domain.UserRole;

/**
 * 
 * 版权：融贯资讯 <br/>
 * 作者：jian.mei@rogrand.com <br/>
 * 生成日期：2013-10-24 <br/>
 * 描述：角色管理逻辑
 */
@Service("sysRoleService")
@Transactional(isolation = Isolation.READ_COMMITTED, readOnly = true, rollbackFor = Throwable.class)
public class RoleService extends BaseService {
    /**
     * 查询角色表对象
     *
     * @param sr_id 字符串型主键
     * @return Role
     * @throws Exception 异常
     */
    public Role query(String sr_id) throws Exception{
        return query(new Role(sr_id));
    }

    /**
     * 查询角色表对象
     *
     * @param sr_id long型主键
     * @return Role
     * @throws Exception 异常
     */
    public Role query(Long sr_id) throws Exception{
        return query(new Role(sr_id));
    }


    /**
     * 查询角色表对象
     *
     * @param role 角色表对象
     * @return Role
     * @throws Exception 异常
     */
    public Role query(Role role) throws Exception{
        return sqlDao.query("sys_role.query",role);
    }

    /**
     * 查询角色表对象集合
     *
     * @param role 角色表对象
     * @return List
     * @throws Exception 异常
     */
    public List<Role> list(Role role) throws Exception {
        return sqlDao.list("sys_role.query",role);
    }


    /**
     * 角色表翻页查询
     * @param role 分页条件对象
     * @return PageResult
     * @throws Exception 异常
     */
    public PageResult pageList(PageParam role) throws Exception {
        role.setCountSql("sys_role.pageCount");
        role.setRecordSql("sys_role.pageList");
        return pageService.pageQuery(role);
    }

    /**
     * 插入角色表记录
     *
     * @param role 角色表对象
     * @return String
     * @throws Exception 异常
     */
    @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = Throwable.class)
    public String create(Role role,Map<String,String[]> menuAction) throws Exception  {
        role.setMode("sr_code");
        Role check = query(role);
        if(check!=null) return "2";
        sqlDao.create("sys_role.create",role);


        RoleMenu roleMenu = new RoleMenu();
        RoleAction roleAction = new RoleAction();
        for (Map.Entry<String, String[]> entry : menuAction.entrySet()) {
            roleMenu.setSr_id(role.getSr_id());
            roleMenu.setSm_id(entry.getKey());
            sqlDao.create("sys_role_menu.create",roleMenu);
            String[] sa_ids = entry.getValue();
            if(sa_ids!=null&&sa_ids.length>0){
                for(String sa_id:sa_ids){
                    roleAction.setSr_id(role.getSr_id());
                    roleAction.setSm_id(roleMenu.getSm_id());
                    roleAction.setSa_id(sa_id);
                    sqlDao.create("sys_role_action.create",roleAction);
                }
            }
        }

        return "1";
    }

    /**
     * 更新角色表记录
     *
     * @param role 角色表对象
     * @return String
     * @throws Exception 异常
     */
    @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = Throwable.class)
    public String update(Role role,Map<String,String[]> menuAction) throws Exception {
        role.setMode("sr_code");
        Role check=query(role);
        if(check!=null&&!check.getSr_id().equals(role.getSr_id())) return "2";
        sqlDao.update("sys_role.update", role);
        sqlDao.delete("sys_role_action.delete",new RoleAction("sr_id",role.getSr_id()));
        sqlDao.delete("sys_role_menu.delete",new RoleMenu("sr_id",role.getSr_id()));
        RoleMenu roleMenu = new RoleMenu();
        RoleAction roleAction = new RoleAction();
        for (Map.Entry<String, String[]> entry : menuAction.entrySet()) {
            roleMenu.setSr_id(role.getSr_id());
            roleMenu.setSm_id(entry.getKey());
            sqlDao.create("sys_role_menu.create",roleMenu);
            String[] sa_ids = entry.getValue();
            if(sa_ids!=null&&sa_ids.length>0){
                for(String sa_id:sa_ids){
                    roleAction.setSr_id(role.getSr_id());
                    roleAction.setSm_id(roleMenu.getSm_id());
                    roleAction.setSa_id(sa_id);
                    sqlDao.create("sys_role_action.create",roleAction);
                }
            }
        }
        return "1";
    }

    /**
     * 删除角色表记录
     *
     * @param role 角色表对象
     * @return String
     * @throws Exception 异常
     */
    @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = Throwable.class)
    public String delete(Role role) throws Exception {
        sqlDao.delete("sys_user_role.delete",new UserRole("sr_id",role.getSr_id()));
        sqlDao.delete("sys_role_action.delete",new RoleAction("sr_id",role.getSr_id()));
        sqlDao.delete("sys_role_menu.delete",new RoleMenu("sr_id",role.getSr_id()));
        sqlDao.delete("sys_role.delete",role);
        return "1";
    }


    /**
     * 删除角色表记录
     *
     * @param sr_id 字符串型主键
     * @return String
     * @throws Exception 异常
     */
    @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = Throwable.class)
    public String delete(String sr_id) throws Exception {
        return delete(new Role(sr_id));
    }



    public String tree(Role role) throws Exception {
        List<App> appList = sqlDao.list("sys_app.query",new App("order","sap_id"));
        List<Map<String,Object>> appArray = new ArrayList<Map<String,Object>>();
        for(App app:appList){
            role.setSap_id(app.getSap_id());
            List<Menu> menuList = sqlDao.list("sys_role_menu.queryMenu",role);
            List<Action> actionList = sqlDao.list("sys_role_action.queryAction",role);
            Map<String,Object> node = new HashMap<String,Object>();
            node.put("id",app.getSap_id());
            node.put("sap_id",app.getSap_id());
            node.put("sm_id",0);
            node.put("sm_name",app.getSap_name());
            node.put("state","open");
            List<Map<String,Object>> menuArray = createTree(menuList,actionList,"0");
            if(menuArray.size()>0)  {
                node.put("children",menuArray);
                int check1=0; //选中的
                int check2=0; //部分选中的
                for(Map map:menuArray){
                    if(map.get("style").equals("tree-checkbox1")) check1++;
                    if(map.get("style").equals("tree-checkbox2")) check2++;
                }
                if(check2>0){
                    node.put("style","tree-checkbox2");
                }
                else{
                    if(check1==0) node.put("style","tree-checkbox0");
                    else if(check1==menuArray.size()) node.put("style","tree-checkbox1");
                    else node.put("style","tree-checkbox2");
                }
            }
            else{
                node.put("style","tree-checkbox0");
            }
            appArray.add(node);
        }
        return BeanUtil.toJsonString(appArray);
    }

    private List<Map<String,Object>> createTree(List<Menu> menuList,List<Action> actionList,String sm_parentid){
        List<Map<String,Object>> menuArray = new ArrayList<Map<String,Object>>();
        boolean find = false;
        for(Menu menu:menuList){
            if(menu.getSm_parentid().equals(sm_parentid)){
                find=true;
                Map<String,Object> node = new HashMap<String, Object>();
                node.put("id",menu.getSm_id());
                node.put("sap_id",menu.getSap_id());
                node.put("sm_id",menu.getSm_id());
                node.put("sm_name",menu.getSm_name());
                node.put("sm_parentid",menu.getSm_parentid());
                if(menu.getSm_child().equals("1")){ //有子菜单
                    List<Map<String,Object>> children = createTree(menuList,actionList,menu.getSm_id());
                    if(children.size()>0)  {
                        node.put("children",children);
                        node.put("state","open");

                        int check1=0; //选中的
                        int check2=0; //部分选中的
                        for(Map map:children){
                            if(map.get("style").equals("tree-checkbox1")) check1++;
                            if(map.get("style").equals("tree-checkbox2")) check2++;
                        }
                        if(check2>0){
                            node.put("style","tree-checkbox2");
                        }
                        else{
                            if(check1==0) node.put("style","tree-checkbox0");
                            else if(check1==children.size()) node.put("style","tree-checkbox1");
                            else node.put("style","tree-checkbox2");
                        }
                    }
                    else{
                        node.put("style","tree-checkbox0");
                    }
                }
                else{     //无子菜单
                    node.put("style",menu.getStatus().equals("1")?"tree-checkbox1":"tree-checkbox0");
                    boolean search=false;
                    List<Map<String,Object>> actionArray = new ArrayList<Map<String,Object>>();
                    for(Action action:actionList){
                        if(action.getSm_id().equals(menu.getSm_id())){
                            search=true;
                            Map<String,Object> item = new HashMap<String,Object>();
                            item.put("sa_id",action.getSa_id());
                            item.put("sa_group",action.getSa_group());
//                            item.put("sm_id",action.getSm_id());
                            item.put("style",action.getStatus().equals("1")?"tree-checkbox1":"tree-checkbox0");
                            actionArray.add(item);
                        }
                        else if(search){
                            break;
                        }
                    }
                    node.put("actionList",actionArray);
                }
                menuArray.add(node);
            }
            else if (find) {
                break;
            }
        }
        return menuArray;
    }
}