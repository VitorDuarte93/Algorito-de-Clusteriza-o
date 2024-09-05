public class Distancia implements Comparable<Distancia> {

    private Cluster c1;
    private Cluster c2;
    private double distancia;

    //Complexidade O(1)
    public Distancia (Cluster c1, Cluster c2){
        this.c1 = c1;
        this.c2 = c2;

        this.distancia = calcDist(c1, c2);
        
    }   

    //Complexidade O(1)    
    public double calcDist(Cluster c1, Cluster c2){
        
        float diferencaX = c1.getCentroide().getX() - c2.getCentroide().getX();
        float diferencaY = c1.getCentroide().getY() - c2.getCentroide().getY();

        return Math.sqrt(Math.pow(diferencaX, 2) + Math.pow(diferencaY, 2));
        
    }  
    
    
    /* Complexidade O(1) */
    public int compareTo(Distancia d) {
        if (distancia == d.distancia)
            return 0;
        else if (distancia > d.distancia)
            return 1;
        else
            return -1;
    }

    //necessario para realizar clusterização
    public Cluster getC1(){
        return this.c1;
    }

    public Cluster getC2(){
        return this.c2;
    }
}
