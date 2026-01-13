package com.example.tob.mapper.config;


import java.util.List;

public interface BaseMapper<E, D> {

    /**
     * Convert Entity to Dto
     *
     * @param entity
     * @return dto need mapper
     */
    D toDto(E entity);

    /**
     * Convert Dto to Entity
     *
     * @param dto
     * @return entity need mapper
     */
    E toEntity(D dto);

    /**
     * Convert list of Entity to list of Dto
     *
     * @param entityList
     * @return list of dto need mapper
     */
    List<D> toDtoList(List<E> entityList);

    /**
     * Convert list of Dto to list of Entity
     *
     * @param dtoList
     * @return list of entity need mapper
     */
    List<E> toEntityList(List<D> dtoList);

}
