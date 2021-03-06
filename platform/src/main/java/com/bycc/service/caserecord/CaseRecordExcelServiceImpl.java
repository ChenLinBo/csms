package com.bycc.service.caserecord;


import com.bycc.dao.BdmPoliceStationDao;
import com.bycc.dao.CaseRecordDao;
import com.bycc.entity.BdmPoliceStation;
import com.bycc.entity.CaseRecord;
import com.bycc.enumitem.CaseHandle;
import org.apache.poi.hssf.usermodel.*;
import org.smartframework.utils.helper.DateHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by wanghaidong on 2017/6/20.
 */
@Service
public class CaseRecordExcelServiceImpl implements CaseRecordExcelService{


    @Autowired
    CaseRecordService caseRecordService;
    @Autowired
    CaseRecordDao caseRecordDao;
    @Autowired
    BdmPoliceStationDao bdmPoliceStationDao;
    @PersistenceContext
    private EntityManager em;

    @Override
    public HSSFWorkbook getExcel(String handleStatus,String masterUnit,String acceptStart,String acceptEnd,String closeStart,String closeEnd) throws ParseException {
        String[] excelHeader = { "ID","案件编号", "案件名称", "嫌疑人","主办单位","主办人","办理状态","警情编号","简要案情","案件类型","受理单位","受理人","协办单位","协办人","案发时间","受案时间","立案时间","办案时间","结案时间","案件状态","备注"};
        List<CaseRecord> list=findCaseRecordExport(handleStatus,masterUnit,acceptStart,acceptEnd,closeStart,closeEnd);
        HSSFWorkbook wb = new HSSFWorkbook();
        HSSFSheet sheet = wb.createSheet("sheet");
        //第一行的空白
        sheet.createRow(0);
        //第二行的标题栏
        HSSFRow row = sheet.createRow(1);
        HSSFCellStyle style = wb.createCellStyle();
        for (int i = 0; i < excelHeader.length; i++) {
            HSSFCell cell = row.createCell(i);
            cell.setCellValue(excelHeader[i]);
            cell.setCellStyle(style);
        }
        for (int i = 0; i < list.size(); i++) {
            row = sheet.createRow(i + 2);
            CaseRecord caseRecord = list.get(i);
            row.createCell(0).setCellValue(caseRecord.getId()==null?"":caseRecord.getId().toString());
            row.createCell(1).setCellValue(caseRecord.getCaseCode());
            row.createCell(2).setCellValue(caseRecord.getCaseName());
            row.createCell(3).setCellValue(caseRecord.getSuspect());
            row.createCell(4).setCellValue(caseRecord.getMasterUnit()==null?"":caseRecord.getMasterUnit().getName());
            row.createCell(5).setCellValue(caseRecord.getMasterPolice()==null?"":caseRecord.getMasterPolice().getUser().getName());
            row.createCell(6).setCellValue(caseRecord.getCaseHandle().name());
            row.createCell(7).setCellValue(caseRecord.getAlarmCode());
            row.createCell(8).setCellValue(caseRecord.getCaseSummary());
            row.createCell(9).setCellValue(caseRecord.getCaseType().name());
            row.createCell(10).setCellValue(caseRecord.getAcceptUnit()==null?"":caseRecord.getAcceptUnit().getName());
            row.createCell(11).setCellValue(caseRecord.getAcceptPolice()==null?"":caseRecord.getAcceptPolice().getUser().getName());
            row.createCell(12).setCellValue(caseRecord.getSlaveUnit()==null?"":caseRecord.getSlaveUnit().getName());
            row.createCell(13).setCellValue(caseRecord.getSlavePolice()==null?"":caseRecord.getSlavePolice().getUser().getName());
            if (caseRecord.getOccurDate()!=null){
                row.createCell(14).setCellValue(caseRecord.getOccurDate().toString());
            }
            if (caseRecord.getAcceptDate()!=null){
                row.createCell(15).setCellValue(caseRecord.getAcceptDate().toString());
            }
            if (caseRecord.getRegisterDate()!=null) {
                row.createCell(16).setCellValue(caseRecord.getRegisterDate().toString());
            }
            if (caseRecord.getStartDate()!=null){
                row.createCell(17).setCellValue(caseRecord.getStartDate().toString());
            }
            if (caseRecord.getCloseDate()!=null){
                row.createCell(18).setCellValue(caseRecord.getCloseDate().toString());
            }
            row.createCell(19).setCellValue(caseRecord.getCaze()==null?"":caseRecord.getCaze().getCaseStatus().name());
            row.createCell(20).setCellValue(caseRecord.getNote()==null?"":caseRecord.getNote());

        }

        for (int i = 0; i < excelHeader.length; i++) {
            sheet.autoSizeColumn(i,true);
        }
        return wb;
    }


