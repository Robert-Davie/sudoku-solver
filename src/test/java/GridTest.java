import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

class GridTest {
    @Test
    void setFirstCellThree(){
        var grid = new Grid();
        grid.setCell(0, 0, 3);
        assertEquals(3, grid.grid.get(0).get(0));
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
}