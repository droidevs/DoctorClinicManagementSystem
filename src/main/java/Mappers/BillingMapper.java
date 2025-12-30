/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Mappers;

import Dtos.BillDto;
import Entities.BillEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 *
 * @author admin
 */

@Mapper(uses = {AppointmentMapper.class})
public interface BillingMapper {
    
    BillingMapper INSTANCE = Mappers.getMapper(BillingMapper.class);
    
    BillDto toDto(BillEntity bill);
    BillEntity toEntity(BillDto dto);
}
