package pieces;

import main.Board;
import main.Move;

import java.awt.image.BufferedImage;

public class King extends Piece
{
    public  King(Board board, int col, int row , boolean iswhite)
    {
        super(board);
        this.col = col;
        this.row = row;
        this.xpos = col * board.tileSize;
        this.ypos = row * board.tileSize;

        this.iswhite = iswhite;

        this.name = "King";

        this.sprite = sheet.getSubimage(0*sheetcale, iswhite?0:sheetcale , sheetcale,sheetcale).getScaledInstance(board.tileSize,board.tileSize, BufferedImage.SCALE_SMOOTH);

    }

    @Override
    public boolean isValidMovement(int col, int row)
    {
        return Math.abs((col-this.col)*(row - this.row)) == 1 || Math.abs(col-this.col) + Math.abs(row - this.row) ==1 || canCastle(col , row);
    }

    private  boolean canCastle(int col , int row)
    {
        if(this.row==row) {
            if (col == 6) {
                Piece rook = board.getpiece(7, row);
                if (rook != null && rook.isFirstMove && isFirstMove)
                    return board.getpiece(5, row) == null &&
                            board.getpiece(6, row) == null &&
                            !board.checkScanner.isKingChecked (new Move(board, this, 4, row) )&&
                            !board.checkScanner.isKingChecked (new Move(board, this, 5, row));
            } else if (col == 2) {

                Piece rook = board.getpiece(0, row);
                if (rook != null && rook.isFirstMove && isFirstMove)
                    return board.getpiece(1, row) == null &&
                            board.getpiece(2, row) == null &&
                            board.getpiece(3, row) == null &&
                            !board.checkScanner.isKingChecked(new Move(board, this, 4, row))
                                    &&!board.checkScanner.isKingChecked(new Move(board, this, 3, row))
                                    &&!board.checkScanner.isKingChecked(new Move(board, this, 2, row));


            }
        }
        return  false;
    }
}
