package main.java;

public class Row {
    private final int[] row;

    public Row(int numberOfPerson) {
        validateNumberPerson(numberOfPerson);
        row = new int[numberOfPerson];
    }

    public int nextPosition(int position){
        validatePosition(position);

        if(isRight(position)){
            return position + 1;
        }

        if(isLeft(position)){
            return position - 1;
        }
        return position;
    }

    private boolean isRight(int position){
        return row[position] == -1;
    }

    private boolean isLeft(int position){
        return row[position] == 1;
    }

    private void validatePosition(int position){
        if(position >= row.length || position < 0){
            throw new IndexOutOfBoundsException("유효하지 않은 위치입니다.");
        }
    }

    public void drawLine(int startPosition){
        validateDrawLinePosition(startPosition);
        row[startPosition]=1;
        row[startPosition+1]=-1;

    }

    private void validateDrawLinePosition(int startPosition){
        if(startPosition >=row.length || startPosition <0 || row[startPosition]==-1 || row[startPosition+1]==1){
            throw new IndexOutOfBoundsException("사다리를 그릴 수 없는 위치입니다.");
        }
    }

    private void validateNumberPerson(int numberOfPerson){
        if(numberOfPerson <=1){
            throw new IllegalArgumentException("참여 인원은 1명 이상이어야 합니다.");
        }
    }
}
