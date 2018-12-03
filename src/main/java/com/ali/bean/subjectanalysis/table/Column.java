package com.ali.bean.subjectanalysis.table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Column {
    private String text;
    private String value;
    private List<Child> children;
}
