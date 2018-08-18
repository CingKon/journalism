package com.journalism.controller.backend;

import com.journalism.common.Const;
import com.journalism.common.ResponseCode;
import com.journalism.common.ServerResponse;
import com.journalism.pojo.Anthology;
import com.journalism.pojo.User;
import com.journalism.service.IAnthologyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller("/manage/anthology")
public class AnthologyManageController {

    @Autowired
    private IAnthologyService iAnthologyService;

    @RequestMapping("save.do")
    @ResponseBody
    public ServerResponse saveOrUpdateAnthology(HttpSession session, Anthology anthology){
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null)
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),ResponseCode.NEED_LOGIN.getDesc());
        return iAnthologyService.save(anthology);
    }

    @RequestMapping("list.do")
    @ResponseBody
    public ServerResponse list(HttpSession session,
                               @RequestParam(value = "pageNum",defaultValue = "1") int pageNum,
                               @RequestParam(value = "pageSize",defaultValue = "10") int pageSize){
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null)
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),ResponseCode.NEED_LOGIN.getDesc());
        return iAnthologyService.list(user.getId(),pageNum,pageSize);
    }

    @RequestMapping("delete.do")
    @ResponseBody
    public ServerResponse delete(HttpSession session,Integer anthologyId){
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null)
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),ResponseCode.NEED_LOGIN.getDesc());
        return iAnthologyService.delete(user.getId(),anthologyId);
    }
}
