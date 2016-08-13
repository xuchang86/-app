package com.rogrand.core.util;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;

import com.rogrand.core.domain.PageParam;
import com.rogrand.core.domain.PageResult;
import com.rogrand.core.domain.TreeConfig;

/**
 * 
 * 版权：融贯资讯 <br/>
 * 作者：jian.mei@rogrand.com <br/>
 * 生成日期：2013-10-24 <br/>
 * 描述：数据转换成easyui页面展现需要的格式
 */
public class EasyuiUtil {

    public static String toDataGridData(PageResult pageResult) {
        Map<String, Object> map = new HashMap<String, Object>();
        PageParam pageParam = pageResult.getPageParam();
        List pageList = pageResult.getPageList();
        map.put("total", pageParam.getRecordCount());
        map.put("page", pageParam.getPage());
        map.put("size", pageParam.getRows());
        if (pageList == null) {
            map.put("rows", false);
        }
        else {
            map.put("rows", pageList);
        }
        return BeanUtil.toJsonString(map);
    }


    public static String toDataGridData(List list) {
        Map<String, Object> map = new HashMap<String, Object>();

        if (list == null || list.size() == 0) {
            map.put("total", 0);
            map.put("rows", false);
        }
        else {
            map.put("total", list.size());
            map.put("rows", list);
        }
        return BeanUtil.toJsonString(map);
    }


    public static String toComboboxData(List list, String fields) throws InvocationTargetException, NoSuchMethodException, IllegalAccessException {
        if (list == null || list.size() == 0) return "[]";
        return fields == null ? BeanUtil.toJsonString(list) : toComboboxData(list, fields.split(","));
    }

    public static String toComboboxData(List list, String[] fields) throws InvocationTargetException, NoSuchMethodException, IllegalAccessException {
        if (list == null || list.size() == 0) return "[]";
        if (fields != null && fields.length > 0) {
            List<Map<String, String>> data = new ArrayList<Map<String, String>>();
            for (Object obj : list) {
                Map<String, String> model = new HashMap<String, String>();
                for (String field : fields) {

                    model.put(field.trim(), BeanUtils.getProperty(obj, field.trim()));
                }
                data.add(model);
            }
            return BeanUtil.toJsonString(data);
        }
        else {
            return BeanUtil.toJsonString(list);
        }
    }

    public static String toComboboxData(List list) {
        if (list == null || list.size() == 0) return "[]";
        return BeanUtil.toJsonString(list);
    }

    public static String toTreeData(List list, TreeConfig config) throws InvocationTargetException, NoSuchMethodException, IllegalAccessException {
        return toTreeData(list, config, true);
    }

    public static String toTreeData(List list, TreeConfig config, boolean showRoot) throws InvocationTargetException, NoSuchMethodException, IllegalAccessException {
        if (showRoot) {
            Map<String, Object> tree = new HashMap<String, Object>();
            tree.put("id", config.getRootID());
            tree.put("text", config.getRootText());
            if (list != null && list.size() > 0) {
                List<Map<String, Object>> childTree = StringUtil.isEmpty(config.getFieldChild()) ?
                        createTreeData(list, config, config.getRootID(), 0) :
                        createTreeData(list, config, config.getRootID());

                if (childTree != null && childTree.size() > 0) {
                    tree.put("children", childTree);
                }
            }
            return BeanUtil.toJsonString(new Object[]{tree});
        }
        else {
            if (list != null && list.size() > 0) {
                List<Map<String, Object>> childTree = StringUtil.isEmpty(config.getFieldChild()) ?
                        createTreeData(list, config, config.getRootID(), 0) :
                        createTreeData(list, config, config.getRootID());
                return childTree != null && childTree.size() > 0 ? BeanUtil.toJsonString(childTree) : "[]";
            }
            else {
                return "[]";
            }
        }
    }

    private static List<Map<String, Object>> createTreeData(List list, TreeConfig config, String parentID) throws InvocationTargetException, NoSuchMethodException, IllegalAccessException {
        List<Map<String, Object>> treeList = new ArrayList<Map<String, Object>>();
        boolean find = false;
        for (Object obj : list) {
            if (BeanUtils.getProperty(obj, config.getFieldParentID()).equals(parentID)) {
                find = true;
                Map<String, Object> tree = new HashMap<String, Object>();
                tree.put("id", BeanUtils.getProperty(obj, config.getFieldID()));
                tree.put("text", BeanUtils.getProperty(obj, config.getFieldText()));
                if (!StringUtil.isEmpty(config.getFieldChecked())) {
                    if (BeanUtils.getProperty(obj, config.getFieldChecked()).equals("1")) {
                        tree.put("checked", true);
                    }
                }
                if (config.getFieldAttributes() != null && config.getFieldAttributes().length > 0) {
                    Map<String, Object> attributeMap = new HashMap<String, Object>();
                    for (String attribute : config.getFieldAttributes()) {
                        attributeMap.put(attribute.trim(), BeanUtils.getProperty(obj, attribute.trim()));
                    }
                    tree.put("attributes", attributeMap);
                }
                if (BeanUtils.getProperty(obj, config.getFieldChild()).equals("1")) {
                    List<Map<String, Object>> childTree = createTreeData(list, config, BeanUtils.getProperty(obj, config.getFieldID()));
                    if (childTree != null && childTree.size() > 0) {
                        tree.put("children", childTree);
                        if (!StringUtil.isEmpty(config.getState())) {
                            tree.put("state", config.getState());
                        }
                    }
                }
                treeList.add(tree);
            }
            else if (find) {
                break;
            }
        }
        return treeList;
    }

    private static List<Map<String, Object>> createTreeData(List list, TreeConfig config, String parentID, int start) throws InvocationTargetException, NoSuchMethodException, IllegalAccessException {
        List<Map<String, Object>> treeList = new ArrayList<Map<String, Object>>();
        boolean find = false;
        for (int i = start; i < list.size(); i++) {
            Object obj = list.get(i);
            if (BeanUtils.getProperty(obj, config.getFieldParentID()).equals(parentID)) {
                find = true;
                Map<String, Object> tree = new HashMap<String, Object>();
                tree.put("id", BeanUtils.getProperty(obj, config.getFieldID()));
                tree.put("text", BeanUtils.getProperty(obj, config.getFieldText()));
                if (!StringUtil.isEmpty(config.getFieldChecked())) {
                    if (BeanUtils.getProperty(obj, config.getFieldChecked()).equals("1")) {
                        tree.put("checked", true);
                    }
                }
                if (config.getFieldAttributes() != null && config.getFieldAttributes().length > 0) {
                    Map<String, Object> attributeMap = new HashMap<String, Object>();
                    for (String attribute : config.getFieldAttributes()) {
                        attributeMap.put(attribute.trim(), BeanUtils.getProperty(obj, attribute.trim()));
                    }
                    tree.put("attributes", attributeMap);
                }
                if (i + 1 < list.size()) {
                    List<Map<String, Object>> childTree = createTreeData(list, config, BeanUtils.getProperty(obj, config.getFieldID()), i + 1);
                    if (childTree != null && childTree.size() > 0) {
                        tree.put("children", childTree);
                        if (!StringUtil.isEmpty(config.getState())) {
                            tree.put("state", config.getState());
                        }
                    }
                }
                treeList.add(tree);
            }
            else if (find) {
                break;
            }
        }
        return treeList;
    }
}
