package Criteria;

import Requests.Pagination;

public class DoctorQuery {
    private final Pagination pagination;
    private final String name;
    private final String email;
    private final String specialisation;

    public DoctorQuery(Pagination pagination, String name, String email, String specialisation) {
        this.pagination = pagination;
        this.name = name;
        this.email = email;
        this.specialisation = specialisation;
    }

    public Pagination getPagination() { return pagination; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public String getSpecialisation() { return specialisation; }
}
