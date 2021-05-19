import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;
import javax.swing.JComponent;

public class ToggleBtn extends JComponent {
    private boolean isOn = true;
    private Color backgroundColor;
    private Color buttonColor;
    private final Color disabledColor = new Color(131, 131, 131);

    public ToggleBtn() {
        super();
        setSize(new Dimension(60, 30));
        setCursor(new Cursor(Cursor.HAND_CURSOR));
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (isEnabled()) {
                    isOn = !isOn;
                    repaint();
                }
            }
        });
        setBackgroundColor(new Color(75, 216, 101));
        setButtonColor(new Color(255, 255, 255));
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        if (isOpaque()) {
            g2.setColor(getBackground());
            g2.fill(new Rectangle2D.Double(0, 0, getWidth(), getHeight()));
        }

        int margin = 5;
        if (isEnabled()) {
            g2.setColor(((isOn) ? getBackgroundColor() : new Color(216, 217, 219)));
            g2.fill(new RoundRectangle2D.Double((float) margin, (float) margin,
                    (float) getWidth() - margin * 2, (float) getHeight() - margin * 2,
                    getHeight() - margin * 2, getHeight() - margin * 2));
        } else {
            g2.setColor(disabledColor);
            g2.draw(new RoundRectangle2D.Double((float) margin, (float) margin,
                    (float) getWidth() - margin * 2, (float) getHeight() - margin * 2,
                    getHeight() - margin * 2, getHeight() - margin * 2));
        }

        g2.setColor((isEnabled()) ? getButtonColor() : disabledColor);

        int border = 4;
        if (isOn) {
            g2.fillOval(margin + border / 2, margin + border / 2,
                    getHeight() - margin * 2 - border, getHeight() - margin * 2 - border);
        } else {
            g2.fillOval(getWidth() - getHeight() + margin + border / 2, margin + border / 2,
                    getHeight() - margin * 2 - border, getHeight() - margin * 2 - border);
        }
    }

    public boolean isOn() {
        return isOn;
    }

    public Color getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(Color backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public Color getButtonColor() {
        return buttonColor;
    }

    public void setButtonColor(Color buttonColor) {
        this.buttonColor = buttonColor;
    }


}