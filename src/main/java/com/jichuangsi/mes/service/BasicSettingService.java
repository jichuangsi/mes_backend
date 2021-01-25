package com.jichuangsi.mes.service;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.IoUtil;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.util.StringUtil;
import com.jichuangsi.mes.common.OrderStateChange;
import com.jichuangsi.mes.common.RepairReportStateChange;
import com.jichuangsi.mes.common.getDateConfig;
import com.jichuangsi.mes.constant.ResultCode;
import com.jichuangsi.mes.constant.ResultSetting;
import com.jichuangsi.mes.entity.*;
import com.jichuangsi.mes.exception.PassportException;
import com.jichuangsi.mes.mapper.IMesMapper;
import com.jichuangsi.mes.model.*;
import com.jichuangsi.mes.repository.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class BasicSettingService {


    @Resource
    private IMesMapper mesMapper;


    @Resource
    private TProductRepository tpRepository;
    @Resource
    private SDictionarierRepository sdRepository;
    @Resource
    private TProstandardRepository tprostRepository;
    @Resource
    private StockRepository stockRepository;
    @Resource
    private TStandardsRepository tStandardsRepository;
    @Resource
    private TBobbinRepository tbobbinRepository;

    @Resource
    private GXLossBiRepository gxLossBiRepository;
    @Resource
    private RawMaterialRatioRepository rawMaterialRatioRepository;


    @Resource
    private EquipmentRepository equipmentRepository;

    @Resource
    private EquipmentItemsRepository equipmentItemsRepository;
    @Resource
    private EquipmentOverhaulRepository equipmentOverhaulRepository;
    @Resource
    private EquipmentCheckRecordRepository equipmentCheckRecordRepository;
    @Resource
    private EquipmentCheckDetailRecordRepository equipmentCheckDetailRecordRepository;
    @Resource
    private RepairReportRepository  repairReportRepository;
    @Resource
    private AuditSettingRepository auditSettingRepository;
    @Resource
    private MattersRepository mattersRepository;
    @Resource
    private AuditPocessRepository auditPocessRepository;
    @Resource
    private OrderAuditPocessRepository orderAuditPocessRepository;
    @Resource
    private TMouldRepository mouldRepository;


    @Resource
    private TBobbinDetailRepository bobbinDetailRepository;
    @Resource
    private TBobbinHistoryRepository bobbinHistoryRepository;

    /**
     * 基础设置-查询(产品型号规格，原材料型号规格，线轴型号规格，其他型号规格等)
     * @param
     * @throws PassportException
     */
    public PageInfo getAllBasicSettingByName(SelectModel smodel, HttpServletRequest request, InputStream inputStream)throws PassportException{
        PageInfo page=new PageInfo();

        Integer pagenum = null;
        Integer pagesize = null;
        if(!StringUtils.isEmpty(smodel.getPageNum()) || StringUtils.isEmpty(smodel.getPageSize())){
            pagenum=(smodel.getPageNum()-1)*smodel.getPageSize();
            pagesize = smodel.getPageSize();
        }

        switch (smodel.getFindModelName()){
            case "product"://产品管理-查询产品
                List<TProductModel> listProduct = mesMapper.findAllProduct(smodel.getFindName(),smodel.getFindIdOne(),pagenum,pagesize);
                page.setList(listProduct);
                page.setTotal(mesMapper.countByProduct(smodel.getFindName(),smodel.getFindIdOne()));

                break;
            case "stock"://原材料管理-查询原材料
                page.setList(mesMapper.findAllStock(smodel.getFindName(),smodel.getFindIdOne(),1,pagenum,pagesize));
                page.setTotal(mesMapper.countByStock(smodel.getFindName(),1));
                break;
            case "bobbin"://线轴管理-线轴

                List<StockModel> listbobbin = mesMapper.findAllbobbin(smodel.getFindName(),smodel.getFindIdOne(),pagenum,pagesize);
                page.setList(listbobbin);
                page.setTotal(mesMapper.countBybobbin(smodel.getFindName(),smodel.getFindIdOne()));
                break;
            case "elseother"://其他
                page.setList(mesMapper.findAllStock(smodel.getFindName(),smodel.getFindIdOne(),2,pagenum,pagesize));
                page.setTotal(mesMapper.countByStock(smodel.getFindName(),2));
                break;
            default:

        }
        page.setPageSize(smodel.getPageSize());
        page.setPageNum(smodel.getPageNum());
        return page;
    }

    /**
     * 基础设置-根据Id查询单条数据(产品型号规格，原材料型号规格，线轴型号规格，其他型号规格等)
     * @param
     * @throws PassportException
     */
    public JSONObject getAllBasicSettingByNameAndId(SelectModel smodel)throws PassportException{
        JSONObject jsonObject = new JSONObject();

        switch (smodel.getFindModelName()){
            case "product"://产品管理-查询产品
//                TProductModel model = mesMapper.findAllProductById(smodel.getFindById());
                jsonObject.put("TProductModel",tpRepository.findByid(smodel.getFindById()));

                //规格型号 TProstandard
                jsonObject.put("listTProstandard",tprostRepository.findByProductIdAndDeleteNo(smodel.getFindById(),0));
//                // 工序损耗比 GXLossBi
//                jsonObject.put("listGXLossBi",mesMapper.findAllGXLossBiVoById(smodel.getFindById()));
                // 原料配比 RawMaterialRatio
                jsonObject.put("listRawMaterialRatio",mesMapper.findAllRawMaterialRatioById(smodel.getFindById()));
                break;
            case "stock"://原材料管理-查询原材料
                jsonObject.put("stockModel",stockRepository.findByid(smodel.getFindById()));
                jsonObject.put("listDetail",tStandardsRepository.findByMaterialIdAndMaterialTypeAndDeleteNo(smodel.getFindById(),1,0));
                break;
            case "bobbin"://线轴管理-线轴
                TBobbin bobbin = tbobbinRepository.findByid(smodel.getFindById());
                jsonObject.put("bobbin",bobbin);
                jsonObject.put("listbobbinDetail",tStandardsRepository.findByMaterialIdAndMaterialTypeAndDeleteNo(smodel.getFindById(),2,0));
                break;
            case "elseother"://其他
                jsonObject.put("elseother",stockRepository.findByid(smodel.getFindById()));
                jsonObject.put("listDetail",tStandardsRepository.findByMaterialIdAndMaterialTypeAndDeleteNo(smodel.getFindById(),3,0));
                break;
            default:

        }
        return jsonObject;
    }

    /**
     * 基础设置管理-产品页面 --新增/编辑页面获取的下拉框
     * @param
     * @throws PassportException
     */
    public JSONObject getProductBasicInfo(SelectModel selectModel)throws PassportException {
        JSONObject job = new JSONObject();

        job.put("XB",sdRepository.findByDicCode("XB"));//线别
        job.put("DW",sdRepository.findByDicCode("DW"));//单位
        job.put("XZ",mesMapper.findAllBobbinByXiaLa());//线轴
        job.put("SD",sdRepository.findByDicCode("SD"));//始端
        job.put("MD",sdRepository.findByDicCode("MD"));//末端

        job.put("YL",sdRepository.findByDicCode("YL"));//原料下拉框


        if(StringUtils.isEmpty(selectModel.getFindById())){//判断是否为空。如果为空就是新增。查询对应的各工序损耗比
            List<SDictionarier> sDictionarierList = sdRepository.findByDicCode("GX");
            List<GXLossBiVo> gxLossBislist = new ArrayList<>();
            for (int i = 0; i < sDictionarierList.size(); i++) {
                GXLossBiVo gxLossBi = new GXLossBiVo();
                gxLossBi.setGXid(sDictionarierList.get(i).getId());
                gxLossBi.setName(sDictionarierList.get(i).getName());//工序名称
                gxLossBislist.add(gxLossBi);
            }

            job.put("gxLossBislist",gxLossBislist);//查询工序
        }else{
            // 工序损耗比 GXLossBi
            job.put("gxLossBislist",mesMapper.findAllGXLossBiVoById(selectModel.getFindById()));
        }

        long tpNum =(tpRepository.findLastId()+1);
        String strnum = "TP000"+tpNum;

        job.put("productNumber",strnum);//产品编号

        return job;
    }

    /**
     * 原材料管理- 新增/编辑原材料型号规格获取下拉框数据
     * @param
     * @throws PassportException
     */
    public JSONObject getRawStockInfo(SelectModel selectModel)throws PassportException {
        JSONObject job = new JSONObject();
        String strnum ="";
        switch (selectModel.getFindModelName()){
            case "YL":
                strnum = "YL-000"+(stockRepository.findLastId()+1);
                break;
            case "XZ":
                strnum = "XZ-000"+(tbobbinRepository.findLastId()+1);
                break;
            case "QT":
                strnum = "QT-000"+(stockRepository.findLastId()+1);
                break;
        }

        job.put("rawStockNum",strnum);//编号
        job.put("XB",sdRepository.findByDicCode("XB"));//原材料类型
        job.put("DW",sdRepository.findByDicCode("DW"));//单位
        job.put("GX",sdRepository.findByDicCode("GX"));//工序

        return job;
    }

    /**
     * 产品管理-新增/修改产品
     * @param
     * @throws PassportException
     */
    @Transactional(rollbackFor = Exception.class)//回滚标志
    public void saveProduct(ClientModel model)throws PassportException {
        TProduct tProduct = model.gettProduct();
        if (StringUtil.isEmpty(tProduct.getProductName()) || StringUtils.isEmpty(tProduct.getProductModel())){
            throw new PassportException(ResultCode.PARAM_MISS_MSG);
        }

//        if(StringUtils.isEmpty(tProduct.getId()) && tpRepository.countByProductNumber(tProduct.getProductNumber()) > 0){
//            throw new PassportException(ResultCode.NUMBER_ISEXIST_MSG);
//        }

        if(StringUtils.isEmpty(tProduct.getId())){
            Integer getLastId =  tpRepository.findLastId();
            tProduct.setProductNumber("TP000"+(StringUtils.isEmpty(getLastId) ? 1: getLastId+1));
        }

        tProduct.setState(0);
        tProduct.setDeleteNo(0);
        TProduct p2 =  tpRepository.save(tProduct);

        Integer tpid = p2.getId();

        if(tpid > 0){

            tprostRepository.updateprostandardByOrderId(tpid);
            rawMaterialRatioRepository.updateratioByOrderId(tpid);

            //        规格型号
            for (int i = 0; i < model.gettProstandards().size(); i++) {
                TProstandard tProstandard = model.gettProstandards().get(i);

                tProstandard.setMilStart(tProstandard.getUmStart().divide(ResultSetting.RETURNCP_Um,8,BigDecimal.ROUND_DOWN));//换算 1mil = 25.4um
                tProstandard.setDeleteNo(0);
                tProstandard.setProductId(tpid);
            }
            tprostRepository.saveAll(model.gettProstandards());

            // 工序损耗比
            List<GXLossBi> list = new ArrayList<>();
            for (int i = 0; i < model.getGxLossBislist().size(); i++) {
                GXLossBiVo gxLossBiVo =  model.getGxLossBislist().get(i);
                GXLossBi gxLossBi = new GXLossBi();
                gxLossBi.setGXid(gxLossBiVo.getGXid());
                gxLossBi.setProductId(tpid);
                gxLossBi.setCpstart(gxLossBiVo.getCpstart());
                gxLossBi.setCpend(gxLossBiVo.getCpend());
                gxLossBi.setFpstart(gxLossBiVo.getFpstart());
                gxLossBi.setFpend(gxLossBiVo.getFpend());
                gxLossBi.setLossstart(gxLossBiVo.getLossstart());
                gxLossBi.setLossend(gxLossBiVo.getLossend());
                gxLossBi.setDeleteNo(0);

                list.add(gxLossBi);
            }
            gxLossBiRepository.saveAll(list);

            // 原料配比
            for (int i = 0; i < model.getRawMaterialRatios().size(); i++) {
                RawMaterialRatio rawMaterialRatio = model.getRawMaterialRatios().get(i);
                rawMaterialRatio.setDeleteNo(0);
                rawMaterialRatio.setProductId(tpid);
            }
            rawMaterialRatioRepository.saveAll(model.getRawMaterialRatios());
        }
    }


    /**
     * 原材料规格型号维护-新增/修改原材料
     * @param
     * @throws PassportException
     */
    @Transactional(rollbackFor = Exception.class)//回滚标志
    public void saveRawStock(ClientModel model)throws PassportException {
        Stock stock = model.getStock();
        if (StringUtil.isEmpty(stock.getStockName()) || StringUtils.isEmpty(stock.getStockModel())){
            throw new PassportException(ResultCode.PARAM_MISS_MSG);
        }

//        if(StringUtils.isEmpty(stock.getId()) && stockRepository.countByStockNumber(stock.getStockNumber()) > 0){
//            throw new PassportException(ResultCode.NUMBER_ISEXIST_MSG);
//        }
        if(StringUtils.isEmpty(stock.getId())){//新增的时候
            stock.setStockNumber("YL-000"+(stockRepository.findLastId()+1));
        }

        stock.setMaterialType(1);//1原料 2其他
        stock.setState(0);
        stock.setDeleteNo(0);
        Stock stock2 =  stockRepository.save(stock);

        Integer tpid = stock2.getId();

        tStandardsRepository.updateByOrderId(tpid);
        for (int i = 0; i < model.getTstandards().size(); i++) {
            TStandards tStandards = model.getTstandards().get(i);
            tStandards.setDeleteNo(0);
            tStandards.setMaterialId(tpid);
            tStandards.setMaterialType(1);
            tStandardsRepository.save(tStandards);
        }
    }

    /**
     * 线轴型号规格维护-新增/修改线轴
     * @param
     * @throws PassportException
     */
    @Transactional(rollbackFor = Exception.class)//回滚标志
    public void saveBobbin(ClientModel model)throws PassportException {
        TBobbin tb = model.gettBobbin();
        if (StringUtil.isEmpty(tb.getBobbinName()) || StringUtils.isEmpty(tb.getBobbinModel())){
            throw new PassportException(ResultCode.PARAM_MISS_MSG);
        }

//        if(StringUtils.isEmpty(tb.getId()) && tbobbinRepository.countByBobbinNumber(tb.getBobbinNumber()) > 0){
//            throw new PassportException(ResultCode.NUMBER_ISEXIST_MSG);
//        }

        if(StringUtils.isEmpty(tb.getId())){//新增
            tb.setBobbinNumber("XZ-000"+tbobbinRepository.findLastId()+1);//编号
        }
        tb.setDeleteNo(0);
        tb.setState(0);
        TBobbin tBobbin =   tbobbinRepository.save(tb);
        Integer tpid = tBobbin.getId();

        tStandardsRepository.updateByOrderId(tpid);
        List<TStandards> list = model.getTstandards();
        for (int i = 0; i < list.size(); i++) {
            TStandards tStandards = list.get(i);

            if(StringUtils.isEmpty(tStandards.getStandards())){
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();//手动回滚
                throw new PassportException(ResultCode.PARAM_MISS_MSG);
            }
            tStandards.setDeleteNo(0);
            tStandards.setMaterialId(tpid);
            tStandards.setMaterialType(2);
        }
        tStandardsRepository.saveAll(list);
    }

    /**
     * 其他规格型号维护-新增/修改其他东西
     * @param
     * @throws PassportException
     */

    @Transactional(rollbackFor = Exception.class)//回滚标志
    public void saveElseOther(ClientModel model)throws PassportException {
        Stock stock = model.getStock();
        if (StringUtil.isEmpty(stock.getStockName()) || StringUtils.isEmpty(stock.getStockModel())){
            throw new PassportException(ResultCode.PARAM_MISS_MSG);
        }

//        if(StringUtils.isEmpty(stock.getId()) &&stockRepository.countByStockNumber(stock.getStockNumber()) > 0){
//            throw new PassportException(ResultCode.NUMBER_ISEXIST_MSG);
//        }

        if(StringUtils.isEmpty(stock.getId())){//新增的时候
            stock.setStockNumber("QT-000"+(stockRepository.findLastId()+1));
        }

        stock.setMaterialType(2);//1原料 2其他
        stock.setState(0);
        stock.setDeleteNo(0);
        Stock stock2 =  stockRepository.save(stock);

        Integer tpekseid = stock2.getId();

        tStandardsRepository.updateByOrderId(tpekseid);

        for (int i = 0; i < model.getTstandards().size(); i++) {
            TStandards tStandards = model.getTstandards().get(i);
            tStandards.setDeleteNo(0);
            tStandards.setMaterialId(tpekseid);
            tStandards.setMaterialType(3);
            tStandardsRepository.save(tStandards);
        }
    }


    /**
     * 基础设置-根据Id修改订单状态 （state ‘S’或deleteno 'D'）(产品型号规格，原材料型号规格，线轴型号规格，其他型号规格等)
     * @param
     * @throws PassportException
     */
    @Transactional(rollbackFor = Exception.class)//回滚标志
    public void  updateStateById(UpdateModel updateModel)throws PassportException{
        switch (updateModel.getFindModelName()){
            case "product"://产品管理-修改产品
                TProduct tProduct = tpRepository.findByid(updateModel.getUpdateID());
                if(StringUtils.isEmpty(tProduct)){
                    throw new PassportException(ResultCode.DATA_NOEXIST_MSG);
                }
                if(updateModel.getUpdateType().equals("S")){
                    tProduct.setState(tProduct.getState() == 0?1:0);
                }else if(updateModel.getUpdateType().equals("D")){
                    tProduct.setDeleteNo(tProduct.getDeleteNo() == 0?1:0);
                }
                tpRepository.save(tProduct);

                break;
            case "stock"://原材料管理-修改原材料
                Stock stock = stockRepository.findByid(updateModel.getUpdateID());
                if(StringUtils.isEmpty(stock)){
                    throw new PassportException(ResultCode.DATA_NOEXIST_MSG);
                }
                if(updateModel.getUpdateType().equals("S")){
                    stock.setState(stock.getState() == 0?1:0);
                }else if(updateModel.getUpdateType().equals("D")){
                    stock.setDeleteNo(stock.getDeleteNo() == 0?1:0);
                }
                stockRepository.save(stock);
                break;
            case "bobbin"://线轴管理-修改线轴
                TBobbin tBobbin = tbobbinRepository.findByid(updateModel.getUpdateID());
                if(StringUtils.isEmpty(tBobbin)){
                    throw new PassportException(ResultCode.DATA_NOEXIST_MSG);
                }
                if(updateModel.getUpdateType().equals("S")){
                    tBobbin.setState(tBobbin.getState() == 0?1:0);
                }else if(updateModel.getUpdateType().equals("D")){
                    tBobbin.setDeleteNo(tBobbin.getDeleteNo() == 0?1:0);
                }
                tbobbinRepository.save(tBobbin);
                break;
            case "elseother"://其他修改
                Stock stockelse = stockRepository.findByid(updateModel.getUpdateID());
                if(StringUtils.isEmpty(stockelse)){
                    throw new PassportException(ResultCode.DATA_NOEXIST_MSG);
                }
                if(updateModel.getUpdateType().equals("S")){
                    stockelse.setState(stockelse.getState() == 0?1:0);
                }else if(updateModel.getUpdateType().equals("D")){
                    stockelse.setDeleteNo(stockelse.getDeleteNo() == 0?1:0);
                }
                stockRepository.save(stockelse);
                break;
            default:

        }
    }

//    ---------------------------------线轴管理维护----------------------------------------------------------------
    /**
     * 线轴管理-查询
     * @param
     * @throws PassportException
     */
    public PageInfo getAllTbobbinInfo(SelectModel smodel)throws PassportException{
        PageInfo page=new PageInfo();
        if(StringUtils.isEmpty(smodel.getPageNum()) || StringUtils.isEmpty(smodel.getPageSize())){
            throw new PassportException(ResultCode.PARAM_MISS_MSG);
        }

        List<StockModel> list = mesMapper.findAllBobbinDetailInfo(smodel.getFindById(),(smodel.getPageNum()-1)*smodel.getPageSize(),smodel.getPageSize());

        page.setList(list);
        page.setTotal(mesMapper.countByBobbinDetailInfo(smodel.getFindById()));
        page.setPageSize(smodel.getPageSize());
        page.setPageNum(smodel.getPageNum());
        return page;
    }

    /**
     * 线轴管理维护- 新增/编辑设备页面获取下拉框数据
     * @param
     * @throws PassportException
     */
    public JSONObject getBobbinBasicInfo()throws PassportException {
        JSONObject job = new JSONObject();

        job.put("bobbinXiaLa",mesMapper.findAllBobbinByXiaLa());//线轴下拉框
        return job;
    }


    /**
     * 线轴管理维护- 新增/编辑设备页面根据线轴id获取线轴规格
     * @param
     * @throws PassportException
     */
    public JSONObject getBobbinBasicInfoByBobbinId(SelectModel selectModel)throws PassportException {
        JSONObject job = new JSONObject();

        job.put("bobbinDetailXiaLa",mesMapper.findAllBobbinByBobbinId(selectModel.getFindById()));//根据线轴id查询线轴规格

        return job;
    }


    /**
     * 线轴管理维护- 新增/编辑设备页面根据线轴id获取线轴规格
     * @param
     * @throws PassportException
     */
    public JSONObject getBobbinInfoBystandardsId(SelectModel selectModel)throws PassportException {
        JSONObject job = new JSONObject();

        job.put("bobbinInfo",mesMapper.findByBobbinDetailId(selectModel.getFindById()));//根据线轴的规格id查询线轴信息

        List<TBobbinDetailVo> tBobbinDetailVoList = new ArrayList<>();

        List<TBobbinDetail> list = bobbinDetailRepository.findByStandardIdAndDeleteNo(selectModel.getFindById(),0);
        for (TBobbinDetail tbobbinDetail:list) {

            List<TBobbinHistory> bobbinHistoryList = bobbinHistoryRepository.findByBobbinDetailIdOrderByCreateTimeDesc(tbobbinDetail.getId());

            TBobbinDetailVo tBobbinDetailVo = new TBobbinDetailVo();

            tBobbinDetailVo.setId(tbobbinDetail.getId());
            tBobbinDetailVo.setStandardId(tbobbinDetail.getStandardId());
            tBobbinDetailVo.setAxisNumber(tbobbinDetail.getAxisNumber());
            tBobbinDetailVo.setNewBobbinWeight(tbobbinDetail.getNewBobbinWeight());

            tBobbinDetailVo.setBobbinWeight1(tbobbinDetail.getNewBobbinWeight());

            if(bobbinHistoryList.size() >=2){
                tBobbinDetailVo.setBobbinWeight2(bobbinHistoryList.get(1).getHistoryBobbinWeight());
            }else if(bobbinHistoryList.size() >=3){
                tBobbinDetailVo.setBobbinWeight3(bobbinHistoryList.size() >=3? BigDecimal.ZERO:bobbinHistoryList.get(2).getHistoryBobbinWeight());
            }else if(bobbinHistoryList.size() >=4){
                tBobbinDetailVo.setBobbinWeight4(bobbinHistoryList.size() >=4? BigDecimal.ZERO:bobbinHistoryList.get(3).getHistoryBobbinWeight());
            }else if(bobbinHistoryList.size() >=5){
                tBobbinDetailVo.setBobbinWeight5(bobbinHistoryList.size() >=5? BigDecimal.ZERO:bobbinHistoryList.get(4).getHistoryBobbinWeight());
            }


//            tBobbinDetailVo.setBobbinWeight2(bobbinHistoryList.size() >=2 ? BigDecimal.ZERO:bobbinHistoryList.get(1).getHistoryBobbinWeight());
//            tBobbinDetailVo.setBobbinWeight3(bobbinHistoryList.size() >=3? BigDecimal.ZERO:bobbinHistoryList.get(2).getHistoryBobbinWeight());
//            tBobbinDetailVo.setBobbinWeight4(bobbinHistoryList.size() >=4? BigDecimal.ZERO:bobbinHistoryList.get(3).getHistoryBobbinWeight());
//            tBobbinDetailVo.setBobbinWeight5(bobbinHistoryList.size() >=5? BigDecimal.ZERO:bobbinHistoryList.get(4).getHistoryBobbinWeight());

            tBobbinDetailVo.setState(tbobbinDetail.getState());
            tBobbinDetailVo.setDeleteNo(tbobbinDetail.getDeleteNo());

            tBobbinDetailVoList.add(tBobbinDetailVo);

        }
        job.put("bobbinDetail",tBobbinDetailVoList);//查询库存线轴信息

        return job;
    }


    /**
     * 线轴管理维护- 新增/编辑线轴明细-根据线轴明细查询线轴明细历史
     * @param
     * @throws PassportException
     */
    public JSONObject getBobbinHistoryByBobbinDetailId(SelectModel selectModel)throws PassportException {
        JSONObject job = new JSONObject();

        job.put("bobbinHistoryData",bobbinHistoryRepository.findByBobbinDetailIdOrderByCreateTimeDesc(selectModel.getFindById()));//查询线轴明细历史数据
        return job;
    }


    /**
     * 线轴管理维护- 新增/编辑线轴明细操作
     *
     * 1、判断一下该id是否存在，如果存在就是修改，不存在就是新增
     * 2、新增历史记录
     *
     * @param
     * @throws PassportException
     */
    public void saveBobbinDetail(List<TBobbinDetail> tBobbinDetailList)throws PassportException {

        for (TBobbinDetail tBobbinDetail:tBobbinDetailList) {
            if(StringUtils.isEmpty(tBobbinDetail.getStandardId()) || StringUtils.isEmpty(tBobbinDetail.getAxisNumber()) || StringUtils.isEmpty(tBobbinDetail.getNewBobbinWeight())){
                throw new PassportException(ResultCode.PARAM_MISS_MSG);
            }

            tBobbinDetail.setState(0);
            tBobbinDetail.setDeleteNo(0);

            TBobbinDetail tBobbinDetail1 =  bobbinDetailRepository.save(tBobbinDetail);//保存线轴明细

            TBobbinHistory tBobbinHistory = new TBobbinHistory();
            tBobbinHistory.setBobbinDetailId(tBobbinDetail1.getId());
            tBobbinHistory.setCreateTime(new Date());
            tBobbinHistory.setHistoryBobbinWeight(tBobbinDetail.getNewBobbinWeight());

            bobbinHistoryRepository.save(tBobbinHistory);//保存线轴历史数据
        }

    }

//    ---------------------------------线轴管理维护end----------------------------------------------------------------





//-------------------仪器设备管理维护-----------------------------------------------------



    /**
     * 设备管理- 新增/编辑设备页面获取下拉框数据
     * @param
     * @throws PassportException
     */
    public JSONObject getEquipmentBasicInfo()throws PassportException {
        JSONObject job = new JSONObject();

        job.put("SBType",sdRepository.findByDicCode("SBType"));//设备类型
        job.put("SBNumber",getEquipmentNumber());//设备编号
        return job;
    }

    /**
     * 设备管理- 新增/编辑设备页面根据模具类型id查询模具
     * @param
     * @throws PassportException
     */
    public JSONObject getAllTmouldByTypeId(SelectModel selectModel)throws PassportException {
        JSONObject job = new JSONObject();

        job.put("mouldXiaLa",mesMapper.findAllTmouldByXiaLa(selectModel.getFindById()));//模具下拉框
        return job;
    }

    //获取设备编号
    public String getEquipmentNumber(){
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy-HHmmss");
        String dateNowStr = sdf.format(date);
        return dateNowStr;
    }

    /**
     * 设备管理- 新增/编辑
     * @param
     * @throws PassportException
     */
    @Transactional(rollbackFor = Exception.class)//回滚标志
    public void saveEquipment(EquipmentModel model)throws PassportException {
        Equipment eq = model.getEquipment();
        if (StringUtil.isEmpty(eq.getEquipmentName()) || StringUtils.isEmpty(eq.getEquipmentModel())|| StringUtils.isEmpty(eq.getEquipmentTypeId())){
            throw new PassportException(ResultCode.PARAM_MISS_MSG);
        }

        //判断一下是否为报废状态
        if(!StringUtils.isEmpty(eq.getState()) && eq.getState() == 2){
            throw new PassportException(ResultCode.NO_ACCESS);
        }

        if(StringUtils.isEmpty(eq.getId()) && equipmentRepository.countByEquipmentNumber(eq.getEquipmentNumber()) > 0){
            throw new PassportException(ResultCode.NUMBER_ISEXIST_MSG);
        }

        eq.setDeleteNo(0);
        eq.setState(0);
        eq.setCheckNo(0);

        Equipment equipment = equipmentRepository.save(eq);
        Integer tpid = equipment.getId();

        //        设备新增的时候多加了个绑定模具id的,如果模具id不为空就顺便绑定模具管理的设备id
        if(!StringUtils.isEmpty(eq.getMouldId())){
            TMould tMould =  mouldRepository.findByid(eq.getMouldId());
            if(StringUtils.isEmpty(tMould)){throw new PassportException(ResultCode.DATA_NOEXIST_MSG);}

            tMould.setModelusedId(tpid);//绑定设备id
            mouldRepository.save(tMould);//保存
        }

        List<EquipmentItems> equipmentItemsList = new ArrayList<>();

        equipmentItemsRepository.updateByEquipmentId(tpid);
        for (int i = 0; i < model.getEquipmentItemsList().size(); i++) {
            EquipmentItems equipmentItems = model.getEquipmentItemsList().get(i);
            equipmentItems.setDeleteNo(0);
            equipmentItems.setEquipmentId(tpid);
            equipmentItemsList.add(equipmentItems);
        }

        equipmentItemsRepository.saveAll(equipmentItemsList);
    }

    /**
     * 设备管理-查询
     * @param
     * @throws PassportException
     */
    public PageInfo getAllEquipment(SelectModel smodel)throws PassportException{
        PageInfo page=new PageInfo();
        Calendar c = Calendar.getInstance();
        if(StringUtils.isEmpty(smodel.getPageNum()) || StringUtils.isEmpty(smodel.getPageSize())){
            throw new PassportException(ResultCode.PARAM_MISS_MSG);
        }

        Integer intMonth = c.get(Calendar.MONTH) > 10 ? c.get(Calendar.MONTH) : 0 + c.get(Calendar.MONTH);

        List<Equipment> listSale = mesMapper.findAllEquipment(smodel.getFindName(),smodel.getFindIdOne(),(smodel.getPageNum()-1)*smodel.getPageSize(),smodel.getPageSize(),
                   c.get(Calendar.YEAR),intMonth+1,c.get(Calendar.DAY_OF_MONTH));

//        for (Equipment eq:listSale) {
//            //此步操作是查询当天是否有新增数据
//            Integer countoverhaul = equipmentCheckRecordRepository.countByEquipmentTimeAndEquipmentId(DateUtil.today(),eq.getId());
//            eq.setCheckNo(countoverhaul >= 0 ? 0 :1);//检修否（当日）
//        }

        page.setList(listSale);
        page.setTotal(mesMapper.countByEquipment(smodel.getFindName(),smodel.getFindIdOne()));
        page.setPageSize(smodel.getPageSize());
        page.setPageNum(smodel.getPageNum());
        return page;
    }

    /**
     * 设备管理-根据Id修改设备状态 （state ‘S’或deleteno 'D'）
     * @param
     * @throws PassportException
     */
    @Transactional(rollbackFor = Exception.class)//回滚标志
    public void  updateEquipmentStateById(UpdateModel updateModel)throws PassportException{
        Equipment equipment = equipmentRepository.findByid(updateModel.getUpdateID());
        if(StringUtils.isEmpty(equipment)){
            throw new PassportException(ResultCode.DATA_NOEXIST_MSG);
        }


        //判断一下是否为报废状态
        if(!StringUtils.isEmpty(equipment.getState()) && equipment.getState() == 2){
            throw new PassportException(ResultCode.NO_ACCESS);
        }

        if(updateModel.getUpdateType().equals("S")){
            equipment.setState(equipment.getState() == 0?1:0);
        }else if(updateModel.getUpdateType().equals("D")){
            equipment.setDeleteNo(equipment.getDeleteNo() == 0?1:0);
        }else if(updateModel.getUpdateType().equals("B")){
            equipment.setState(2);//报废状态后，所有都无法修改
        }
        equipmentRepository.save(equipment);
    }

    /**
     * 设备管理-根据Id查询单条数据
     * @param
     * @throws PassportException
     */
    public JSONObject getAllEquipmentById(SelectModel smodel)throws PassportException{
        JSONObject job = new JSONObject();

        if(StringUtils.isEmpty(smodel.getFindById())){
            throw new PassportException(ResultCode.PARAM_MISS_MSG);
        }

        job.put("equipment",equipmentRepository.findByid(smodel.getFindById()));//设备
        job.put("equipmentOverhauls",equipmentItemsRepository.findAllByEquipmentIdAndDeleteNo(smodel.getFindById(),0));//检修条目

        return job;
    }

    /**
     * 设备管理-检修页面-根据Id查询单条数据
     * @param
     * @throws PassportException
     */
    public JSONObject getAllEquipmentOverhaulById(SelectModel smodel)throws PassportException{
        JSONObject jsonObject = new JSONObject();

        if(StringUtils.isEmpty(smodel.getFindById())){
            throw new PassportException(ResultCode.PARAM_MISS_MSG);
        }

        List<EquipmentOverhaul> equipmentOverhauls = equipmentOverhaulRepository.findAllByEquipmentId(smodel.getFindById());//检修项目

        Integer equipId = smodel.getFindById();

        Integer currentNormalMonth = 0;
        Integer currentNONormalMonth = 0;
        Integer currentRestMonth = 0;
        Integer currentOutMonth = 0;

        Integer SumNormalMonth = 0;
        Integer SumNONormalMonth = 0;
        Integer SumRestMonth = 0;
        Integer SumOutMonth = 0;

        if(equipmentOverhauls.size() != 0){
            String[] strings =equipmentOverhauls.get(0).getEquipmentTime().split("\\-");
            Integer year = Integer.valueOf(strings[0]);
            Integer month = Integer.valueOf(strings[1]);
            currentNormalMonth = mesMapper.sumEquipmentUserTimeByEqId(equipId,1,year,month).stream().mapToInt(Integer::intValue).sum();
            currentNONormalMonth = mesMapper.sumEquipmentUserTimeByEqId(equipId,2,year,month).stream().mapToInt(Integer::intValue).sum();
            currentRestMonth = mesMapper.sumEquipmentUserTimeByEqId(equipId,3,year,month).stream().mapToInt(Integer::intValue).sum();
            currentOutMonth = mesMapper.sumEquipmentUserTimeByEqId(equipId,4,year,month).stream().mapToInt(Integer::intValue).sum();

            SumNormalMonth = mesMapper.sumEquipmentUserTimeByEqId(equipId,1,null,null).stream().mapToInt(Integer::intValue).sum();
            SumNONormalMonth = mesMapper.sumEquipmentUserTimeByEqId(equipId,2,null,null).stream().mapToInt(Integer::intValue).sum();
            SumRestMonth = mesMapper.sumEquipmentUserTimeByEqId(equipId,3,null,null).stream().mapToInt(Integer::intValue).sum();
            SumOutMonth = mesMapper.sumEquipmentUserTimeByEqId(equipId,4,null,null).stream().mapToInt(Integer::intValue).sum();
        }

        JSONObject jsonObject1 = new JSONObject();
        jsonObject1.put("currentNormalMonth",currentNormalMonth);//本月正常运行时间
        jsonObject1.put("currentNONormalMonth",currentNONormalMonth);//本月不正常运行时间
        jsonObject1.put("currentRestMonth",currentRestMonth);//本月休息运行时间
        jsonObject1.put("currentOutMonth",currentOutMonth);//本月停用运行时间

        jsonObject1.put("SumNormalMonth",SumNormalMonth);//累计正常运行时间
        jsonObject1.put("SumNONormalMonth",SumNONormalMonth);//累计不正常运行时间
        jsonObject1.put("SumRestMonth",SumRestMonth);//累计休息运行时间
        jsonObject1.put("SumOutMonth",SumOutMonth);//累计停用运行时间

        Calendar c = Calendar.getInstance();
        Integer intMonth = c.get(Calendar.MONTH) > 10 ? c.get(Calendar.MONTH) : 0 + c.get(Calendar.MONTH);
        jsonObject.put("equipment",mesMapper.findEquipmentVoById(smodel.getFindById(),c.get(Calendar.YEAR),intMonth+1,c.get(Calendar.DAY_OF_MONTH)));
        jsonObject.put("sumTime",jsonObject1);//统计的时间


        return jsonObject;
    }

    /**
     * 设备管理- 检修-新增(年月)
     * 1、选择需要新增的年-月检修时间后
     * 2、获取该设备ID的检项id list
     * 3、获取该年-月的天数
     * @param
     * @throws PassportException
     */
    @Transactional(rollbackFor = Exception.class)//回滚标志
    public JSONObject saveOverhaulYM(SelectModel selectModel)throws PassportException {
        JSONObject jsonObject = new JSONObject();
        if(StringUtils.isEmpty(selectModel.getFindById()) || StringUtils.isEmpty(selectModel.getFindDate())){
            throw new PassportException(ResultCode.DATA_NOEXIST_MSG);
        }

        Equipment equipment = equipmentRepository.findByid(selectModel.getFindById());

        //判断一下是否为报废状态
        if(!StringUtils.isEmpty(equipment.getState()) && equipment.getState() == 2){
            throw new PassportException(ResultCode.NO_ACCESS);
        }

        Integer countoverhaul = equipmentOverhaulRepository.countByEquipmentIdAndEquipmentTime(selectModel.getFindById(),selectModel.getFindDate());
        if(countoverhaul > 0 ){
            throw new PassportException(ResultCode.DICTIONARY_ISEXIST_MSG);
        }
        mesMapper.insertEquipmentOverhaul(selectModel.getFindById(),selectModel.getFindDate());//把当前设备的检修项目记录下来。

        //查询获取该设备ID的检项id list
        jsonObject.put("listequipmentItemsRecord",getEquipmentRecordByYMandEquipmentId(selectModel));//检修数据

        return  jsonObject;
    }

    /**
     * 设备管理-检修页面- 新增检修页面(每一天)获取检项数据
     * @param
     * @throws PassportException
     */
    public JSONObject getEquipmentOverhaulBasicInfo(SelectModel selectModel)throws PassportException {
        JSONObject job = new JSONObject();
        String[] strArr = selectModel.getFindDate().split("\\-");

        String str = strArr[0]+strArr[1];

        List<EquipmentItems> equipmentItemsList =mesMapper.findEquipmentoverhaulByEquipmentIdAndTime(selectModel.getFindById(),str);//检修项目

        if(equipmentItemsList.size() == 0){//如果为空则从设备list里面查询。在新增的时候再自动添加该设备的年月
            equipmentItemsList = equipmentItemsRepository.findAllByEquipmentIdAndDeleteNo(selectModel.getFindById(),0);
        }
        job.put("equipmentItemsList",equipmentItemsList);//检修list
        return job;
    }

    //方法 ：判断是否超出当前日期了
    public String[] comDate(String strdate)throws PassportException {
        String[] strArr = strdate.split("\\-");
        Date date1=new Date(Integer.valueOf(strArr[0]) ,Integer.valueOf(strArr[1]),Integer.valueOf(strArr[2]));
        Calendar c = Calendar.getInstance();
        Date nowdate=new Date(c.get(Calendar.YEAR), c.get(Calendar.MONTH) + 1, c.get(Calendar.DAY_OF_MONTH));
        if(date1.after(nowdate)){
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();//手动回滚
            throw new PassportException(ResultCode.Date_ERROR);
        }
        return strArr;
    }

    /**
     * 设备管理-检修页面-新增检修操作
     * @param
     * @throws PassportException
     */
    @Transactional(rollbackFor = Exception.class)//回滚标志
    public void  saveEquipmentOverhaulRecord(UserInfoForToken userInfoForToken, EquipmentModel equipmentModel)throws PassportException{
        EquipmentCheckRecord equipmentCheckRecord =  equipmentModel.getEquipmentCheckRecord();

        if(StringUtils.isEmpty(equipmentCheckRecord.getEquipmentId()) ||StringUtils.isEmpty(equipmentCheckRecord.getFrequency())){
            throw new PassportException(ResultCode.PARAM_MISS_MSG);
        }

        Equipment equipment = equipmentRepository.findByid(equipmentCheckRecord.getEquipmentId());

        //判断一下是否为报废状态
        if(!StringUtils.isEmpty(equipment.getState()) && equipment.getState() == 2){
            throw new PassportException(ResultCode.NO_ACCESS);
        }

        if(equipmentCheckRecordRepository.countByEquipmentTimeAndEquipmentIdAndFrequency(equipmentCheckRecord.getEquipmentTime(),equipmentCheckRecord.getEquipmentId(),equipmentCheckRecord.getFrequency()) > 0 ){
            throw new PassportException(ResultCode.DICTIONARY_ISEXIST_MSG);
        }

        String[] strArr =  comDate(equipmentCheckRecord.getEquipmentTime());

        //此步操作是查询是否有新增当前设备的年月，如果没有则保存一下
        Integer countoverhaul = equipmentOverhaulRepository.countByEquipmentIdAndEquipmentTime(equipmentCheckRecord.getEquipmentId(),strArr[0]+"-"+strArr[1]);
        if(countoverhaul <= 0 ){
            mesMapper.insertEquipmentOverhaul(equipmentCheckRecord.getEquipmentId(),strArr[0]+"-"+strArr[1]);//把当前设备的检修项目记录下来。
        }

        equipmentCheckRecord.setStaffId(Integer.valueOf(userInfoForToken.getUserId()));
        equipmentCheckRecord.setCreateTime(new Date());
        equipmentCheckRecord.setCheckYear(Integer.valueOf(strArr[0]));
        equipmentCheckRecord.setCheckMonth(Integer.valueOf(strArr[1]));
        equipmentCheckRecord.setCheckDay(Integer.valueOf(strArr[2]));
        EquipmentCheckRecord equipmentCheckRecord2 = equipmentCheckRecordRepository.save(equipmentCheckRecord);

        List<EquipmentCheckDetailRecord> listdetail = equipmentModel.getEquipmentCheckDetailRecordList();
        for (int i = 0; i < listdetail.size(); i++) {
            EquipmentCheckDetailRecord equipmentCheckDetailRecord = listdetail.get(i);
            if(StringUtils.isEmpty(equipmentCheckDetailRecord.getEquipmentOverhaulId())||StringUtils.isEmpty(equipmentCheckDetailRecord.getOverhaulState())){
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();//手动回滚
                throw new PassportException(ResultCode.PARAM_MISS_MSG);
            }

            equipmentCheckDetailRecord.setEquipmentCheckRecordId(equipmentCheckRecord2.getId());
        }
        equipmentCheckDetailRecordRepository.saveAll(listdetail);
    }

    /**
     * 设备管理-检修页面- 根据设备ID查询已创建的年月点检信息
     * @param
     * @throws PassportException
     */
    public JSONObject getEquipmentRecordYMByEquipmentId(SelectModel selectModel)throws PassportException {
        JSONObject job = new JSONObject();

        List<EquipmentOverhaul> equipmentOverhauls = equipmentOverhaulRepository.findAllByEquipmentId(selectModel.getFindById());//检修项目
        job.put("equipmentOverhauls",equipmentOverhauls);//检修年月列表

        return job;
    }

    /**
     * 设备管理-检修页面- 根据设备id跟年月 查询该设备的点检信息
     *
     * B：白班 Y：夜班  E 正常 F 不正常 G 休班 H 停用
     * @param
     * @throws PassportException
     */
    public JSONObject getEquipmentRecordByYMandEquipmentId(SelectModel selectModel)throws PassportException {
        JSONObject job = new JSONObject();
        if(StringUtils.isEmpty(selectModel.getFindById())){
            throw new PassportException(ResultCode.PARAM_MISS_MSG);
        }
        EquipmentOverhaul equipmentOverhaul = new EquipmentOverhaul();
        String equipmentTime = selectModel.getFindDate();
        if(!StringUtils.isEmpty(selectModel.getFindIdOne())){
            equipmentOverhaul = equipmentOverhaulRepository.findByid(selectModel.getFindIdOne());//检修项目:根据id查询
            equipmentTime = equipmentOverhaul.getEquipmentTime();
        }

        List<Integer> liststr = getDateConfig.getDayListsByYearMonth(equipmentTime);

        String[] strArr = equipmentTime.split("\\-");

        //把设备时间和操作人员两个项目弄进去
        List<EquipmentItems> equipmentItemsList =mesMapper.findEquipmentoverhaulByEquipmentIdAndTime(selectModel.getFindById(),equipmentTime);//检修项目
        EquipmentItems equipmentItems = new EquipmentItems();
        EquipmentItems equipmentItems2 = new EquipmentItems();
        equipmentItems.setEquipmentItems("设备运行时间");
        equipmentItems.setId(0);
        equipmentItems2.setEquipmentItems("操作人员");
        equipmentItems2.setId(-1);
        equipmentItemsList.add(equipmentItems);
        equipmentItemsList.add(equipmentItems2);

        List<EquipmentRecordVo> equipmentRecordVoList = mesMapper.findCheckRecordByEquipmentIdAndTime(selectModel.getFindById(),Integer.valueOf(strArr[0]) ,Integer.valueOf(strArr[1]));

        JSONArray jsonArray = new JSONArray();
        for (int i = 0; i < equipmentItemsList.size(); i++) {//检修的项目类型
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("name",equipmentItemsList.get(i).getEquipmentItems());//修检项目名称
            jsonObject.put("shiftShow",equipmentItemsList.get(i).getId() <= 0 ? false:true);//是否显示班次

            JSONArray jsonArray1 = new JSONArray();

                for (int j = 0; j < liststr.size(); j++) {
                    Map<String,String> map = new HashMap<>();
                    map.put("day",liststr.get(j).toString());//day
                    map.put("value","");//检修情况:初始值为空
                    if(equipmentRecordVoList.size() >0){//判断数据是否大于0/是否存在数据
                        for (int s = 0; s < equipmentRecordVoList.size(); s++) {
                            Integer getInt = equipmentItemsList.get(i).getId();
                            if(equipmentRecordVoList.get(s).getCheckDay() == liststr.get(j) && equipmentRecordVoList.get(s).getEquipmentOverhaulId() == getInt){//如果当前检修项目相等,日期相等。则赋值
                                map.put("value",equipmentRecordVoList.get(s).getFrequencystr()+equipmentRecordVoList.get(s).getOverhaulState());//赋值检修情况
                            }

                            if(equipmentRecordVoList.get(s).getCheckDay() == liststr.get(j) && getInt <= 0){
                                map.put("value",getInt == 0 ? equipmentRecordVoList.get(s).getUserTime().toString():equipmentRecordVoList.get(s).getStaffName());//赋值检修情况
                            }
                        }
                    }
                    jsonArray1.add(map);
                }
            jsonObject.put("mechanism",jsonArray1);
            jsonArray.add(jsonObject);
        }
        job.put("tableData3",jsonArray);
        job.put("mechanism",liststr);
        return job;
    }

    /**
     * 设备管理-历史报修页面-查询
     * @param
     * @throws PassportException
     */
    public PageInfo getAllRepairReport(SelectModel smodel)throws PassportException{
        PageInfo page=new PageInfo();

        if(StringUtils.isEmpty(smodel.getPageNum()) || StringUtils.isEmpty(smodel.getPageSize())|| StringUtils.isEmpty(smodel.getFindById())){
            throw new PassportException(ResultCode.PARAM_MISS_MSG);
        }

        List<EquipmentVo> listSale = mesMapper.getAllRepairReport(smodel.getFindName(),smodel.getFindIdOne(),smodel.getFindById(),(smodel.getPageNum()-1)*smodel.getPageSize(),smodel.getPageSize());

        page.setList(listSale);
        page.setTotal(mesMapper.countByRepairReport(smodel.getFindName(),smodel.getFindIdOne(),smodel.getFindById()));
        page.setPageSize(smodel.getPageSize());
        page.setPageNum(smodel.getPageNum());
        return page;
    }


    /**
     * 设备管理-新增报修-操作
     * 1、判断一下是否该设备还有正在处理的单子
     * 2、提交报修
     * @param
     * @throws PassportException
     */
    @Transactional(rollbackFor = Exception.class)//回滚标志
    public void  saveRepairReport(UpdateModel updateModel)throws PassportException{
        if(StringUtils.isEmpty(updateModel.getUpdateID()) || StringUtils.isEmpty(updateModel.getUpdateRemark())){
            throw new PassportException(ResultCode.PARAM_MISS_MSG);
        }

        Equipment equipment = equipmentRepository.findByid(updateModel.getUpdateID());
        if(StringUtils.isEmpty(equipment)){
            throw new PassportException(ResultCode.DATA_NOEXIST_MSG);
        }

        //判断一下是否为报废状态
        if(!StringUtils.isEmpty(equipment.getState()) && equipment.getState() == 2){
            throw new PassportException(ResultCode.NO_ACCESS);
        }

        if(repairReportRepository.countByEquipmentIdAndStateNot(updateModel.getUpdateID(),RepairReportStateChange.Purchase_OrderAudit_Finished_Sured) > 0 ){
            throw new PassportException(ResultCode.DICTIONARY_ISEXIST_REPORD_MSG);
        }

        //新增报修订单
        RepairReport repairReport = new RepairReport();
        repairReport.setEquipmentId(updateModel.getUpdateID());
        repairReport.setRepairReportNumber("BX-"+getEquipmentNumber());//报修单号
        repairReport.setCreateTime(new Date());
        repairReport.setState(RepairReportStateChange.RepairReport_Audit_NotAudit);
        repairReport.setDeleteNo(0);

        RepairReport repairReport1 =repairReportRepository.save(repairReport);

        long t4=System.currentTimeMillis();
        mesMapper.insertOrderPocess(repairReport1.getId(),t4,"BX","");//保存订单的时候就把需要审核的流程保存起来

        Matters matters = new Matters();
        matters.setMatterNews("您有1个报修单待处理");
        matters.setStaffId(auditSettingRepository.getstaffIdByauditTypeandLevel("BX","1"));
        matters.setOrderId(repairReport1.getId());
        matters.setType(6);//类型 1 采购-订单审核  2 采购-来料检验 3销售-订单审核 4销售-退回审核 5生产计划单-审核 6 设备报修-审核
        matters.setDeleteNo(0);
        matters.setFinishedNo(0);
        matters.setReadNo(0);
        mattersRepository.save(matters);//新增待办事项

        AuditPocess auditPocess = new AuditPocess();
        auditPocess.setAuditOrderId(repairReport1.getId());
        auditPocess.setAuditType("BX");
        auditPocess.setAuditSetting(RepairReportStateChange.getRepairReportState(repairReport.getState()));
        auditPocess.setAuditSettingId(0);
        auditPocess.setRemark(updateModel.getUpdateRemark());
        auditPocess.setDeleteNo(0);
        auditPocessRepository.save(auditPocess);//新增审核流程
    }

    /**
     * 设备管理-历史报修页面-根据报修单id查询详情
     * @param
     * @throws PassportException
     */
    public JSONObject getRepairReportById(UserInfoForToken userInfoForToken,SelectModel smodel)throws PassportException{
        JSONObject jsonObject=new JSONObject();

        if(StringUtils.isEmpty(smodel.getFindById())){
            throw new PassportException(ResultCode.PARAM_MISS_MSG);
        }
        EquipmentVo equipmentVo = mesMapper.getRepairReportById(smodel.getFindById());

        updateMatterReadNo(Integer.valueOf(userInfoForToken.getUserId()),equipmentVo.getId());

        jsonObject.put("equipmentVo",equipmentVo);
        jsonObject.put("auditDetail",mesMapper.findAuditListById(equipmentVo.getId(),"BX",""));//加上审核详情

        return jsonObject;
    }


    /**
     * 待办事项-修改状态（已读否）
     * @throws PassportException
     */
    public void updateMatterReadNo(Integer staffId,Integer orderId)throws PassportException {

        String str = "BX";
        Integer type = 6;

        List<OrderAuditPocess> oldaudit = orderAuditPocessRepository.findByAuditTypeAndOrderId(str,orderId);

        if(oldaudit.size() != 0 &&  staffId == oldaudit.get(0).getStaffId()){//对比两个操作的员工是否相同
            mattersRepository.updateReadNoByStaffIdAndTypeAndOrderId(staffId,type,orderId);
        }

    }

    /**
     * 设备管理-历史报修页面-根据报修单Id修改状态 （state ‘S’或deleteno 'D'）
     * @param
     * @throws PassportException
     */
    @Transactional(rollbackFor = Exception.class)//回滚标志
    public void  updateRepairReportStateById(UpdateModel updateModel)throws PassportException{
        RepairReport repairReport = repairReportRepository.findByid(updateModel.getUpdateID());
        if(StringUtils.isEmpty(repairReport)){
            throw new PassportException(ResultCode.DATA_NOEXIST_MSG);
        }

        //判断一下是否为报废状态
        Equipment equipment = equipmentRepository.findByid(repairReport.getEquipmentId());
        if(!StringUtils.isEmpty(equipment.getState()) && equipment.getState() == 2){
            throw new PassportException(ResultCode.NO_ACCESS);
        }

        if(repairReport.getState() != RepairReportStateChange.Purchase_OrderAudit_Finished_Sured){
            throw new PassportException(ResultCode.DICTIONARY_ISNOTFINISHED_REPORD_MSG);
        }
        if(updateModel.getUpdateType().equals("D")){
            repairReport.setDeleteNo(repairReport.getDeleteNo() == 0?1:0);
        }
        repairReportRepository.save(repairReport);
    }

    /**
     * 当前订单状态Id
     * @param orderStateId 订单状态Id
     * @return
     */
    public Integer showRepairReportState(Integer orderStateId)throws PassportException{
        Integer getint = 0;
        switch (orderStateId){
            case 0://待处理
                getint = 1;
                break;
            case 1://1待维修
                getint = 2;
                break;
            case 2:////待检验
                getint = 3;
                break;
            case 3://已完成

                break;
            default:
                getint = 0;
                break;
        }
        return  getint;
    }

    /**
     * 设备管理-历史报修页面-根据报修单Id修改报修流程(待处理 待维修 待检验 已完成)
     * @param
     * @throws PassportException
     */
    @Transactional(rollbackFor = Exception.class)//回滚标志
    public void  updateRepairReportAuditPocessById(UserInfoForToken userInfoForToken,UpdateModel updateModel)throws PassportException{
        Integer currentStaffId = Integer.valueOf(userInfoForToken.getUserId());

        RepairReport repairReport = repairReportRepository.findByid(updateModel.getUpdateID());
        if(StringUtils.isEmpty(repairReport)){
            throw new PassportException(ResultCode.DATA_NOEXIST_MSG);
        }

        Equipment equipment = equipmentRepository.findByid(repairReport.getEquipmentId());
        if(!StringUtils.isEmpty(equipment.getState()) && equipment.getState() == 2){
            throw new PassportException(ResultCode.NO_ACCESS);
        }

        if(repairReport.getState() == RepairReportStateChange.Purchase_OrderAudit_Finished_Sured){
            throw new PassportException(ResultCode.NO_ACCESS);
        }

        Integer orderId = updateModel.getUpdateID();
        List<OrderAuditPocess> oldauditCG = orderAuditPocessRepository.findByAuditTypeAndOrderId("BX",orderId);
        if(!StringUtils.isEmpty(oldauditCG)){

            Integer oldstaffid = oldauditCG.get(0).getStaffId();

            if(currentStaffId != oldstaffid){//对比两个操作的员工是否相同
                throw new PassportException(ResultCode.NO_ACCESS);
            }

            //修改待办事项
            mattersRepository.updateByStaffIdAndTypeAndOrderId(oldstaffid,6,orderId);

            //修改订单审核流程完成度
            orderAuditPocessRepository.updateByAuditTypeAndOrderIdandAndStaffId("BX",orderId,oldstaffid);
        }
        if(oldauditCG.size() >1){
            Integer countId = oldauditCG.get(1).getStaffId() ;

            if(!StringUtil.isEmpty(countId.toString()) && countId > 0){//如果有进程。则进入到下一个阶段。并且修改上一个人的完成状态
                Matters matters = new Matters();
                matters.setMatterNews("您有1个报修单待处理");
                matters.setStaffId(countId);// 获取下一个阶段需要审核的员工Id
                matters.setOrderId(orderId);
                matters.setDeleteNo(0);
                matters.setType(6);
                matters.setReadNo(0);
                matters.setFinishedNo(0);
                mattersRepository.save(matters);//新增待办事项
            }
        }
        Integer getint = showRepairReportState(repairReport.getState());//新的报修状态id

        AuditPocess auditPocess = new AuditPocess();
        auditPocess.setAuditOrderId(updateModel.getUpdateID());//审核单子ID
        auditPocess.setAuditType("BX");
        auditPocess.setAuditSettingId(0);//处理的单子Id
        auditPocess.setAuditSetting(RepairReportStateChange.getRepairReportState(getint));
        auditPocess.setRemark(updateModel.getUpdateRemark());
        auditPocess.setDeleteNo(0);
        auditPocessRepository.save(auditPocess);//新增审核流程

        repairReport.setState(getint);
        repairReportRepository.save(repairReport);//更新状态
    }


//骚操作：导出excel--暂停
//    @RequestMapping("/export")
//    @ResponseBody
//    public void export(HttpServletResponse response){
//        List<User> list = new ArrayList<>();
//        list.add(new User("zhangsan","1231",new Date()));
//        list.add(new User("zhangsan1","1232",new Date()));
//        list.add(new User("zhangsan2","1233",new Date()));
//        list.add(new User("zhangsan3","1234",new Date()));
//        list.add(new User("zhangsan4","1235",new Date()));
//        list.add(new User("zhangsan5","1236", DateUtil.date(new Date())));
//        // 通过工具类创建writer，默认创建xls格式
//        ExcelWriter writer = ExcelUtil.getWriter();
//        //自定义标题别名
//        writer.addHeaderAlias("name", "姓名");
//        writer.addHeaderAlias("age", "年龄");
//        writer.addHeaderAlias("birthDay", "生日");
//        // 合并单元格后的标题行，使用默认标题样式
//        writer.merge(2, "申请人员信息");
//        // 一次性写出内容，使用默认样式，强制输出标题
//        writer.write(list, true);
//        //out为OutputStream，需要写出到的目标流
//        //response为HttpServletResponse对象
//        response.setContentType("application/vnd.ms-excel;charset=utf-8");
//        //test.xls是弹出下载对话框的文件名，不能为中文，中文请自行编码
//        String name = StringUtils.toUtf8String("申请学院");
//        response.setHeader("Content-Disposition","attachment;filename="+name+".xls");
//        ServletOutputStream out= null;
//        try {
//            out = response.getOutputStream();
//            writer.flush(out, true);
//        }
//        catch (IOException e) {
//            e.printStackTrace();
//        }
//        finally {
//            // 关闭writer，释放内存
//            writer.close();
//        }
//        //此处记得关闭输出Servlet流
//        IoUtil.close(out);
//    }
}
