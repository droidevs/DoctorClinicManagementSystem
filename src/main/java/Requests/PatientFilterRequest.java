package Requests;

public record PatientFilterRequest(
    int page,
    int size,
    String name,
    String email
) implements PageRequest {}
