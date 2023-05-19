package pieces;

import main.Board;

import java.awt.image.BufferedImage;

public class Knight extends Piece
{
    public  Knight(Board board, int col, int row , boolean iswhite)
    {
        super(board);
        this.col = col;
        this.row = row;
        this.xpos = col * board.tileSize;
        this.ypos = row * board.tileSize;

        this.iswhite = iswhite;

        this.name = "Knight";

                this.sprite = sheet.getSubimage(3*sheetcale, iswhite?0:sheetcale , sheetcale,sheetcale).getScaledInstance(board.tileSize,board.tileSize, BufferedImage.SCALE_SMOOTH);

    }

    public boolean isValidMovement(int col,int row)
    {
        return Math.abs(col-this.col)*Math.abs((row-this.row))==2;
    }
}
