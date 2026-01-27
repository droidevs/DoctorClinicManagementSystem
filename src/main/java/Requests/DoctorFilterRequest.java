package Requests;

public record DoctorFilterRequest(
    int page,
    int size,
    String name,
    String email,
    String specialisation
) implements PageRequest {}
