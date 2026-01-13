package com.example.tob.mapper.config;

import org.mapstruct.*;

@MapperConfig(
        componentModel = "spring", // Integration with Spring Framework
        unmappedTargetPolicy = ReportingPolicy.WARN, // Warning when compiler (Recommended)
        unmappedSourcePolicy = ReportingPolicy.IGNORE, // Ignore unmapped source properties
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE, // Keep the original value if the source property is null
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, // Always check for null values (DEFAULT on the property type )
        injectionStrategy = InjectionStrategy.CONSTRUCTOR, // Constructor injection for dependencies
        collectionMappingStrategy = CollectionMappingStrategy.TARGET_IMMUTABLE, // Collection mapping
        builder = @Builder(disableBuilder = true) // Disable builder pattern
)
public interface GlobalMapperConfig {
}
