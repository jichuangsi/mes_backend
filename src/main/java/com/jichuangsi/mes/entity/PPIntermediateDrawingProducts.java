package com.jichuangsi.mes.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 中拉本班产物
 */
@Entity
@Table(name = "pp_intermediateDrawingProducts")
public class PPIntermediateDrawingProducts {

    @Id
    @GeneratedValue(strategy =GenerationType.IDENTITY)
    private Integer id;//熔炼产物ID
    private Integer PPPId;//生产id
    private Date createTime;//生产时间
    private Integer bobbinId;//线轴规格id
    private BigDecimal wireDiameterUm;//线径um
    private BigDecimal lengthM;//长度m/轴
    private BigDecimal grossWeight ;//毛重g
    private BigDecimal netWeightg;//净重g
    private BigDecimal wastageg;//废料g
    private BigDecimal lossg;//损耗g
    private String Slip;//滑差
    private String tractionSpeed;//牵引速度
    private String takeUpSpeed ;//收线速度
    private String surface;//表面
    private Integer deleteNo;//删除否

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPPPId() {
        return PPPId;
    }

    public void setPPPId(Integer PPPId) {
        this.PPPId = PPPId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getBobbinId() {
        return bobbinId;
    }

    public void setBobbinId(Integer bobbinId) {
        this.bobbinId = bobbinId;
    }

    public BigDecimal getWireDiameterUm() {
        return wireDiameterUm;
    }

    public void setWireDiameterUm(BigDecimal wireDiameterUm) {
        this.wireDiameterUm = wireDiameterUm;
    }

    public BigDecimal getLengthM() {
        return lengthM;
    }

    public void setLengthM(BigDecimal lengthM) {
        this.lengthM = lengthM;
    }

    public BigDecimal getGrossWeight() {
        return grossWeight;
    }

    public void setGrossWeight(BigDecimal grossWeight) {
        this.grossWeight = grossWeight;
    }

    public BigDecimal getNetWeightg() {
        return netWeightg;
    }

    public void setNetWeightg(BigDecimal netWeightg) {
        this.netWeightg = netWeightg;
    }

    public BigDecimal getWastageg() {
        return wastageg;
    }

    public void setWastageg(BigDecimal wastageg) {
        this.wastageg = wastageg;
    }

    public BigDecimal getLossg() {
        return lossg;
    }

    public void setLossg(BigDecimal lossg) {
        this.lossg = lossg;
    }

    public String getSlip() {
        return Slip;
    }

    public void setSlip(String slip) {
        Slip = slip;
    }

    public String getTractionSpeed() {
        return tractionSpeed;
    }

    public void setTractionSpeed(String tractionSpeed) {
        this.tractionSpeed = tractionSpeed;
    }

    public String getTakeUpSpeed() {
        return takeUpSpeed;
    }

    public void setTakeUpSpeed(String takeUpSpeed) {
        this.takeUpSpeed = takeUpSpeed;
    }

    public String getSurface() {
        return surface;
    }

    public void setSurface(String surface) {
        this.surface = surface;
    }

    public Integer getDeleteNo() {
        return deleteNo;
    }

    public void setDeleteNo(Integer deleteNo) {
        this.deleteNo = deleteNo;
    }
}