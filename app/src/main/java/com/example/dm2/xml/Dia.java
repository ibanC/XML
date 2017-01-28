package com.example.dm2.xml;

/**
 * Created by iban on 30/12/2016.
 */
public class Dia {
    private String fecha;
    private String temp_Max;
    private String temp_Min;
    private String localidad;

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getlocalidad() {
        return localidad;
    }


    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }
    public String getTemp_Max() {
        return temp_Max;
    }

    public void setTemp_Max(String temp_Max) {
        this.temp_Max = temp_Max;
    }

    public String getTemp_Min() {
        return temp_Min;
    }

    public void setTemp_Min(String temp_Min) {
        this.temp_Min = temp_Min;
    }

    @Override
    public String toString() {
        return localidad;
    }
}
