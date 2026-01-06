/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Resources;

import Dtos.BillDto;
import Requests.BillFilterRequest;
import Requests.CreateBillRequest;
import Requests.PaymentRequest;
import Services.BillingService;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.BeanParam;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DefaultValue;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.net.URI;
import java.util.List;
import java.util.UUID;

@Path("/bills")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class BillingResource {
    
    @Inject
    private BillingService billingService;
    
    @POST
    public Response createBill(@Valid CreateBillRequest request) {
        BillDto bill = billingService.create(request);
        
        return Response
                .created(URI.create("/bills/" + bill.id()))
                .entity(bill)
                .build();
    }
    
    @GET
    @Path("/{id}")
    public Response getBill(@PathParam("id") UUID id) {
        BillDto bill = billingService.findById(id);
        return Response.ok(bill).build();
    }
    
    @POST
    @Path("/filter")
    public Response filterBills(@Valid BillFilterRequest filterRequest) {
        List<BillDto> filteredBills = billingService.filter(filterRequest);
        return Response.ok(filteredBills).build();
    }
    
    @PUT
    @Path("/{id}/pay")
    public Response payBill(
            @PathParam("id") UUID id,
            @Valid PaymentRequest request) {
        
        BillDto paidBill = billingService.pay(id, request);
        return Response.ok(paidBill).build();
    }
    
    // GET /bills/patient/{patientId} - Get bills by patient
    @GET
    @Path("/patient/{patientId}")
    public Response getBillsByPatient(@PathParam("patientId") UUID patientId) {
        List<BillDto> allBills = billingService.findAll();
        List<BillDto> patientBills = allBills.stream()
                .filter(bill -> patientId.equals(bill.appointment().patient()))
                .toList();
        
        return Response.ok(patientBills).build();
    }
    
    // GET /bills/status/{status} - Get bills by status
    @GET
    @Path("/status/{status}")
    public Response getBillsByStatus(@PathParam("status") String status) {
        List<BillDto> allBills = billingService.findAll();
        List<BillDto> statusBills = allBills.stream()
                .filter(bill -> status.equalsIgnoreCase(bill.status().name()))
                .toList();
        
        return Response.ok(statusBills).build();
    }
    
    // GET /bills/unpaid - Get all unpaid bills
    @GET
    @Path("/unpaid")
    public Response getUnpaidBills() {
        List<BillDto> allBills = billingService.findAll();
        List<BillDto> unpaidBills = allBills.stream()
                .filter(bill -> "UNPAID".equalsIgnoreCase(bill.status().name()))
                .toList();
        
        return Response.ok(unpaidBills).build();
    }
}