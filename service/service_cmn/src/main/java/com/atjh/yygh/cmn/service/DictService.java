package com.atjh.yygh.cmn.service;

import com.atjh.yygh.hosp.model.cmn.Dict;
import com.atjh.yygh.hosp.model.hosp.HospitalSet;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface DictService extends IService<Dict> {

    //根据数据id查询子数据
    List<Dict> findChildData(Long id);
}
