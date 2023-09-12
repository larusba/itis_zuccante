package it.zuccante.stage.web.rest.errors;

public class BadHospitalOrHealthServiceNameException extends BadRequestAlertException{
    public BadHospitalOrHealthServiceNameException() {
        super(ErrorConstants.DEFAULT_TYPE, "Wrong Hospital or Health Service name!", "nomeHospedale/nomePrestazione", "namedoesntexist");
    }
}
