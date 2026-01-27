package Requests;

public record DoctorFilterRequest(
    Pagination pagination,
    String name,
    String email,
    String specialisation
) {}
