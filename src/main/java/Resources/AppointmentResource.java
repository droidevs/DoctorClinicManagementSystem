/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Resources;

import Dtos.AppointmentDto;
import Requests.AddAppointmentNoteRequest;
import Requests.AppointmentFilterRequest;
import Requests.CancelAppointmentRequest;
import Requests.CompleteAppointmentRequest;
import Requests.CreateAppointmentRequest;
import Requests.UpdateAppointmentRequest;
import Services.AppointmentService;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;
import java.net.URI;
import java.util.List;
import java.util.UUID;

@Path("/appointments")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AppointmentResource {
    
    @Inject
    private AppointmentService appointmentService;
    
    @Context
    private UriInfo uriInfo;
    
    // POST /appointments - Create new appointment
    @POST
    public Response createAppointment(@Valid CreateAppointmentRequest request) {
        AppointmentDto createdAppointment = appointmentService.create(request);
        
        URI location = uriInfo.getAbsolutePathBuilder()
                .path((String) createdAppointment.id().toString())
                .build();
        
        return Response
                .created(location)
                .entity(createdAppointment)
                .build();
    }
    
    // PUT /appointments/{id} - Update appointment
    @PUT
    @Path("/{id}")
    public Response updateAppointment(
            @PathParam("id") String id,
            @Valid UpdateAppointmentRequest request) {
        
        AppointmentDto updatedAppointment = appointmentService.update(id, request);
        return Response.ok(updatedAppointment).build();
    }
    
    // POST /appointments/{id}/notes - Add notes to appointment
    @POST
    @Path("/{id}/notes")
    public Response addAppointmentNotes(
            @PathParam("id") String id,
            @Valid AddAppointmentNoteRequest request) {
        
        AppointmentDto updatedAppointment = appointmentService.addNotes(id, request);
        return Response.ok(updatedAppointment).build();
    }
    
    // GET /appointments/{id} - Get appointment by ID
    @GET
    @Path("/{id}")
    public Response getAppointmentById(@PathParam("id") UUID id) {
        AppointmentDto appointment = appointmentService.findById(id);
        return Response.ok(appointment).build();
    }
    
    // GET /appointments - Get all appointments
    @GET
    public Response getAllAppointments() {
        List<AppointmentDto> appointments = appointmentService.findAll();
        return Response.ok(appointments).build();
    }
    
    // POST /appointments/filter - Filter appointments
    @POST
    @Path("/filter")
    public Response filterAppointments(@Valid AppointmentFilterRequest request) {
        List<AppointmentDto> appointments = appointmentService.filter(request);
        return Response.ok(appointments).build();
    }
    
    // PUT /appointments/{id}/status - Update appointment status
    @PUT
    @Path("/{id}/status")
    public Response updateAppointmentStatus(
            @PathParam("id") UUID id,
            @QueryParam("status") String status) {
        
        AppointmentDto updatedAppointment = appointmentService.updateStatus(id, status);
        return Response.ok(updatedAppointment).build();
    }
    
    // PUT /appointments/{id}/complete - Complete appointment
    @PUT
    @Path("/{id}/complete")
    public Response completeAppointment(
            @PathParam("id") String id,
            @Valid CompleteAppointmentRequest request) {
        
        AppointmentDto completedAppointment = appointmentService.complete(id, request);
        return Response.ok(completedAppointment).build();
    }
    
    // PUT /appointments/{id}/cancel - Cancel appointment
    @PUT
    @Path("/{id}/cancel")
    public Response cancelAppointment(
            @PathParam("id") String id,
            @Valid CancelAppointmentRequest request) {
        
        AppointmentDto cancelledAppointment = appointmentService.cancel(id, request);
        return Response.ok(cancelledAppointment).build();
    }
    
    // DELETE /appointments/{id} - Delete appointment
    @DELETE
    @Path("/{id}")
    public Response deleteAppointment(@PathParam("id") UUID id) {
        appointmentService.delete(id);
        return Response.noContent().build();
    }
}