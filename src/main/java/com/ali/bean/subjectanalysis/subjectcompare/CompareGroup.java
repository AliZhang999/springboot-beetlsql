package com.ali.bean.subjectanalysis.subjectcompare;

import com.ali.bean.subjectanalysis.table.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompareGroup {
    private String leftName;
    private VSData vsData;
    private String rightName;
    private IncrementData incrementData;
    private Map<String,Object> oparam;
    private Table table;
}
