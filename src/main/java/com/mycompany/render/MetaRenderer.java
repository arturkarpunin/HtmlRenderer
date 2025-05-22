/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.render;

/**
 *
 * @author artur
 */
public class MetaRenderer implements Renderer {
    
    final String charset;

    public MetaRenderer(String charset) {
        this.charset = charset;
    }

    @Override
    public String render() {
        return new Node.Builder("meta").noEndTag(true).attributes(new Attributes().add("charset", charset)).build().render();
    }
    
}
