package pieces;

import main.Board;

import java.awt.image.BufferedImage;

public class Bishop extends Piece {

    public Bishop(Board board, int col, int row, boolean iswhite) {
        super(board);
        this.col = col;
        this.row = row;
        this.xpos = col * board.tileSize;
        this.ypos = row * board.tileSize;

        this.iswhite = iswhite;

        this.name = "Bishop";

        this.sprite = sheet.getSubimage(2 * sheetcale, iswhite ? 0 : sheetcale, sheetcale, sheetcale).getScaledInstance(board.tileSize, board.tileSize, BufferedImage.SCALE_SMOOTH);

    }

    public boolean isValidMovement(int col, int row) {
        return Math.abs(this.col - col) == Math.abs(this.row - row);


    }

    @Override
    public boolean moveCollidesWithPiece(int col, int row) {
        //up left

        if (this.col > col &&  this.row>row)
            for(int i =1; i < Math.abs(this.col - col); i++)
                if(board.getpiece(this.col -i,this.row -i)!= null)
            return true;

        //up right
        if (this.col < col &&  this.row>row)
            for(int i =1; i < Math.abs(this.col - col); i++)
                if(board.getpiece(this.col +i,this.row -i)!= null)
                    return true;
       //down left
        if (this.col > col &&  this.row<row)
            for(int i =1; i < Math.abs(this.col - col); i++)
                if(board.getpiece(this.col -i,this.row +i)!= null)
                    return true;

        //down right
        if (this.col < col &&  this.row<row)
            for(int i =1; i < Math.abs(this.col - col); i++)
                if(board.getpiece(this.col +i,this.row +i)!= null)
                    return true;

            return false;

    }
}
