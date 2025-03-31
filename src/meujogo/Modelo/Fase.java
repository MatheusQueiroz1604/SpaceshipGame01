package meujogo.Modelo;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Fase extends JPanel implements ActionListener{
    
    private Image fundo;
    private Player player;
    private Timer timer;
    private List <Enemy1> enemy1;
    private List <Stars> stars;
    private boolean emJogo;

    public Fase(){
        ImageIcon referencia = new ImageIcon("res//background.png");
        setFocusable(true);
        setDoubleBuffered(true);
        fundo = referencia.getImage();
        
        player = new Player();
        player.load();
        
        addKeyListener(new TecladoAdapter());
        
        timer = new Timer(5,this);
        timer.start();
        
        inicializaInimigos();
        inicializaStars();
        emJogo = true;
    }
    
    public void inicializaInimigos(){
        int coordenadas[] = new int[100];
        enemy1 = new ArrayList<Enemy1>();
        
        for (int i = 0 ; i < coordenadas.length ; i++){
            int x = (int)(Math.random() * 12000+1024);
            int y = (int)(Math.random() * 650+30);
            enemy1.add(new Enemy1(x,y));
        }
    }
    
    public void inicializaStars(){
        int coordenadas[] = new int[100];
        stars = new ArrayList<Stars>();
        
        for (int i = 0 ; i < coordenadas.length ; i++){
            int x = (int)(Math.random() * 1024+0);
            int y = (int)(Math.random() * 768+30);
            stars.add(new Stars(x,y));
        }
    }
    public void paint(Graphics g){
        Graphics2D graficos = (Graphics2D) g;
            if(emJogo==true){
        graficos.drawImage(fundo, 0, 0, null);
        
        for (int i=0;i<stars.size();i++){
            Stars q = stars.get(i);
            q.load();
            graficos.drawImage(q.getImagem(), q.getX(), q.getY(), this);
        }
        
        graficos.drawImage(player.getImagem(), player.getX(), player.getY(), this);
        
        List<Tiro> tiros = player.getTiros();
        for (int i=0;i<tiros.size();i++){
            Tiro m = tiros.get(i);
            m.load();
            graficos.drawImage(m.getImagem(), m.getX(), m.getY(), this);
        }
        
        for(int o=0;o<enemy1.size();o++){
            Enemy1 in = enemy1.get(o);
            in.load();
            graficos.drawImage(in.getImagem(), in.getX(), in.getY(), this);
            }
        } else {
                ImageIcon fimJogo = new ImageIcon("res//fimJogo.png");
                graficos.drawImage(fimJogo.getImage(), 0, 0, null);
            }
        g.dispose();
    }
    
    /**
     *
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e){
        player.update();
        
        if(player.isTurbo()){
            timer.setDelay(2);
        }
        if(player.isTurbo()==false){
            timer.setDelay(5);
        }
        
        for  (int i=0;i<stars.size();i++){
            Stars on = stars.get(i);
            if(on.isIsVisible()){
                on.update();
            } else {
                stars.remove(i);
            }
        }
        List<Tiro> tiros = player.getTiros();
        for (int i=0;i<tiros.size();i++){
            Tiro m = tiros.get(i);
                if (m.isIsVisible()){
                    m.update();
                    if(player.isTurbo()){
                        tiros.get(i).setVELOCIDADE(1);
                    }
                    if(player.isTurbo()==false){
                        tiros.get(i).setVELOCIDADE(4);
                    }
                } else {
                    tiros.remove(i);
                }
        }
        
        for(int o=0;o<enemy1.size();o++){
            Enemy1 in = enemy1.get(o);
                if(in.isIsVisible()){
                    in.update();
                } else {
                    enemy1.remove(o);
                }
        }
        checarColisoes();
        repaint();
    }
    
    public void checarColisoes(){
        Rectangle formaNave = player.getBounds();
        Rectangle formaEnemy1;
        Rectangle formaTiro;
        
        for(int i=0; i<enemy1.size();i++){
            Enemy1 tempEnemy1 = enemy1.get(i);
            formaEnemy1 = tempEnemy1.getBounds();
                if(formaNave.intersects(formaEnemy1)){
                    if(player.isTurbo()==true){
                        tempEnemy1.setIsVisible(false);
                    } else {
                        player.setIsVisible(false);
                        tempEnemy1.setIsVisible(false);
                        emJogo = false;
                    }
                }
        }
        List<Tiro> tiros = player.getTiros();
        for (int i=0;i<tiros.size();i++){
            Tiro tempTiro = tiros.get(i);
            formaTiro = tempTiro.getBounds();
            for (int j=0;j<enemy1.size();j++){
                Enemy1 tempEnemy1 = enemy1.get(j);
                formaEnemy1 = tempEnemy1.getBounds();
                if (formaTiro.intersects(formaEnemy1)){
                    tempEnemy1.setIsVisible(false);
                    tempTiro.setIsVisible(false);
                }
            }
        }
    }
        
        public class TecladoAdapter extends KeyAdapter {   
        @Override
        public void keyPressed(KeyEvent e){
            player.keyPressed(e);
        }
        @Override
        public void keyReleased(KeyEvent e){
            player.keyRelease(e);
        }
    }
}
