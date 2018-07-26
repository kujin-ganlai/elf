package com.su.system.controller;

import com.alibaba.fastjson.JSONObject;
import com.su.common.entity.ResponseMessage;
import com.su.common.entity.SearchParam;
import com.su.system.entity.Role;
import com.su.system.service.role.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Desc
 * @author surongyao
 * @date 2018/5/25 下午5:40
 * @version
 */
@RestController
@RequestMapping("/role")
public class RoleController {

    //private static final Logger logger = LoggerFactory.getLogger(RoleController.class);

    @Autowired
    RoleService roleService;

    @RequestMapping(method = RequestMethod.GET)
    public String getRoleList(SearchParam param){
        param.setOffset((param.getPage()-1)*param.getLimit());
        List<Role> list = roleService.getList(param);
        int total = roleService.getCount(param);
        JSONObject json = new JSONObject();
        json.put("count", total);
        json.put("list", list);
        return ResponseMessage.ok(json);
    }

    @RequestMapping(method = RequestMethod.POST)
    public String addRole(@RequestBody Role role){
        int id = roleService.insertPojo(role);
        JSONObject json = new JSONObject();
        json.put("id", id);
        return ResponseMessage.ok(json);
    }

    @RequestMapping(value = "/{pid}", method = RequestMethod.DELETE)
    public String deleteRole(@PathVariable int pid){
        int result = roleService.deletePojo(pid);
        JSONObject json = new JSONObject();
        json.put("result", result);
        return ResponseMessage.ok(json);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public String updateRole(@RequestBody Role role){
        int result = roleService.updatePojo(role);
        JSONObject json = new JSONObject();
        json.put("result", result);
        return ResponseMessage.ok(json);
    }

    @RequestMapping(value = "/{pid}", method = RequestMethod.GET)
    public String getRole(@PathVariable int pid){
        Role role = roleService.getPojo(pid);
        JSONObject json = new JSONObject();
        json.put("role", role);
        return ResponseMessage.ok(json);
    }

}
