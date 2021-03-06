package com.jichuangsi.mes.entity;

import javax.persistence.*;

@Entity
@Table(name = "s_rolepower")
public class RolePower {

    @Id
    @GeneratedValue(strategy =GenerationType.IDENTITY)
    private Integer id;//配置ID
    private Integer Fid;//配置父ID
    private String rolePowerName;//配置名称
    private String roleCodeUrl;//配置路径
    private Integer isNode;//是否为节点
    private Integer sysType;//1 mes 2 erp
    private Integer state;
    private Integer deleteNo;

    public Integer getIsNode() {
        return isNode;
    }

    public void setIsNode(Integer isNode) {
        this.isNode = isNode;
    }

    public String getRoleCodeUrl() {
        return roleCodeUrl;
    }

    public void setRoleCodeUrl(String roleCodeUrl) {
        this.roleCodeUrl = roleCodeUrl;
    }

    public Integer getSysType() {
        return sysType;
    }

    public void setSysType(Integer sysType) {
        this.sysType = sysType;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getFid() {
        return Fid;
    }

    public void setFid(Integer fid) {
        Fid = fid;
    }

    public String getRolePowerName() {
        return rolePowerName;
    }

    public void setRolePowerName(String rolePowerName) {
        this.rolePowerName = rolePowerName;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Integer getDeleteNo() {
        return deleteNo;
    }

    public void setDeleteNo(Integer deleteNo) {
        this.deleteNo = deleteNo;
    }
}
