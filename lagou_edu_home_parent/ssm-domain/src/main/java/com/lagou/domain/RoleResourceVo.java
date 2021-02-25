package com.lagou.domain;

import java.util.List;

/**
 * 接收 角色ID 与 资源ID集合
 */
public class RoleResourceVo {

    //角色ID
    private Integer roleId;

    //资源ID集合
    private List<Integer> resourceIdList;

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public List<Integer> getResourceIdList() {
        return resourceIdList;
    }

    public void setResourceIdList(List<Integer> resourceIdList) {
        this.resourceIdList = resourceIdList;
    }
}
