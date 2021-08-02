package com.atjh.yygh.hosp.service.impl;


import com.atjh.yygh.model.hosp.HospitalSet;
import com.atjh.yygh.hosp.service.HospitalSetService;
import com.atjh.yygh.hosp.mapper.HospitalSetMapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class HospitalSetServiceImpl extends ServiceImpl<HospitalSetMapper, HospitalSet> implements HospitalSetService {



    @Override
    public String setSignKey(String hoscode) {
        QueryWrapper<HospitalSet> wrapper = new QueryWrapper<>();
        wrapper.eq("hoscode",hoscode);
        HospitalSet hospitalSet = baseMapper.selectOne(wrapper);
        return hospitalSet.getSignKey();
    }
}
