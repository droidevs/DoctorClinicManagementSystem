package Criteria;

import Requests.Pagination;

public class SpecialisationQuery {
    private final Pagination pagination;
    private final String searchQuery;

    public SpecialisationQuery(Pagination pagination, String searchQuery) {
        this.pagination = pagination;
        this.searchQuery = searchQuery;
    }

    public Pagination getPagination() { return pagination; }
    public String getSearchQuery() { return searchQuery; }
}
