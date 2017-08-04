package com.P1520026;

import java.awt.*;

import static com.P1520026.Commons.BOARD_HEIGHT;
import static com.P1520026.Commons.BOARD_WIDTH;

public class ScreenText {
    private Graphics g;

    ScreenText(Graphics g) {
        this.g = g;
    }

    public void display(String texts[]) {
        Font font = new Font(
            "Helvetica",
            Font.BOLD,
            30
        );

        g.setColor(Color.white);
        g.setFont(font);

        FontMetrics metr = g.getFontMetrics(font);
        int height = metr.getHeight();

        if (texts.length % 2 == 0) {
            int offset = metr.getHeight() / 2;

            for (int k = 0; k < texts.length; k++) {
                String message = texts[k];
                //System.out.println(message);

                g.drawString(
                    message,
                    BOARD_WIDTH / 2 - metr.stringWidth(message) / 2,
                    BOARD_HEIGHT / 2 +  (k - texts.length / 2) * height
                );
            }

        } else {
            for (int k = 0; k < texts.length; k++) {
                String message = texts[k];

                //System.out.println(message);
                g.drawString(
                    message,
                    BOARD_WIDTH / 2 - metr.stringWidth(message) / 2,
                    BOARD_HEIGHT / 2 +  (k - (texts.length - 1) / 2) * height
                );
            }
        }
    }
}
