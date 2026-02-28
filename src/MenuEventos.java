package com.mycompany.menueventos;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MenuEventos {
   private static List<Evento>eventos = new ArrayList<>();
   private static List<Evento>eventosConfirmados = new ArrayList<>();
    private static int escolha;
    public static void main(String[] args) {
        Scanner ler = new Scanner(System.in);       
         
        carregarEventos();
        
        System.out.println("Seu nome de usuario: ");
        String noUser = ler.nextLine();
        System.out.println("Agora seu email: ");
        String emUser = ler.nextLine();
        System.out.println("Digite sua senha: ");
        String seUser = ler.nextLine();
        
       Usuario userAtual = new Usuario (noUser, emUser, seUser);
        System.out.println("Como vai, " + userAtual.getNome() + "?");
        
        
        while (true) {
           
        System.out.println("==================================");
        System.out.println("        MENU DE EVENTOS           ");
        System.out.println("==================================");
        System.out.println("| 1 - Cadastrar novo evento");
        System.out.println("| 2 - Listar todos os eventos");
        System.out.println("| 3 - Confirmar presenca");
        System.out.println("| 4 - Ver meus eventos confirmados");
        System.out.println("| 5 - Ver eventos acontecendo AGORA");
        System.out.println("| 0 - Sair");
        
        System.out.println("Digite sua opcao: ");
        int opcao = ler.nextInt();
        ler.nextLine();
        
        if (opcao == 0) {
            salvarEventos();
            System.out.println("__________________________");
            System.out.println("Obrigado por nos acessar!");
            System.out.println("__________________________");
            break;
        } else if (opcao == 1) {
                System.out.println("Nome do evento: ");
                   String nome = ler.nextLine();
                   System.out.println("__________________");
     
                   System.out.println("Data: (dd/MM/yyyy HH:mm): ");
                   String dataTexto = ler.nextLine();
                   System.out.println("__________________");
                   
                   LocalDateTime dataHora;
                   try {
                   DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
                   dataHora = LocalDateTime.parse(dataTexto, formato);
                       } catch (DateTimeParseException e) {
                    System.out.println("Data invalida! Use o formato dd/MM/yyyy HH:mm (ex: 26/02/2026 20:00)");
                    System.out.println("__________________");
            continue;  
        }
                   
                   System.out.println("Local do evento: ");
                   String endereco = ler.nextLine();
                   System.out.println("__________________");
                   
                   System.out.println("Categoria: ");
                   String categoria = ler.nextLine();
                   System.out.println("__________________");
                   
                   System.out.println("Descricao do evento: ");
                   String descricao = ler.nextLine();
                   System.out.println("__________________");
                   
                   Evento novo = new Evento (nome, dataHora, endereco, categoria, descricao);
                   eventos.add(novo);
                   System.out.println("Evento cadastrado!"); 
                  
                   
        } else if (opcao == 2) {
            if (eventos.isEmpty()) {
                System.out.println("...........................................");
                System.out.println("Voce nao tem tem eventos cadastrados.");
                System.out.println("...........................................");
            } else {
                eventos.sort((e1, e2) -> e1.getDataHora().compareTo(e2.getDataHora()));
                System.out.println("________________________");
                System.out.println("Eventos cadastrados: ");
            for (Evento ev: eventos ){
                System.out.println(ev);
                }
                System.out.println("Voce tem: " + eventos.size() + " eventos cadastrados.");
                
            } 
            } 
           else if (opcao == 3) {
            if (eventos.isEmpty()) {
                System.out.println("..........................................");
                System.out.println("Sem eventos disponiveis para confirmar");
                System.out.println("..........................................");
            } else {
                System.out.println("Eventos disponiveis: ");
            int numero = 1;
            for (Evento ev : eventos) {
                System.out.println(numero + " - " + ev);
                                   numero++;
            }

         System.out.print("Digite o numero do evento que deseja confirmar: ");
            int escolha = ler.nextInt();
            ler.nextLine();  // limpa o Enter

            if (escolha >= 1 && escolha <= eventos.size()) {
            Evento escolhido = eventos.get(escolha - 1);
            userAtual.confirmP(escolhido);
            eventosConfirmados.add(escolhido);
            System.out.println("=========================================================");
            System.out.println("Voce confirmou sua presenca em " + escolhido.getNome() + "!");
            System.out.println("=========================================================");
            } else {
                System.out.println("-----------------");
                System.out.println("Numero invalido!");
            }
        }
    }
                        else if (opcao == 4) { 
                                if (eventosConfirmados.isEmpty()){
                                  System.out.println(".......................................");
                                  System.out.println("Voce nao tem eventos confirmados");
                              } else { 
                                    System.out.println("___________________________");
                                    System.out.println("Seus eventos confirmados: ");
                                   for (Evento ev : eventosConfirmados) {
                                  System.out.println(ev);
                                }
                               }
                               
                                  
                              } 
                            else if (opcao == 5) {
                                System.out.println("____________________________________________");
                                System.out.println("Eventos acontecendo agora: ");
                                 LocalDateTime now = LocalDateTime.now();
                                  boolean eAgora = false;
                                  for (Evento ev : eventos){
                                      
                                      LocalDateTime inicioE = ev.getDataHora();
                                      LocalDateTime fimE = inicioE.plusHours(4);
                                  if (!now.isBefore(inicioE) && now.isBefore(fimE)){
                                      System.out.println("- " + ev);
                                      eAgora = true;
                                  }    
                                  }
                                  if (!eAgora) {
                                      System.out.println("..........................................");
                                      System.out.println("Sem eventos acontecendo no momento.");  
                                  }
                              }
                            else {
                                System.out.println("-------------------------");
                                System.out.println("Opcao invalida! Tente novamente");
                        }
            }
        ler.close();
    }

    private static void carregarEventos() {
    try (BufferedReader reader = new BufferedReader(new FileReader("events.data"))) {
        String linha;
        while ((linha = reader.readLine()) != null) {
            String[] partes = linha.split("\\|");
            if (partes.length == 5) {
                String nome = partes[0].trim();
                LocalDateTime dataHora = LocalDateTime.parse(partes[1].trim());
                String endereco = partes[2].trim();
                String categoria = partes[3].trim();
                String descricao = partes[4].trim();

                Evento ev = new Evento(nome, dataHora, endereco, categoria, descricao);
                eventos.add(ev);
            }
        }
        System.out.println("Carregados " + eventos.size() + " eventos do arquivo events.data");
    } catch (IOException | DateTimeParseException e) {
        System.out.println("Arquivo events.data não foi encontrado. Iniciando vazio.");
    }
    }
    private static void salvarEventos() {
    try (BufferedWriter writer = new BufferedWriter(new FileWriter("events.data"))) {
        for (Evento ev : eventos) {
            String linha = ev.getNome() + "|" +
                           ev.getDataHora() + "|" +
                           ev.getEndereco() + "|" +
                           ev.getCategoria() + "|" +
                           ev.getDescricao();
            writer.write(linha);
            writer.newLine();
        }
        System.out.println("Eventos salvos em events.data!");
    } catch (IOException e) {
        System.out.println("Erro ao salvar: " + e.getMessage());
    }

}
}
