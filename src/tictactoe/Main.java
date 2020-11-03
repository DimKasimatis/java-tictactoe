package tictactoe;

import java.util.*;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    static char[][] board = new char[3][3];
    static int Xcord, Ycord;
    static char player = 'X';

    public static void main(String[] args) {
        createTable(board);
        getCoordinates();
    }

    static void createTable(char[][] arr) { // draws the empty game board
        System.out.println("---------");
        for (int i = 0; i < 3; i++) {
            System.out.print("| ");
            for (int j = 0; j < 3; j++) {
                arr[i][j] = ' ';
                System.out.print(arr[i][j] + " ");
            }
            System.out.println("|");
        }
        System.out.println("---------");
    }

    static void drawTable(char[][] arr) { // draws the board updates
        System.out.println("---------");
        for (int i = 0; i < 3; i++) {
            System.out.print("| ");
            for (int j = 0; j < 3; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println("|");
        }
        System.out.println("---------");
        checkState();
    }

    static void checkState() {  // checks the current game state
        boolean xxx = false;
        boolean ooo = false;
        int xSum = 0;
        int oSum = 0;
        for (int i = 0; i < 9; i++) {
            if (board[i / 3][i % 3] == 'X') {
                xSum += 1;
            } else if (board[i / 3][i % 3] == 'O') {
                oSum += 1;
            }
        }

        for (int i = 0; i < 3; i++) {
            int row = 0;
            int col = 0;
            int aDiag = 0;
            int bDiag = 0;

            for (int j = 0; j < 3; j++) {
                row += board[i][j];
                col += board[j][i];
                aDiag += board[j][j];
                bDiag += board[j][2 - j];

                // ASCII value for X is 88 (X+X+X is 264)
                // ASCII value for O is 79 (O+O+O is 237)
                xxx = xxx || row == 264 || col == 264 || aDiag == 264 || bDiag == 264;
                ooo = ooo || row == 237 || col == 237 || aDiag == 237 || bDiag == 237;
            }
        }
        String result = Math.abs(xSum - oSum) > 1 || xxx && ooo ? "Impossible"
                : xxx ? "X wins"
                : ooo ? "O wins"
                : xSum + oSum == 9 ? "Draw"
                : "Game note finished";

        switch (result) {
            case "X wins":
                System.out.println("X wins");
                System.exit(0);
                break;
            case "O wins":
                System.out.println("O wins");
                System.exit(0);
                break;
            case "Impossible":
                System.out.println("Impossible");
                System.exit(0);
                break;
            case "Draw":
                System.out.println("Draw");
                System.exit(0);
                break;
            default:
                getCoordinates();
        }

    }


    static void getCoordinates() { // gets coordinates and checks whether it satisfies specific conditions
        System.out.println("Enter coordinates: ");
        while (true) {
            while (!(scanner.hasNextInt())) {
                System.out.println("You should enter numbers!");
                scanner.next();
            }

            Xcord = scanner.nextInt();
            Ycord = scanner.nextInt();

            if (Xcord > 3 || Xcord < 1 || Ycord > 3 || Ycord < 1) {
                System.out.println("Coordinates should be from 1 to 3!");
            } else {
                ninetyRight(board);
                if (isEmpty(board, Xcord, Ycord)) {
                    fillValue(board, player, Xcord, Ycord);
                    ninetyLeft(board);
                    if (player == 'X') {
                        player = 'O';
                    } else {
                        player = 'X';
                    }
                    drawTable(board);
                } else {
                    System.out.println("This cell is occupied! Choose another one!");
                }
                ninetyLeft(board);
            }
        }
    }


    static char[][] fillValue(char[][] arr, char value, int X, int Y) { // fills matrix with a specific value
        arr[X - 1][Y - 1] = value;
        return arr;
    }

    static char[][] ninetyRight(char[][] arr) { // rotates matrix to 90 degree right
        char[][] carr = new char[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                carr[i][j] = arr[i][j];
            }
        }

        int k = 2;
        for (int i = 0; i < 3; i++) {
            arr[0][i] = carr[k][0];
            arr[1][i] = carr[k][1];
            arr[2][i] = carr[k][2];
            k--;
        }
        return arr;
    }

    static char[][] ninetyLeft(char[][] arr) { // rotates matrix to 90 degree left
        for (int i = 0; i < 3; i++) {
            ninetyRight(arr);
        }
        return arr;
    }

    static boolean isEmpty(char[][] arr, int X, int Y) {  // checks whether the chosen coordinates are empty
        return (arr[X - 1][Y - 1] == ' ' || arr[X - 1][Y - 1] == '_');
    }
}