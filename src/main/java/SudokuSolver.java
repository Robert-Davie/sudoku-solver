import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class SudokuSolver {
    public static void main(String[] args) {
        Grid grid = new Grid();
        grid.getInputFromTextFile("src/main/java/grid_to_solve.txt");
        grid.printGrid();
    }
}
