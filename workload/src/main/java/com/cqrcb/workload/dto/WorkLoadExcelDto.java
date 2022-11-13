package com.cqrcb.workload.dto;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WorkLoadExcelDto {

    @ExcelProperty(value = "序号",index = 0)
    private String index;
    @ExcelProperty(value = "工作条款内容",index = 1)
    private String content;
    @ExcelProperty(value = "初评工时",index = 2)
    private String initWorkLoad;
    @ExcelProperty(value = "认定工时",index = 3)
    private String finalWorkLoad;
}
