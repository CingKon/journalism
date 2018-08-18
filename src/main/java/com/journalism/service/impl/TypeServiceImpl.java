package com.journalism.service.impl;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.journalism.common.ServerResponse;
import com.journalism.dao.TypeMapper;
import com.journalism.pojo.Type;
import com.journalism.service.ITypeService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service("iTypeService")
public class TypeServiceImpl implements ITypeService {

    @Autowired
    private TypeMapper typeMapper;

    private static Logger logger = LoggerFactory.getLogger(TypeServiceImpl.class);

    public ServerResponse addType(String typeName, Integer parentId){
        if (parentId != null ||StringUtils.isBlank(typeName))
            return ServerResponse.createByErrorMessage("添加种类的参数错误！");

        Type type = new Type();
        type.setName(typeName);
        type.setParentId(parentId);
        type.setStatus(true);

        int rowCount = typeMapper.insert(type);
        if (rowCount > 0)
            return ServerResponse.createBySuccess("添加种类成功！");
        return ServerResponse.createByErrorMessage("添加种类失败！");
    }

    public ServerResponse updateTypeName(Integer typeId,String typeName){
        if (typeId != null ||StringUtils.isBlank(typeName))
            return ServerResponse.createByErrorMessage("更新种类的参数错误！");

        Type type = new Type();
        type.setName(typeName);
        type.setId(typeId);

        int rowCount = typeMapper.updateByPrimaryKeySelective(type);
        if (rowCount > 0)
            return ServerResponse.createBySuccess("更新种类成功！");
        return ServerResponse.createByErrorMessage("更新种类失败");
    }

    public ServerResponse<List<Type>> getChildrenParallelType(Integer TypeId){
        List<Type> typeList = typeMapper.selectTypeChildrenByParentId(TypeId);
        if (CollectionUtils.isEmpty(typeList))
            logger.info("未找到当前分类的子分类");
        return ServerResponse.createBySuccess(typeList);
    }

    /**
     * 递归查询本节点的id及子节点的id
     * @param typeId
     * @return
     */
    public ServerResponse<List<Integer>> selectTypeAndChildrenById(Integer typeId){
        Set<Type> typeSet = Sets.newHashSet();
        findChildType(typeSet,typeId);

        List<Integer> typeIdList = Lists.newArrayList();
        if (typeId != null){
            for (Type type: typeSet)
                typeIdList.add(type.getId());
        }
        return ServerResponse.createBySuccess(typeIdList);
    }

    //递归算法，算出子节点
    private Set<Type> findChildType(Set<Type> typeSet,Integer typeId){
        Type type = typeMapper.selectByPrimaryKey(typeId);
        if (type != null)
            typeSet.add(type);

        List<Type> typeList = typeMapper.selectTypeChildrenByParentId(typeId);
        for (Type type1 : typeList)
            findChildType(typeSet,type1.getId());
        return typeSet;
    }
}
