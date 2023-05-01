
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;

public class DrawingPanel extends JPanel
{

    public int[][] paintedGrid;
 

    public int numOfCells;

    public int pixelSizeofSquare;

    public DrawingPanel(int[][] incomingGrid1, int numberOfCells, int pixelsSideCell) {
        this.paintedGrid = incomingGrid1;
        this.numOfCells = numberOfCells;
        this.pixelSizeofSquare = pixelsSideCell;
    }
    
    
    public void paintComponent(Graphics myPen) 
    {

        super.paintComponent(myPen);

        Graphics2D g2 = (Graphics2D) myPen;
        

        for(int x = 0; x < numOfCells * pixelSizeofSquare ; x = x + pixelSizeofSquare) 
        {
            for(int y = 0; y < numOfCells * pixelSizeofSquare; y = y + pixelSizeofSquare)
            {
                if(paintedGrid[x/pixelSizeofSquare][y/pixelSizeofSquare] == 0) 
                {
                    myPen.setColor(Color.black);
                }
                
                else 
                {
                    myPen.setColor(Color.green);
                }
                

                myPen.fillRect(y, x, pixelSizeofSquare, pixelSizeofSquare);
            }
        }
        
        

        g2.setStroke(new BasicStroke(2)); 

        g2.setColor(Color.BLACK);

        
        for(int m = 0; m  <=numOfCells; m ++) 
        {
       
            g2.drawLine(m*pixelSizeofSquare, 0, m*pixelSizeofSquare, this.getHeight());

        }
        
        for(int n = 0; n  <=numOfCells; n ++) 
        {
       
            g2.drawLine(0, n*pixelSizeofSquare, this.getWidth(), n *pixelSizeofSquare);

        }
        
        
        
    }
}