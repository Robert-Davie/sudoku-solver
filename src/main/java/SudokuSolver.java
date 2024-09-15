import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class SudokuSolver {
    public static void main(String[] args) {
        Grid grid = new Grid();
        try {
            File gridFile = new File("src/main/java/grid_to_solve.txt");
            Scanner scanner = new Scanner(gridFile);
            int row = 0;
            int column = 0;
            int value;
            while (row < 9){
                value = scanner.nextInt();
                grid.setCell(row, column, value);
                column++;
                if (column >= 9){
                    column = 0;
                    row++;
                    scanner.nextLine();
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File Used to Print Grid Not Found");
            System.exit(1);
        }
        grid.printGrid();
    }
}
