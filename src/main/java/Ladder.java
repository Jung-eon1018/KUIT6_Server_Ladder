package main.java;

public class Ladder {

    private final int[][] rows;

    public Ladder(int row, int numberOfPerson) {
        rows = new int[row][numberOfPerson];
    }

    public void drawLine(int row, int col){
        if (row < 0 || row >= rows.length) throw new IndexOutOfBoundsException();
        if (col < 0 || col >= rows[0].length - 1) throw new IndexOutOfBoundsException();

        //(row, col) 좌표를 받으면 무조건 그 좌표로 부터 오른쪽으로 한 칸의 선을 긋는다.
        //이미 존재?
        if(rows[row][col] == 1){
            throw new IllegalStateException("이미 라인이 있음");
        }

        // 같은 높이에서 양옆 검사, 양쪽으로 연속된 선을 그을 수 없도록 막는다.
        if (col > 0 && rows[row][col - 1] == 1) {
            throw new IllegalArgumentException("왼쪽과 인접 라인 금지");
        }
        if (col < rows[0].length - 2 && rows[row][col + 1] == 1) {
            throw new IllegalArgumentException("오른쪽과 인접 라인 금지");
        }

        //그리기: (row, col) ~ (row, col+1)
        rows[row][col] = 1;
    }

    public int run(int startLine){
        int persons = rows[0].length; //열의 개수 = 사람 수
        if (startLine < 1 || startLine > persons) {
            throw new IllegalArgumentException("시작 번호가 범위를 벗어남");
        }

        int col = startLine - 1;
        for(int r =0; r < rows.length; r++){
            if(col>0 && rows[r][col-1] == 1){
                col--;
            } else if (col<persons-1 && rows[r][col] == 1){
                col++;
            }
        }
        return col+1;
    }
}
