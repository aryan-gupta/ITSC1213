/**
 * This class represents a Board for a Connect 4 game. You can
 * place a chip at any column and the class will automatically 
 * figure out the next available slot for the chip in that column
 * and place the chip there. The class also has method for checking
 * the winner, if any \sa getWinner() 
 *
 * To summarized work load:
 * We work together in lab the day the assigment was created. Almost the whole game was finished this day
 * that is why some code have three names
 * we finish the main class and the Board class working together the same day. 
 * then Aryan added two more methods to the board class and some comments
 * Efrem added code for the validation of inputs and a Ai feature to the computer's turn
 * Manuel commented all methods left in the board class, added comments for each of the parts in main and debugged code
 * to be ready to submit
 *
 * @author Manuel Diaz, Efrem Gebrekidane, Aryan Gupta
 * @version 1.0
 */
public class Board{
    private char[][] board; // Stores our board as 2D Array of chars
    
    /** 
     * Default Constructor. Creates a 7 by 6 board.
     */
    public Board(){
        board =new char [6][7];
    }
    
    /** 
     * Returns the winner of the board's piece or null (\0) is there is no winner.
     *
     * @author Aryan Gupta
     * @return char - The piece that is the winner of the board
     */
    public char getWinner() {
        /// @note The original code here was written with a helper class called Coordinate,
        /// This allowed me to forget the differences between XY and RC coordinates, however
        /// was later removed. Another idea we had was to make a ConnectX game where we could
        /// choose how many 'in-a-row' we would need to win (4-in-a-row, 5-in-a-row, etc).
        /// However idea was abandoned due to time constraints. mDepth represents the 'in-rows'
        /// we need to win (in this case its 4). I replaced all instances of mDepth with constant 4
        
        // Pretty much we want to go through each position on the board and skip any ones that
        // don't have any chars (char at that position is \0). For the Up/Down direction we only
        // need to check every mDepth row starting at row number -1. So for example, if our mDepth
        // is 4 then we check row 3, 7, 11 and so on. We can get this by moding it with mDepth and
        // checking if its equal to mDepth - 1 (% 4 would equal to 4, 8, 12). Similarly, for the 
        // left right direction we want to do the same thing with the columns instead of the rows. 
        // The diagonals is interesting, we want to check a corner row/column starting at (3, 3),
        // (7, 7), (11, 11) and so on. So like this:
        //   0 1 2 3 4 5 6 7 8 9
        // 0
        // 1
        // 2
        // 3       X X X X X X X
        // 4       X
        // 5       X
        // 6       X
        // 7       X       X X X
        // 8       X       X
        // 9       X       X
        // This is only valid for top left to bottom right. Where ever there is an X we want to 
        // check in the tl to br direction. For the other diagonal direction we have this:
        //   0 1 2 3 4 5 6 7 8 9
        // 0
        // 1
        // 2
        // 3 X X X X X X X X
        // 4               X
        // 5               X
        // 6 X X X X       X
        // 7       X       X
        // 8       X       X
        // 9       X       X
        // Lets go back to the up/down direction:
        //   0 1 2 3 4 5 6 7 8 9
        // 0
        // 1
        // 2
        // 3 X X X X X X X X X X
        // 4
        // 5
        // 6 X X X X X X X X X X
        // 7
        // 8
        // 9
        // And the left/right direction:
        //   0 1 2 3 4 5 6 7 8 9
        // 0       X       X
        // 1       X       X
        // 2       X       X
        // 3       X       X
        // 4       X       X
        // 5       X       X
        // 6       X       X
        // 7       X       X
        // 8       X       X
        // 9       X       X
        // If we overlay them over we get this pattern:
        //   0 1 2 3 4 5 6 7 8 9
        // 0       X       X
        // 1       X       X
        // 2       X       X
        // 3 X X X X X X X X X X
        // 4       X O O O X
        // 5       X O O O X
        // 6       X O O O X
        // 7 X X X X X X X X X X
        // 8       X       X
        // 9       X       X
        // To show distances I have added a few O's
        // Honestly, I'm not sure how to implement this with a for loop, so I'm just
        // going to loop though every one of them and just filter the ones we want
        // to use.
        for (int row = board.length - 1; row >= 0; --row) {
            for (int col = 0; col < board[0].length; ++col) {
                char ch = board[row][col];
                if (ch == 0) continue;
                
                // UP DOWN DIRECTION
                if (row % 4 == 3) 
                    if ((getInRowLength(ch, 0, 0, 1, row, col) + getInRowLength(ch, 0, 0, -1, row, col)) > 4)
                        return ch;
                
                // LEFT RIGHT DIRECTION
                if (col % 4 == 3)
                    if ((getInRowLength(ch, 0, 1, 0, row, col) + getInRowLength(ch, 0, -1, 0, row, col)) > 4)
                        return ch;
                
                // DIAGONAL DIRECTION
                if (row % 4 == 4 - 1 || col % 4 == 3) {
                    if ((getInRowLength(ch, 0, -1, 1, row, col) + getInRowLength(ch, 0, 1, -1, row, col)) > 4)
                        return ch;
                    
                    if ((getInRowLength(ch, 0, -1, -1, row, col) + getInRowLength(ch, 0, 1, 1, row, col)) > 4)
                        return ch;
                }
            }
        }
        return 0; // return \0 on no winner
    }
    
