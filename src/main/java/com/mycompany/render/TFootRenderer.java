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
public class TFootRenderer extends Element {
    
    private TFootRenderer(Builder builder) {
        super(builder);
    }

    public static class Builder extends Element.Builder<TFootRenderer, Builder> {

        public Builder() {
            super("tfoot");
        }

        @Override
        public TFootRenderer build() {
            return new TFootRenderer(this);
        }
    }
    
}
