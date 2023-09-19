package it.zuccante.stage.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TrackDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String hospitalName;
    private double duration;
    private double distance;
    private String congestion;
    private double longitude;
    private double latitude;
}
