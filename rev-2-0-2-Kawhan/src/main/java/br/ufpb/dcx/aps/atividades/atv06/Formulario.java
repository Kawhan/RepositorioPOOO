package br.ufpb.dcx.aps.atividades.atv06;

import java.util.ArrayList;
import java.util.List;

public class Formulario {

    private String titulo;
    private List<Campo> campos;

    public Formulario() {
        this.titulo = "";
        campos = new ArrayList<>();
    }

    public String getTitulo() {
        return this.titulo;
    }

    public void setTitulo(String novoTitutlo) {
        this.titulo = novoTitutlo;
    }

    public void addCampo(Campo campo) {
        for (Campo c: this.campos) {
            if(c.getId().equals(campo.getId())) {
                throw new RuntimeException("Já existe um campo com este id:'"+campo.getId()+"'");

            }
        }
        this.campos.add(campo);
    }

    public Campo getCampo(String id) {
        for (Campo c: this.campos) {
            if(c.getId().equals(id)) {
                return c;
            }
        }
        throw  new RuntimeException("Campo não encontrado");
    }

    public List<Campo> getCampos() {
        return this.campos;
    }

    public Resultado validar() {
        Resultado result = new Resultado();
        for (Campo c: this.campos) {
            if(c.isObrigatorio() && !c.isPreenchido()) {
                result.addMensagem("Campo "+c.getId()+": Este campo é obrigatório e não foi preenchido");
                result.setErro(true);
            }
        }
        return result;
    }
}