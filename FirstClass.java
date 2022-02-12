import javax.swing.*;
import java.awt.*;
import java.awt.geom.Line2D;
import java.util.*;

import static java.lang.Thread.*;

class FirstClass {

    public static void main(String[] args) {

        Runnable r = new Runnable() {
            public void run() {
                int width = 400;
                int height =400;

                LineComponent lineComponent = new LineComponent(width,height);
//                for (int ii=0; ii<30; ii++) {
//                    lineComponent.addLine();
//                }

                int gap=80;
                lineComponent.addLine(width/2,height-gap,width/2,gap);
                lineComponent.addLine(gap,height/2,width-gap,height/2);
                lineComponent.addLine(width/2,height-gap,width-gap,height-gap);
                lineComponent.addLine(width-gap,height/2,width-gap,gap);
                lineComponent.addLine(gap,gap,width/2,gap);
                lineComponent.addLine(gap,height/2,gap,height-gap);

                JOptionPane.showMessageDialog(null, lineComponent);
            }
        };
        SwingUtilities.invokeLater(r);
    }
}

class LineComponent extends JComponent {

    ArrayList<Line2D.Double> lines;
    Random random;

    LineComponent(int width, int height) {
        super();
        setPreferredSize(new Dimension(width,height));
        lines = new ArrayList<Line2D.Double>();
        random = new Random();
    }

    public void addLine() {
        int width = (int)getPreferredSize().getWidth();
        int height = (int)getPreferredSize().getHeight();
        Line2D.Double line = new Line2D.Double(
                random.nextInt(width),
                random.nextInt(height),
                random.nextInt(width),
                random.nextInt(height)
        );
        lines.add(line);
        repaint();
    }

    public void addLine(int width1,int height1,int width2,int height2){
        int width = (int)getPreferredSize().getWidth();
        int height = (int)getPreferredSize().getHeight();
        if(!(width>width1 && width>width2 && height>height1 && height>height2)) {
            return;
        }
        Line2D.Double line = new Line2D.Double(width1,height1,width2,height2);
        lines.add(line);
        repaint();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

//        g2.setColor(Color.blue);
//        g2.fillRect(0,0,400,200);
//
//        g2.setColor(Color.yellow);
//        g2.fillRect(0,200,400,200);

        g2.setColor(Color.RED);
        g2.fillRect(0, 0, getWidth(), getHeight());
        g2.setColor(Color.white);
        g2.fillOval(0, 0, getWidth()-10, getHeight()-10);
        Dimension d = getPreferredSize();
        g2.setColor(Color.black);
        g2.setStroke(new BasicStroke(16));
        for (Line2D.Double line : lines) {
            g2.drawLine(
                    (int)line.getX1(),
                    (int)line.getY1(),
                    (int)line.getX2(),
                    (int)line.getY2()
            );
        }
        while (true){
            g2.rotate(2);
            //Thread.sleep(4000);
        }
    }
}
