package it.zuccante.stage.service.dto;

import it.zuccante.stage.domain.Hospital;

import java.io.Serializable;

public class HospitalDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
