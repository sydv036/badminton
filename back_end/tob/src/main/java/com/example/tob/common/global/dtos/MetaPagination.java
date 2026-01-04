package com.example.tob.common.global.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MetaPagination {
    private long total;
    private int currentPage;
    private int pageSize;
    private int pages;
}
