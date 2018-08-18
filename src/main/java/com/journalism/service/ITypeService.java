package com.journalism.service;

import com.journalism.common.ServerResponse;
import com.journalism.pojo.Type;

import java.util.List;

public interface ITypeService {

    ServerResponse addType(String typeName, Integer parentId);

    ServerResponse updateTypeName(Integer typeId,String typeName);

    ServerResponse<List<Type>> getChildrenParallelType(Integer TypeId);

    ServerResponse<List<Integer>> selectTypeAndChildrenById(Integer typeId);
}
