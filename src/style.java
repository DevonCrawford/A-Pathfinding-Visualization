import java.awt.Color;
import java.awt.Font;

/* Custom fonts and colours used in "Frame.java" class
 * by Devon Crawford
 */
public @interface style {
	Font bigText = new Font("arial", Font.BOLD, 24);
	Font REALBIGText = new Font("arial", Font.BOLD, 72);
	Font numbers = new Font("arial", Font.BOLD, 12);
	Font smallNumbers = new Font("arial", Font.PLAIN, 11);
	Color greenHighlight = new Color(132, 255, 138);
	Color redHighlight = new Color(253, 90, 90);
	Color blueHighlight = new Color(32, 233, 255);
	Color btnPanel = new Color(120, 120, 120, 80);
	Color darkText = new Color(48, 48, 48);
	Color lightText = new Color(232, 232, 232);
}
