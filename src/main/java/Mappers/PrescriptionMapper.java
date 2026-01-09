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
        componentModel = "spring",
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

    // ------------------------
    // DTO → Entity
    // ------------------------
    @Mapping(target = "id", ignore = true)                  // handled by JPA
    @Mapping(target = "appointment", ignore = true)        // set in service layer
    @Mapping(source = "dosage", target = "dosage")
    @Mapping(source = "frequency", target = "frequency")
    @Mapping(source = "medication", target = "medication")
    @Mapping(target = "specificTimes", ignore = true)      // handled manually
    @Mapping(target = "editHistory", ignore = true)        // handled manually
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedBy", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    PrescriptionEntity toEntity(PrescriptionDto dto);
}

