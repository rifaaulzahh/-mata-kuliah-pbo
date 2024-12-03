import java.awt.*;
public class WindowText extends Frame {
public void paint(Graphics g) {
setSize(300, 200);
setTitle(getClass().getName());
Font f = new Font( "Monospaced" , Font.BOLD, 16);
g.setFont(f);
g.drawString( "Hello ... ", 10 , 100);
g.drawString( "Salam kenal dari Frame ... ", 30 ,120);
}
public static void main(String[ ] args) {
WindowText coba = new WindowText();
coba.setSize(500, 300);
coba.setLocation(200, 100);
coba.setTitle("Window Text" );
coba.show();
}
}