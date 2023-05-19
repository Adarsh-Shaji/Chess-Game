package main;

import pieces.Piece;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class Input extends MouseAdapter {

Board board;
    public Input(Board board)
    {
        this.board = board;
    }


    @Override
    public void mousePressed(MouseEvent e) {

        int col = e.getX()/board.tileSize;
        int row = e.getY()/board.tileSize;

        Piece pieceXY = board.getpiece(col , row);
        if(pieceXY!=null &&(board.count % 2 ==0 && pieceXY.iswhite )|| (board.count % 2 ==1 && !pieceXY.iswhite))
        {
            board.selectedPiece = pieceXY;
        }
    }

    @Override
    public void mouseReleased(MouseEvent e)
    {
        int col = e.getX()/board.tileSize;
        int row = e.getY()/board.tileSize;

        if(board.selectedPiece!=null)
        {
            Move move = new Move(board,board.selectedPiece,col , row);

            if(board.isValidMove(move))
            {

                    board.makeMove(move);


            }
            else {
                board.selectedPiece.xpos = board.selectedPiece.col * board.tileSize;
                board.selectedPiece.ypos = board.selectedPiece.row * board.tileSize;
            }
        }

        board.selectedPiece = null;
        board.repaint();
    }



    @Override
    public void mouseDragged(MouseEvent e)

    {
        if(board.selectedPiece!=null)
        {
            board.selectedPiece.xpos = e.getX() - board.tileSize/2;
            board.selectedPiece.ypos = e.getY() - board.tileSize/2;

            board.repaint();
        }

    }


}
