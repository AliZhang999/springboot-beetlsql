package com.ali.bean.subjectanalysis.subjectcompare;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LeftItem {
    private List<Item> itemList;
}
