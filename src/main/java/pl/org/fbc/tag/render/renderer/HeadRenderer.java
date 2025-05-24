/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.org.fbc.tag.render.renderer;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author artur
 */
public class HeadRenderer implements Renderer {
    
    final List<Renderer> contents = new ArrayList<>();

    public HeadRenderer() {
        this.contents.add(new MetaRenderer("utf-8"));
        this.contents.add(new StyleRenderer());
        this.contents.add(new TitleRenderer("Tytuł"));
    }

    @Override
    public String render() {
        return new Node.Builder("head")
                .content(contents)
                .build()
                .render();
    }
    
}
