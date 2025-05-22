/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.render;

import com.mycompany.render.Node;
import com.mycompany.render.Renderer;

/**
 *
 * @author artur
 */
public class StyleRenderer implements Renderer {

    private static final String TABLE_CSS = "table {width: 100%;border-collapse: collapse;}";
    private static final String TH_TD_CSS = "th, td {\n" + "      border: 1px solid #C3C1C1;\n" + "      padding: 8px;\n" + "      background: #fff;\n" + "      color: #000;\n" + "      text-align: center;\n" + "    }";
    private static final String DOT_UI_ICON_CSS = ".ui-icon {\n" + "      width: 16px;\n" + "      height: 16px;\n" + "      display: inline-block;\n" + "    }";
    private static final String DOT_UI_ICON_CHECK_CSS = ".ui-icon-check {\n" + "      background-color: green;\n" + "    }";
    private static final String DOT_UI_ICON_CLOSE_CSS = ".ui-icon-close {\n" + "      background-color: red;\n" + "    }";
    private static final String DOT_IU_STATE_DISABLED_CSS = ".ui-state-disabled {\n" + "      color: #999;\n" + "    }";
    private final Node<String> style = new Node.Builder("style").content(new StringBuilder().append(TABLE_CSS).append('\n').append(TH_TD_CSS).append('\n').append(DOT_UI_ICON_CSS).append('\n').append(DOT_UI_ICON_CHECK_CSS).append('\n').append(DOT_UI_ICON_CLOSE_CSS).append('\n').append(DOT_IU_STATE_DISABLED_CSS).append('\n').toString()).build();

    @Override
    public String render() {
        return style.render();
    }

}
