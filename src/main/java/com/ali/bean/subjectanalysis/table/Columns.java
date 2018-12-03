package com.ali.bean.subjectanalysis.table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Columns {
    private List<Column> columns;

    public static void main(String[] args) {
        Columns columns = new Columns();
        List<Column> columnList = new ArrayList<>();
        //---------------
        Column column1 = new Column();
        column1.setText("指标维度");
        column1.setValue("event");
        columnList.add(column1);
        //---------------
        Column column2 = new Column();
        column2.setText("总量");
        List<Child> children = new ArrayList<>();

        Child child1 = new Child();
        child1.setText("年份");
        child1.setValue("zlyear");
        children.add(child1);

        Child child2 = new Child();
        child2.setText("数据");
        child2.setValue("zldata");
        children.add(child2);

        column2.setChildren(children);

        columnList.add(column2);
        //---------------

        columns.setColumns(columnList);

        System.out.println(columns.toString());

    }
}
