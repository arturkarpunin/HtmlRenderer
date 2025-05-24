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
public class TrRenderer extends Element {
    
    private TrRenderer(Builder builder) {
        super(builder);
    }

    public static class Builder extends Element.Builder<TrRenderer, Builder> {

        public Builder() {
            super("tr");
        }

        @Override
        public TrRenderer build() {
            return new TrRenderer(this);
        }
    }
    
}
