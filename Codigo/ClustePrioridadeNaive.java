import java.util.ArrayList;
import java.util.List;

public class ClustePrioridadeNaive {

    int tam;
    List<Cluster> clusters = new ArrayList<>();
    HeapBinariaMinima dist;

    public ClustePrioridadeNaive (int nEntradas){

        
        //O for tem que ser aqui, se for dentro da função o return não funcione
        //por algum motivo do while dentro da função so retorna 1 vez, gerando erro
        for(int i = 0; i < nEntradas; i++){
            clusters.add(geraCluster(nEntradas));
        }
        
  

        //processo de juntar clusters
        do{
            //O(n^2)
            dist = calcDist(clusters);

            //junta clusters mais proximos
            Distancia minDist = dist.min();
            Cluster cluster = new Cluster(minDist.getC1(), minDist.getC2()); //O(n)

            //remove clusters que foram transformados em 1, O(n)
            clusters.remove(minDist.getC1()); 
            clusters.remove(minDist.getC2());

            clusters.add(cluster); //O(1)


        }while(clusters.size() > 1);

        
    }

    //Gerar pontos e cluster aleatorios
    // Complexidade O(n) 
    public Cluster geraCluster (int nEntradas) {
        // Usar "for" faz com que o "return" não seja reconhecido
        
            
            Ponto p = new Ponto(nEntradas);
            Cluster c = new Cluster(p);

            return c;     
        
        
    }

    //Complexidade O(n^2)
    public HeapBinariaMinima calcDist(List<Cluster> clusters){
        
        int qtdDist = (clusters.size() * (clusters.size() - 1)) / 2;  // quantidade de distancias a serem calculadas
        Distancia[] dist = new Distancia [qtdDist];


        int indiceVetor = 0;

        //calcula dist do primeiro para todos, segundo para todos, terceiro para todos...
        for (int i = 0; i < clusters.size() - 1; i++) {
            for (int j = i + 1; j < clusters.size(); j++) {
                dist[indiceVetor++] = new Distancia(clusters.get(i), clusters.get(j));
            }
        }

        return new HeapBinariaMinima(qtdDist, qtdDist, dist);
         
    }



    
    
}