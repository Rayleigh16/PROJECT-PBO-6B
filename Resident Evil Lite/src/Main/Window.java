package Main;

import GameState.GameStateManager;
import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferStrategy;
import javax.swing.JFrame;

//Mengimplementasikan interface Runnable untuk thread
public class Window implements Runnable,
KeyListener, MouseListener{
    public static final int WIDTH = 900;  //Lebar frame
    public static final int HEIGHT = 600; // tinggi frame
 
    private static Thread thread; //thread yang dijalankan
    private Canvas canvas; //menempatkan semua komponen game
    public static JFrame frame; //Frame / Window
    private Graphics g; //membuat grafis image, draw warna, draw tulisan dll
    public static boolean running = false; //kondisi game berjalan atau tidak
    private GameStateManager gsm;  //Asosiasi class GameStateManger
    
    
    private BufferStrategy bs;
    public static long averageFPS = 0; //Rata-rata FPS
    private final int FPS = 60; //target FramePerSeccond
    private static boolean suspend;
    public Window(){
        frame = new JFrame("Resident Evil Lite"); //Mengatur Judul frame
        frame.setSize(WIDTH, HEIGHT); //set Ukuran layar
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE); //fungsi untuk close proses
        
        frame.setLocationRelativeTo(null); //Posisi layar di tengah
        frame.setResizable(false); //Me-nonaktifkan resize frame
        frame.setVisible(true); //Memunculkan frame
        
        canvas = new Canvas(); //Membuat Object Kanvas baru
        
        //Memilih ukuran canvas yang sesuai dengan ukuran lebar dan tinggi frame
        canvas.setPreferredSize( 
              new Dimension(WIDTH,HEIGHT)
        );
        
        //Untuk selalu menjamin ukuran kanvas sesuai yang diinginkan
        canvas.setMaximumSize( 
              new Dimension(WIDTH, HEIGHT)
        );
        canvas.setMinimumSize(
              new Dimension(WIDTH, HEIGHT)
        );
        canvas.setFocusable(true); //delay input
        frame.add(canvas); // menambahkan kanvas di dalam frame
        frame.pack(); // menyesuaikan ukuran frame berdasarkan ukuran kanvas
       
    }
     //method inisialisai
    private void init(){
       
        
       canvas.addKeyListener(this); //Mengatifkan fungsi input Keyboard pada Canvas
       canvas.addMouseListener(this); // Mengaktifkan fungsi input mouse pada Canvas
      
    }
    //method update grafis, gerakan object player,zombie,dll
    private void update(){
        gsm.update();
      
    }
    //method draw smua komponen di atas canvas
    private void render(){
        
        /* menciptakan tempat untuk gambar yang akan ditampilkan di belakang layar
           kemudian ditampilkan */
        bs = canvas.getBufferStrategy();
        if(bs == null){
            canvas.createBufferStrategy(3); //3 "screens"
            return;
        }
        //Menghubungkan Buffer dan Graphics kemudian mendapatkan graphics gambar
        g = bs.getDrawGraphics();
        
        //draw
        gsm.draw(g); // Pemanggilan method draw() dari oject gameStateManager
        //end
        
        g.dispose(); //Menghapus graphics yang tidak digunakan
        bs.show();//Menukar buffers
    }

    //start() akan menjalankan thread
    private void start(){
        //Konstruktor Thread mengambil parameter class mana yang 
        //akan di jalankan, dalam hal ini adalah class Window
        //oleh karena itu menggunakan keyword this
        if(running)
            
            return;
            running = true;
            
        thread = new Thread(this);
        thread.start(); //Method start() ini akan menjalankan method run()
         
    }
   //run() adalah method abstract yang harus diimplentasikan
    //ketika class mengimplementasikan Runnable
    @Override
    public void run() {
       suspend =false;
       init();
       
       double timePerTick = 1000000000/FPS;
       double delta = 0;
       long now;
       long lastTime = System.nanoTime(); // menyimpan waktu akhir eksekusi program
       long ticks = 0;
       long timer = 0;
       gsm = new GameStateManager();
        System.out.println("Last Time"+lastTime);
       while(running){
          
           now = System.nanoTime(); // menyimpan waktu sekarang eksekusi program
           delta += (now - lastTime)/timePerTick; //menghitung waktu eksekusi program dlm detik
           timer += now - lastTime;  //menghitung waktu eksekusi program dlm nano detik
           lastTime = now;
           if(delta >= 1){
               
               update();
                render();
               delta --;
               ticks ++;
               suspend();
              if(delta >= 10){
                  delta = 0;
              }
           }
           if(timer >= 1000000000){
               averageFPS = ticks;
               ticks = 0;
               timer = 0;
           }
       }
     
    }
    
    public static void suspend(){
        while(suspend){
        try {
            Thread.sleep(200);
        } catch (Exception ex) {
           ex.printStackTrace();
        }
        }
        
    }
    //method yg digunakan untuk merequest suspend
    public static void suspendRequest(){
        suspend = true;
    }
      //method yg digunakan untuk resume suspend
    public static void resumeSuspend(){
        suspend = false;
    }
    //Method Utama
    public static void main(String[] args) {
       new Window().start(); //Membuat object Window dan Melakukan Pemanggilan method start()
       
    }
    @Override
    public void keyTyped(KeyEvent ke) {}
    
    //Method yang berjalan ketika tombol Keyboard di tekan/pressed
    @Override

    public void keyPressed(KeyEvent ke) {
      gsm.keyPressed(ke.getKeyCode());
    }
  //Method yang berjalan ketika tombol Keyboard di lepas/realease
    @Override
  
    public void keyReleased(KeyEvent ke) {
       gsm.keyReleased(ke.getKeyCode());
    }

    @Override
    public void mouseClicked(MouseEvent me) {
    }
    //Method yang berjalan ketika tombol Mouse di tekan/pressed
    @Override
    public void mousePressed(MouseEvent me) {
        gsm.mousePressed(me.getButton());
    }

    @Override
    public void mouseReleased(MouseEvent me) {
        gsm.mouseReleased(me.getButton());
    }

    @Override
    public void mouseEntered(MouseEvent me) {}

    @Override
    public void mouseExited(MouseEvent me) {}
}
