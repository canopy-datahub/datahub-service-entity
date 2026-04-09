package ex.org.project.entityservice.mapper;

import ex.org.project.entityservice.model.DTO.HomepageContentDataDTO;
import ex.org.project.entityservice.model.StudyView;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Mapper(componentModel = "spring")
public interface HomepageContentMapper {

    @Mapping(target = "files", source = "files", qualifiedByName = "longMapper")
    @Mapping(target = "studyId", source = "study_id", qualifiedByName = "integerMapper")
    @Mapping(target = "center", source = "center", qualifiedByName = "stringMapper")
    @Mapping(target = "studyName", source = "title", qualifiedByName = "stringMapper")
    @Mapping(target = "date", source = "date", qualifiedByName = "dateMapper")
    HomepageContentDataDTO mapToDataDto(Map<String, Object> map);


    @Mapping(target = "studyId", source = "studyView.id")
    @Mapping(target = "center", source = "studyView.center")
    @Mapping(target = "studyName", source = "studyView.name")
    @Mapping(target = "date", source = "studyView.releaseDate",qualifiedByName = "dateMapper")
    HomepageContentDataDTO mapToDataDto(StudyView studyView);

    List<HomepageContentDataDTO> mapToDataDTOs(List<StudyView> studyViews);

    List<HomepageContentDataDTO> mapToDataDtoList(List<Map<String, Object>> map);

    @Named("stringMapper")
    public static String mapString(Object value){
        return (String) value;
    }

    @Named("longMapper")
    public static Long mapLong(Object value){
        return (Long) value;
    }

    @Named("integerMapper")
    public static Integer mapInteger(Object value){
        return (Integer) value;
    }

    @Named("dateMapper")
    public static String mapDate(Object value) {
        if(value instanceof Timestamp){
            Timestamp date = (Timestamp) value;
            return new SimpleDateFormat("MMM dd, yyyy").format(date);
        }
        else{
            DateTimeFormatter dateFormatter
                    = DateTimeFormatter.ofPattern("MMM dd, yyyy");
            return Optional.ofNullable(value) // wrap the String into an Optional
                    .map(str -> LocalDate.parse((String)value, DateTimeFormatter.ofPattern("MM/dd/yyyy"))
                            .atStartOfDay().format(dateFormatter)) // convert string into a LocalDate fix date format
                    .orElse(null); // if no value is present, return null
        }

    }
}
