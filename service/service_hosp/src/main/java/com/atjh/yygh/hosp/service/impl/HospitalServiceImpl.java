package com.atjh.yygh.hosp.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.atjh.yygh.hosp.repository.HospitalRepository;
import com.atjh.yygh.hosp.service.HospitalService;
import com.atjh.yygh.model.hosp.Hospital;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;

@Service
public class HospitalServiceImpl implements HospitalService {


    @Autowired
    private HospitalRepository hospitalRepository;

    // 上传医院的接口
    @Override
    public void save(Map<String, Object> paramMap) {
        // 把参数map集合转换为对象Hospital
        String mapString = JSONObject.toJSONString(paramMap);
        Hospital hospital = JSONObject.parseObject(mapString, Hospital.class);

        // 判断是否存在数据
        String hoscode = hospital.getHoscode();
        Hospital hospitalExit = hospitalRepository.getHospitalByHoscode(hoscode);

        // 如果存在，进行修改
        if (hospitalExit!=null) {
            hospital.setStatus(hospitalExit.getStatus());
            hospital.setCreateTime(hospitalExit.getCreateTime());
            hospital.setUpdateTime(new Date());
            hospital.setIsDeleted(0);
            hospitalRepository.save(hospital);
        } else { // 如果不存在，进行添加
                //0：未上线 1：已上线
            hospital.setStatus(0);
            hospital.setCreateTime(new Date());
            hospital.setUpdateTime(new Date());
            hospital.setIsDeleted(0);
            hospitalRepository.save(hospital);
        }
    }
}