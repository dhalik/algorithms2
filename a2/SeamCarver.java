import java.awt.Color;

public class SeamCarver {
    private Picture picture;
    private final int boarderValue = 195075;


    // create a seam carver object based on the given picture
    public SeamCarver(Picture picture) {
        this.picture = new Picture(picture);
    }

    // current picture
    public Picture picture() {
        return picture;
    }

    // width of current picture
    public int width() {
        return picture.width();
    }

    // height of current picture
    public int height() {
        return picture.height();
    }

    private double getGrad(Color prev, Color next) {
        return Math.abs(next.getBlue() - prev.getBlue()) * Math.abs(next.getBlue() - prev.getBlue()) +
            Math.abs(next.getRed() - prev.getRed()) * Math.abs(next.getRed() - prev.getRed()) +
            Math.abs(next.getGreen() - prev.getGreen()) * Math.abs(next.getGreen() - prev.getGreen());
    }

    // energy of pixel at column x and row y
    public  double energy(int x, int y) {
        if (x == width()-1 || x == 0 || y >= (height() - 1) || y == 0)
            return boarderValue;
        Color nextx = picture.get(x+1,y);
        Color prevx = picture.get(x-1,y);
        Color nexty = picture.get(x,y-1);
        Color prevy = picture.get(x,y+1);
        return getGrad(nextx, prevx) + getGrad(nexty, prevy);
    }

    // sequence of indices for horizontal seam
    public   int[] findHorizontalSeam() {
        EdgeWeightedDigraph ewd = new EdgeWeightedDigraph(width()*height());
        for (int i = 0; i < width(); i++){
          for (int j = 0; j < height(); j++){
          }
        }
        AcyclicSP shortestPath = new AcyclicSP()
        return new int[5];
    }

    // sequence of indices for vertical seam
    public   int[] findVerticalSeam() {
        return new int[5];
    }

    // remove horizontal seam from current picture
    public    void removeHorizontalSeam(int[] seam) {
    }

    // remove vertical seam from current picture
    public    void removeVerticalSeam(int[] seam) {
    }
}

