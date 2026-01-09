/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Mappers;

import Dtos.FrequencyDto;
import Entities.embeded.Frequency;
import Requests.FrequencyRequest;
import org.mapstruct.Mapper;


@Mapper(componentModel = "jakarta")
public interface FrequencyMapper {

    /* ========================
       Entity → DTO
       ======================== */
    FrequencyDto toDto(Frequency frequency);

}

