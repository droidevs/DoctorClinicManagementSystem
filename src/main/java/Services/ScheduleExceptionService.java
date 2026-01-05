/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Services;

import Dtos.ScheduleExceptionDto;
import Requests.CreateExceptionSlotRequest;
import Requests.CreateScheduleExceptionRequest;
import java.util.List;
import java.util.UUID;


public interface ScheduleExceptionService {

    ScheduleExceptionDto createException(CreateScheduleExceptionRequest request);

    void addExceptionSlots(
            UUID exceptionId,
            List<CreateExceptionSlotRequest> slots
    );
}
