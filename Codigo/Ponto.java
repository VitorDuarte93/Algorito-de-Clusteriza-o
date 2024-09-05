import java.util.Random;

public class Ponto {
    private float x, y;

    Random r = new Random();

    
    
    /* Ponto Inicial */
    //Complexidade O(1)
    public Ponto(int lmt)
    {   
        this.x = r.nextInt(lmt) + 1;
        this.y = r.nextInt(lmt) + 1;
    }



    /* Demais pontos */
    //Complexidade O(1)
    public Ponto(float x, float y)
    {
        this.x = x;
        this.y = y;
    }



    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }


    public String toString(){
        return "(" + x + "," + y + ")";
    }

    
}