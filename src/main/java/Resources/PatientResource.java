/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Resources;

import Dtos.PatientDto;
import Requests.CreatePatientRequest;
import Requests.UpdatePatientRequest;
import Services.PatientService;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;
import java.net.URI;
import java.util.List;
import java.util.UUID;

/**
 *
 * @author admin
 */

@Path("/patients")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PatientResource {
    
    @Inject
    private PatientService patientService;
    
    @Context
    private UriInfo uriInfo;
    
    // POST /patients - Create new patient
    @POST
    public Response createPatient(@Valid CreatePatientRequest request) {
        // Call service method: PatientDto create(CreatePatientRequest request)
        PatientDto createdPatient = patientService.create(request);
        
        // Build location URI for the newly created resource
        URI location = uriInfo.getAbsolutePathBuilder()
                .path(createdPatient.id().toString())
                .build();
        
        // Return 201 Created with Location header and the created patient
        return Response
                .created(location)
                .entity(createdPatient)
                .build();
    }
    
    // PUT /patients/{id} - Update existing patient
    @PUT
    @Path("/{id}")
    public Response updatePatient(
            @PathParam("id") UUID id,
            @Valid UpdatePatientRequest request) {
        
        // Call service method: PatientDto update(UUID id, UpdatePatientRequest request)
        PatientDto updatedPatient = patientService.update(id, request);
        
        // Return 200 OK with updated patient
        return Response
                .ok(updatedPatient)
                .build();
    }
    
    // GET /patients/{id} - Get single patient by ID
    @GET
    @Path("/{id}")
    public Response getPatientById(@PathParam("id") UUID id) {
        // Call service method: PatientDto findById(UUID id)
        PatientDto patient = patientService.findById(id);
        
        // Return 200 OK with patient data
        return Response
                .ok(patient)
                .build();
    }
    
    // GET /patients - Get all patients
    @GET
    public Response getAllPatients() {
        // Call service method: List<PatientDto> findAll()
        List<PatientDto> patients = patientService.findAll();
        
        // Return 200 OK with list of patients
        return Response
                .ok(patients)
                .build();
    }
    
    // DELETE /patients/{id} - Delete patient by ID
    @DELETE
    @Path("/{id}")
    public Response deletePatient(@PathParam("id") UUID id) {
        // Call service method: void delete(UUID id)
        patientService.delete(id);
        
        // Return 204 No Content for successful deletion
        return Response
                .noContent()
                .build();
    }
    
}