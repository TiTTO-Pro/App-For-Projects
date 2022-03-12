package com.app_for_projects;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class TransparentTextArea {

    public TransparentTextArea() {
        EventQueue.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            }
            catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ignored) {
            }
        });
    }
    public static class CustomTextArea extends JTextArea {
        File desktop_path = FileSystemView.getFileSystemView().getHomeDirectory();
        private BufferedImage image;

        public CustomTextArea() {
            super(240, 20);
            try {
                image = ImageIO.read(new File(desktop_path + "\\App-for-projects\\src\\com\\app_for_projects\\Background3.jpeg"));
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

        @Override
        public boolean isOpaque() {
            return false;
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2d = (Graphics2D) g.create();
            g2d.setColor(getBackground());
            g2d.fillRect(0, 0, getWidth(), getHeight());
            if (image != null) {
                int x = getWidth() - image.getWidth();
                int y = getHeight() - image.getHeight();
                g2d.drawImage(image, x, y, this);
            }

            super.paintComponent(g2d);
            g2d.dispose();
        }
    }
}
