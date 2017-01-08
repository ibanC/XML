package com.example.dm2.xml;

/**
 * Created by iban on 30/12/2016.
 */
public class Dia {
    private String estado_cielo;
    private int temp_Max;
    private int temp_Min;
    private String localidad;

    public String getEstado_cielo() {
        return estado_cielo;
    }
    public String getlocalidad() {
        return localidad;
    }

    public void setEstado_cielo(String estado_cielo) {
        this.estado_cielo = estado_cielo;
    }
    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }
    public int getTemp_Max() {
        return temp_Max;
    }

    public void setTemp_Max(int temp_Max) {
        this.temp_Max = temp_Max;
    }

    public int getTemp_Min() {
        return temp_Min;
    }

    public void setTemp_Min(int temp_Min) {
        this.temp_Min = temp_Min;
    }

    @Override
    public String toString() {
        return "pepe";
    }
}
