package com.yyyf.workload.util;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.SyncReadListener;
import com.yyyf.workload.dto.ReadWorkLoadAllContent;
import com.yyyf.workload.dto.WorkLoadExcelDto;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
@Slf4j
public class EasyExcelUtil {

    private static ReadWorkLoadAllContent readExcelByFileName(String fileName) {
        final List list = new ArrayList();
        //使用EasyExcel读取test1.xlsx文件
        EasyExcel.read("C:\\Users\\cumtliuna\\Desktop\\临时\\【信贷投资与管理系统】信贷全流程系统_渝悦贷主动授信2.0_工作量评估-开发版.xlsx", WorkLoadExcelDto.class, new SyncReadListener() {
            //EasyExcel在读取excel表格时，每读取到一行，就会调用一次这个方法，
            //并且将读取到的行数据，封装到指定类型（User）的对象中，传递给我们（Object object）
            /*
            此问题可能出现在低版本的easyExcel中，出现时可以按照下列方式解决
                如果表格数据不是顶行写的，需要通过headRowNumber指定表头行的数量
                如果表格数据不是顶列写的，需要在封装的实体属性上通过@ExcelProperty将实体属性和表格列名进行对应
             */
            @Override
            public void invoke(Object object, AnalysisContext context) {
                //System.out.println(object);
                list.add(object);
            }
        }).sheet(0).doRead();

        //获取读取到的数据
        ReadWorkLoadAllContent readWorkLoadAllContent = new ReadWorkLoadAllContent();
        List<WorkLoadExcelDto> workLoadExcelDtoList = new ArrayList<>();
        readWorkLoadAllContent.setWorkLoadExcelDtoList(workLoadExcelDtoList);
        int i=0;
        for (Object object : list) {
            i++;
            WorkLoadExcelDto workLoadExcelDto = (WorkLoadExcelDto) object;
            if (i == 2) {
                log.info("需求名称：{}",workLoadExcelDto.getContent());
                readWorkLoadAllContent.setRequireName(workLoadExcelDto.getContent());
            }
            if (i > 13) {
                if (workLoadExcelDto.getInitWorkLoad() != null) {
                    workLoadExcelDto.setInitWorkLoad(String.valueOf(new BigDecimal(workLoadExcelDto.getInitWorkLoad()).setScale(2,BigDecimal.ROUND_HALF_UP)));
                }
                if (workLoadExcelDto.getFinalWorkLoad() != null) {
                    workLoadExcelDto.setFinalWorkLoad(String.valueOf(new BigDecimal(workLoadExcelDto.getFinalWorkLoad()).setScale(2,BigDecimal.ROUND_HALF_UP)));
                }
                if (Objects.equals("合计（人月）",workLoadExcelDto.getIndex())) {
                    workLoadExcelDto.setIndex("8888");
                    workLoadExcelDtoList.add(workLoadExcelDto);
                    log.info("excel读取内容:{}",workLoadExcelDto);
                    log.info("读取完毕……");
                    break;
                } else {
                    log.info("excel读取内容:{}",workLoadExcelDto);
                    workLoadExcelDtoList.add(workLoadExcelDto);
                }
            }
        }
        return readWorkLoadAllContent;
    }
}
