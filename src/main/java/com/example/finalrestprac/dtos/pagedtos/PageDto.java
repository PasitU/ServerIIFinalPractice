package com.example.finalrestprac.dtos.pagedtos;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PageDto<O> {
    private List<O> Content;
    private Boolean last;
    private Integer totalElements;
    private Integer totalPages;
    private Integer size;
    private Integer number;
}
