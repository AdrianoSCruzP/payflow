package com.nttdata.payflow.model;

/**
 * Empleado que cobra por horas trabajadas.
 */
public class EmpleadoPorHoras extends Empleado {

    private static final double HORAS_NORMALES_MAXIMAS = 160.0;
    private static final double TARIFA_EXTRA = 1.5;

    // TODO 11: declara los atributos privados tarifaHora (final) y horasTrabajadas.
    //          En el constructor valida tarifaHora > 0 (IllegalArgumentException).
    private final double tarifaHora;
    private double horasTrabajadas;


    public EmpleadoPorHoras(String id, String nombre, Area area, double tarifaHora) {
        super(id, nombre, area);
        if (tarifaHora <= 0){
            throw new IllegalArgumentException("La tarifa por hora debe ser positiva");
        }
        this.tarifaHora = tarifaHora;
        this.horasTrabajadas = 0.00;
    }

    // TODO 12: registra las horas; si son negativas lanza IllegalArgumentException.
    public void registrarHoras(int horas) {
        if (horas < 0) throw new IllegalArgumentException("Las horas debe ser positivas");
        this.horasTrabajadas += horas;
    }

    // TODO 13 (RN-04): hasta 160 horas se pagan a la tarifa normal;
    //          las horas que superan 160 se pagan al 150% de la tarifa.
    @Override
    public double calcularPagoMensual() {
        double horasNormales = Math.min(horasTrabajadas, HORAS_NORMALES_MAXIMAS);
        double horasExtra = Math.max(horasTrabajadas - HORAS_NORMALES_MAXIMAS, 0);

        return horasNormales * tarifaHora + (horasExtra * tarifaHora * TARIFA_EXTRA);
    }

    // TODO 14: debe devolver "Por horas".
    @Override
    public String getTipo() {
        return "Por horas";
    }
}
