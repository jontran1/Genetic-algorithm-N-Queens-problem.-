import java.util.Arrays;
import java.util.Stack;
import java.lang.Math;

public class ChessBoard {
    private Stack<Queen> stack = new Stack();
    private int [] chessBoard;
    private int rows, columns;
    private int currentRow, currentColumn;
    private boolean isSolved;

    /**
     * A constructor for the chess board.
     * @param row
     * @param column
     */
    ChessBoard(int row, int column){
        chessBoard = new int[column];
        this.rows = row;
        this.columns = column;
        this.currentColumn = 0;
        this.isSolved = false;
        this.currentRow = 0;
    }

    /**
     * This function will check the next available position for the queen.
     * If the position isn't valid it will call the backtrack function and look for another
     * position until it finds a valid position for the queen.
     * The next positions are kept track with the column and row variables. Every time a valid
     * position is found. The column variable will increment. The row variable keeps track of which
     * row to check for a valid position.
     * If a valid position is found
     * It will add the queen to the queen stack and chess board.
     * @throws Exception
     */
    public void addQueen() throws Exception {
        if(currentColumn == columns){
            isSolved = true;
            return;
        }
        try{
            if (isValid()){
                chessBoard[currentColumn] = currentRow;
                stack.push(new Queen(currentRow, currentColumn));
                currentRow = 0;
                currentColumn++;
            }else{
                if(currentRow == rows-1){
                    backTrack();
                }else {
                    currentRow++;
                }
            }
        }catch (IndexOutOfBoundsException e){
            e.printStackTrace();
        }catch (Exception e){
            throw e;
        }
    }

    /**
     * If a position isn't valid this backtrack function will be called.
     * This function will pop the most recent. It will then check the next available
     * position for that current queen's column and row. It the max row is reach. It
     * will recursively call the backtrack function again to pop the next queen since the last
     * queen available positions have been exhausted.
     * @throws Exception
     */
    public void backTrack() throws Exception {
        Queen tempQueen = stack.pop();
        chessBoard[currentColumn] = currentRow;
        if(stack.isEmpty()){
            if(tempQueen.getX() == rows-1 && tempQueen.getY() == 0){
                throw new Exception("No solution found");
            }
        }
        if(tempQueen.getX() == rows-1){
            backTrack();
        }else {
            currentRow = tempQueen.getX() + 1;
            currentColumn = tempQueen.getY();
        }
    }

    /**
     * This function will check if the current position is valid for a queen placement.
     * @return
     */
    private boolean isValid() {
        //If current queen is 0 then there's no need to check. It is the only piece on the board.
        if(currentColumn == 0){
            return true;
        }
        /*
        This function will never check if the two points are on the same column,
        because currentColumn will never only have one queen for each column.
         */
        for(int i = 0 ; i < stack.size(); i++){
            Queen currentQueen = stack.get(i);
            //Check if the two points are on the same row.
            if(currentQueen.getX() == currentRow){
                return false;
            }
            if (Math.abs(currentQueen.getX()-currentRow) == Math.abs(currentQueen.getY() - currentColumn)){
                return false;
            }
        }
        return true;
    }

    /**
     * String representation of the board.
     * @return
     */
    public String toString(){
        return Arrays.toString(chessBoard);
    }

    /**
     * A full string representation of the 2D board.
     * @return
     */
    public String getBoard(){
        char[][] board = new char[rows][columns];
        for (int i = 0; i < chessBoard.length; i ++){
            board[chessBoard[i]][i] = 'Q';
        }
        StringBuilder stringBuilderBoard = new StringBuilder(rows * columns);
        for (int i = 0; i < rows; i++ ){
            stringBuilderBoard.append("{");
            for (int j = 0; j < columns ; j++){
                if(board[i][j] == 'Q'){
                    stringBuilderBoard.append(board[i][j]);
                }else {
                    stringBuilderBoard.append(" ");
                }
                if(j != columns-1){
                    stringBuilderBoard.append(",");
                }
            }
            stringBuilderBoard.append("}");
            stringBuilderBoard.append("\n");
        }
        return stringBuilderBoard.toString();
    }

    public int getCurrentRow() {
        return currentRow;
    }

    public int getCurrentColumn() {
        return currentColumn;
    }

    public boolean isSolved() {
        return isSolved;
    }
}
