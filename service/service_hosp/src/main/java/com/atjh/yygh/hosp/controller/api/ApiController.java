package com.atjh.yygh.hosp.controller.api;

import com.atjh.yygh.common.exception.YyghException;
import com.atjh.yygh.common.helper.HttpRequestHelper;
import com.atjh.yygh.common.resulet.Result;
import com.atjh.yygh.common.resulet.ResultCodeEnum;
import com.atjh.yygh.common.util.MD5;
import com.atjh.yygh.hosp.service.HospitalService;
import com.atjh.yygh.hosp.service.HospitalSetService;
import com.atjh.yygh.model.hosp.Hospital;
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

    @Autowired
    private HospitalSetService hospitalSetService;

    // 查询医院
    @PostMapping("/hospital/show")
    public Result getHospital(HttpServletRequest request){
        // 获取传递过来的医院信息
        Map<String, String[]> requestMap = request.getParameterMap();
        Map<String, Object> paramMap = HttpRequestHelper.switchMap(requestMap);

        //1 获取医院系统传递过来的签名，签名进行MD5加密
        String hospSign = (String) paramMap.get("sign");

        //2 根据传递过来的医院编码查询数据库，查询签名
        String hoscode = (String) paramMap.get("hoscode"); // 获取医院编号
        String signKey = hospitalSetService.setSignKey(hoscode);

        //3 把数据库查询签名进行MD5加密
//        String signKeyMd5 = MD5.encrypt(signKey);
        String signKeyMd5 = MD5.encrypt(signKey);
        //4 判断签名是否一致
        if (!hospSign.equals(signKeyMd5)) {
            throw new YyghException(ResultCodeEnum.SIGN_ERROR);
        }

        // 调用service方法实现根据医院编号查询
        Hospital hospital = hospitalService.getByHoscode(hoscode);
        return Result.ok(hospital);


    }

    // 上传医院的接口
    @PostMapping("saveHospital")
    public Result saveHosp(HttpServletRequest request) {
        // 获取传递过来的医院信息
        Map<String, String[]> requestMap = request.getParameterMap();
        Map<String, Object> paramMap = HttpRequestHelper.switchMap(requestMap);

               //1 获取医院系统传递过来的签名，签名进行MD5加密
        String hospSign = (String) paramMap.get("sign");

        //2 根据传递过来的医院编码查询数据库，查询签名
        String hoscode = (String) paramMap.get("hoscode");
        String signKey = hospitalSetService.setSignKey(hoscode);

        //3 把数据库查询签名进行MD5加密
//        String signKeyMd5 = MD5.encrypt(signKey);
        String signKeyMd5 = MD5.encrypt(signKey);
        //4 判断签名是否一致
        if (!hospSign.equals(signKeyMd5)) {
            throw new YyghException(ResultCodeEnum.SIGN_ERROR);
        }


        // 传输过程中“+”转换为了" ",因此需要转换回来
        String logoData = (String) paramMap.get("logoData");
        logoData = logoData.replace(" ", "+");
        paramMap.put("logoData",logoData);

        // 调用service方法
        hospitalService.save(paramMap);
        return Result.ok();

    }
}
