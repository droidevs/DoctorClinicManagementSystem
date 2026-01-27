package Pages;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import jakarta.faces.context.FacesContext;
import jakarta.servlet.http.HttpSession;
import Services.AuthService;
import Requests.LoginRequest;
import jakarta.inject.Inject;

@Named
@RequestScoped
@Getter
@Setter
public class LoginPage {
    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    private String email;

    @NotBlank(message = "Password is required")
    private String password;

    private String message;

    @Inject
    private AuthService authService;

    public String login() {
        try {
            LoginRequest request = new LoginRequest(email, password);
            String token = authService.login(request);
            if (token != null && !token.isEmpty()) {
                FacesContext facesContext = FacesContext.getCurrentInstance();
                HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
                session.setAttribute("authToken", token);
                this.message = "Login successful!";
                return "patientProfile?faces-redirect=true"; // Redirect to patient profile
            } else {
                this.message = "Login failed.";
            }
        } catch (Exception e) {
            this.message = "Login error: " + e.getMessage();
        }
        return null;
    }

    public String logout() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(false);
        if (session != null) {
            session.invalidate();
        }
        return "login?faces-redirect=true";
    }
}
