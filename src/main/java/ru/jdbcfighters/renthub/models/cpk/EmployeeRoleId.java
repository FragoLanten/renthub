package ru.jdbcfighters.renthub.models.cpk;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Data
@Embeddable
public class EmployeeRoleId implements Serializable {

    @Column(name = "employee_id")
    private Long employeeId;

    @Column(name = "role")
    private String role;

}
