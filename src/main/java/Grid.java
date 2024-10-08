import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

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
                if (j == 2 || j == 5) {
                    System.out.print("| ");
                }
            }
            System.out.println();
            if (i == 2 || i == 5) {
                System.out.println("---------------------");
            }
        }
    }
    public void setGrid(ArrayList<ArrayList<Integer>> values){
        grid = values;
    }
    public void setGrid(int[][] values){
        grid.clear();
        for (int[] row: values){
            ArrayList<Integer> temp_array = new ArrayList<>();
            for (int value: row){
                temp_array.add(value);
            }
            grid.add(temp_array);
        }
    }
    public boolean isGridValid(){
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (getPossibleValuesForCell(i, j).isEmpty()){
                    return false;
                }
            }
        }
        for (int i = 0; i < 9; i++) {
            if (containsDuplicate(getRow(i))){
                return false;
            }
            if (containsDuplicate(getColumn(i))){
                return false;
            }
            if (containsDuplicate(getThreeByThreeBox(i / 3, i % 3))){
                return false;
            }
        }
        return true;
    }
    public static boolean containsDuplicate(ArrayList<Integer> listIn){
        ArrayList<Integer> newList = new ArrayList<>();
        for (int i : listIn){
            if (i != 0){
                newList.add(i);
            }
        }
        HashSet<Integer> set = new HashSet<>(newList);
        return set.size() != newList.size();
    }
    public void setCell(int rowIn, int columnIn, int value){
        grid.get(rowIn).set(columnIn, value);
    }
    public int getCell(int rowIn, int columnIn){
        return grid.get(rowIn).get(columnIn);
    }
    public ArrayList<Integer> getColumn(int columnIn){
        ArrayList<Integer> result = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            result.add(getCell(i, columnIn));
        }
        return result;
    }
    public void setColumn(int columnIn, ArrayList<Integer> values){
        for (int i = 0; i < 9; i++) {
            grid.get(i).set(columnIn, values.get(i));
        }
    }
    public ArrayList<Integer> getRow(int rowIn){
        return grid.get(rowIn);
    }
    public void setRow(int rowIn, ArrayList<Integer> values){
        for (int i = 0; i < 9; i++) {
            grid.get(rowIn).set(i, values.get(i));
        }
    }
    public void setRow(int rowIn, int[] values){
        for (int i = 0; i < 9; i++) {
            grid.get(rowIn).set(i, values[i]);
        }
    }
    public ArrayList<Integer> getThreeByThreeBox(int rowIn, int columnIn){
        ArrayList<Integer> result = new ArrayList<>();
        int[] rows = {rowIn*3, rowIn*3+1, rowIn*3+2};
        int[] columns = {columnIn*3, columnIn*3+1, columnIn*3+2};
        for (int i: rows){
            for (int j : columns){
                result.add(getCell(i, j));
            }
        }
        return result;
    }
    public ArrayList<Integer> getPossibleValuesForCell(int rowIn, int columnIn){
        ArrayList<Integer> result = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9));
        //current cell appears three times so will be removed minimum of three times
        result.add(getCell(rowIn, columnIn));
        result.add(getCell(rowIn, columnIn));
        result.add(getCell(rowIn, columnIn));
        for (int i : getRow(rowIn)){
            if(result.contains(i)){
                result.remove(Integer.valueOf(i));
            }
        }
        for (int i : getColumn(columnIn)){
            if(result.contains(i)){
                result.remove(Integer.valueOf(i));
            }
        }
        for (int i : getThreeByThreeBox(rowIn / 3, columnIn / 3)){
            if(result.contains(i)){
                result.remove(Integer.valueOf(i));
            }
        }
        return result;
    }
    public void solve_cell(int rowIn, int columnIn){
        ArrayList<Integer> possible_values = getPossibleValuesForCell(rowIn, columnIn);
        if (possible_values.size() == 1){
            setCell(rowIn, columnIn, possible_values.getFirst());
        }
    }
    public void getInputFromTextFile(String source){
        try {
            File gridFile = new File("src/main/java/grid_to_solve.txt");
            Scanner scanner = new Scanner(gridFile);
            int row = 0;
            int column = 0;
            int value;
            while (row < 9){
                value = scanner.nextInt();
                setCell(row, column, value);
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
    }
    public boolean isValidSolution() {
        HashSet<Integer> numbersOneToNine = new HashSet<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9));
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (0 >= getCell(i, j) && getCell(i, j) > 9) {
                    return false;
                }
            }
            HashSet<Integer> columnSet = new HashSet<>(getColumn(i));
            HashSet<Integer> rowSet = new HashSet<>(getRow(i));
            HashSet<Integer> threeByThreeSet = new HashSet<>(getThreeByThreeBox(i / 3, i % 3));
            if (!columnSet.equals(numbersOneToNine)){
                return false;
            }
            if (!rowSet.equals(numbersOneToNine)){
                return false;
            }
            if (!threeByThreeSet.equals(numbersOneToNine)){
                return false;
            }
        }
        return true;
    }
    public boolean solveGrid(){
        return false;
    }
}
