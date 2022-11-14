package com.yyyf.workload.dto;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WorkLoadExcelDto {

    @ExcelProperty(value = "���",index = 0)
    private String index;
    @ExcelProperty(value = "������������",index = 1)
    private String content;
    @ExcelProperty(value = "������ʱ",index = 2)
    private String initWorkLoad;
    @ExcelProperty(value = "�϶���ʱ",index = 3)
    private String finalWorkLoad;
}
