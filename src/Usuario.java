package com.mycompany.menueventos;

import java.util.ArrayList;
import java.util.List;

public class Usuario {
    private String nome;
    private String email;
    private String senha;
    private List<Evento> eventosConfirmados;
    
    public Usuario(String no, String em, String se) {
           this.nome =  no;
           this.email = em;
           this.senha = se;
           this.eventosConfirmados = new ArrayList<>();
    }
    
    public void confirmP(Evento evento) {
           if (eventosConfirmados.contains(evento)) {
               eventosConfirmados.add(evento);
               System.out.println("A presenca de " + this.nome + " foi confirmada em: " + evento.getNome());
           } else {
               System.out.println(nome + " ja esta inscrito nesse evento"); 
           }
    }
    
    @Override
    public String toString() {
        return ("Usuario: " + this.nome + " (" + this.email + ") | Eventos confirmados: " + eventosConfirmados.size());
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getSenha() {
        return senha;
    }
    
    public List<Evento> getEventosConfirmados() {
        return eventosConfirmados;
    }
    
    
}
