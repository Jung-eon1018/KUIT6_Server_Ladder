package main.java;

public class LadderCreater {

    private final Row[] rows;
    private final int numberOfPerson;

    public LadderCreater(GreaterThanOne numberOfRows, GreaterThanOne numberOfPerson) {
        if (numberOfRows.getNumber() <= 0) throw new IllegalArgumentException("행(층) 수는 1 이상이어야 합니다.");
        this.numberOfPerson = numberOfPerson.getNumber();
        this.rows = new Row[numberOfRows.getNumber()];
        for (int r = 0; r < numberOfRows.getNumber(); r++) {
            rows[r] = new Row(numberOfPerson); // 각 층은 사람 수(열 수)를 안다
        }
    }

    public Row[] getRows() {
        return rows;
    }

    public void drawLine(Position row, Position col){
        rows[row.getValue()].drawLine(col);
    }
}
