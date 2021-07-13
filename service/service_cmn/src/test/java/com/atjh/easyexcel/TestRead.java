package com.atjh.easyexcel;

import com.alibaba.excel.EasyExcel;

public class TestRead {
    public static void main(String[] args) {
        //读取文件路径
        String fileName = "D:\\ProgramFiles\\excel\\01.xlsx";
        //调用方法进行读取
        EasyExcel.read(fileName,UserData.class,new ExcelListener()).sheet().doRead();
    }
}
