package com.ali.bean.subjectanalysis.table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Table {
    private Columns columns;
    private List<Map> datas;
}
