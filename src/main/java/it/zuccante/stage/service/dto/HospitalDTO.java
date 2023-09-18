package it.zuccante.stage.service.dto;

import lombok.*;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HospitalDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private int id;
    private String name;
    private String address;
    private double latitude;
    private double longitude;
}
