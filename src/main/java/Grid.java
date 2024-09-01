import java.util.ArrayList;

public class Grid {
    ArrayList<ArrayList<Integer>> grid;
    public Grid(){
        grid = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            ArrayList<Integer> row = new ArrayList<>();
            for (int j = 0; j < 9; j++) {
                row.add(0);
            }
            grid.add(row);
        }
    }

    public void printGrid(){
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print(grid.get(i).get(j) + " ");
            }
            System.out.println();
        }
    }
}
