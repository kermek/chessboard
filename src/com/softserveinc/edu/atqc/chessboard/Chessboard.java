/*
 * Class Chessboard represents two dimensional chessboard
 *
 * @version  1.0 03 Sep 2014
 * @author   Yermek Kadyrbayev
 */


package com.softserveinc.edu.atqc.chessboard;

import java.util.ArrayList;

class Chessboard {

    private int height;
    private int width;
    private int currentFrontier;
    private final ArrayList<ArrayList<Integer>> cellStates = new ArrayList<ArrayList<Integer>>();
    static final int STATE_UNTOUCHED    =  0;
    private static final int STATE_BLOCKED      = -1;

    /*
     * Constructs chessboard of given size with random blocks in certain % of total space
     */
    public Chessboard(int width, int height, int percent) {
        if ((width < 0) || (height < 0)) {
            return;
        }
        this.width = width;
        this.height = height;
        this.currentFrontier = 1;
        int state;
        for (int i = 0; i < width; i++) {
            ArrayList<Integer> temp = new ArrayList<Integer>();
            for (int j = 0; j < height; j++) {
                state = ((Math.random() * 100) < percent) ? STATE_BLOCKED : STATE_UNTOUCHED;
                temp.add(state);
            }
            cellStates.add(temp);
        }
    }

    public int getCellStates(int x, int y) {
        if ((x < 0) || (x > width - 1) || (y < 0) || (y > height - 1)) {
            return -1;
        }
        ArrayList<Integer> temp = cellStates.get(x);
        return temp.get(y);
    }

    public void setCellStates(int x, int y, int state) {
        if ((x < 0) || (x > width - 1) || (y < 0) || (y > height - 1)) {
            return;
        }
        ArrayList<Integer> temp = cellStates.get(x);
        temp.set(y, state);
        cellStates.set(x, temp);
    }

    public static ArrayList<Figure> getAllValidMoves(Figure figure, Chessboard board, int state) {
        ArrayList<Figure> possibleCells = new ArrayList<Figure>();
        int[] offset = {-figure.getStepHeight(), -figure.getStepWidth(), figure.getStepWidth(), figure.getStepHeight()};

        for (int i: offset) {
            for (int j: offset) {
                if ((i != j) && (-i != j)) {
                    addFigureToArray(possibleCells, board, figure, figure.getX() + i, figure.getY() + j, state);
                }
            }
        }

        /*
        addFigureToArray(possibleCells, board, new Figure(figure, board, figure.getX() + figure.getStepHeight(), figure.getY() - figure.getStepWidth() ));
        addFigureToArray(possibleCells, board, new Figure(figure, board, figure.getX() + figure.getStepHeight(), figure.getY() + figure.getStepWidth() ));
        addFigureToArray(possibleCells, board, new Figure(figure, board, figure.getX() - figure.getStepHeight(), figure.getY() + figure.getStepWidth() ));
        addFigureToArray(possibleCells, board, new Figure(figure, board, figure.getX() - figure.getStepHeight(), figure.getY() - figure.getStepWidth() ));
        addFigureToArray(possibleCells, board, new Figure(figure, board, figure.getX() + figure.getStepWidth(),  figure.getY() + figure.getStepHeight()));
        addFigureToArray(possibleCells, board, new Figure(figure, board, figure.getX() + figure.getStepWidth(),  figure.getY() - figure.getStepHeight()));
        addFigureToArray(possibleCells, board, new Figure(figure, board, figure.getX() - figure.getStepWidth(),  figure.getY() + figure.getStepHeight()));
        addFigureToArray(possibleCells, board, new Figure(figure, board, figure.getX() - figure.getStepWidth(),  figure.getY() - figure.getStepHeight()));
        */
        return possibleCells;
    }
    /*
    private static void addFigureToArray(ArrayList<Figure> array, Chessboard board,Figure figure) {
        if ((figure.getType() != null) && (board.getCellStates(figure.getX(), figure.getY())) == STATE_UNTOUCHED) {
            array.add(figure);
        }
    }
    */
    private static void addFigureToArray(ArrayList<Figure> array, Chessboard board, Figure figure, int x, int y, int state) {
        if ((figure.getType() != null) && (board.getCellStates(x, y)) == state) {
            array.add(new Figure(figure, board, x,  y));
        }
    }

    public int getCurrentFrontier() {
        return currentFrontier;
    }

    public void increaseCurrentFrontier() {
        this.currentFrontier = this.currentFrontier + 1;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }
}