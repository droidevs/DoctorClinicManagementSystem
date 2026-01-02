/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Services;

import Dtos.SpecialisationDto;
import Requests.CreateSpecialisationRequest;

/**
 *
 * @author admin
 */
public interface SpecialisationService {
    
    
    SpecialisationDto create(CreateSpecialisationRequest request);
    
    SpecialisationDto delete(String id);
    
}
