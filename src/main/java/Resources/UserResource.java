/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Resources;

import Dtos.UserDto;
import Requests.RegisterUserRequest;
import Services.AuthService;
import Services.UserService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;
import java.util.List;
import java.util.UUID;
import jakarta.annotation.security.*;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;

@Path("/users")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserResource {
    @Inject
    private UserService userService;

    @Inject
    private AuthService authService;

    // POST /users/register - Register a new user
    @POST
    @Path("/register")
    public Response register(RegisterUserRequest request) {
        String token = authService.register(request);
        return Response.ok(token).build();
    }

    // POST /users/login - Login user
    @POST
    @Path("/login")
    public Response login(Requests.LoginRequest request) {
        String token = authService.login(request);
        return Response.ok(token).build();
    }

    // GET /users/{id} - Get user by ID
    @GET
    @Path("/{id}")
    public Response getUserById(@PathParam("id") UUID id) {
        UserDto user = userService.findById(id);
        return Response.ok(user).build();
    }
    
    // GET /users/email/{email} - Get user by email
    @GET
    @Path("/email/{email}")
    public Response getUserByEmail(@PathParam("email") String email) {
        UserDto user = userService.findByEmail(email);
        return Response.ok(user).build();
    }
    
    // GET /users - Get all users
    @GET
    public Response getAllUsers() {
        List<UserDto> users = userService.findAll();
        return Response.ok(users).build();
    }
    
    // PUT /users/{id}/disable - Disable user
    @PUT
    @Path("/{id}/disable")
    public Response disableUser(@PathParam("id") UUID id) {
        userService.disable(id);
        return Response.ok().entity("User disabled successfully").build();
    }
}
