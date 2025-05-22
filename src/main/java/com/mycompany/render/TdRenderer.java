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
public class TdRenderer extends Element {
    
    private TdRenderer(Builder builder) {
        super(builder);
    }

    public static class Builder extends Element.Builder<TdRenderer, Builder> {

        public Builder() {
            super("td");
        }

        @Override
        public TdRenderer build() {
            return new TdRenderer(this);
        }
    }
    
}
