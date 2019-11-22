package com.yy.server.mapper;

import com.yy.server.model.AiDiagnoseCustResult;
import com.yy.server.util.MyMapper;

import java.util.List;

public interface AiDiagnoseCustResultMapper extends MyMapper<AiDiagnoseCustResult> {

    List<AiDiagnoseCustResult> getAiDiagnoseCustList(AiDiagnoseCustResult vo);

}