package Pages;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import jakarta.faces.context.FacesContext;
import jakarta.servlet.http.HttpSession;
import Services.AuthService;
import Requests.RegisterUserRequest;
import Dtos.UserDto;
import jakarta.inject.Inject;
import jakarta.faces.flow.FlowScoped;

@Named
@FlowScoped("patient")
@Getter
@Setter
public class RegisterPage {
    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    private String email;

    @NotBlank(message = "Password is required")
    @Size(min = 8, max = 100, message = "Password must be between 8 and 100 characters")
    private String password;

    private String message;

    @Inject
    private AuthService authService;

    public String register() {
        try {
            RegisterUserRequest request = new RegisterUserRequest(email, password);
            String token = authService.register(request);
            if (token != null && !token.isEmpty()) {
                FacesContext facesContext = FacesContext.getCurrentInstance();
                HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
                session.setAttribute("authToken", token);
                this.message = "Registration successful!";
                return "patientProfile?faces-redirect=true"; // Redirect to patient profile completion
            } else {
                this.message = "Registration failed.";
            }
        } catch (Exception e) {
            this.message = "Registration error: " + e.getMessage();
        }
        return null;
    }
}
