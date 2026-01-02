/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Resources;


import Dtos.SpecialisationDto;
import Requests.CreateSpecialisationRequest;
import Requests.SpecialisationFilterRequest;
import Requests.UpdateSpecialisationRequest;
import Services.SpecialisationService;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Path("/specialisations")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class SpecialisationResource {
    
    @Inject
    private SpecialisationService specialisationService;
    
    @Context
    private UriInfo uriInfo;
    
    @POST
    public Response createSpecialisation(@Valid CreateSpecialisationRequest request) {
        SpecialisationDto createdSpecialisation = specialisationService.create(request);
        
        URI location = uriInfo.getAbsolutePathBuilder()
                .path(createdSpecialisation.id().toString())
                .build();
        
        return Response
                .created(location)
                .entity(createdSpecialisation)
                .build();
    }
    
    @GET
    @Path("/{id}")
    public Response getSpecialisationById(@PathParam("id") String id) {
        SpecialisationDto specialisation = specialisationService.findById(id);
        return Response.ok(specialisation).build();
    }
    
    @GET
    public Response getAllSpecialisations() {
        List<SpecialisationDto> specialisations = specialisationService.findAll();
        return Response.ok(specialisations).build();
    }
    
    @GET
    @Path("/filter")
    public Response filter(SpecialisationFilterRequest request) {
        List<SpecialisationDto> results = specialisationService.filter(request);
        return Response.ok(results).build();
    }
    
     
    @PUT
    @Path("/{id}")
    public Response updateSpecialisation(
            @PathParam("id") UUID id,
            @Valid UpdateSpecialisationRequest request) {
        
        SpecialisationDto updatedSpecialisation = specialisationService.update(id, request);
        return Response.ok(updatedSpecialisation).build();
    }
    
    @DELETE
    @Path("/{id}")
    public Response deleteSpecialisation(@PathParam("id") String id) {
        specialisationService.delete(id);
        return Response.noContent().build();
    }
}
