package ar.edu.utn.frc.TPGrupo9.Services;

import ar.edu.utn.frc.TPGrupo9.Models.Incidente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class GeneradorReportesService {

    public void generarReporteIncidentes(List<Incidente> incidentes) {

        String NOMBRE_ARCHIVO = "ReportesIncidentes.txt";

        Path filePath = Paths.get(NOMBRE_ARCHIVO);

        try {
            if (Files.exists(filePath)) { // Verifica si el archivo existe
                Files.delete(filePath);  // Elimina el archivo
                System.out.println("Archivo eliminado correctamente.");
            } else {
                System.out.println("El archivo no existe.");
            }
        }catch (IOException e) {
            System.out.println("Error al eliminar el archivo.");
        }

        File file = new File(NOMBRE_ARCHIVO);

        for (Incidente incidente : incidentes) {
            String fechaHoraFormateada = incidente.getPosicion().getFechaHora().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

            String incidenteString = String.format(
                    "ID Prueba: %d | Latitud: %.6f | Longitud: %.6f | Fecha y Hora: %s | Motivo: %s%n",
                    incidente.getPruebaId(),
                    incidente.getPosicion().getLatitud(),
                    incidente.getPosicion().getLongitud(),
                    fechaHoraFormateada,
                    incidente.getMotivo());

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(NOMBRE_ARCHIVO, true))) {
                writer.write(incidenteString);
                System.out.println("Incidente registrado correctamente en el archivo.");
            } catch (IOException e) {
                System.err.println("Error al escribir en el archivo: " + e.getMessage());
            }
        }
    }

    public void generarReporteIncidentesXEmpleado(List<Incidente> incidentes, int idEmplead) {
        String NOMBRE_ARCHIVO = "ReporteIncidentesEmpleado" + idEmplead + ".txt";

        Path filePath = Paths.get(NOMBRE_ARCHIVO);

        try {
            if (Files.exists(filePath)) { // Verifica si el archivo existe
                Files.delete(filePath);  // Elimina el archivo
                System.out.println("Archivo eliminado correctamente.");
            } else {
                System.out.println("El archivo no existe.");
            }
        }catch (IOException e) {
            System.out.println("Error al eliminar el archivo.");
        }

        File file = new File(NOMBRE_ARCHIVO);

        for (Incidente incidente : incidentes) {
            if (incidente.getPrueba().getEmpleadoId() == idEmplead) {
                String fechaHoraFormateada = incidente.getPosicion().getFechaHora().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

                String incidenteString = String.format(
                        "ID Prueba: %d | Latitud: %.6f | Longitud: %.6f | Fecha y Hora: %s | Motivo: %s%n",
                        incidente.getPruebaId(),
                        incidente.getPosicion().getLatitud(),
                        incidente.getPosicion().getLongitud(),
                        fechaHoraFormateada,
                        incidente.getMotivo());

                try (BufferedWriter writer = new BufferedWriter(new FileWriter(NOMBRE_ARCHIVO, true))) {
                    writer.write(incidenteString);
                    System.out.println("Incidente registrado correctamente en el archivo.");
                } catch (IOException e) {
                    System.err.println("Error al escribir en el archivo: " + e.getMessage());
                }
            }
        }
    }


}
