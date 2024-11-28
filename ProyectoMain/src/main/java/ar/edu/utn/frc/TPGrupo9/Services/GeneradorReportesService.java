package ar.edu.utn.frc.TPGrupo9.Services;

import ar.edu.utn.frc.TPGrupo9.Models.Agencia;
import ar.edu.utn.frc.TPGrupo9.Models.Incidente;
import ar.edu.utn.frc.TPGrupo9.Models.Posicion;
import ar.edu.utn.frc.TPGrupo9.Models.Prueba;
import org.springframework.stereotype.Service;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class GeneradorReportesService {

    private void eliminarArchivoSiExiste(String nombreArchivo) {
        Path filePath = Paths.get(nombreArchivo);
        try {
            if (Files.exists(filePath)) {
                Files.delete(filePath);
            }
        } catch (IOException e) {
            System.err.println("Error al eliminar el archivo " + nombreArchivo + ": " + e.getMessage());
        }
    }

    private void escribirEnArchivo(String nombreArchivo, String contenido) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(nombreArchivo, true))) {
            writer.write(contenido);
        } catch (IOException e) {
            System.err.println("Error al escribir en el archivo " + nombreArchivo + ": " + e.getMessage());
        }
    }

    public String generarReporteIncidentes(List<Incidente> incidentes) {
        String nombreArchivo = "ReportesIncidentes.txt";
        eliminarArchivoSiExiste(nombreArchivo);

        if (!incidentes.isEmpty()) {
            for (Incidente incidente : incidentes) {
                String fechaHoraFormateada = incidente.getPosicion().getFechaHora()
                        .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

                String incidenteString = String.format(
                        "ID Prueba: %d | Latitud: %.6f | Longitud: %.6f | Fecha y Hora: %s | Motivo: %s%n",
                        incidente.getPruebaId(),
                        incidente.getPosicion().getLatitud(),
                        incidente.getPosicion().getLongitud(),
                        fechaHoraFormateada,
                        incidente.getMotivo());

                escribirEnArchivo(nombreArchivo, incidenteString);
            }
            return "Reporte generado correctamente";
        }else return "Error! No se encontraron incidentes";
    }

    public String generarReporteIncidentesXEmpleado(List<Incidente> incidentes, int idEmpleado) {
        String nombreArchivo = "ReporteIncidentesEmpleado" + idEmpleado + ".txt";
        eliminarArchivoSiExiste(nombreArchivo);

        for (Incidente incidente : incidentes) {
            boolean flag = false;
            if (incidente.getPrueba().getEmpleadoId() == idEmpleado) {
                flag = true;
                String fechaHoraFormateada = incidente.getPosicion().getFechaHora()
                        .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

                String incidenteString = String.format(
                        "ID Prueba: %d | Latitud: %.6f | Longitud: %.6f | Fecha y Hora: %s | Motivo: %s%n",
                        incidente.getPruebaId(),
                        incidente.getPosicion().getLatitud(),
                        incidente.getPosicion().getLongitud(),
                        fechaHoraFormateada,
                        incidente.getMotivo());

                escribirEnArchivo(nombreArchivo, incidenteString);
            }
            if(!flag){
                return "No se pudo generar el reporte: No se han encontrado incidentes para el empleado: " + idEmpleado;
            }
        }
        return "Reporte generado correctamente";
    }


    public String generarReportePruebasXVehiculo(List<Prueba> pruebas, int idVehiculo) {
        String nombreArchivo = "ReportePruebasVehiculo" + idVehiculo + ".txt";
        eliminarArchivoSiExiste(nombreArchivo);

        for (Prueba prueba : pruebas) {
            boolean flag = false;
            if (prueba.getVehiculoId() == idVehiculo) {
                flag = true;
                String fechaHoraInicioFormateada = prueba.getFechaHoraInicio()
                        .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

                String fechaHoraFinFormateada = prueba.getFechaHoraFin() != null
                        ? prueba.getFechaHoraFin().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
                        : "-";

                String pruebaString = String.format(
                        "ID Prueba: %d | ID Interesado: %d | ID Empleado: %d | Fecha y Hora de Inicio: %s | Fecha y Hora de Fin: %s | Comentarios: %s%n",
                        prueba.getId(),
                        prueba.getInteresadoId(),
                        prueba.getEmpleadoId(),
                        fechaHoraInicioFormateada,
                        fechaHoraFinFormateada,
                        prueba.getComentarios());

                escribirEnArchivo(nombreArchivo, pruebaString);
            }
            if (!flag){
                return "No se pudo generar el reporte: No se han encontrado pruebas asociadas al vehiculo de id: " + idVehiculo;
            }
        }
        return "Reporte generado correctamente";
    }

    public void generarReporteKilometros(List<Posicion> posiciones,
                                           Agencia agencia,
                                           int idVehiculo,
                                           LocalDate fechaInicio,
                                           LocalDate fechaFin){

        String nombreArchivo = "KM_" + idVehiculo + "_D_" + fechaInicio + "_H_" + fechaFin + ".txt";
        eliminarArchivoSiExiste(nombreArchivo);

        double totalKM = 0.0;
        for(Posicion posicion : posiciones){
            totalKM += posicion.getKilometrosRecorridos(agencia.getLatitud(), agencia.getLongitud());
        }

        String kmString = String.format("Total de kilometros recorridos por el vehiculo %d " +
                                        "entre la fecha %s y la fecha %s: %fKM",
                                        idVehiculo,
                                        fechaInicio,
                                        fechaFin,
                                        totalKM);
        escribirEnArchivo(nombreArchivo, kmString);
    }
}

