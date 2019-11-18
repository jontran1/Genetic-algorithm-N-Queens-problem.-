public class Main {
    public static void main(String[] args) {
        int n = 25;
        ChessBoard chessBoard = new ChessBoard(n,n);
        int iterations = 0;
        try{
            // While the n-queen problem isn't solved. Continuing solving until a solution is found.
            while(!chessBoard.isSolved()){
                iterations ++;
                chessBoard.addQueen();
            }

        }catch (Exception e){
            System.out.println(e.getMessage());
            System.out.println(chessBoard.getBoard());

        }
        System.out.println(chessBoard.getBoard());
        System.out.println("Iteration: " + iterations);
        System.out.println("Chesboard String representation: " + chessBoard);


    }
}
