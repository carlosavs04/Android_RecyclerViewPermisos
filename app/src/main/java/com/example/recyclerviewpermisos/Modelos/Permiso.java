package com.example.recyclerviewpermisos.Modelos;

import androidx.activity.result.contract.ActivityResultContracts;

public class Permiso {
    private String nombrePermiso;
    private Integer id;
    private String requestPermisson;


    public Permiso(String nombrePermiso, Integer id, String requestPermisson) {
        this.nombrePermiso = nombrePermiso;
        this.id = id;
        this.requestPermisson = requestPermisson;
    }

    public String getNombrePermiso() {
        return nombrePermiso;
    }

    public void setNombrePermiso(String nombrePermiso) {
        this.nombrePermiso = nombrePermiso;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRequestPermisson() {
        return requestPermisson;
    }

    public void setRequestPermisson(String requestPermisson) {
        this.requestPermisson = requestPermisson;
    }
}
