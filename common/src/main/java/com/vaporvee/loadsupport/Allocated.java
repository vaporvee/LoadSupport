package com.vaporvee.loadsupport;

import com.vaporvee.loadsupport.platform.Services;

import javax.swing.*;
import java.awt.*;

public class Allocated {
    public static float memoryInGB;
    public static void init(){
        memoryInGB = Runtime.getRuntime().maxMemory() / Constants.GIGABYTE;
        memoryInGB = Math.round(Allocated.memoryInGB * 10) / 10f;
    }
    public static void printAllocated() {
        Constants.LOG.info(String.format("Allocated Memory: %.1f GB", memoryInGB));
    }

    public static String[] getWarningMessage(){
        Config config = Services.CONFIG.getConfig();
        return new String[]{config.errorTitle, config.errorDescription
                .replace("{minMemory}", String.valueOf(config.minMemory))
                .replace("{currentMemory}", String.valueOf(memoryInGB))};
    };

    static boolean errorWindowOpen = false;
    private static JFrame errorWindow;

    public static void createErrorWindow() {
        try {
            if (!errorWindowOpen) {
                errorWindowOpen = true;
                SwingUtilities.invokeLater(() -> {
                    errorWindow = new JFrame(getWarningMessage()[0]);
                    errorWindow.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
                    errorWindow.setSize(400, 200);
                    errorWindow.setLocationRelativeTo(null);

                    JLabel message = new JLabel("<html><p style=\"width:200px\">"+ getWarningMessage()[1]+"</p></html>", JLabel.CENTER);
                    JButton exitButton = new JButton("OK");

                    exitButton.addActionListener(e -> {
                        errorWindow.dispose();
                        //minecraftClient.stop(); // how to get client crossplatform?
                    });

                    errorWindow.setLayout(new BorderLayout());
                    errorWindow.add(message, BorderLayout.CENTER);
                    errorWindow.add(exitButton, BorderLayout.SOUTH);
                    errorWindow.setVisible(true);
                });
            }
        } catch (RuntimeException e) {
            Constants.LOG.error(String.valueOf(e));
        }
    }
}
