package br.ufpb.dcx.aps.atividades.atv05;

import java.util.HashMap;
import java.util.Map;

public class Formulario {

    private String titulo;
    private Map<String, Campo> campoo = new HashMap<>();

    public Formulario() {
        this.titulo = "";
    }

    public String getTitulo() {
        return this.titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void addCampo(Campo campo) {
        if (this.campoo.get(campo.getId()) != null) {
            throw new RuntimeException("Já existe um campo com este id:'"+campo.getId()+"'");
        }
        this.campoo.put(campo.getId(), campo);
    }

    public Campo getCampo(String id) {
        if (this.campoo.get(id) == null){
            return null;
        }
        else {
            return this.campoo.get(id);
        }
    }

    public Map<String , Campo> getCampos() {
        return this.campoo;
    }


}
