package com.yy.server.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.yy.server.mapper.AppImageMapper;
import com.yy.server.mapper.AppProductMapper;
import com.yy.server.model.AppImage;
import com.yy.server.model.AppProduct;
import com.yy.server.model.view.AppProductVO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class AppProductService {

    @Resource
    private AppProductMapper appProductMapper;

    @Resource
    private AppImageMapper appImageMapper;

    public HashMap<String, Object> getProductList(AppProductVO vo) {
        if (vo.getPage() != null && vo.getRows() != null) {
            PageHelper.startPage(vo.getPage(), vo.getRows());
        }
        List<AppProductVO> products = appProductMapper.getProductList(vo);
        PageInfo pageinfo = new PageInfo(products);
        HashMap<String, Object> map = new HashMap<>();
        map.put("pageCount", pageinfo.getTotal());
        map.put("pageList", products);
        return map;
    }

    public boolean addAppProduct(AppProduct appProduct) {
        return appProductMapper.insertSelective(appProduct) > 0;
    }


    public int addProductVO(AppProductVO appProductVO) {
        AppProduct appProduct = new AppProduct();
        appProduct.setName(appProductVO.getName());
        appProduct.setProductTypeId(appProductVO.getProductTypeId());
        appProduct.setRemark(appProductVO.getRemark());
        appProduct.setPrice(appProductVO.getPrice());
        appProduct.setOrderId(appProductVO.getOrderId());
        appProduct.setAppId(appProductVO.getAppId());
        appProduct.setImgUrl(appProductVO.getImgUrl());
        appProduct.setState(true);
        appProductMapper.insertSelective(appProduct);

        int fromId = appProduct.getId();
        List<AppImage> imageList = appProductVO.getImageList();
        List<AppImage> insertImageList = Lists.newArrayList();
        if (imageList != null) {
            for (AppImage appImageTemp : imageList) {
                AppImage appImage = new AppImage();
                appImage.setFromId(fromId);
                appImage.setAppId(appProductVO.getAppId());
                appImage.setImgPath(appImageTemp.getImgPath());
                appImage.setOrderId(appImageTemp.getOrderId());
                appImage.setDelFlag("N");
                appImage.setType("1");
                appImage.setUploadTime(new Date());
                appImage.setCreateTime(new Date());
                appImage.setUpdateTime(new Date());
                insertImageList.add(appImage);
            }
            appImageMapper.insertList(insertImageList);
        }
        return fromId;
    }

    public int updateProductVO(AppProductVO appProductVO) {
        Date nowDate = new Date();

        AppProduct appProduct = new AppProduct();
        appProduct.setId(appProductVO.getId());
        appProduct.setName(appProductVO.getName());
        appProduct.setProductTypeId(appProductVO.getProductTypeId());
        appProduct.setRemark(appProductVO.getRemark());
        appProduct.setOrderId(appProductVO.getOrderId());
        appProduct.setAppId(appProductVO.getAppId());
        appProduct.setImgUrl(appProductVO.getImgUrl());
        appProduct.setPrice(appProductVO.getPrice());
        appProduct.setUpdateTime(nowDate);
        appProduct.setState(true);
        List<AppImage> imageList = appProductVO.getImageList();
        List<AppImage> insertImageList = Lists.newArrayList();
        for (AppImage appImageTemp : imageList) {
            AppImage appImage = new AppImage();
            appImage.setFromId(appProductVO.getId());
            appImage.setAppId(appProductVO.getAppId());
            appImage.setImgPath(appImageTemp.getImgPath());
            appImage.setOrderId(appImageTemp.getOrderId());
            appImage.setDelFlag("N");
            appImage.setType("1");
            appImage.setUploadTime(nowDate);
            appImage.setCreateTime(nowDate);
            appImage.setUpdateTime(nowDate);
            insertImageList.add(appImage);
        }
        //图片先删除，再增加
        appImageMapper.updateDelImagesByFromId(appProductVO.getId());
        appImageMapper.insertList(insertImageList);
        int success = appProductMapper.updateByPrimaryKey(appProduct);
        return success;
    }


    public int deleteProductVO(AppProductVO appProductVO) {
        Date nowDate = new Date();
        if (appProductVO != null && appProductVO.getId() > 0) {
            AppProduct delEntity = new AppProduct();
            delEntity.setId(appProductVO.getId());
            delEntity.setState(false);
            delEntity.setUpdateTime(nowDate);
            //图片先删除，再增加
            appImageMapper.updateDelImagesByFromId(appProductVO.getId());
            return appProductMapper.updateByPrimaryKeySelective(delEntity);
        }
        return 0;
    }

    public boolean updateAppProduct(AppProduct appProduct) {
        return appProductMapper.updateByPrimaryKey(appProduct) > 0;
    }

    public AppProduct queryOneAppProduct(AppProduct appProduct) {
        return appProductMapper.selectOne(appProduct);
    }

    public AppProductVO queryOneAppProductVO(AppProduct appProduct) {
        AppProductVO appProductVO = new AppProductVO();

        AppProduct appProductQueryVo = appProductMapper.selectOne(appProduct);
        if (appProductQueryVo != null) {
            appProductVO.setId(appProductQueryVo.getId());
            appProductVO.setName(appProductQueryVo.getName());
            appProductVO.setPrice(appProductQueryVo.getPrice());
            appProductVO.setProductTypeId(appProductQueryVo.getProductTypeId());
            appProductVO.setOrderId(appProductQueryVo.getOrderId());
            appProductVO.setImgUrl(appProductQueryVo.getImgUrl());
            AppImage appImage = new AppImage();
            appImage.setFromId(appProductQueryVo.getId());
            List<AppImage> imageList = appImageMapper.getAppImages(appImage);
            appProductVO.setImageList(imageList);
        }
        return appProductVO;
    }


    public Map queryOneAppProductMap(AppProduct appProduct) {
        Map map = new HashMap();
        AppProduct appProductQueryVo = appProductMapper.selectOne(appProduct);
        if (appProductQueryVo != null) {
            map.put("id", appProductQueryVo.getId());
            map.put("name", appProductQueryVo.getName());
            map.put("imgUrl", appProductQueryVo.getImgUrl());
            map.put("price", appProductQueryVo.getPrice());
            map.put("remark", appProductQueryVo.getRemark());
            map.put("productTypeId", appProductQueryVo.getProductTypeId());
            map.put("orderId", appProductQueryVo.getOrderId());

            AppImage appImage = new AppImage();
            appImage.setFromId(appProductQueryVo.getId());
            List<AppImage> imageList = appImageMapper.getAppImages(appImage);
            List<Map> images = Lists.newArrayList();
            for (AppImage appImageTemp : imageList) {
                Map imageMap = new HashMap();
                imageMap.put("id", appImageTemp.getId());
                imageMap.put("imgPath", appImageTemp.getImgPath());
                imageMap.put("orderId", appImageTemp.getOrderId());
                images.add(imageMap);
            }
            map.put("imageList", images);
        }
        return map;
    }

    public int getAppProductCount(AppProduct appProduct) {
        return appProductMapper.selectCount(appProduct);
    }

    public List<AppProduct> getAppProductList(AppProduct appProduct) {
        return appProductMapper.getProducts(appProduct);
    }


}