    /**
     * Jpa多条件组合查询
     * @param caseHandle
     * @param masterUnitId
     * @param acceptDateStart
     * @param acceptDateEnd
     * @param closeDateStart
     * @param closeDateEnd
     * @return
     */
    private Specification<CaseRecord> where(final String caseHandle,final String masterUnitId,final String acceptDateStart,final String acceptDateEnd,final String closeDateStart,final String closeDateEnd){
        return new Specification<CaseRecord>() {
            @Override
            public Predicate toPredicate(Root<CaseRecord> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates = new ArrayList<Predicate>();
                //案件状态
                if (caseHandle!=null&&!caseHandle.equals("")){
                    predicates.add(criteriaBuilder.equal(root.<String>get("caseHandle"),caseHandle));
                }
                //主办单位
                if (masterUnitId!=null&&!masterUnitId.equals("")){
                    predicates.add(criteriaBuilder.equal(root.<String>get("masterUnit"),masterUnitId));
                }
                //受立案时间
                if (acceptDateStart!=null&&!acceptDateStart.equals("")&&acceptDateEnd!=null&&!acceptDateEnd.equals("")){
                    Date startDate;
                    Date endDate;
                    try{
                        startDate = DateHelper.formatStringToDate(acceptDateStart,"yyyy-MM-dd");
                    }catch (ParseException e){
                        startDate = new Date(2000,1,1);//2000 01 01
                    }

                    try{
                        endDate = DateHelper.formatStringToDate(acceptDateEnd,"yyyy-MM-dd");
                    }catch (ParseException e){
                        endDate = new Date(2100,1,1);//2000 01 01
                    }
                    predicates.add(criteriaBuilder.between(root.<Date>get("acceptDate"),startDate,endDate));
                }
                //结案时间
                if(closeDateStart!=null&&!closeDateStart.equals("")&&closeDateEnd!=null&&!closeDateEnd.equals("")){
                    Date startDate;
                    Date endDate;
                    try{
                        startDate = DateHelper.formatStringToDate(closeDateStart,"yyyy-MM-dd");
                    }catch (ParseException e){
                        startDate = new Date(2000,1,1);//2000 01 01
                    }

                    try{
                        endDate = DateHelper.formatStringToDate(closeDateEnd,"yyyy-MM-dd");
                    }catch (ParseException e){
                        endDate = new Date(2100,1,1);//2000 01 01
                    }
                    predicates.add(criteriaBuilder.between(root.<Date>get("closeDate"),startDate,endDate));
                }
                return  criteriaQuery.where(predicates.toArray(new Predicate[predicates.size()])).getRestriction();
            }
        };

    }

    /**
     * hibernate查找,组合查询条件
     * @param caseHandle
     * @param masterUnitId
     * @param acceptDateStart
     * @param acceptDateEnd
     * @param closeDateStart
     * @param closeDateEnd
     * @return
     */
    private List<CaseRecord> findCaseRecordExport(final String caseHandle,final String masterUnitId,final String acceptDateStart,final String acceptDateEnd,final String closeDateStart,final String closeDateEnd) throws ParseException {
        StringBuffer querySb = new StringBuffer("select c from CaseRecord c where 1=1 ");
        if (acceptDateStart!=null&&!acceptDateStart.equals("")){
            querySb.append("and c.acceptDate >= :acceptDateStart ");
        }
        if (acceptDateEnd!=null&&!acceptDateEnd.equals("")){
            querySb.append("and c.acceptDate <= :acceptDateEnd ");
        }
        if (closeDateStart!=null&&!closeDateStart.equals("")){
            querySb.append("and c.closeDate >= :closeDateStart ");
        }
        if (closeDateEnd!=null&&!closeDateEnd.equals("")){
            querySb.append("and c.closeDate <= :closeDateEnd ");
        }
        if (caseHandle!=null&&!caseHandle.equals("")){
            querySb.append("and c.caseHandle = :caseHandle ");
        }
        if (masterUnitId!=null&&!masterUnitId.equals("")){
            querySb.append("and c.masterUnit = :masterUnit ");
        }
        TypedQuery<CaseRecord> query = em.createQuery(querySb.toString(),CaseRecord.class);
        if (caseHandle!=null&&!caseHandle.equals("")){
            CaseHandle caseHandle1Enum = CaseHandle.getMatchByKey(caseHandle);
            query.setParameter("caseHandle",caseHandle1Enum);
        }
        if (masterUnitId!=null&&!masterUnitId.equals("")){
            BdmPoliceStation masterUnit = bdmPoliceStationDao.findOne(Integer.valueOf(masterUnitId));
            query.setParameter("masterUnit",masterUnit);
        }
        if (acceptDateStart!=null&&!acceptDateStart.equals("")){
            Date acceptStartD = DateHelper.formatStringToDate(acceptDateStart,"yyyy-MM-dd");
            query.setParameter("acceptDateStart",acceptStartD);
        }
        if (acceptDateEnd!=null&&!acceptDateEnd.equals("")){
            Date acceptEndD = DateHelper.formatStringToDate(acceptDateEnd,"yyyy-MM-dd");
            query.setParameter("acceptDateEnd",acceptEndD);
        }
        if (closeDateEnd!=null&&!closeDateEnd.equals("")){
            Date closeEndD = DateHelper.formatStringToDate(closeDateEnd,"yyyy-MM-dd");
            query.setParameter("closeDateEnd",closeEndD);
        }
        if (closeDateStart!=null&&!closeDateStart.equals("")){
            Date closeStartD = DateHelper.formatStringToDate(closeDateStart,"yyyy-MM-dd");
            query.setParameter("closeDateStart",closeStartD);
        }
        return query.getResultList();
    }
}
