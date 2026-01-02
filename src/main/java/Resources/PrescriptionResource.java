/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Resources;

import Dtos.PrescriptionDto;
import Requests.CreatePrescriptionRequest;
import Services.PrescriptionService;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;
import java.net.URI;
import java.util.List;
import java.util.UUID;

@Path("/prescriptions")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PrescriptionResource {
    
    @Inject
    private PrescriptionService prescriptionService;
    
    @Context
    private UriInfo uriInfo;
    
    // POST /prescriptions - Create new prescription
    @POST
    public Response createPrescription(@Valid CreatePrescriptionRequest request) {
        PrescriptionDto createdPrescription = prescriptionService.create(request);
        
        URI location = uriInfo.getAbsolutePathBuilder()
                .path(createdPrescription.id().toString())
                .build();
        
        return Response
                .created(location)
                .entity(createdPrescription)
                .build();
    }
    
    // GET /prescriptions/{id} - Get prescription by ID
    @GET
    @Path("/{id}")
    public Response getPrescriptionById(@PathParam("id") UUID id) {
        PrescriptionDto prescription = prescriptionService.findById(id);
        return Response.ok(prescription).build();
    }
    
    // GET /prescriptions - Get all prescriptions
    @GET
    public Response getAllPrescriptions() {
        List<PrescriptionDto> prescriptions = prescriptionService.findAll();
        return Response.ok(prescriptions).build();
    }
}
