// comment added after using GitHub
import java.util.Arrays;
import java.util.Scanner;
public class ConnectFour {
    public static void main(String[] args) {
        //initialize the variables used in the program
        char chipType;
        int row;
        int numOfTurns = 0;
        int height;
        int length;
        int selectedColumn;
        boolean winner;
        //set player 1 to true and player 2 to false for the while loop later to determine the turns for the players
        boolean playerOne = true;
        boolean playerTwo = false;
        Scanner Keyboard = new Scanner(System.in);
        //let the user input the height and length they want
        System.out.println("What would you like the height of the board to be?");
        height = Keyboard.nextInt();
        System.out.println("What would you like the length of the board to be?");
        length = Keyboard.nextInt();
        char[][] board = new char[height][length];
        //initialize the board with dashes in the array by calling the method
        initializeBoard(board);
        printBoard(board);
        //print the blank board with the dashes using the method
        System.out.println("\n \nPlayer 1: x");
        System.out.println("Player 2: o");
        //assign player one to x and player two to o
        while(true){
            //set the while loop to true so the program continuously loops
            if(playerOne == true){
                //use the playerOne boolean that is true to let player one take their turn
                chipType = 'x';
                //assign the chip to x
                System.out.println("\nPlayer 1: Which column would you like to choose?");
                selectedColumn = Keyboard.nextInt();
                row = insertChip(board, selectedColumn, 'x');
                //call the method so that the chip can be placed in the designated location
                numOfTurns++;
                //increase the number of turns so it can be used in the event of a draw
                printBoard(board);
                //print the new board
                winner = checkIfWinner(board, selectedColumn, row, chipType);
                //call the method to check if player one won or if a draw occurs
                if(winner){
                    System.out.println("\nPlayer 1 won the game!");
                    System.exit(0);
                } else if (numOfTurns == length * height){
                    //if the number of spaces and turns are equal there are no other available spaces so it is a draw
                    System.out.println("\nDraw. Nobody wins.");
                    System.exit(0);
                }
                //flip the booleans for player 1 and 2 so player 2 can take their turn
               playerOne = false;
                playerTwo = true;
            }
            if (playerTwo == true){
                //set the chip to o
                chipType = 'o';
                System.out.println("\n Player 2: Which column would you like to choose?");
                selectedColumn = Keyboard.nextInt();
                //call the insertChip method so that the turn can be done
                row = insertChip(board, selectedColumn, 'o');
                //print the new board with the placed piece
                printBoard(board);
                numOfTurns++;
                //increase the number of turns by one
                winner = checkIfWinner(board, selectedColumn, row, chipType);
                //same logic as for player one; if there are 4 in a row P2 wins and if all spots are full it is a draw
                if(winner){
                    System.out.println("\n Player 2 won the game!");
                    System.exit(0);
                } else if (numOfTurns == length * height){
                    System.out.println("\n Draw. Nobody wins.");
                    System.exit(0);
                }
                //flip the booleans so player one can take their turn
                playerOne = true;
                playerTwo = false;
            }
        }
    }
    public static void printBoard(char[][]array){
        //use the 2 loops to generate the rows and columns
        for(int r = array.length - 1; r >= 0; r--){
            for(int c = 0; c < array[0].length; c++){
                System.out.print(array[r][c] + "");
                //print the board with the chips
            }System.out.println();
        }
    }
    public static void initializeBoard(char[][]array){
        //initialize the board by filling the entirety of it with a dash
        for (char[] chars : array) {
            Arrays.fill(chars, '-');
        }
    }

    public static int insertChip(char[][]array, int col, char chipType){
        //place the chip on the board at the lowest free position
        for(int r = 0; r < array.length; r++){
            if(array[r][col] == '-'){
                //change the dash to the chip type of the player
                array[r][col] = chipType;
                return r;
            }
        } return 0;
    }
    public static boolean checkIfWinner(char[][] array, int col, int row, char chipType) {
        int connectedFour = 0;
        //check if there are 4 in a row in a column
        for (char[] chars : array) {
            if (chars[col] == chipType) {
                //add one to the variable for each of the same chips in a column
                connectedFour++;
                if (connectedFour == 4) {
                    return true;
                    //if there are 4 in a row
                }
            } else {
                connectedFour = 0;
                //if not four in a row reset it :(
            }
        }
        connectedFour = 0;
        //check if there are 4 in a row
        for(int r = 0;r < array[0].length; r++){
            if(array[row][r] == chipType){
                connectedFour++;
                //add one to the variable for each of the same chip in a row
                if(connectedFour == 4){
                    return true;
                    //if there are 4 in a row its true
                }
            }else{
                connectedFour = 0;
                //if not reset it
            }
        }
        return false;
        //return false if it does not have 4 in a row or 4 in a column
    }
}


