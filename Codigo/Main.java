//Para gerar saida dos dados em arquivo de texto
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Main{
    public static void main(String[] args){

        boolean naive = false;
        final int nExecucoes = 10;

        int [] teste = new int [11];
        teste [0] = 10;
        teste [1] = 20;
        teste [2] = 30;
        teste [3] = 40;
        teste [4] = 50;
        teste [5] = 100;
        teste [6] = 200;
        teste [7] = 500;
        teste [8] = 1000;
        teste [9] = 5000;
        teste [10] = 10000;
        //teste [11] = 20000;
        //teste [12] = 50000;
        //teste [13] = 100000;

      if (naive == false) {
        
        for (int i = 0; i < teste.length; i++) {

            String fileName = "tempo_execucao_HEAP " + teste[i] + " Pontos.txt";
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))){

           
            for(int j = 0; j < nExecucoes; j++){

                long startTime = System.nanoTime(); 

                new ClustePrioridade(teste[i]);

                long endTime = System.nanoTime();

                double ms = (endTime - startTime) / 1_000_000;
                double sec = (endTime - startTime) / 1_000_000_000.0;

                String secRounded = String.format("%.3f", sec);
                

                writer.write("Tempo " + (j + 1) + ": " + ms + " Ms" + " | " + "Tempo " + (j + 1) + ": " + secRounded+ " Sec");
                writer.newLine();

                System.out.println("Execução HEAP " + (j + 1) + " do teste de " + teste[i] + "pontos - CONCLUIDA");
            }
           
            }catch (IOException e) {
         e.printStackTrace(); 
        }
            
        }
        
      }

naive = true;


      if (naive == true) {
        
        for (int i = 0; i < teste.length; i++) {

            String fileName = "tempo_execucao_NAIVE " + teste[i] + " Pontos.txt";
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))){

           
            for(int j = 0; j < nExecucoes; j++){

                long startTime = System.nanoTime(); 

                new ClustePrioridadeNaive(teste[i]);

                long endTime = System.nanoTime();

                long ms = (endTime - startTime) / 1_000_000;
                double sec = (endTime - startTime) / 1_000_000_000.0;

                String secRounded = String.format("%.3f", sec);
                

                writer.write("Tempo " + (j + 1) + ": " + ms + " Ms" + " | " + "Tempo " + (j + 1) + ": " + secRounded+ " Sec");
                writer.newLine();

                System.out.println("Execução NAIVE " + (j + 1) + " do teste de " + teste[i] + "pontos - CONCLUIDA");
            }
           
            }catch (IOException e) {
         e.printStackTrace(); 
        }
            
        }
        
      }

    }
}