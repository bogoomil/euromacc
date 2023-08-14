package hu.feladat.elastic.dto;

import java.util.ArrayList;
import java.util.List;

public class UserSearchResponse {
    private Long total;
    private List<UserResponse> users = new ArrayList<>();

    public void setTotal(long size) {
        this.total = size;
    }

    public Long getTotal() {
        return total;
    }

    public List<UserResponse> getUsers() {
        return users;
    }

    public void setUsers(List<UserResponse> users) {
        this.users = users;
    }
}
