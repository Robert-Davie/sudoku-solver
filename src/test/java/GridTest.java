import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import static org.junit.jupiter.api.Assertions.*;

class GridTest {
    @Test
    void setFirstCellThree(){
        var grid = new Grid();
        grid.setCell(0, 0, 3);
        assertEquals(3, grid.grid.getFirst().getFirst());
    }
    @Test
    void getColumn(){
        var grid = new Grid();
        grid.setCell(0, 1, 2);
        grid.setCell(1, 1, 1);
        grid.setCell(2, 1, 7);
        grid.setCell(3, 1, 8);
        grid.setCell(4, 1, 9);
        grid.setCell(5, 1, 4);
        grid.setCell(6, 1, 5);
        grid.setCell(7, 1, 6);
        grid.setCell(8, 1, 3);
        ArrayList<Integer> expectedResult = new ArrayList<>(Arrays.asList(2, 1, 7, 8, 9, 4, 5, 6, 3));
        assertEquals(expectedResult, grid.getColumn(1));
    }
    @Test
    void setThirdColumn123456789(){
        var grid = new Grid();
        ArrayList<Integer> values = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9));
        grid.setColumn(2, values);
        assertEquals(values, grid.getColumn(2));
        assertEquals(1, grid.getCell(0, 2));
    }
    @Test
    void setFourthRow123456789(){
        var grid = new Grid();
        ArrayList<Integer> values = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9));
        grid.setRow(4, values);
        assertEquals(values, grid.getRow(4));
        assertEquals(8, grid.getCell(4, 7));
    }
    @Test
    void getThreeByThreeBox(){
        var grid = new Grid();
        ArrayList<Integer> values0 = new ArrayList<>(Arrays.asList(0, 0, 0, 1, 2, 3, 0, 0, 0));
        ArrayList<Integer> values1 = new ArrayList<>(Arrays.asList(0, 0, 0, 4, 5, 6, 0, 0, 0));
        ArrayList<Integer> values2 = new ArrayList<>(Arrays.asList(0, 0, 0, 7, 8, 9, 0, 0, 0));
        ArrayList<Integer> expected = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9));
        grid.setRow(0, values0);
        grid.setRow(1, values1);
        grid.setRow(2, values2);
        assertEquals(expected, grid.getThreeByThreeBox(0, 1));
    }
    @Test
    void getPossibleValueForCellOnlyNine(){
        var grid = new Grid();
        ArrayList<Integer> values0 = new ArrayList<>(Arrays.asList(0, 1, 2, 0, 0, 0, 0, 0, 0));
        ArrayList<Integer> values1 = new ArrayList<>(Arrays.asList(3, 4, 5, 0, 0, 0, 0, 0, 0));
        ArrayList<Integer> values2 = new ArrayList<>(Arrays.asList(0, 3, 6, 7, 8, 0, 0, 0, 0));
        grid.setRow(0, values0);
        grid.setRow(1, values1);
        grid.setColumn(0, values2);
        ArrayList<Integer> expected = new ArrayList<>();
        expected.add(9);
        assertEquals(expected, grid.getPossibleValuesForCell(0, 0));

    }
    @Test
    void checkGridInvalidAsCellHasNoPossibleValues(){
        var grid = new Grid();
        int[] values0 = {0, 0, 3, 4, 5, 6, 7, 8, 9};
        int[] values1 = {1, 2, 0, 0, 0, 0, 0, 0, 0};
        grid.setRow(0, values0);
        grid.setRow(1, values1);
        assertFalse(grid.isGridValid());
    }
    @Test
    void checkGridValidAsCellHasPossibleValues(){
        var grid = new Grid();
        int[] values0 = {0, 0, 0, 4, 5, 6, 7, 8, 9};
        int[] values1 = {1, 2, 0, 0, 0, 0, 0, 0, 0};
        grid.setRow(0, values0);
        grid.setRow(1, values1);
        assertTrue(grid.isGridValid());
    }
    @Test
    void checkGridInvalidAsDuplicate4InThreeByThreeBox(){
        var grid = new Grid();
        int[] values0 = {0, 0, 0, 4, 5, 6, 7, 8, 9};
        int[] values1 = {1, 4, 4, 0, 0, 0, 0, 0, 0};
        grid.setRow(0, values0);
        grid.setRow(1, values1);
        assertFalse(grid.isGridValid());
    }
    @Test
    void containsNoDuplicate(){
        ArrayList<Integer> listWithNoDuplicate = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6));
        assertFalse(Grid.containsDuplicate(listWithNoDuplicate));
    }
    @Test
    void containsDuplicate(){
        ArrayList<Integer> listWithNoDuplicate = new ArrayList<>(Arrays.asList(1, 2, 3, 3, 5, 6));
        assertTrue(Grid.containsDuplicate(listWithNoDuplicate));
    }
    @Test
    void setGridWithStaticArray(){
        var grid = new Grid();
        int[][] grid_values = {{1, 2, 3, 4, 5, 6, 7, 8, 9}, {2, 3, 4, 5, 6, 7, 8, 9, 1}};
        grid.setGrid(grid_values);
        assertEquals(4, grid.getCell(1, 2));
    }
    @Test
    void solveCellWhenOneOption(){
        var grid = new Grid();
        int[] values0 = {8, 0, 9, 4, 5, 6, 0, 0, 0};
        int[] values1 = {1, 2, 3, 0, 0, 0, 0, 0, 0};
        grid.setRow(0, values0);
        grid.setRow(1, values1);
        grid.solve_cell(0, 1);
        assertEquals(7, grid.getCell(0, 1));
    }
    @Test
    void solvedGridShouldBeDeemedSolved(){
        var grid = new Grid();
        int[][] grid_values = {
                {1, 2, 3, 6, 7, 8, 9, 4, 5},
                {5, 8, 4, 2, 3, 9, 7, 6, 1},
                {9, 6, 7, 1, 4, 5, 3, 2, 8},
                {3, 7, 2, 4, 6, 1, 5, 8, 9},
                {6, 9, 1, 5, 8, 3, 2, 7, 4},
                {4, 5, 8, 7, 9, 2, 6, 1, 3},
                {8, 3, 6, 9, 2, 4, 1, 5, 7},
                {2, 1, 9, 8, 5, 7, 4, 3, 6},
                {7, 4, 5, 3, 1, 6, 8, 9, 2},
        };
        grid.setGrid(grid_values);
        assertTrue(grid.isValidSolution());
    }
    @Test
    void gridWith0ShouldBeDeemedUnsolved(){
        var grid = new Grid();
        int[][] grid_values = {
                {1, 2, 3, 6, 7, 8, 9, 4, 5},
                {5, 8, 4, 2, 3, 9, 7, 6, 1},
                {9, 6, 7, 1, 4, 5, 3, 2, 8},
                {3, 7, 2, 0, 6, 1, 5, 8, 9},
                {6, 9, 1, 5, 8, 3, 2, 7, 4},
                {4, 5, 8, 7, 9, 2, 6, 1, 3},
                {8, 3, 6, 9, 2, 4, 1, 5, 7},
                {2, 1, 9, 8, 5, 7, 4, 3, 6},
                {7, 4, 5, 3, 1, 6, 8, 9, 2},
        };
        grid.setGrid(grid_values);
        assertFalse(grid.isValidSolution());
    }
    @Test
    void gridWithInvalidRowAndColumnShouldBeDeemedUnsolved(){
        var grid = new Grid();
        int[][] grid_values = {
                {1, 2, 3, 6, 7, 8, 9, 4, 5},
                {5, 8, 4, 2, 3, 9, 7, 6, 1},
                {9, 6, 7, 1, 4, 5, 3, 2, 8},
                {3, 7, 2, 4, 6, 1, 5, 8, 9},
                {6, 9, 1, 5, 8, 3, 2, 7, 4},
                {4, 5, 8, 7, 9, 2, 6, 1, 3},
                {8, 3, 6, 9, 2, 4, 1, 5, 7},
                {2, 1, 1, 8, 5, 7, 4, 3, 6},
                {7, 4, 5, 3, 1, 6, 8, 9, 2},
        };
        grid.setGrid(grid_values);
        assertFalse(grid.isValidSolution());
    }
}