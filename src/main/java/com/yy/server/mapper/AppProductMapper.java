package com.yy.server.mapper;

import com.yy.server.model.AppProduct;
import com.yy.server.model.view.AppProductVO;
import com.yy.server.util.MyMapper;

import java.util.List;

public interface AppProductMapper extends MyMapper<AppProduct> {
    List<AppProduct> getProducts(AppProduct appProduct);

    List<AppProductVO> getProductList(AppProductVO vo);

    List<AppProductVO> getProductsByAIResultId(int resultId);

}