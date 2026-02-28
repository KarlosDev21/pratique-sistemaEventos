package com.mycompany.menueventos;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Evento {
        private String nome;
        private LocalDateTime dataHora;
        private String endereco;
        private String categoria;
        private String descricao;
        

    public Evento(String no, LocalDateTime dh, String en, String ca, String de) {
        this.nome = no;
        this.dataHora = dh;
        this.endereco = en;
        this.categoria = ca;
        this.descricao = de;
    }

     @Override
    public String toString(){
            DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy ' as ' HH:mm");
            return ("Evento: " + this.nome + 
                    " em " + this.dataHora.format(formato) + " | Categoria: " + this.categoria
                    + " | Descricao do evento: " + this.descricao);
            
    }
    
    public String getNome() {
        return nome;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public String getEndereco() {
        return endereco;
    }
    

    public String getCategoria() {
        return categoria;
    }

    public String getDescricao() {
        return descricao;
    }
    
    }
        
        

