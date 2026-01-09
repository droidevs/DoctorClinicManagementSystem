package Mappers;


import Dtos.PaymentDto;
import Entities.PaymentEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(
        componentModel = "jakarta",
        uses = {
                AuditMapper.class,
                BillMapper.class
        }
)
public interface PaymentMapper {

    /* ========================
       Entity → DTO
       ======================== */

    @Mapping(source = "receivedBy.id", target = "receivedBy")
    @Mapping(source = ".", target = "audit")
    PaymentDto toDto(PaymentEntity entity);

}
