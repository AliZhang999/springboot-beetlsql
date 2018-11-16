/**
 * 
 */
package com.ali.controller;

import com.ali.entity.DynamicAnalysisOptionModel;
import com.ali.entity.IndicationResult;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * 动态分析
 * 
 * @author qqwer
 *
 */
public interface DynamicAnalysis {

	/**
	 * 页面打开时，所有选项的数据，返回给前端，供前端填充下拉框的数据
	 * @return
	 */
	@GetMapping("option")
    ResponseEntity<DynamicAnalysisOptionModel> option();// 返回分析选项

	/**
	 * model:前端包装好的选择后的数据，供后端根据这些数据，查询对应的数据再给前端
	 * @param model
	 * @return
	 */
	@PostMapping("analysis")
    ResponseEntity<List<IndicationResult>> analysis(@RequestBody DynamicAnalysisOptionModel model);// 分析

}
