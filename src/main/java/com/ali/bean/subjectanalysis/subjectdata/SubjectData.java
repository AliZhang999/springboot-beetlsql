package com.ali.bean.subjectanalysis.subjectdata;

import com.ali.bean.subjectanalysis.table.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SubjectData {
    private String subjectName;
    private Echart echart;
    private Table table;
}
