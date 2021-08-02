package com.atjh.yygh.hosp.controller.api;

import com.atjh.yygh.common.helper.HttpRequestHelper;
import com.atjh.yygh.common.resulet.Result;
import com.atjh.yygh.hosp.service.HospitalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
@RequestMapping("/api/hosp")
public class ApiController {

    @Autowired
    private HospitalService hospitalService;

    // 上传医院的接口
    @PostMapping("saveHospital")
    public Result saveHosp(HttpServletRequest request) {
        // 获取传递过来的医院信息
        Map<String, String[]> requestMap = request.getParameterMap();
        Map<String, Object> paramMap = HttpRequestHelper.switchMap(requestMap);
        // 调用service方法
        hospitalService.save(paramMap);
        return Result.ok();

    }
}