package main.java;

public class Ladder {

    private final Row[] rows;
    private final int numberOfPerson;


    public Ladder(int numberOfRows, int numberOfPerson) {
        if (numberOfRows <= 0) throw new IllegalArgumentException("행(층) 수는 1 이상이어야 합니다.");
        this.numberOfPerson = numberOfPerson;
        this.rows = new Row[numberOfRows];
        for (int r = 0; r < numberOfRows; r++) {
            rows[r] = new Row(numberOfPerson); // 각 층은 사람 수(열 수)를 안다
        }
    }

    public void drawLine(int row, int col){
        if (row < 0 || row >= rows.length) throw new IndexOutOfBoundsException("유효하지 않은 행입니다.");
        if (col < 0 || col >= numberOfPerson - 1) throw new IndexOutOfBoundsException("오른쪽으로 선을 그을 수 없는 열입니다.");

        rows[row].drawLine(col);
    }

    public int run(int startLine){
        if (startLine < 1 || startLine > numberOfPerson) {
            throw new IllegalArgumentException("시작 번호가 범위를 벗어남");
        }

        int col = startLine - 1;
        for(Row r : rows){
            col = r.nextPosition(col);
        }
        return col+1;
    }
}
