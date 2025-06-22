package com.vaporvee.loadsupport;

import com.vaporvee.loadsupport.platform.Services;

import javax.swing.*;
import java.awt.*;
import java.net.URI;
import java.util.Objects;

public class Allocated {
    public static float memoryInGB;
    public static void init(){
        memoryInGB = Runtime.getRuntime().maxMemory() / Constants.GIGABYTE;
        memoryInGB = Math.round(Allocated.memoryInGB * 10) / 10f;
    }
    public static void printAllocated() {
        Constants.LOG.info(String.format("Allocated Memory: %.1f GB", memoryInGB));
    }

    public static String[] getWarningMessage() {
        Config config = Services.CONFIG.getConfig();

        String title = stripHtml(config.errorTitle);
        String minMemoryText = stripHtml(config.errorMinMemory);
        String currentMemoryText = stripHtml(config.errorCurrentMemory);

        String minMemoryFormatted = String.format("<span style=\"color:green\">%.1f</span>", config.minMemory);
        String currentMemoryFormatted = String.format("<span style=\"color:red\">%.1f</span>", memoryInGB);

        String heading = Constants.MOD_NAME + " - " + title;
        String body = minMemoryText.replace("{minMemory}", minMemoryFormatted)
                + "<br><br>"
                + currentMemoryText.replace("{currentMemory}", currentMemoryFormatted);
        return new String[] { heading, body, config.memoryInfoLink };
    }

    private static String stripHtml(String input) {
        return input == null ? "" : input.replaceAll("<[^>]*>", "");
    }

    public static boolean enoughMemory = true;
    private static JFrame errorWindow;

    public static boolean isWindowOpen(){
        return errorWindow.isDisplayable();
    }

    public static void createMemoryError() {
        try {
            if (enoughMemory) {
                enoughMemory = false;
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                SwingUtilities.invokeLater(() -> {
                    String[] warning = getWarningMessage();
                    errorWindow = new JFrame(warning[0]);
                    Image icon = new ImageIcon(Objects.requireNonNull(Allocated.class.getResource("/assets/" + Constants.MOD_ID + "/icon.png"))).getImage();
                    errorWindow.setIconImage(icon);
                    errorWindow.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
                    errorWindow.setSize(500, 350);
                    errorWindow.setLocationRelativeTo(null);

                    JLabel message = new JLabel(
                            "<html><p style=\"width:350px; font-size:20px;\">" + warning[1] + "</p></html>",
                            JLabel.CENTER
                    );

                    JButton okButton = new JButton("OK");
                    okButton.addActionListener(e -> errorWindow.dispose());

                    int buttonCount = 1 + (!warning[2].isBlank() ? 1 : 0);

                    JPanel buttonPanel = new JPanel(new GridLayout(1, buttonCount, 10, 0));
                    buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

                    buttonPanel.add(okButton);

                    if (!warning[2].isBlank()) {
                        JButton moreInfoButton = new JButton("More info");
                        moreInfoButton.addActionListener(e -> {
                            try {
                                Desktop.getDesktop().browse(new URI(warning[2]));
                            } catch (Exception ex) {
                                Constants.LOG.error(String.valueOf(ex));
                            }
                        });
                        buttonPanel.add(moreInfoButton);
                    }

                    errorWindow.setLayout(new BorderLayout());
                    errorWindow.add(message, BorderLayout.CENTER);
                    errorWindow.add(buttonPanel, BorderLayout.SOUTH);
                    errorWindow.setVisible(true);
                });
            }
        } catch (RuntimeException | ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            Constants.LOG.error(String.valueOf(e));
        } catch (UnsupportedLookAndFeelException e) {
            throw new RuntimeException(e);
        }
    }
}
