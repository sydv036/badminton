package com.example.tob.mapper.moduleMember;

import com.example.tob.dtos.requests.RegisterRequestDto;
import com.example.tob.entity.Member;
import com.example.tob.mapper.config.BaseMapper;
import com.example.tob.mapper.config.GlobalMapperConfig;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = GlobalMapperConfig.class)
public interface MemberAuthMapper extends BaseMapper<Member, RegisterRequestDto> {

    @Override
    @Mapping(target = "userName", source = "email")
    Member toEntity(RegisterRequestDto registerRequestDto);

}
