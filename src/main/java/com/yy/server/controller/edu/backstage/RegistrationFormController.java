package com.yy.server.controller.edu.backstage;

import com.yy.server.common.ErrorEnum;
import com.yy.server.model.dto.CounselingRegistrationAddDTO;
import com.yy.server.model.dto.CounselingRegistrationListDTO;
import com.yy.server.service.RegistrationFormService;
import com.yy.server.util.JsonUtil;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.*;

/**
 * @author ChenXiangpeng
 * @ClassName RegistrationFormController
 * @date：2019/11/13
 * @version: V1.0.0
 * @description：心理咨询登记表Controller
 */
@RestController
@RequestMapping("/counseling/registration/form")
@Api(tags = "心理咨询登记")
@Slf4j
public class RegistrationFormController {
    @Resource
    private RegistrationFormService registrationFormService;

    @PostMapping(value = "/add")
    public Map add(@RequestBody @Valid CounselingRegistrationAddDTO params){
        try {
            registrationFormService.add(params);
            return JsonUtil.toJsonSuccess("成功");
        }catch (Exception e){
            return JsonUtil.toJsonError(ErrorEnum.ERROR_99999.getCode(),"登记失败");
        }
    }

    @PostMapping(value = "/list")
    public Map list(@RequestBody @Valid CounselingRegistrationListDTO params){
        Map<String,Object> map = registrationFormService.list(params);
        return JsonUtil.toJsonSuccess("成功",map);
    }
}
