package com.atjh.yygh.cmn.controller;

import com.atjh.yygh.cmn.service.DictService;
import com.atjh.yygh.hosp.common.resulet.Result;
import com.atjh.yygh.hosp.model.cmn.Dict;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(value = "数据字典")
@RestController
@RequestMapping("/admin/cmn/dict")
public class DictController {

    @Autowired
    private DictService dictService;

    //根据数据id查询子数据
    @ApiOperation(value = "根据数据id查询子数据")
    @GetMapping("findChildData/{id}")
    public Result findChildData(@PathVariable Long id){

       List<Dict> list = dictService.findChildData(id);
       return Result.ok(list);
    }

}
