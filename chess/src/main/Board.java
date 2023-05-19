package main;

import pieces.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Board extends JPanel
{
    public int tileSize = 85;
    int rows=8;
    int cols =8;

    int count=0;

    public Piece selectedPiece;

    Input input = new Input(this);



    ArrayList<Piece> pieceList = new ArrayList<>();

    public int enPassantTile = -1;

    public CheckScanner checkScanner = new CheckScanner(this);

    public  Board()
    {
        this.setPreferredSize(new Dimension(cols*tileSize,rows*tileSize));
        this.addMouseListener(input);
        this.addMouseMotionListener(input);
        addPieces();;

    }

    public Piece getpiece(int col , int row)
    {
        for (Piece piece :pieceList) {
            if (piece.col == col && piece.row == row) {
                return piece;
            }
        }
        return  null;
    }


    public void makeMove(Move move)
    {count++;

        if(move.piece.name.equals("Pawn"))
        {
            movePawn(move);
        }
        else if(move.piece.name.equals("King"))
        {
            moveKing(move);
        }

        move.piece.col = move.newCol;
        move.piece.row = move.newRow;
        move.piece.xpos=move.newCol*tileSize;

        move.piece.isFirstMove = false;

        capture(move.capture);
        System.out.println(count);

    }

    private void moveKing(Move move)
    {
        if(Math.abs(move.piece.col - move.newCol )== 2)
        {
            Piece rook;

            if(move.piece.col<move.newCol)
            {
                rook = getpiece(7,move.piece.row);
                rook.col = 5;
            }
            else {
                rook = getpiece(0,move.piece.row);
                rook.col = 3;
            }
            rook.xpos = rook.col *tileSize;
        }
    }
    private  void movePawn(Move move)
    {
        int colorIndex = move.piece.iswhite ? 1:-1;

        if(getTileNum(move.newCol,move.newRow) == enPassantTile)
        {
            move.capture = getpiece(move.newCol,move.newRow + colorIndex);
        }

        if(Math.abs(move.piece.row -move.newRow) == 2)
        {
            enPassantTile = getTileNum(move.newCol, move.newRow + colorIndex);
        }
        else {
            enPassantTile =-1;
        }

        //promotions
        colorIndex = move.piece.iswhite ? 0 : 7;
        if(move.newRow == colorIndex)
        {
            promotePawn(move);
        }

        move.piece.col = move.newCol;
        move.piece.row = move.newRow;
        move.piece.xpos=move.newCol*tileSize;

        move.piece.isFirstMove = false;

        capture(move.capture);
    }

   private  void promotePawn(Move move)
   {
//       System.out.println("enter choice for promoting pawn");  ///for choosing upon promotion but requires input from user
//       System.out.println(" b - for bishop");
//       System.out.println(" r - for rook");
//       System.out.println(" b - for knight");
//       System.out.println(" q - for queen");
//       int n = new Scanner(System.in).next().charAt(0);
//
//       switch (n)
//       {
//                        case 'q' :pieceList.add(new Queen(this ,move.newCol , move.newRow , move.piece.iswhite));
//                        break;
//                        case 'b' :pieceList.add(new Bishop(this ,move.newCol , move.newRow , move.piece.iswhite));
//                        break;
//                        case 'k' :pieceList.add(new Knight(this ,move.newCol , move.newRow , move.piece.iswhite));
//                        break;
//                        case 'r' :pieceList.add(new ROOK(this ,move.newCol , move.newRow , move.piece.iswhite));
//                        break;
//       }
       pieceList.add(new Queen(this ,move.newCol , move.newRow , move.piece.iswhite));
       capture(move.piece);
   }



    public void capture(Piece piece)
    {
        pieceList.remove(piece);

    }
    public boolean isValidMove(Move move) {
        if (sameTeam(move.piece, move.capture))
        {

            return false;

        }

        if (!move.piece.isValidMovement(move.newCol, move.newRow))
        {
            return false;
        }
        if(move.piece.moveCollidesWithPiece(move.newCol , move.newRow))
        {
            return false;
        }
        if(checkScanner.isKingChecked(move))
        {
            return false;
        }
            return true;

    }

    public boolean sameTeam(Piece p1,Piece p2)
    {
        if(p1 ==null || p2 ==null)
        {

            return false;
        }

        return p1.iswhite == p2.iswhite;
    }

    public  int getTileNum(int col , int row)
    {
        return  row*rows + col;
    }

    Piece findking(boolean iswhite)
    {
        for(Piece piece : pieceList)
        {
            if(iswhite ==piece.iswhite && piece.name.equals("King"))
            {
                return piece;
            }
        }
        return null;
    }
    public void addPieces()
    {

        pieceList.add(new Knight(this,1,0,false));
        pieceList.add(new Knight(this,6,0,false));

        pieceList.add(new Bishop(this,2,0,false));
        pieceList.add(new Bishop(this,5,0,false));

        pieceList.add(new ROOK(this,0,0,false));
        pieceList.add(new ROOK(this,7,0,false));

        pieceList.add(new King(this,4,0,false));

        pieceList.add(new Queen(this,3,0,false));

        pieceList.add(new Pawn(this,0,1,false));
        pieceList.add(new Pawn(this,1,1,false));
        pieceList.add(new Pawn(this,2,1,false));
        pieceList.add(new Pawn(this,3,1,false));
        pieceList.add(new Pawn(this,4,1,false));
        pieceList.add(new Pawn(this,5,1,false));
        pieceList.add(new Pawn(this,6,1,false));
        pieceList.add(new Pawn(this,7,1,false));


        // for other side
        pieceList.add(new Knight(this,1,7,true));
        pieceList.add(new Knight(this,6,7,true));

        pieceList.add(new Bishop(this,2,7,true));
        pieceList.add(new Bishop(this,5,7,true));

        pieceList.add(new ROOK(this,0,7,true));
        pieceList.add(new ROOK(this,7,7,true));

        pieceList.add(new King(this,4,7,true));

        pieceList.add(new Queen(this,3,7,true));

        pieceList.add(new Pawn(this,0,6,true));
        pieceList.add(new Pawn(this,1,6,true));
        pieceList.add(new Pawn(this,2,6,true));
        pieceList.add(new Pawn(this,3,6,true));
        pieceList.add(new Pawn(this,4,6,true));
        pieceList.add(new Pawn(this,5,6,true));
        pieceList.add(new Pawn(this,6,6,true));
        pieceList.add(new Pawn(this,7,6,true));
    }


    public void paint(Graphics g)
    {
        Graphics2D g2d = (Graphics2D) g;
        //paint board
        for (int i = 0; i < rows; i++)
            for (int j = 0; j < cols; j++)
            {

                g2d.setColor((i+j) % 2 == 0 ? new Color(208, 238, 210, 255) :  new Color(1, 103, 5, 255));
                g2d.fillRect(i * tileSize,j * tileSize,tileSize,tileSize);

            }
         //paint highlights
        if(selectedPiece!=null)
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++)
            {
                if(isValidMove((new Move(this,selectedPiece,j,i))))
                {
                    g2d.setColor(new Color(246, 7, 7, 113));
                    g2d.fillRect(j*tileSize,i*tileSize,tileSize,tileSize);

//                    g2d.setColor(new Color(3,4,5, 48));
//                    g2d.fillRect(selectedPiece.col*tileSize,selectedPiece.row*tileSize,tileSize,tileSize);
                }
            }
        }

        //paint pieces
        for(Piece piece : pieceList)
            piece.paint(g2d);


    }
}
