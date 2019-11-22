package com.yy.server.mapper;


import com.yy.server.model.AiDiagnoseTemplateResultProductRel;
import com.yy.server.util.MyMapper;
import org.apache.ibatis.annotations.Delete;

public interface AiDiagnoseTemplateResultProductRelMapper extends MyMapper<AiDiagnoseTemplateResultProductRel> {
    @Delete("DELETE FROM ai_diagnose_template_result_product_rel WHERE result_id = #{resultId}")
    void updateDelProductRelsByResultId(int resultId);
}