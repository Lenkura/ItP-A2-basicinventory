import java.awt.Color;
import java.awt.Font;

public class Assignment2 {

	public static void main(String[] args) {
		GTerm gt = new GTerm(800, 400);
		gt.setFontName(Font.SERIF);
		gt.println("Assignment 2 ");
		gt.setXY(0, 200);
		gt.setFontSize(20);
		gt.setFontStyle(Font.BOLD);
		gt.setFontName(Font.SANS_SERIF);
		gt.setFontColor(102, 102, 255);
		gt.println("Dessert is the best meal");
		gt.setFontColor(255, 100, 100);
	}

}
