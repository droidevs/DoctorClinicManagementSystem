/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Mappers;

import Dtos.BillDto;
import Entities.BillEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;


@Mapper(
        componentModel = "jakarta",
        uses = {
                AppointmentMapper.class,
                AuditMapper.class
        }
)
public interface BillMapper {

    // ------------------------
    // Entity → DTO
    // ------------------------

    @Mapping(source = ".", target = "audit")
    BillDto toDto(BillEntity entity);

}
