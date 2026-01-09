/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Mappers;

import Dtos.PrescriptionEditDto;
import Entities.PrescriptionEditHistoryEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(
        componentModel = "jakarta",
        uses = {PrescriptionSnapshotMapper.class, AuditMapper.class}
)
public interface PrescriptionEditHistoryMapper {

    // ------------------------
    // Entity → DTO
    // ------------------------
    @Mapping(source = "prescription.id", target = "prescriptionId")
    @Mapping(source = "previousState", target = "previousState") // mapped via PrescriptionSnapshotMapper
    @Mapping(source = "newState", target = "newState")           // mapped via PrescriptionSnapshotMapper
    @Mapping(source = ".", target = "audit")                     // mapped via AuditMapper
    PrescriptionEditDto toDto(PrescriptionEditHistoryEntity entity);

}
