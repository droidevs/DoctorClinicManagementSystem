package Mappers;

import Criteria.SpecialisationQuery;
import Requests.SpecialisationFilterRequest;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface SpecialisationQueryMapper {
    SpecialisationQueryMapper INSTANCE = Mappers.getMapper(SpecialisationQueryMapper.class);
    SpecialisationQuery toQuery(Requests.SpecialisationFilterRequest request);
}
