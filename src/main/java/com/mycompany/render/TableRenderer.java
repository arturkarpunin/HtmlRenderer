/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.render;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author artur
 */
public class TableRenderer implements Renderer {
    
    final List<Renderer> contents = new ArrayList<>();

    public TableRenderer(THeadRenderer head, TBodyRenderer body, TFootRenderer foot) {
        if (head != null) {
            this.contents.add(head);
        }
        this.contents.add(body);
        if (foot != null) {
            this.contents.add(foot);
        }
    }

    @Override
    public String render() {
        return new Node.Builder("table").content(contents).build().render();
    }
    
}
