/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.htmlrend;

import com.mycompany.render.BodyRenderer;
import com.mycompany.render.TFootRenderer;
import com.mycompany.render.TBodyRenderer;
import com.mycompany.render.THeadRenderer;
import com.mycompany.render.TdRenderer;
import com.mycompany.render.TrRenderer;
import com.mycompany.render.ThRenderer;
import com.mycompany.render.HtmlRenderer;
import com.mycompany.render.HeadRenderer;
import com.mycompany.render.TableRenderer;
import com.mycompany.render.Node;

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
                        new Node.Builder()
                                .content("ala ma kota")
                                .build())
                .build();
        
        ThRenderer th = new ThRenderer.Builder()
                .colspan(2)
                .style("width: 16px;")
                .addContent(new Node.Builder()
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
