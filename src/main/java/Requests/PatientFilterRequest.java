package Requests;

public record PatientFilterRequest(
    Pagination pagination,
    String name,
    String email
) {}
