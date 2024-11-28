package ar.edu.utn.frc.TPGrupo9.DTO;

import lombok.Data;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Data
public class ReporteKilometrosRequest {
    private int idVehiculo;
    private String fechaInicio;
    private String fechaFin;


    public LocalDate getFechaInicio(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return LocalDate.parse(this.fechaInicio, formatter);
    }

    public LocalDate getFechaFin(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return LocalDate.parse(this.fechaInicio, formatter);
    }
}
