/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Resources;

import Dtos.DoctorDto;
import Requests.AssignSpecialisationsRequest;
import Requests.CreateDoctorRequest;
import Requests.UpdateDoctorRequest;
import Services.DoctorService;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;
import java.net.URI;
import java.util.List;
import java.util.UUID;

@Path("/doctors")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class DoctorResource {
    
    @Inject
    private DoctorService doctorService;
    
    @Context
    private UriInfo uriInfo;
    
    // POST /doctors - Create new doctor
    @POST
    public Response createDoctor(@Valid CreateDoctorRequest request) {
        DoctorDto createdDoctor = doctorService.create(request);
        
        URI location = uriInfo.getAbsolutePathBuilder()
                .path(createdDoctor.id().toString())
                .build();
        
        return Response
                .created(location)
                .entity(createdDoctor)
                .build();
    }
    
    // PUT /doctors/{id} - Update doctor
    @PUT
    @Path("/{id}")
    public Response updateDoctor(
            @PathParam("id") UUID id,
            @Valid UpdateDoctorRequest request) {
        
        DoctorDto updatedDoctor = doctorService.update(id, request);
        return Response.ok(updatedDoctor).build();
    }
    
    // GET /doctors/{id} - Get doctor by ID
    @GET
    @Path("/{id}")
    public Response getDoctorById(@PathParam("id") UUID id) {
        DoctorDto doctor = doctorService.findById(id);
        return Response.ok(doctor).build();
    }
    
    // GET /doctors - Get all doctors
    @GET
    public Response getAllDoctors() {
        List<DoctorDto> doctors = doctorService.findAll();
        return Response.ok(doctors).build();
    }
    
    // POST /doctors/{id}/specialisations - Assign specialisations to doctor
    @POST
    @Path("/{id}/specialisations")
    public Response assignSpecialisations(
            @PathParam("id") String id,
            @Valid AssignSpecialisationsRequest request) {
        
        DoctorDto updatedDoctor = doctorService.assignSpecialisations(id, request);
        return Response.ok(updatedDoctor).build();
    }
    
    // DELETE /doctors/{id} - Delete doctor
    @DELETE
    @Path("/{id}")
    public Response deleteDoctor(@PathParam("id") UUID id) {
        doctorService.delete(id);
        return Response.noContent().build();
    }
}
