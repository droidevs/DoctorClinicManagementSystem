/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Mappers;

import Dtos.DosageDto;
import Entities.embeded.Dosage;
import Requests.DosageRequest;
import org.mapstruct.Mapper;

/**
 *
 * @author admin
 */
@Mapper(componentModel = "jakarta")
public interface DosageMapper {


    DosageDto toDto(Dosage dosage);

}

