package pieces;

import main.Board;

import java.awt.image.BufferedImage;

public class Queen extends Piece {
    public Queen(Board board, int col, int row, boolean iswhite) {
        super(board);
        this.col = col;
        this.row = row;
        this.xpos = col * board.tileSize;
        this.ypos = row * board.tileSize;

        this.iswhite = iswhite;

        this.name = "Queen";

        this.sprite = sheet.getSubimage(1 * sheetcale, iswhite ? 0 : sheetcale, sheetcale, sheetcale).getScaledInstance(board.tileSize, board.tileSize, BufferedImage.SCALE_SMOOTH);

    }

    @Override
    public boolean isValidMovement(int col, int row) {
        return this.col == col || this.row == row || Math.abs(this.col - col) == Math.abs(this.row - row);
    }

    public boolean moveCollidesWithPiece(int col, int row) {

        if (this.col == col || this.row == row) {
            if (this.col > col)
                for (int i = this.col - 1; i > col; i--)
                    if (board.getpiece(i, this.row) != null)
                        return true;

            //checking right
            if (this.col < col)
                for (int i = this.col + 1; i < col; i++)
                    if (board.getpiece(i, this.row) != null)
                        return true;

            //checking up
            if (this.row > row)
                for (int i = this.row - 1; i > row; i--)
                    if (board.getpiece(this.col, i) != null)
                        return true;

            //checking down
            if (this.row < row)
                for (int i = this.row + 1; i < row; i++)
                    if (board.getpiece(this.col, i) != null)
                        return true;

        } else {
            //up left

            if (this.col > col && this.row > row)
                for (int i = 1; i < Math.abs(this.col - col); i++)
                    if (board.getpiece(this.col - i, this.row - i) != null)
                        return true;

            //up right
            if (this.col < col && this.row > row)
                for (int i = 1; i < Math.abs(this.col - col); i++)
                    if (board.getpiece(this.col + i, this.row - i) != null)
                        return true;
            //down left
            if (this.col > col && this.row < row)
                for (int i = 1; i < Math.abs(this.col - col); i++)
                    if (board.getpiece(this.col - i, this.row + i) != null)
                        return true;

            //down right
            if (this.col < col && this.row < row)
                for (int i = 1; i < Math.abs(this.col - col); i++)
                    if (board.getpiece(this.col + i, this.row + i) != null)
                        return true;
        }
        return false;
    }
}