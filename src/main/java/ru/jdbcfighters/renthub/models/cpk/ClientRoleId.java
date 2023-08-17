package ru.jdbcfighters.renthub.models.cpk;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Data
@Embeddable
public class ClientRoleId implements Serializable {

    @Column(name = "client_id")
    private Long clientId;

    @Column(name = "role")
    private String role;

}
