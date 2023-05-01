
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class GameOfLife {

    public static void main(String[] args) {

        int pixelSizeCell = 10;

        int lengthGrid = 80;

        int generationNum = 1;

        float percentAlive;

        int continueValue;

        JFrame mainWindow = new JFrame("Game Of Life");

        mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        mainWindow.setAlwaysOnTop(true);
        

        mainWindow.setSize((pixelSizeCell * lengthGrid) + 15,(pixelSizeCell * lengthGrid) + 40);
        

        mainWindow.setLocation(0, 0);
    
        JFrame optionPane = new JFrame();

        optionPane.setAlwaysOnTop(true);
        optionPane.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        optionPane.setLocation(900,400);

        optionPane.setVisible(true);


        

        JOptionPane.showMessageDialog(optionPane, 
                "Welcome! This is the Game Of Life. You will be asked for the proportion of living cells"
                + " to start with. \nMake sure to enter a decimal between 0 - 1.\n" 
                + "Alive cells will be colored green and dead cells will be colored black.\n" 
        );
        

        String percentAliveString = JOptionPane.showInputDialog(optionPane,
                "Enter a decimal (from 0 to 1)-- this is the probability of a cell being alive",
                "Proportion of cells alive",
                3
        );
        

        percentAlive = Float.parseFloat(percentAliveString);
        


        int tempGrid[][] = createGrid(lengthGrid, percentAlive);
        

        DrawingPanel myPanel = new DrawingPanel(tempGrid,lengthGrid,pixelSizeCell);

        mainWindow.add(myPanel);
        

        mainWindow.setTitle("The Game Of Life -- Generation #" + generationNum);
        

        mainWindow.setVisible(true);

        do {


            continueValue = JOptionPane.showConfirmDialog(optionPane, 
                    "Would you like to create another generaton?", 
                    "Keep going?", 
                    JOptionPane.YES_NO_OPTION,
                    3
            );
            

            if (continueValue == 1) {
                break;
            }
            

            generationNum ++;

            tempGrid = nextGeneration(tempGrid);


            mainWindow.remove(myPanel);

            DrawingPanel myPanel2 = new DrawingPanel(tempGrid,lengthGrid,pixelSizeCell);

            mainWindow.add(myPanel2);

            mainWindow.setTitle("The Game Of Life -- Generation #" + generationNum);

            mainWindow.setVisible(true);
            
            

        } while (continueValue == 0);
        

        JOptionPane.showMessageDialog(optionPane,
                "Thanks for playing! \n"
                + "You created " + generationNum + " generations. \n" );
        

        System.exit(0);

    }

    

    static int[][] nextGeneration(int[][] nextGrid) {
   
        int future[][] = new int[nextGrid.length][nextGrid.length];

   
        for (int i = 0; i < nextGrid.length; i++) {
            for (int j = 0; j < nextGrid.length; j++) {
                int lifeSum = 0;


                if ((i == 0 || i == (nextGrid.length - 1)) || (j == 0 || j == (nextGrid.length - 1))) {
 
                    future[i][j] = 0;
                } 
                else {
                    for (int m = -1; m <= 1; m++) {
                        for (int n = -1; n <= 1; n++) {
                            lifeSum += nextGrid[i + m][j + n];
                        }
                    }


                    lifeSum = lifeSum - nextGrid[i][j];


                    if (nextGrid[i][j] == 0) {

                        if (lifeSum == 3) {
                            future[i][j] = 1;
                        } else 
                        {
                            future[i][j] = 0;
                        }

                    } 
                    else {
                       
                        if (lifeSum <= 1) {
                            future[i][j] = 0;
                        } 
                        else if (lifeSum < 4) {
                            future[i][j] = 1;
                        } 
                        else {
                            future[i][j] = 0;
                        }
                    }

                }

            }
        }

    
        return future;
    } 


    static int[][] createGrid(int sideLength, float percentAlive) {

        int grid[][] = new int[sideLength][sideLength];

        for (int m = 0; m < sideLength; m++) {
            for (int n = 0; n < sideLength; n++) {
                if (m == 0 || m == (sideLength - 1) || n == 0 || n == (sideLength - 1)) {
                    grid[m][n] = 0;
                } else {
                    if (Math.random() < percentAlive) {
                        grid[m][n] = 1;
                    } else {
                        grid[m][n] = 0;
                    }
                }
            }
        }


        return grid;

    }

} 