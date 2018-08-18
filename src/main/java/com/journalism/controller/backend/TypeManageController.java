package com.journalism.controller.backend;

import com.journalism.common.Const;
import com.journalism.common.ResponseCode;
import com.journalism.common.ServerResponse;
import com.journalism.pojo.User;
import com.journalism.service.ITypeService;
import com.journalism.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller("/manage/type/")
public class TypeManageController {

    @Autowired
    private IUserService iUserService;

    @Autowired
    private ITypeService iTypeService;

    @RequestMapping("add_type.do")
    @ResponseBody
    public ServerResponse addType(HttpSession session,String typeName,@RequestParam(value = "parentId",defaultValue = "0") Integer parentId){
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null)
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"用户未登录，请登录！");
        if (iUserService.checkAdminRole(user).isSuccess())
            return iTypeService.addType(typeName,parentId);
        return ServerResponse.createByErrorMessage("无权限操作，需要管理员权限！");
    }

    @RequestMapping("set_type_name.do")
    @ResponseBody
    public ServerResponse setTypeName(HttpSession session,String typeName,Integer typeId){
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null)
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"用户未登录，请登录！");
        if (iUserService.checkAdminRole(user).isSuccess())
            return iTypeService.updateTypeName(typeId,typeName);
        return ServerResponse.createByErrorMessage("无权限操作，需要管理员权限！");
    }

    @RequestMapping("get_type.do")
    @ResponseBody
    public ServerResponse getChildrenParallelType(HttpSession session,@RequestParam(value = "typeId",defaultValue = "0") Integer typeId){
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null)
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"用户未登录，请登录！");
        if (iUserService.checkAdminRole(user).isSuccess())
            return iTypeService.getChildrenParallelType(typeId);
        return ServerResponse.createByErrorMessage("无权限操作，需要管理员权限！");
    }

    @RequestMapping("get_deep_type.do")
    @ResponseBody
    public ServerResponse getTypeAndDeepType(HttpSession session,@RequestParam(value = "typeId",defaultValue = "0") Integer typeId){
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null)
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"用户未登录，请登录！");
        if (iUserService.checkAdminRole(user).isSuccess())
            return iTypeService.selectTypeAndChildrenById(typeId);
        return ServerResponse.createByErrorMessage("无权限操作，需要管理员权限！");
    }
}
