package br.ufpb.dcx.aps.strategy.form;


import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Formulario {

    private String titulo;
    private Map<String, ItemFormulario> campos= new LinkedHashMap<>();

    public Formulario() {
        this("");
    }

    public Formulario(String titulo) {
        this.titulo = titulo;
    }

    public String getTitulo() {
        return this.titulo;
    }

    public void setTitulo(String novoTitutlo) {
        this.titulo = novoTitutlo;
    }

    public void addItemFormulario(ItemFormulario campo) {
        if(campos.containsKey(campo.getId())) {
            throw new RuntimeException("'"+campo.getId()+"' já existe");
        }
        this.campos.put(campo.getId(),campo);
    }

    public ItemFormulario getItemFormulario(String id) {
        return campos.get(id);
    }

    public List<ItemFormulario> getItensFormulario() {
        return new ArrayList<>(campos.values());
    }

    public Resultado validar() {
        Resultado result = new Resultado();
        for (ItemFormulario c: this.campos.values()) {
            if(c.validar().isErro()) {
                result.setErro(true);
                result.addMensagem(c.getId()+": "+c.validar().getMensagens().get(0));
            }
        }
        return result;
    }
}