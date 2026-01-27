package Pages;

import Securities.RequestUserContext;
import Services.PatientService;
import Requests.CreatePatientRequest;
import Dtos.PatientDto;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.flow.FlowScoped;
import jakarta.inject.Named;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import java.time.LocalDate;
import jakarta.annotation.PostConstruct;
import Validators.annotations.ValidAddress;
import Validators.annotations.ValidDateOfBirth;
import Validators.annotations.ValidName;
import Validators.annotations.ValidPhoneNumber;

@Named
@FlowScoped("patient")
@Getter
@Setter
public class PatientProfilePage {

    @ValidName
    @NotBlank(message = "First name is required")
    private String firstName;

    @ValidName
    @NotBlank(message = "Last name is required")
    private String lastName;

    private String gender;

    @ValidDateOfBirth
    private LocalDate dateOfBirth;

    @ValidPhoneNumber
    private String phone;

    @ValidAddress
    private String address;

    private String message;

    private boolean hasProfile;

    @Inject
    private RequestUserContext userContext;

    @Inject
    private PatientService patientService;

    @PostConstruct
    public void init() {
        hasProfile = false;
        if (userContext.getUserId() != null) {
            try {
                PatientDto patient = patientService.findById(userContext.getUserId());
                if (patient != null) {
                    hasProfile = true;
                }
            } catch (Exception e) {
                // Profile does not exist
            }
        }
    }

    public void redirectIfHasProfile() {
        if (hasProfile) {
            try {
                FacesContext.getCurrentInstance().getExternalContext().redirect("dashboard.xhtml");
            } catch (Exception e) {
                // Handle exception
            }
        }
    }

    public String saveProfile() {
        try {
            CreatePatientRequest request = new CreatePatientRequest(
                userContext.getUserId(),
                firstName,
                lastName,
                gender,
                dateOfBirth,
                phone,
                address
            );
            PatientDto patient = patientService.create(request);
            this.message = "Profile completed successfully!";
            return "dashboard?faces-redirect=true";
        } catch (Exception e) {
            this.message = "Error saving profile: " + e.getMessage();
            return null;
        }
    }
}
