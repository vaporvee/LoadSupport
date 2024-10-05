package com.vaporvee.loadsupport;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.AccessibilityOnboardingScreen;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.TitleScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.client.gui.components.Button;
import net.minecraft.util.FormattedCharSequence;


import javax.swing.*;
import java.awt.*;
import java.util.List;


public class LSWindow {
    static boolean windowOpen = false;
    private static JFrame window;

    public static void createWindow(Minecraft minecraftClient, Screen screen) {
        if(screen instanceof TitleScreen || screen instanceof AccessibilityOnboardingScreen){
            minecraftClient.setScreen(new BlockedScreen());
            if(window != null){
                window.dispose();
            }
            return;
        }
        try {
            if (!windowOpen) {
                windowOpen = true;
                SwingUtilities.invokeLater(() -> {
                    window = new JFrame(LoadSupport.getWarningMessage()[0]);
                    window.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
                    window.setSize(400, 200);
                    window.setLocationRelativeTo(null);

                    JLabel message = new JLabel("<html><p style=\"width:200px\">"+ LoadSupport.getWarningMessage()[1]+"</p></html>", JLabel.CENTER);
                    JButton exitButton = new JButton("OK");

                    exitButton.addActionListener(e -> {
                        window.dispose();
                        minecraftClient.stop();
                    });

                    window.setLayout(new BorderLayout());
                    window.add(message, BorderLayout.CENTER);
                    window.add(exitButton, BorderLayout.SOUTH);
                    window.setVisible(true);
                });
            }
        } catch (RuntimeException e) {
            LoadSupport.logger.error(String.valueOf(e));
        }
    }
    public static class BlockedScreen extends Screen {

        protected BlockedScreen() {
            super(Component.literal("BlockedScreen"));
        }

        @Override
        protected void init() {
            Button closeButton = Button.builder(Component.literal("Quit Game"), button -> Minecraft.getInstance().stop())
                    .bounds(this.width / 2 - 100, this.height - 35, 200, 20)
                    .build();

            this.addRenderableWidget(closeButton);
        }

        @Override
        public void render(GuiGraphics graphics, int mouseX, int mouseY, float delta) {
            super.render(graphics, mouseX, mouseY, delta);

            String title = LoadSupport.getWarningMessage()[0];
            String body = LoadSupport.getWarningMessage()[1];

            int lineHeight = this.font.lineHeight + 5;
            int maxTextWidth = this.width - 40;

            List<FormattedCharSequence> wrappedLines = this.font.split(Component.literal(body), maxTextWidth);

            int totalTextHeight = (wrappedLines.size() * lineHeight) + lineHeight + 10;
            int startY = (this.height / 2) - (totalTextHeight / 2);

            graphics.drawCenteredString(this.font, Component.literal(title), this.width / 2, startY, 0xf94449);

            int bodyStartY = startY + lineHeight + 10;
            for (int i = 0; i < wrappedLines.size(); i++) {
                FormattedCharSequence line = wrappedLines.get(i); // Get each wrapped line
                graphics.drawCenteredString(this.font, line, this.width / 2, bodyStartY + (i * lineHeight), 0xffffff);
            }
        }
    }
}
