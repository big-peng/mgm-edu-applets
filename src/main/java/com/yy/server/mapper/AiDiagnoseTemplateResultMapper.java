package com.yy.server.mapper;

import com.yy.server.model.AiDiagnoseTemplateResult;
import com.yy.server.model.view.AiDiagnoseTemplateResultVO;
import com.yy.server.model.view.AiProductResultVO;
import com.yy.server.util.MyMapper;

import java.util.List;

public interface AiDiagnoseTemplateResultMapper extends MyMapper<AiDiagnoseTemplateResult> {

    List<AiDiagnoseTemplateResult> getAiDiagnoseTemplateResults(AiDiagnoseTemplateResult entity);

    List<AiDiagnoseTemplateResultVO> getAiDiagnoseTemplateResultList(AiDiagnoseTemplateResultVO vo);

    List<AiDiagnoseTemplateResultVO> getAiDiagnoseTemplateResultsById(int id);

    List<AiProductResultVO> getProductsByAIResultId(int resultId);

}