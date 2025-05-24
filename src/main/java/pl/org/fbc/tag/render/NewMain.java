/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.org.fbc.tag.render;

import pl.org.fbc.tag.render.renderer.BodyRenderer;
import pl.org.fbc.tag.render.renderer.TFootRenderer;
import pl.org.fbc.tag.render.renderer.TBodyRenderer;
import pl.org.fbc.tag.render.renderer.THeadRenderer;
import pl.org.fbc.tag.render.renderer.TdRenderer;
import pl.org.fbc.tag.render.renderer.TrRenderer;
import pl.org.fbc.tag.render.renderer.ThRenderer;
import pl.org.fbc.tag.render.renderer.HtmlRenderer;
import pl.org.fbc.tag.render.renderer.HeadRenderer;
import pl.org.fbc.tag.render.renderer.TableRenderer;
import pl.org.fbc.tag.render.renderer.Node;

/**
 *
 * @author artur
 */
public class NewMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        TdRenderer td = new TdRenderer.Builder()
                .addContent(
                        new Node.Builder<String>()
                                .content("ala ma kota")
                                .build())
                .build();
        
        ThRenderer th = new ThRenderer.Builder()
                .colspan(2)
                .style("width: 16px;")
                .addContent(new Node.Builder<String>()
                        .content("ala ma kota")
                        .build())
                .build();
        TrRenderer tr = new TrRenderer.Builder()
                .addContent(th)
                .build();
        
        THeadRenderer thead = new THeadRenderer.Builder()
                .addContent(tr)
                .build();
        

        TableRenderer table = new TableRenderer(
                thead,
                new TBodyRenderer.Builder()
                        .build(),
                new TFootRenderer.Builder()
                        .build());

        BodyRenderer body = (BodyRenderer) new BodyRenderer.Builder()
                .addContent(table)
                .build();

        HtmlRenderer html = new HtmlRenderer(new HeadRenderer(), body);
        System.out.println(html.render());
    }
    
}
