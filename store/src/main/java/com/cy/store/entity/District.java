package com.cy.store.entity;


import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.Objects;

@Data
@ToString
public class District  implements Serializable {

    private Integer id;
    private String parent;
    private String code;
    private String name;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        District district = (District) o;
        return Objects.equals(id, district.id) && Objects.equals(parent, district.parent) && Objects.equals(code, district.code) && Objects.equals(name, district.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, parent, code, name);
    }
}
