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
import com.rogrand.sys.domain.Org;

/**
 * 
 * 版权：融贯资讯 <br/>
 * 作者：jian.mei@rogrand.com <br/>
 * 生成日期：2013-10-24 <br/>
 * 描述：机构管理逻辑
 */
@Service("sysOrgService")
@Transactional(isolation = Isolation.READ_COMMITTED, readOnly = true, rollbackFor = Throwable.class)
public class OrgService extends BaseService {

    public Org query(Long so_id) throws Exception{
        return query(new Org(so_id));
    }

    public Org query(String so_id) throws Exception{
        return query(new Org(so_id));
    }

    public Org query(Org org) throws Exception{
        return sqlDao.query("sys_org.query",org);
    }

    public List<Org> list(Org org) throws Exception{
        return sqlDao.list("sys_org.query", org);
    }

    public String tree(Org org) throws Exception{
        org.setOrder("a.so_parentid,a.so_id");
        List<Org> orgList = list(org);
        List<Map<String,Object>> orgArray = new ArrayList<Map<String,Object>>();
        Map<String,Object> root = new HashMap<String, Object>();
        root.put("id",0l);
        root.put("text","热线助手");
        Map<String,String> attributes = new HashMap<String,String>();
        attributes.put("so_code","");
        root.put("attributes",attributes);
        List<Map<String,Object>> children = createTree(orgList,"0");
        if(children.size()>0){
            root.put("children",children);
        }
        orgArray.add(root);
        return BeanUtil.toJsonString(orgArray);
    }

    public String tree() throws Exception{
        return tree(new Org());
    }

    private List<Map<String,Object>> createTree(List<Org> orgList,String so_parentid){
        List<Map<String,Object>> orgArray = new ArrayList<Map<String,Object>>();
        boolean find = false;
        for(Org org:orgList){
            if(org.getSo_parentid().equals(so_parentid)){
                find=true;
                Map<String,Object> node = new HashMap<String, Object>();
                node.put("id",org.getSo_id());
                node.put("text",org.getSo_name());
                Map<String,String> attributes = new HashMap<String,String>();
                attributes.put("so_code",org.getSo_code());
                node.put("attributes",attributes);
                if(org.getSo_child().equals("1")){
                    List<Map<String,Object>> children = createTree(orgList,org.getSo_id());
                    if(children.size()>0){
                        node.put("children",children);
                    }
                }
                orgArray.add(node);
            }
            else if(find){
                break;
            }
        }
        return orgArray;
    }

     /**
     * 系统日志表翻页查询
     * @param param 分页条件对象
     * @return PageResult
     * @throws Exception 异常
     */
    public PageResult pageList(PageParam param) throws Exception {
        param.setCountSql("sys_org.pageCount");
        param.setRecordSql("sys_org.pageList");
        Map<String,String> alias = new HashMap<String, String>();
        alias.put("so_name", "a.so_name");
        alias.put("so_parentname", "a.so_parentid");
        alias.put("so_code", "a.so_code");
        alias.put("so_contact", "a.so_contact");
        alias.put("so_email", "a.so_email");
        alias.put("so_post", "a.so_post");
        return pageService.pageQuery(param,alias);
    }

    @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = Throwable.class)
    public String create(Org org) throws Exception{
        org.setMode("so_code");
        Org check = query(org);
        if(check!=null) return "2";
        sqlDao.create("sys_org.create",org);
        return "1";
    }

    @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = Throwable.class)
    public String update(Org org) throws Exception{
        org.setMode("so_code");
        Org check = query(org);
        if (check != null && !org.getSo_id().equals(check.getSo_id())) return "2";
        sqlDao.update("sys_org.update",org);
        return "1";
    }

    @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = Throwable.class)
    public String delete(Org org) throws Exception{
        Org check = query(org);
        if(check.getSo_used().equals("1")) return "2";
        sqlDao.delete("sys_org.delete",org);
        return "1";
    }

    @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = Throwable.class)
    public String delete(String so_id) throws Exception {
        return delete(new Org(so_id));
    }

    @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = Throwable.class)
    public String delete(String[] so_ids) throws Exception {
        for(String so_id:so_ids){
            delete(so_id);
        }
        return "1" ;
    }

}
