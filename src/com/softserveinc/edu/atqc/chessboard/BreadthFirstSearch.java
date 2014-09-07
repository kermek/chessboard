/**
 * Chessboard performs solution of the knight task using breadth-first search iterative algorithm.
 * Initial values include chessboard dimension, start, end, figure step.
 * Output written in json files.
 * Result view through path.html
 *
 * @version  1.0 03 Sep 2014
 * @author   Yermek Kadyrbayev
*
 */


package com.softserveinc.edu.atqc.chessboard;

import java.util.*;

/*
* Class BreadFirstSearch solves the task of chessboard
* It finds optimal way of figure moving from start to finish on the chessboard
*
* @version  1.0 03 Sep 2014
* @author   Yermek Kadyrbayev
 */
class BreadthFirstSearch {

    //private int boardHeight;
    //private int boardWidth;
    //private int currentFrontier;


    //private ArrayList<ArrayList<Integer>> cellStates = new ArrayList<ArrayList<Integer>>();
    //static final int STATE_UNTOUCHED    =  0;
    //static final int STATE_BLOCKED      = -1;

    public static void main(String[] args) {
        int n                   = 1000;
        int blockPercent        = 60;
        int xStart              = 0;
        int yStart              = 0;
        int xFinish             = 999;
        int yFinish             = 999;
        String figureType       = "knight";
        int stepWidth           = 2;
        int stepHeight          = 1;
        String fileJSONboard    = "C:\\Users\\ykadytc\\IdeaProjects\\chessboard\\json\\board.json";
        String fileJSONpath     = "C:\\Users\\ykadytc\\IdeaProjects\\chessboard\\json\\path.json";
        int frontier;
        Queue<Figure> queue     = new LinkedList<Figure>();
        ArrayList<Figure> possibilities;

        Chessboard          board       = new Chessboard(n, n, blockPercent);
        Figure              figure      = new Figure(xStart, yStart, figureType, stepWidth, stepHeight);

        queue.add(figure);
        board.setCellStates(xStart,  yStart,  board.getCurrentFrontier());
        board.setCellStates(xFinish, yFinish, Chessboard.STATE_UNTOUCHED);
        frontier = 0;

        while (!queue.isEmpty()) {
            figure = queue.poll();
            if (frontier != board.getCellStates(figure.getX(), figure.getY())) {
                frontier = board.getCurrentFrontier();
                System.out.println(frontier);
                board.increaseCurrentFrontier();
            }
            possibilities = Chessboard.getAllValidMoves(figure, board, Chessboard.STATE_UNTOUCHED);
            for (Figure possible: possibilities) {
                if ((possible.getX() == xFinish) && (possible.getY() == yFinish)) {
                    System.out.println("Found target cell after " + frontier + " steps.");
                    board.setCellStates(possible.getX(), possible.getY(), frontier);
                    findPath(board, possible, fileJSONboard, fileJSONpath);
                    return;
                }
                queue.add(possible);
                board.setCellStates(possible.getX(), possible.getY(), board.getCurrentFrontier());
                }
            }
            System.out.println("Target could not be reached");
        }
    private static void findPath(Chessboard board, Figure figure, String fileJSONboard, String fileJSONpath) {
        int frontier;
        ArrayList<Figure> possibilities;
        ArrayList<Figure> path = new ArrayList<Figure>();

        frontier = board.getCellStates(figure.getX(), figure.getY());

        while (frontier > 0) {
            path.add(figure);
            possibilities = Chessboard.getAllValidMoves(figure, board, frontier--);
            figure = possibilities.get(0);
            }

        System.out.println("\nPath:");
        Collections.reverse(path);
        for (Figure obj: path) {
            System.out.println(obj.getX() + "," + obj.getY());
        }
        WriteToJSON.writeToJSON(fileJSONboard, board);
        WriteToJSON.writeToJSON(fileJSONpath, path);
    }
}