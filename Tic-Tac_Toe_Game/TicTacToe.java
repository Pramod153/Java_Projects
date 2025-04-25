package com.TicTacToe;


	import java.util.Scanner;

	public class TicTacToe {

	    private static final char EMPTY = '-';
	    private static final char PLAYER_X = 'X';
	    private static final char PLAYER_O = 'O';

	    private char[][] board;
	    private char currentPlayer;

	    public TicTacToe() {
	        board = new char[3][3];
	        initializeBoard();
	        currentPlayer = PLAYER_X;
	    }

	    private void initializeBoard() {
	        for (int i = 0; i < 3; i++) {
	            for (int j = 0; j < 3; j++) {
	                board[i][j] = EMPTY;
	            }
	        }
	    }

	    public void printBoard() {
	        for (int i = 0; i < 3; i++) {
	            for (int j = 0; j < 3; j++) {
	                System.out.print(board[i][j] + " ");
	            }
	            System.out.println();
	        }
	    }

	    public boolean isBoardFull() {
	        for (int i = 0; i < 3; i++) {
	            for (int j = 0; j < 3; j++) {
	                if (board[i][j] == EMPTY) {
	                    return false;
	                }
	            }
	        }
	        return true;
	    }

	    public boolean makeMove(int row, int col) {
	        if (row < 0 || row >= 3 || col < 0 || col >= 3 || board[row][col] != EMPTY) {
	            return false;
	        }
	        board[row][col] = currentPlayer;
	        return true;
	    }

	    public boolean checkWin() {
	        // Check rows and columns
	        for (int i = 0; i < 3; i++) {
	            if ((board[i][0] == currentPlayer && board[i][1] == currentPlayer && board[i][2] == currentPlayer) ||
	                (board[0][i] == currentPlayer && board[1][i] == currentPlayer && board[2][i] == currentPlayer)) {
	                return true;
	            }
	        }

	        // Check diagonals
	        if ((board[0][0] == currentPlayer && board[1][1] == currentPlayer && board[2][2] == currentPlayer) ||
	            (board[0][2] == currentPlayer && board[1][1] == currentPlayer && board[2][0] == currentPlayer)) {
	            return true;
	        }

	        return false;
	    }

	    public void switchPlayer() {
	        currentPlayer = (currentPlayer == PLAYER_X) ? PLAYER_O : PLAYER_X;
	    }

	    public static void main(String[] args) {
	        Scanner scanner = new Scanner(System.in);
	        TicTacToe game = new TicTacToe();

	        System.out.println("Welcome to Tic Tac Toe!");
	        game.printBoard();

	        while (true) {
	            System.out.println("Player " + game.currentPlayer + ", enter your move (row and column): ");
	            int row = scanner.nextInt();
	            int col = scanner.nextInt();

	            if (game.makeMove(row, col)) {
	                game.printBoard();

	                if (game.checkWin()) {
	                    System.out.println("Player " + game.currentPlayer + " wins!");
	                    break;
	                }

	                if (game.isBoardFull()) {
	                    System.out.println("The game is a tie!");
	                    break;
	                }

	                game.switchPlayer();
	            } else {
	                System.out.println("This move is not valid. Try again.");
	            }
	        }

	        scanner.close();
	    }
	}


