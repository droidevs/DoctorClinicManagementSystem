/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Services;

import Dtos.SpecialisationDto;
import Requests.CreateSpecialisationRequest;
import Requests.SpecialisationFilterRequest;
import Requests.UpdateSpecialisationRequest;
import java.util.List;
import java.util.UUID;

/**
 *
 * @author admin
 */
public interface SpecialisationService {
    
    
    SpecialisationDto create(CreateSpecialisationRequest request);
    
    SpecialisationDto delete(String id);
    
    SpecialisationDto findById(String id);
    
    List<SpecialisationDto> findAll();
    
    List<SpecialisationDto> filter(SpecialisationFilterRequest request);

    public SpecialisationDto update(UUID id, UpdateSpecialisationRequest request);
}
