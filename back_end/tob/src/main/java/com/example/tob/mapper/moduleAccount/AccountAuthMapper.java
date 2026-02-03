package com.example.tob.mapper.moduleAccount;

import com.example.tob.dtos.requests.RegisterRequestDto;
import com.example.tob.entity.Account;
import com.example.tob.mapper.config.BaseMapper;
import com.example.tob.mapper.config.GlobalMapperConfig;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = GlobalMapperConfig.class)
public interface AccountAuthMapper extends BaseMapper<Account, RegisterRequestDto> {

    @Override
    @Mapping(target = "locked", constant = "false")
    @Mapping(target = "actived", constant = "true")
    @Mapping(target = "userName", expression = "java(dto.getEmail())")
    Account toEntity(RegisterRequestDto dto);

}
