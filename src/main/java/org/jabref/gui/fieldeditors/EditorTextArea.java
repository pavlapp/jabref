package org.jabref.gui.fieldeditors;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.text.Font;

import org.jabref.gui.GUIGlobals;

import com.sun.javafx.scene.control.skin.TextAreaSkin;

public class EditorTextArea extends javafx.scene.control.TextArea implements Initializable {

    public EditorTextArea() {
        this("");
    }

    public EditorTextArea(String text) {
        super(text);

        setMinHeight(1);
        setMinWidth(200);

        // Hide horizontal scrollbar and always wrap text
        setWrapText(true);

        if (GUIGlobals.currentFont != null) {
            setFont(Font.font(GUIGlobals.currentFont.getFontName(), GUIGlobals.currentFont.getSize()));

            setStyle(
                    "text-area-background: " + convertToHex(GUIGlobals.validFieldBackgroundColor) + ";"
                            + "text-area-foreground: " + convertToHex(GUIGlobals.editorTextColor) + ";"
                            + "text-area-highlight: " + convertToHex(GUIGlobals.activeBackgroundColor) + ";"
            );
        }

        getStylesheets().add("org/jabref/gui/fieldeditors/EditorTextArea.css");
    }

    /**
     * Adds the given list of menu items to the context menu.
     */
    public void addToContextMenu(List<MenuItem> items) {
        TextAreaSkin customContextSkin = new TextAreaSkin(this) {
            @Override
            public void populateContextMenu(ContextMenu contextMenu) {
                super.populateContextMenu(contextMenu);
                contextMenu.getItems().addAll(0, items);
            }
        };
        setSkin(customContextSkin);
    }

    private String convertToHex(java.awt.Color color) {
        return String.format("#%02x%02x%02x", color.getRed(), color.getGreen(), color.getBlue());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
