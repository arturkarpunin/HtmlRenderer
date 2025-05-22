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
public class BodyRenderer extends Element {
    
    private BodyRenderer(Builder builder) {
        super(builder);
    }

    public static class Builder extends Element.Builder<BodyRenderer, Builder> {

        public Builder() {
            super("body");
        }

        @Override
        public BodyRenderer build() {
            return new BodyRenderer(this);
        }
    }
    
}
