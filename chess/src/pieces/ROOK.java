package pieces;

import main.Board;

import java.awt.image.BufferedImage;

public class ROOK extends Piece
{
    public  ROOK(Board board, int col, int row , boolean iswhite)
    {
        super(board);
        this.col = col;
        this.row = row;
        this.xpos = col * board.tileSize;
        this.ypos = row * board.tileSize;

        this.iswhite = iswhite;

        this.name = "Rook";

        this.sprite = sheet.getSubimage(4*sheetcale, iswhite?0:sheetcale , sheetcale,sheetcale).getScaledInstance(board.tileSize,board.tileSize, BufferedImage.SCALE_SMOOTH);

    }

    public boolean isValidMovement(int col,int row)
    {
        return  this.col ==col || this.row == row;



    }

    @Override
    public boolean moveCollidesWithPiece(int col, int row)
    {
        //checking left
        if(this.col>col)
            for(int i =this.col -1;i>col;i--)
                if(board.getpiece(i,this.row)!=null)
        return true;

        //checking right
        if(this.col<col)
            for(int i =this.col +1;i<col;i++)
                if(board.getpiece(i,this.row)!=null)
        return true;

        //checking up
        if(this.row>row)
            for(int i =this.row -1;i>row;i--)
                if(board.getpiece(this.col,i)!=null)
        return true;

        //checking down
        if(this.row<row)
            for(int i =this.row +1;i<row;i++)
                if(board.getpiece(this.col,i)!=null)
        return true;
return false;
    }
}
