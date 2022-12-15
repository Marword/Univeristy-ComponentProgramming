package com.sudokugame.view.view;

import java.util.ListResourceBundle;

public class Authors extends ListResourceBundle {

    @Override
    protected Object[][] getContents() {
        return new Object[][]{
                {"name1", "Marcin"},
                {"name2", "Wiktoria"},
                {"surname1", "Janicki"},
                {"surname2", "Sluzewska"}
        };
    }
}
