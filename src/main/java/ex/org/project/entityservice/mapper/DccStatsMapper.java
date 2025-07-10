package ex.org.project.entityservice.mapper;

import ex.org.project.entityservice.model.DTO.DccDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Mapper(componentModel = "spring")
public interface DccStatsMapper {
    DccStatsMapper INSTANCE = Mappers.getMapper(DccStatsMapper.class);

    @Mapping(target = "name", source = "name", qualifiedByName = "stringMapper")
    @Mapping(target = "studyCount", source = "study_count", qualifiedByName = "longMapper")
    @Mapping(target = "totalFileSize", source = "total_file_size", qualifiedByName = "bigDecimalMapper")
    @Mapping(target = "dataFileCount", source = "data_file_count", qualifiedByName = "bigDecimalMapper")
    @Mapping(target = "documentCount", source = "document_count", qualifiedByName = "bigDecimalMapper")
    DccDTO mapToDccDto(Map<String, Object> map);

    List<DccDTO> mapToDccDtoList(List<Map<String, Object>> maps);

    @Named("stringMapper")
    public static String mapString(Object value){
        return (String) value;
    }

    @Named("longMapper")
    public static Long mapLong(Object value){
        return (Long) value;
    }

    @Named("bigDecimalMapper")
    public static BigDecimal mapBigDecimal(Object value){
        return (BigDecimal) value;
    }
}