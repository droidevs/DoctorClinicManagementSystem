package Mappers;

import Criteria.AppointmentQuery;
import Requests.AppointmentFilterRequest;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface AppointmentQueryMapper {
    AppointmentQueryMapper INSTANCE = Mappers.getMapper(AppointmentQueryMapper.class);
    AppointmentQuery toQuery(AppointmentFilterRequest request);
}
