package com.journalism.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.journalism.common.ResponseCode;
import com.journalism.common.ServerResponse;
import com.journalism.dao.AnthologyMapper;
import com.journalism.pojo.Anthology;
import com.journalism.service.IAnthologyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("iAnthologyService")
public class AnthologyServiceImpl implements IAnthologyService {

    @Autowired
    private AnthologyMapper anthologyMapper;

    public ServerResponse save(Anthology anthology){
        if (anthology != null){
            if (anthology.getId() != null){
                int updateCount = anthologyMapper.updateByPrimaryKey(anthology);
                if (updateCount > 0)
                    return ServerResponse.createBySuccessMessage("更新文集成功！");
                return ServerResponse.createByErrorMessage("更新文集失败!");
            }else {
                int addCount = anthologyMapper.insert(anthology);
                if (addCount > 0)
                    return ServerResponse.createBySuccessMessage("新增文集成功！");
                return ServerResponse.createByErrorMessage("新增文集失败！");
            }
        }
        return ServerResponse.createByErrorCodeMessage(ResponseCode.ILLEGAL_ARGUMENT.getCode(),"更新或新增文集的参数不正确！");

    }

    public ServerResponse list(Integer authorId, int pageNum, int pageSize){
        if (authorId == null)
            return ServerResponse.createByErrorMessage("请先登录！");
        PageHelper.startPage(pageNum,pageSize);
        List<Anthology> anthologyList = anthologyMapper.getAnthologyList(authorId);
        PageInfo pageInfo = new PageInfo(anthologyList);
        return ServerResponse.createBySuccess(pageInfo);
    }

    public ServerResponse delete(Integer authorId,Integer anthologyId){
        if (authorId == null)
            return ServerResponse.createByErrorMessage("请先登录！");
        if (anthologyId == null)
            return ServerResponse.createByErrorMessage("请选中要删除的文集");
        int delCount = anthologyMapper.deleteByAuthorIdAndAnthologyId(authorId,anthologyId);
        if ( delCount >0 )
            return ServerResponse.createBySuccess();
        return ServerResponse.createByErrorMessage("删除失败！");
    }
}
