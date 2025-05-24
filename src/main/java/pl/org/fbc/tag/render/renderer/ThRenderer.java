/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.org.fbc.tag.render.renderer;

/**
 *
 * @author artur
 */
public class ThRenderer extends Element {
    
    private ThRenderer(Builder builder) {
        super(builder);
    }

    public static class Builder extends Element.Builder<ThRenderer, Builder> {

        public Builder() {
            super("th");
        }

        @Override
        public ThRenderer build() {
            return new ThRenderer(this);
        }
    }
    
}
