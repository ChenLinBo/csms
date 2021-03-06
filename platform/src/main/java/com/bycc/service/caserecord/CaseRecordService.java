/**
 * 
 */
package com.bycc.service.caserecord;

import java.text.ParseException;
import java.util.List;

import com.bycc.dto.CaseFindParamDto;
import com.bycc.entity.CaseRecord;
import com.bycc.enumitem.CaseHandle;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.smartframework.common.kendo.QueryBean;

import com.bycc.dto.CaseRecordDto;
import org.smartframework.platform.exception.BusinessException;

/**
 * @description
 * @author gaoningbo
 * @date 2017年4月14日
 * 
 */
public interface CaseRecordService {
	/**
	 * 保存案件记录
	 */
	CaseRecordDto saveCaseRecord(CaseRecordDto dto) throws Exception;
	/**
	 * 删除案件记录
	 */
	void deleteCaseRecordById(String ids);
	/**
	 * 根据ID查找案件记录
	 */
	CaseRecordDto findById(Integer id);
	/**
	 * 根据查询条件
	 */
	List<CaseRecordDto> query(QueryBean qb);

    /**
     * 开始办案
     */
	CaseRecordDto startCase(Integer caseId, Integer caseRecordId) throws Exception;
    /**
     * 结束办案
     */
	CaseRecordDto finishCase(Integer caseId, Integer caseRecordId) throws Exception;

	/**
	 * 多条件复合查询
	 * @return
     */
	List<CaseRecordDto> search(QueryBean queryBean,CaseFindParamDto caseFindParamDto) throws ParseException;

	/**
	 * 导出Excel文件
	 * @param queryBean
	 * @param caseFindParamDto
	 * @return
	 * @throws ParseException
     */
	HSSFWorkbook getExcel(QueryBean queryBean,CaseFindParamDto caseFindParamDto) throws ParseException;
}