    /**
     * Recursive algorithm to get the number of chips 'in-row' of each other. Used
     * by the \sa getWinner() method. 
     * 
     * @author Aryan Gupta
     * @return int - The number of chips in a row
     * @param char ch - The character that should be checked for in-a-row
     * @param int depth - A variable containing the depth of the recursive calls
     * @param int dx - The direction we are checking (x direction)
     * @param int dy - The direction we are checking (y direction)
     * @param int r - The current row we are checking
     * @param int c - The current column we are checking
     */
    private int getInRowLength(char ch, int depth, int dx, int dy, int row, int col) {
        /// @todo change algorithm to use dr and dc
        
        // we are given a direction by \p dx and \p dy. The algorithm travels that direction
        // until it reaches a char that is not equal to \p ch. Each call of this function
        // is the next place in the direction of dx/dy, depth is incremented receptively
        // This function should be run in every 8 cardinal direction (n s e w ne nw se sw)        
    
        // If we are out of bounds then just return how far we have gotten
        if (checkOutOfBounds(row, col))
            return depth;
        
        // if we have the same char as the 0th depth then continue deeper
        // else just return the depth because this depth is the last one in
        // a sequence
        if (board[row][col] == ch)
            return getInRowLength(ch, depth + 1, dx, dy, row + dy, col + dx);
        return depth;
    }
    
    /**
     * Checks if the row and column we pass in area within the board
     *
     * @author Aryan Gupta
     * @return boolean - If the parameters passed in are within the bounds of the board
     * @param int r - The row number to check
     * @param int c - The column number to check
     */
    private boolean checkOutOfBounds(int row, int col) {
        return
           row >= board   .length
        || col >= board[0].length
        || row <  0
        || col <  0;
        
    }
    
    /**
     * Places a piece on the board. Lets the piece 'slide' to the bottom.
     *
     * @author Manuel Diaz, Efrem Gebrekidane, Aryan Gupta
     * @param int column - The column number to place the piece
     * @param char player - The placer's piece
     */    
    public void place(int columm, char player){
        int index = getAvailable(columm);
        if (index== -1){
            System.out.println("this row is filled"); return;
        }
        board[index][columm] = player;
        
    }
    
    /** 
     * Returns the top most available spot to place the piece
     * The int returned and the column pair to make a coordinate
     * that is guaranteed to be free (empty)
     *
     * @author Manuel Diaz, Efrem Gebrekidane, Aryan Gupta
     * @param int column - The column to get the top-most available cell
     * @return int - The row of the top-most available cell
     */
    private int getAvailable(int column){
      for(int i = 0; i< board.length; i++){
          if (board[i][column] != '\0'){
             return i - 1;
            }  
        }
        return board.length -1;
    }
    
    /** 
     * Returns true if the board is filled and no more pieces can be 
     * placed on the board. 
     *
     * @author Manuel Diaz, Efrem Gebrekidane, Aryan Gupta
     * @return boolean - If the board is filled
     */
    public boolean getFill(){
        for (int i = 0; i< board.length; i++){
          for(int k = 0; k< board[i].length; k++){
              if (board[i][k] == '\0'){
                  return false;
                }
            }
        }
        return true;
    }
    
    /** 
     * Returns the string representation of the board
     *
     * @author Manuel Diaz, Efrem Gebrekidane, Aryan Gupta
     * @return String - A textual representation of the state of the Board object
     * showing the chips that have been place
     */
    public String toString(){
        String table = new String();
        
        for(int i =0; i <board.length; i++){
            table += "|";
            for(int k = 0; k< board[i].length; k++){
                table += (board[i][k] == '\0') ? ' ': board[i][k];
                table += " | ";
                
            }
            table += " |\n";
        }
        return table;
    }
    
}


/* CHANGELOG
 Aug 1 - Aryan
    - Add doc for class and other methods
    - set getAvailable(int) function to private as it will only be called within the class
    - Formating
 Aug 9 - Manuel Diaz
    - Added comment to each method left
 Aug 10 - Aryan
    - Add documentation to non-doc'ed methods
    - Add grid to toString() method
    - Merged Manuel's edits into final version
*/