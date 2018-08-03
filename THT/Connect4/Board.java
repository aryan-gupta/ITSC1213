
/**
 * This class represents a Board for a Connect 4 game. You can
 * place a chip at any column and the class will automatically 
 * figure out the next available slot for the chip in that column
 * and place the chip there. The class also has method for checking
 * the winner, if any \sa getWinner() 
 *
 * @author Manuel Diaz, Efrem Gebrekidane, Aryan Gupta
 * @version 1.0
 */
public class Board{
    private char[][] board;
    
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
        // sigh... https://stackoverflow.com/questions/9963325/shortening-type-names-in-java
        
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
        // 4       X O     X
        // 5       X   O   X
        // 6       X     O X
        // 7 X X X X X X X X X X
        // 8       X       X
        // 9       X       X
        // To show diagonal distance I have added a few Os's
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
    private int getInRowLength(char ch, int depth, int dx, int dy, int r, int c) {
        /// @todo change algorithm to use dr and dc
        
        // we are given a direction by \p dx and \p dy. The algorithm travels that direction
        // until it reaches a char that is not equal to \p ch. Each call of this function
        // is the next place in the direction of dx/dy, depth is incremented receptively
        // This function should be run in every 8 cardinal direction (n s e w ne nw se sw)        
    
        // If we are out of bounds then just return how far we have gotten
        if (checkOutOfBounds(r, c))
            return depth;
        
        // if we have the same char as the 0th depth then continue deeper
        // else just return the depth because this depth is the last one in
        // a sequence
        if (board[r][c] == ch)
            return getInRowLength(ch, depth + 1, dx, dy, r + dy, c + dx);
        return depth;
    }
    
    /**
     * Checks if the row and column we pass in are within the board
     *
     * @author Aryan Gupta
     * @return boolean - If the parameters passed in are within the bounds of the board
     * @param int r - The row number to check
     * @param int c - The column number to check
     */
    private boolean checkOutOfBounds(int r, int c) {
        return
           r >= board   .length
        || c >= board[0].length
        || r <  0
        || c <  0;
        
    }
    
    public void place(int columm, char player){
        int index = getAvailable(columm);
        if (index== -1){
            System.out.println("this row is filled"); return;
        }
        board[index][columm] = player;
        
    }
    
    private int getAvailable(int columm){
      for(int i = 0; i< board.length; i++){
          if (board[i][columm] != '\0'){
             return i - 1;
            }  
        }
        return board.length -1;
    }
    
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
    
    public String toString(){
        String table = new String();
        
        for(int i =0; i <board.length; i++){
            for(int k = 0; k< board[i].length; k++){
                table += (board[i][k] == '\0') ? ' ': board[i][k];
                table += " ";
                
            }
            table += "\n";
        }
        return table;
    }
    
}


/* CHANGELOG
 Aug 1 - Aryan
    - Add doc for class and other methods
    - set getAvailable(int) function to private as it will only be called within the class
    - Formating
*/