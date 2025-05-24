/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.org.fbc.tag.render.renderer;

/**
 *
 * @author artur
 */
public class TitleRenderer implements Renderer {
    
    final String title;

    public TitleRenderer(String title) {
        this.title = title;
    }

    @Override
    public String render() {
        return new Node.Builder("title")
                .content(title)
                .build()
                .render();
    }
    
}
