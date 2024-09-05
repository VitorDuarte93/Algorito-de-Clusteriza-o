public class Cluster implements Comparable<Cluster>{

    private Ponto pontos[]; //vetor de ponto ao inves de P1 e P2, para os clusters que ja utilizam clusters e terão varios pontos dentro de si
    private Ponto centroide;
    private int tam;

    private Arvbin<Cluster> registro;

    // Complexidade O(1)
    // Construtor para transformas todos os pontos em clusters, para poder utilizar heap
    public Cluster (Ponto p){
        pontos = new Ponto[1]; 
        pontos[0] = p;
        tam = 1;
        calcCentroide();

        registro = new Arvbin<>(this);
    }

    // Complexidade O(n)
    // Construtor para os clusters que utilizam outros clusters
    public Cluster (Cluster c1, Cluster c2){

        tam = c1.tam + c2.tam;
        pontos = new Ponto[tam];
        
        for (int i = 0; i < c1.tam; i++) {
            pontos[i] = c1.pontos[i];            
        }

        for (int cont = 0, i = c1.tam; cont < c2.tam; i++, cont++){
            pontos[i] = c2.pontos[cont];            
        }

        calcCentroide();

        registro = new Arvbin<Cluster>(this, c1.registro, c2.registro);

        

    }



    public void calcCentroide(){
        float pX = 0;
        float pY = 0;
            
        for(int i = 0; i < this.tam; i++){
            pX += pontos[i].getX();
            pY += pontos[i].getY();
        }

        float mediaPX = pX/this.tam;
        float mediaPY = pY/this.tam;

        this.centroide = new Ponto(mediaPX, mediaPY);

     }

    public Ponto getCentroide() {
        return centroide;
    }

    public int getTam() {
        return tam;
    }



    /* Complexidade O(1) */
    public int compareTo(Cluster c){
        
        if (this.centroide.getX() == c.centroide.getX() && this.centroide.getY() == c.centroide.getY())
			return 0;
		if (this.centroide.getX() > c.centroide.getX() && this.centroide.getY() > c.centroide.getY())
			return 1;
		else
			return -1;
    }


    public String toString(){
        return centroide.toString();
    }

    public void mostra(){
        registro.mostra();
    }
    
 }

