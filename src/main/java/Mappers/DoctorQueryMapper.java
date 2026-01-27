package Mappers;

import Criteria.DoctorQuery;
import Requests.DoctorFilterRequest;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "jakarta")
public interface DoctorQueryMapper {
    DoctorQueryMapper INSTANCE = Mappers.getMapper(DoctorQueryMapper.class);
    DoctorQuery toQuery(Requests.DoctorFilterRequest request);
}
