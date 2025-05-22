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
public class TBodyRenderer extends Element {
    
    private TBodyRenderer(Builder builder) {
        super(builder);
    }

    public static class Builder extends Element.Builder<TBodyRenderer, Builder> {

        public Builder() {
            super("tbody");
        }

        @Override
        public TBodyRenderer build() {
            return new TBodyRenderer(this);
        }
    }
    
}
