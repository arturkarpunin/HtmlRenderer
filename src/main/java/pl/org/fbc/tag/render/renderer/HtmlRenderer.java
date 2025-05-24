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
public class HtmlRenderer implements Renderer {
    
    final List<Renderer> contents = new ArrayList<>();

    public HtmlRenderer(HeadRenderer head, BodyRenderer body) {
        this.contents.add(head);
        this.contents.add(body);
    }

    @Override
    public String render() {
        return new Node.Builder("html")
                .attributes(
                        new Attributes()
                                .add("lang", "pl"))
                .content(contents)
                .build()
                .render();
    }
    
}
