package com.yyyf.workload.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReadWorkLoadAllContent {
    private String requireName;
    private List<WorkLoadExcelDto> workLoadExcelDtoList;
}
