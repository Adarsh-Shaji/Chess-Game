package pieces;

import main.Board;

import java.awt.image.BufferedImage;

public class Pawn extends Piece
{
    public  Pawn(Board board, int col, int row , boolean iswhite)
    {
        super(board);
        this.col = col;
        this.row = row;
        this.xpos = col * board.tileSize;
        this.ypos = row * board.tileSize;

        this.iswhite = iswhite;

        this.name = "Pawn";

        this.sprite = sheet.getSubimage(5*sheetcale, iswhite?0:sheetcale , sheetcale,sheetcale).getScaledInstance(board.tileSize,board.tileSize, BufferedImage.SCALE_SMOOTH);

    }

    @Override
    public boolean isValidMovement(int col, int row) {
       int colorIndex = iswhite?1:-1;

       //push pawn 1

        if(this. col == col && row == this.row - colorIndex && board.getpiece(col , row)== null)
            return true;

        //push pawn 2
        if(isFirstMove && this.col == col && row == this.row - colorIndex * 2 && board.getpiece(col , row) ==null && board.getpiece(col , row +colorIndex) ==null)
            return  true;

        //capture leftside

        if(col == this.col -1 && row ==this.row - colorIndex && board.getpiece(col , row) != null)
            return  true;
            //capture rightside

        if(col == this.col +1 && row ==this.row - colorIndex && board.getpiece(col , row) != null)
            return  true;

        //en passant left
        if(board.getTileNum(col,row) == board.enPassantTile && col == this.col -1 && row==this.row - colorIndex && board.getpiece(col , row + colorIndex) !=null)
            return  true;

        if(board.getTileNum(col,row) == board.enPassantTile && col == this.col + 1 && row==this.row - colorIndex && board.getpiece(col , row + colorIndex) !=null)
            return  true;

        return false;

    }
}
