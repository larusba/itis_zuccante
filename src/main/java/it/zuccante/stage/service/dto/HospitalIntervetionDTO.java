package it.zuccante.stage.service.dto;


import lombok.Data;

@Data
public class HospitalIntervetionDTO {
    private static final long serialVersionUID = 1L;

    private HospitalDTO hospital;
    private int CountIntervetion;
}
