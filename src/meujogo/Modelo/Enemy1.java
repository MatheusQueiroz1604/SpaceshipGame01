package meujogo.Modelo;

import java.awt.Image;
import java.awt.Rectangle;
import javax.swing.ImageIcon;

public class Enemy1 {
    private Image imagem;
    private int x, y;
    private int largura, altura;
    private boolean isVisible;
    
    //private static final int LARGURA = 938;
    private static int VELOCIDADE = 3;
    
    public Enemy1 (int x,int y){
        this.x = x;
        this.y = y;
        isVisible=true;
    }
    public void load(){
        ImageIcon referencia = new ImageIcon("res//inimigo1.png");
        imagem = referencia.getImage();
        
        this.largura = imagem.getWidth(null);
        this.altura = imagem.getHeight(null);  
    }
    public void update(){
        this.x -= VELOCIDADE;
        //if (this.x > LARGURA){
        //    isVisible = false;
        //}
    }
    
    public Rectangle getBounds(){
        return new Rectangle(x,y,largura,altura);
    }

    public boolean isIsVisible() {
        return isVisible;
    }

    public void setIsVisible(boolean isVisible) {
        this.isVisible = isVisible;
    }

    public static int getVELOCIDADE() {
        return VELOCIDADE;
    }

    public static void setVELOCIDADE(int VELOCIDADE) {
        Enemy1.VELOCIDADE = VELOCIDADE;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Image getImagem() {
        return imagem;
    }
    
}
