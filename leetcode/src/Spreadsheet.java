import java.util.ArrayList;
import java.util.List;

class Spreadsheet {

    private int[][] ss;
    public Spreadsheet(int rows) {
        ss = new int[rows][26];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < 26; j++) {
                ss[i][j] = 0;
            }
        }
    }



    public void setCell(String cell, int value) {
        int col = cell.charAt(0) - 'A';
        int row = Integer.parseInt(cell.substring(1))-1;
        ss[row][col] = value;
    }

    public void resetCell(String cell) {
        int col = cell.charAt(0) - 'A';
        int row = Integer.parseInt(cell.substring(1))-1;
        ss[row][col] = 0;
    }


    private int eval(String s){
        if(Character.isDigit(s.charAt(0))) {
            return Integer.valueOf(s);
        }
        else{
            return ss[Integer.valueOf(s.substring(1))-1][s.charAt(0)-'A'];
        }
    }

    public int getValue(String formula) {
        String[] arr = formula.substring(1).split("\\+");
        return eval(arr[0]) + eval(arr[1]);
    }
}

/**
 * Your Spreadsheet object will be instantiated and called as such:
 * Spreadsheet obj = new Spreadsheet(rows);
 * obj.setCell(cell,value);
 * obj.resetCell(cell);
 * int param_3 = obj.getValue(formula);
 */