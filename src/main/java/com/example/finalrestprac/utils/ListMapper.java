package com.example.finalrestprac.utils;

import com.example.finalrestprac.dtos.pagedtos.PageDto;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;

public class ListMapper<O,T> {

    private static final ListMapper LIST_MAPPER = new ListMapper();

    private ListMapper(){}

    public static ListMapper getInstance(){
        return LIST_MAPPER;
    }

    public List<T> mapList(List<O> list, Class<T> TargetClass, ModelMapper modelMapper){
        return list.stream().map((item) -> modelMapper.map(item, TargetClass)).collect(Collectors.toList());
    }

    public PageDto<T> mapPage(Page<O> page, Class<T> TargetClass, ModelMapper modelMapper){
        List<T> transformedList = this.mapList(page.getContent(), TargetClass, modelMapper);
        PageDto pageDto = modelMapper.map(page, PageDto.class);
        pageDto.setContent(transformedList);
        return pageDto;
    }

}
