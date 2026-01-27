package Mappers;

import Criteria.BillQuery;
import Requests.BillFilterRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;
import java.time.LocalDate;

@Mapper(componentModel = "jakarta")
public interface BillQueryMapper {
    BillQueryMapper INSTANCE = Mappers.getMapper(BillQueryMapper.class);

    @Mapping(target = "fromDate", source = "fromDate", qualifiedByName = "stringToLocalDate")
    @Mapping(target = "toDate", source = "toDate", qualifiedByName = "stringToLocalDate")
    BillQuery toQuery(BillFilterRequest request);

    @Named("stringToLocalDate")
    default LocalDate stringToLocalDate(String date) {
        return date == null ? null : LocalDate.parse(date);
    }
}
