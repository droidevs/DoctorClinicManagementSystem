/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Mappers;

import Dtos.PrescriptionDto;
import Entities.PrescriptionEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 *
 * @author admin
 */
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(
        componentModel = "jakarta",
        uses = {
                DosageMapper.class,
                FrequencyMapper.class,
                MedicationMapper.class,
                PrescriptionEditHistoryMapper.class,
                AuditMapper.class
        }
)
public interface PrescriptionMapper {

    // ------------------------
    // Entity → DTO
    // ------------------------
    @Mapping(source = ".", target = "audit")  // maps BaseEntity → AuditDto
    @Mapping(source = "dosage", target = "dosage")
    @Mapping(source = "frequency", target = "frequency")
    @Mapping(source = "medication", target = "medication")
    @Mapping(source = "editHistory", target = "editHistory")
    @Mapping(source = "appointment.id", target = "appointmentId")
    PrescriptionDto toDto(PrescriptionEntity entity);

}

