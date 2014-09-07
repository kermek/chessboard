/*
* Class Figure represents chess figure
*
* @version  1.0 03 Sep 2014
* @author   Yermek Kadyrbayev
 */


package com.softserveinc.edu.atqc.chessboard;

class Figure {
    private int x;
    private int y;
    private String type;
    private int stepHeight;
    private int stepWidth;

    /*
     * Constructor of new figure of type in location
     */
    public Figure(int x, int y, String type, int stepWidth, int stepHeight) {
        this.x          = x;
        this.y          = y;
        this.type       = type;
        this.stepWidth  = stepWidth;
        this.stepHeight = stepHeight;
    }

    /*
     * Constructor of the given figures copy with another location
     */
    public Figure(Figure figure, Chessboard board, int x, int y) {
        if ((x < 0) || (x > board.getWidth() - 1) || (y < 0) || (y > board.getHeight() - 1)) {
            return;
        }
        this.x          = x;
        this.y          = y;
        this.type       = figure.type;
        this.stepWidth  = figure.stepWidth;
        this.stepHeight = figure.stepHeight;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public String getType() {
        return type;
    }

    public int getStepHeight() {
        return stepHeight;
    }

    public int getStepWidth() {
        return stepWidth;
    }
}