import java.util.ArrayList;
import java.util.List;

public class ClustePrioridade {

    int tam;
    List<Cluster> clusters = new ArrayList<>();
    HeapBinariaMinima dist;


    public ClustePrioridade (int nEntradas){

        
        //O for tem que ser aqui, se for dentro da função o return não funcione
        //por algum motivo do while dentro da função so retorna 1 vez, gerando erro
        for(int i = 0; i < nEntradas; i++){
            clusters.add(geraCluster(nEntradas));
        }
        
        //O(n^2)
        dist = calcDist(clusters);

        //processo de juntar clusters
        do{
            //junta clusters mais proximos
            Distancia minDist = dist.min();
            Cluster cluster = new Cluster(minDist.getC1(), minDist.getC2()); //O(n)

            //remove clusters que foram transformados em 1, O(n) tudo
            clusters.remove(minDist.getC1()); 
            clusters.remove(minDist.getC2());
            dist = removeDist(dist, minDist);


            //calcula distancia de somente este cluster com os outros e adiciona, O(n log n)
            for(int i = 0; i < clusters.size(); i++){
                dist.insere(new Distancia(cluster, clusters.get(i)));
            }

            clusters.add(cluster);

  
           
           /* if (clusters.size() == 1) {
                cluster.mostra();
            }
                */

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
    
    //O(n)
    public HeapBinariaMinima removeDist(HeapBinariaMinima dist, Distancia minDist){
        
        // Cria um vetor para armazenar as distâncias filtradas
        Distancia[] vetorOriginal = dist.getVetor();
        Distancia[] vetDistancias = new Distancia[dist.getN()];
        int indice = 0;

      // Percorre o vetor original e não adiciona se um dos cluster usados, menores distancias, estiverem
     for (int i = 1; i <= dist.getN(); i++) {
         Distancia distOriginal = vetorOriginal[i];
            if (!(distOriginal.getC1().equals(minDist.getC1()) || distOriginal.getC1().equals(minDist.getC2())
                || distOriginal.getC2().equals(minDist.getC1()) || distOriginal.getC2().equals(minDist.getC2()))) {
               vetDistancias[indice++] = distOriginal;
          }
     }

     if (indice == 0) {
        return new HeapBinariaMinima(1); 
    }

    // Retorna heap sem as distancias usadas
    return new HeapBinariaMinima(dist.getN(), indice, vetDistancias);


    }
}