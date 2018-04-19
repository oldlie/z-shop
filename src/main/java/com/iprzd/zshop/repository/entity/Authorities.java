package com.iprzd.zshop.repository.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Authorities implements Serializable {

    @Id
    private long id;
    private String username;
    private String authority;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
    return username;
    }

    public void setUsername(String username) {
    this.username = username;
    }


    public String getAuthority() {
    return authority;
    }

    public void setAuthority(String authority) {
    this.authority = authority;
    }

}
