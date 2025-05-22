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
public class THeadRenderer extends Element {
    
    private THeadRenderer(Builder builder) {
        super(builder);
    }

    public static class Builder extends Element.Builder<THeadRenderer, Builder> {

        public Builder() {
            super("thead");
        }

        @Override
        public THeadRenderer build() {
            return new THeadRenderer(this);
        }
    }
    
}
