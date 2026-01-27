package Mappers;

import Criteria.PatientQuery;
import Requests.PatientFilterRequest;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "jakarta")
public interface PatientQueryMapper {
    PatientQueryMapper INSTANCE = Mappers.getMapper(PatientQueryMapper.class);
    PatientQuery toQuery(Requests.PatientFilterRequest request);
}
