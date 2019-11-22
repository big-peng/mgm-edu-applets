package com.yy.server.controller.back;


import com.yy.server.common.ErrorEnum;
import com.yy.server.model.Admin;
import com.yy.server.model.AppShop;
import com.yy.server.model.view.AppShopVO;
import com.yy.server.service.AppShopService;
import com.yy.server.util.JsonUtil;
import com.yy.server.util.UuidUtil;
import org.apache.shiro.SecurityUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;
import java.util.Map;

@RestController
@RequestMapping(value = "/console/shop")
public class AppShopController extends BaseController {

    @Resource
    private AppShopService shopService;


    /**
     * 店铺列表
     *
     * @param vo
     * @return
     */
    @RequestMapping("list.json")
    public Map list(@RequestBody AppShopVO vo, @RequestHeader HttpHeaders httpHeaders) {
        vo.setState(true);
        return JsonUtil.toJsonSuccess("获取列表成功", shopService.queryAppShopByPage(vo));
    }


    /**
     * 新增店铺信息
     *
     * @param shop
     * @return
     */
    @RequestMapping("add.json")
    public Map add(@RequestBody AppShop shop) {
        Boolean isNoHave = shopService.checkShopName(shop);
        if (!isNoHave) {
            return JsonUtil.toJsonSuccess("店铺名称不能重复", false);
        }
        Date date = new Date();
        Admin admin = (Admin) SecurityUtils.getSubject().getPrincipal();
        shop.setCreatedUser(admin.getUsername());
        shop.setUpdatedUser(admin.getUsername());
        shop.setCreatedTime(date);
        shop.setUpdateTime(date);
        shop.setZid(UuidUtil.getUUID()); //店铺唯一标识
        shop.setState(Boolean.TRUE);
        int m = shopService.insertAppShop(shop);
        if (m > 0) {
            return JsonUtil.toJsonSuccess("新增数据成功", m);
        } else {
            return JsonUtil.toJsonError(ErrorEnum.ERROR_36003);
        }

    }


    /**
     * 更新店铺信息
     *
     * @param shop
     * @return
     */
    @RequestMapping("update.json")
    public Map update(@RequestBody AppShop shop) {
        if (shop == null || shop.getId() == null || shop.getId() == 0) {
            return JsonUtil.toJsonError(ErrorEnum.ERROR_26001);
        }
        Admin admin = (Admin) SecurityUtils.getSubject().getPrincipal();
        shop.setUpdatedUser(admin.getUsername());
        AppShop shop1 = shopService.getAppShopById(shop.getId());
        if (shop1 != null && !shop.getShopName().equals(shop1.getShopName())) {
            Boolean m = shopService.checkShopName(shop);
            if (!m) {
                return JsonUtil.toJsonSuccess("店铺名称不能重复", false);
            }
        }
        int m = shopService.updateAppShop(shop);
        if (m > 0) {
            return JsonUtil.toJsonSuccess("更新数据成功", m);
        } else {
            return JsonUtil.toJsonError(ErrorEnum.ERROR_36003);
        }

    }


    /**
     * 获得店铺信息
     *
     * @param shop
     * @return
     */
    @RequestMapping("detail.json")
    public Map detail(@RequestBody AppShop shop) {
        return JsonUtil.toJsonSuccess("获得信息成功", shopService.getOneAppShop(shop));
    }


    /**
     * 删除店铺信息
     *
     * @param vo
     * @return
     */
    @RequestMapping("del.json")
    public Map delete(@RequestBody AppShop vo) {
        if (vo == null || vo.getId() == null || vo.getId() == 0) {
            return JsonUtil.toJsonError(ErrorEnum.ERROR_26001);
        }
        int m = shopService.updateStateAppShop(vo);
        if (m > 0) {
            return JsonUtil.toJsonSuccess("删除店铺信息成功", m);
        } else {
            return JsonUtil.toJsonError(ErrorEnum.ERROR_36003);
        }
    }

    @RequestMapping("checkShopName")
    public Map check(@RequestBody AppShop shop) {
        Boolean m = shopService.checkShopName(shop);
        if (m) {
            return JsonUtil.toJsonSuccess("店铺名称可用", true);
        } else {
            return JsonUtil.toJsonSuccess("店铺名不能重复", false);
        }
    }

    /**
     * 店铺下拉框
     *
     * @return
     */
    @RequestMapping("getList.json")
    public Map getCompanyList() {
        return JsonUtil.toJsonSuccess("列表", shopService.queryShop());
    }

    public AppShop checkShopZid(String zid) {
        return shopService.checkShopZid(zid);
    }

}
