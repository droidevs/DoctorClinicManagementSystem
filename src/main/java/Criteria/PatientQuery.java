package Criteria;

import Requests.Pagination;

public class PatientQuery {
    private final Pagination pagination;
    private final String name;
    private final String email;

    public PatientQuery(Pagination pagination, String name, String email) {
        this.pagination = pagination;
        this.name = name;
        this.email = email;
    }

    public Pagination getPagination() { return pagination; }
    public String getName() { return name; }
    public String getEmail() { return email; }
}
