import java.util.Scanner;
import java.util.Random;
public class TicTacToe{
    private static Scanner scanner;
    private static Random random;
    private static String[][] board;
    private static String currentPlayer;
    private static boolean initalizedfirst;

    public TicTacToe(){
        scanner=new Scanner(System.in);
        random=new Random();
        board=new String[3][3];
        initializeBoard();
        printBoard();
        initalizeGame();

    }

    private static boolean ifGameWon() {

        for(int i = 0; i < 3 ; i++){
            if(!board[i][0].equals("-") && board[i][0].equals(board[i][1]) && board[i][1].equals(board[i][2])){
                return true;
            }
        }

        for(int i = 0; i < 3 ; i++){
            if(!board[0][i].equals("-") && board[0][i].equals(board[1][i]) && board[1][i].equals(board[2][i])){
                return true;
            }
        }

        if(!board[0][0].equals("-") && board[0][0].equals(board[1][1]) && board[1][1].equals(board[2][2])){
            return true;
        }

        if(!board[0][2].equals("-") && board[0][2].equals(board[1][1]) && board[1][1].equals(board[2][0])){
            return true;
        }
        return false;

    }

    private static void initializeBoard(){
        for(int i = 0; i < 3 ; i++){
            for( int j = 0; j < 3 ; j++){
                board[i][j] =  "-";
            }
        }
    }

    private static void initalizeGame(){
        initalizedfirst = true;
        currentPlayer=getRandomPlayer();
        while (!isBoardFull()) {
            if(!initalizedfirst) {
                printBoard();
            }
            initalizedfirst = false;
            System.out.println("PLAYER "+currentPlayer+" TURN");
            System.out.println("Enter row value first (0 ,1 ,2) : ");
            int row =scanner.nextInt();
            System.out.println("Enter column value second (0 ,1 ,2) : ");
            int column = scanner.nextInt();
            if( row < 0 || row >2 || column < 0 || column > 2 && !board[row][column].equals("-")){
                System.out.println("Invalid input");
            }else{
                if(!board[row][column].equals("-")){
                    System.out.println("Place already occupied");
                    continue;
                }
                board[row][column] = currentPlayer;
                if(ifGameWon()){
                    System.out.println("Game is won by "+currentPlayer);
                    break;
                }else{
                    currentPlayer = currentPlayer.equals("X") ? "0" : "X";
                }
            }
        }
        System.out.println("Game is Draw");
        System.out.println("Do you want to play again? (Y/N)");
        if(scanner.next().equalsIgnoreCase("Y")){
            reinitalizeGame();
        }
        else{
            graceFullExit();
        }
    }

    private static String getRandomPlayer(){
       return random.nextBoolean() ? "X" : "0";
    }
    private static boolean isBoardFull(){
        for(int i = 0; i < 3 ; i++ ){
            for( int j = 0; j < 3; j++){
                if(board[i][j].equals("-")){
                    return false;
                }
            }

        }
        return true;
    }

    private static void printBoard(){
        System.out.println(" ");
        System.out.println("\t"+"BOARD");
        System.out.println("-".repeat(13));
        for(int i = 0; i < 3 ; i++){
            for( int j = 0; j < 3 ; j++){
                System.out.print("  "+board[i][j]+" ");
            }
            System.out.println();
        }
        System.out.println("-".repeat(13));
    }
    private static void reinitalizeGame(){
        initializeBoard();
        printBoard();
        initalizeGame();
    }

    private static void graceFullExit() {
        System.out.println("Exiting the game...");
        try {
            Thread.sleep(3000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        scanner.close();
        System.exit(0);
    }

    public static void main(String[] args){
        new TicTacToe();
    }
}