package com.jenkin.excel.test;

import com.jenkin.excel.ExcelUtils;
import com.sun.xml.internal.bind.v2.runtime.reflect.opt.Const;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class test {

    public static void main(String[] args) throws IOException, NoSuchMethodException, InvalidFormatException, InstantiationException, IllegalAccessException, InvocationTargetException {

        SlrEmpSalary s = new SlrEmpSalary(1,"100","tom");
        List<SlrEmpSalary> list = new ArrayList();
        list.add(s);

        testExport(list);
//        testImport();
    }

    /**
     * 测试导入
     */
    public static void testImport() throws IOException, InvocationTargetException, NoSuchMethodException, InvalidFormatException, InstantiationException, IllegalAccessException {
        FileInputStream inputStream =new FileInputStream(new File("D:\\test.xls"));
////文件输入流
//        inputStream =uploadedFile.getInputStream();
        //获取对象集合
        List< SlrEmpSalary> empSalaryList =
                (List< SlrEmpSalary>)ExcelUtils.parseExcelToList(inputStream,  SlrEmpSalary.class);

        System.out.println(empSalaryList);

    }

    /**
     * 测试导出
     */
    public static void testExport(List<SlrEmpSalary> list) throws FileNotFoundException {
        //无下拉列表
//        FileOutputStream outputStream = new FileOutputStream(new File("D:\\test.xls"));
//        ExcelUtils.exportExcel(outputStream, list,  SlrEmpSalary.class, null, null);

        //有下拉列表
        Map<Integer, Map<String,String>> select = new HashMap<Integer, Map<String, String>>();
        Map map = new HashMap();
        map.put("red","红色");
        map.put("yellow","黄色");
        select.put(1,map);
        Map map1 = new HashMap();
        map1.put("black","黑色");
        map1.put("blue","蓝色");
        select.put(2,map1);
        FileOutputStream outputStream = new FileOutputStream(new File("D:\\test.xls"));
        ExcelUtils.exportExcel(outputStream, list,  SlrEmpSalary.class, select, null);

    }
}
