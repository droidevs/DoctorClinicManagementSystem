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

    /* ========================
       DTO → Entity
       ======================== */

    @Mapping(target = "bill", ignore = true)
    @Mapping(target = "receivedBy", ignore = true)

    // audit (BaseEntity-managed)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedBy", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)

    // external provider (decided by service)
    @Mapping(target = "stripePaymentIntentId", ignore = true)

    PaymentEntity toEntity(PaymentDto dto);
}
