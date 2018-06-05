public class BoardGUI {
    public static int[][] board;
    public static int n;

    public static void main(String[] args) {
        n = 2;
        board = new int[2][2];
        board[0][0] = 0;
        board[0][1] = 1;
        board[1][0] = 0;
        board[1][1] = 1;
        printBoardInConsole();
    }

    public static void printBoardInConsole() {
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.print('\n');
        }
    }
}
