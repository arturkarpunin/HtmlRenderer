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
public class DivRenderer extends Element {
    
    private DivRenderer(Builder builder) {
        super(builder);
    }

    public static class Builder extends Element.Builder<DivRenderer, Builder> {

        public Builder() {
            super("div");
        }

        @Override
        public DivRenderer build() {
            return new DivRenderer(this);
        }
    }
    
}
