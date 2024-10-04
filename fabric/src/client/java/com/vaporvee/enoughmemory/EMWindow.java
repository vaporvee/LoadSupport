package com.vaporvee.enoughmemory;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.TitleScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.OrderedText;
import net.minecraft.text.Text;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class EMWindow {
    static boolean windowOpen = false;
    private static JFrame window;

    public static void createWindow(MinecraftClient minecraftClient, Screen screen) {
        if(screen instanceof TitleScreen){
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
                    window = new JFrame(EnoughMemoryClient.getWarningMessage()[0]);
                    window.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
                    window.setSize(400, 200);
                    window.setLocationRelativeTo(null);

                    JLabel message = new JLabel("<html><p style=\"width:200px\">"+EnoughMemoryClient.getWarningMessage()[1]+"</p></html>", JLabel.CENTER);
                    JButton exitButton = new JButton("OK");

                    exitButton.addActionListener(e -> {
                        window.dispose();
                        minecraftClient.scheduleStop();
                    });

                    window.setLayout(new BorderLayout());
                    window.add(message, BorderLayout.CENTER);
                    window.add(exitButton, BorderLayout.SOUTH);
                    window.setVisible(true);
                });
            }
        } catch (RuntimeException e) {
            EnoughMemory.logger.error(String.valueOf(e));
        }
    }

    @Environment(EnvType.CLIENT)
    public static class BlockedScreen extends Screen {
        protected BlockedScreen() {
            super(Text.literal("BlockedScreen"));
        }

        @Override
        public  void init(){
            ButtonWidget closeButton = ButtonWidget.builder(Text.literal("Quit Game"), button -> {
                        MinecraftClient.getInstance().scheduleStop();
                    })
                    .dimensions(width / 2 - 100, height - 35 , 200, 20)
                    .build();
            addDrawableChild(closeButton);
        }

        @Override
        public void render(DrawContext context, int mouseX, int mouseY, float delta) {
            super.render(context, mouseX, mouseY, delta);

            String title = EnoughMemoryClient.getWarningMessage()[0];
            String body = EnoughMemoryClient.getWarningMessage()[1];

            int lineHeight = textRenderer.fontHeight + 5;
            int maxTextWidth = width - 40;
            List<OrderedText> wrappedLines = textRenderer.wrapLines(Text.literal(body), maxTextWidth);

            int totalTextHeight = (wrappedLines.size() * lineHeight) + lineHeight + 10;
            int startY = (height / 2) - (totalTextHeight / 2);

            context.drawCenteredTextWithShadow(textRenderer, Text.literal(title), width / 2, startY, 0xf94449);

            int bodyStartY = startY + lineHeight + 10;

            // Automatic line breaks
            for (int i = 0; i < wrappedLines.size(); i++) {
                OrderedText line = wrappedLines.get(i);
                context.drawCenteredTextWithShadow(textRenderer, line, width / 2, bodyStartY + (i * lineHeight), 0xffffff);
            }
        }

    }
}
