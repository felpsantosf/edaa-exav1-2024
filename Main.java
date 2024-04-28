import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

class pais {
    private String nome;
    private double tvAudiencia;

    public pais(String nome, double tvAudiencia) {
        this.nome = nome;
        this.tvAudiencia = tvAudiencia;
    }

    public String getnome() {
        return nome;
    }

    public double gettvAudiencia() {
        return tvAudiencia;
    }
}

public class Main {
    public static void main(String[] args) {
        String csvFile = "fifa_countries_audience.csv";
        List<pais> paises = new ArrayList<>();

        
        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            String linha;
            br.readLine(); 
            while ((linha = br.readLine()) != null) {
                String[] dados = linha.split(",");
                String nome = dados[0];
                double tvAudiencia = Double.parseDouble(dados[3]);
                paises.add(new pais(nome, tvAudiencia));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    
        Scanner scanner = new Scanner(System.in);
        int opcao;

        do {
            System.out.println("\nDigite a opção desejada:");
            System.out.println("[1] Ordenar por Ordem Alfabética");
            System.out.println("[2] Ordenar por audiência TV");
            System.out.println("[3] Encerrar");

            opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                    ordemAlfabetica(paises);
                    break;
                case 2:
                    ordemAudiencia(paises);
                    break;
                case 3:
                    System.out.println("Encerrando o programa...");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        } while (opcao != 3);
        
        scanner.close();
    }

 
    private static void ordemAlfabetica(List<pais> paises) {
        Collections.sort(paises, Comparator.comparing(pais::getnome));
        printpaises(paises);
    }


    private static void ordemAudiencia(List<pais> paises) {
        Collections.sort(paises, Comparator.comparing(pais::gettvAudiencia).reversed());
        printpaises(paises);
    }


    private static void printpaises(List<pais> paises) {
        for (pais pais : paises) {
            System.out.printf("País: %s ,Audiencia: %.1f%% \n", pais.getnome() , pais.gettvAudiencia());
        }
    }
}