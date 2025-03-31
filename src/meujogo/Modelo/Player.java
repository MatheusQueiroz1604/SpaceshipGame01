package meujogo.Modelo;

import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.Timer;

public class Player implements ActionListener{
    private int x,y;
    private int dx,dy;
    private Image imagem;
    private int altura, largura;
    private List <Tiro> tiros;
    private boolean isVisible, isTurbo;
    private Timer timer;
    
    private static int VELOCIDADE = 1;
    
    public Player(){
        this.x = 100;
        this.y = 100;
        isVisible=true;
        isTurbo=false;
        
        tiros = new ArrayList<Tiro>();
        
        timer = new Timer(5000,this);
        timer.start();
    }
    @Override
    public void actionPerformed(ActionEvent e){
        if(isTurbo==true){
            turbo();
            isTurbo=false;
        } if (isTurbo==false){
            load();
        }
    }
    public void load(){
        ImageIcon referencia = new ImageIcon("res//nave.png");
        imagem = referencia.getImage();
        altura = imagem.getHeight(null);
        largura = imagem.getWidth(null);
    }
    public void update(){
            x += dx;
            y += dy;
    }
    
    public void tiroBasico(){
        this.tiros.add(new Tiro(x+largura, y+(altura/2)));
    }
    
    public void turbo(){
        isTurbo=true;
        ImageIcon referencia = new ImageIcon("res//naveTurbo.png");
        imagem = referencia.getImage();
    }
    
    public Rectangle getBounds(){
        return new Rectangle(x,y,largura,altura);
    }
    
    public void keyPressed(KeyEvent tecla){
        int codigo = tecla.getKeyCode();
        if (codigo==KeyEvent.VK_SPACE){
            turbo();
        }
        if (codigo==KeyEvent.VK_Z){
            if(isTurbo==false){
                tiroBasico();
            }
        }
        if (codigo==KeyEvent.VK_UP){
            dy=-3;
        }
        if (codigo==KeyEvent.VK_DOWN){
            dy=3;
        }
        if (codigo==KeyEvent.VK_LEFT){
            dx=-3;
        }
        if (codigo==KeyEvent.VK_RIGHT){
            dx=3;
        }
    }
    public void keyRelease(KeyEvent tecla){
        int codigo = tecla.getKeyCode();
        if (codigo==KeyEvent.VK_UP){
            dy=0;
        }
        if (codigo==KeyEvent.VK_DOWN){
            dy=0;
        }
        if (codigo==KeyEvent.VK_LEFT){
            dx=0;
        }
        if (codigo==KeyEvent.VK_RIGHT){
            dx=0;
        }
    }

    public boolean isIsVisible() {
        return isVisible;
    }

    public void setIsVisible(boolean isVisible) {
        this.isVisible = isVisible;
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

    public List<Tiro> getTiros() {
        return tiros;
    }

    public boolean isTurbo() {
        return isTurbo;
    }
}
