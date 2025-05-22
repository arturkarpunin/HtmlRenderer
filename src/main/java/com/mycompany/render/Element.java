/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.render;

import java.util.ArrayList;
import java.util.List;

// *************************************************************************

public class Element implements Renderer {

    final String tag;
    final Integer colspan;
    final Integer rowspan;
    final String style;
    final String clazz;
    final List<Renderer> contents;

    protected Element(Builder builder) {
        this.tag = builder.tag;
        this.colspan = builder.colspan;
        this.rowspan = builder.rowspan;
        this.style = builder.style;
        this.contents = builder.contents;
        this.clazz = builder.clazz;
    }

    public static abstract class Builder<T extends Element, B extends Builder<T, B>> {

        private final String tag;
        private Integer colspan = null;
        private Integer rowspan = null;
        private String style = null;
        private String clazz = null;
        private List<Renderer> contents = new ArrayList<>();

        public B colspan(Integer colspan) {
            this.colspan = colspan;
            return (B) this;
        }

        public B rowspan(Integer rowspan) {
            this.rowspan = rowspan;
            return (B) this;
        }

        public B style(String style) {
            this.style = style;
            return (B) this;
        }

        public B clazz(String clazz) {
            this.clazz = clazz;
            return (B) this;
        }

        public B contents(List<Renderer> contents) {
            this.contents.clear();
            this.contents.addAll(contents);
            return (B) this;
        }

        public B addContent(Renderer content) {
            this.contents.add(content);
            return (B) this;
        }

        public Builder(String tag) {
            this.tag = tag;
        }

        public abstract T build();
    }

    public Integer getColspan() {
        return colspan;
    }

    public Integer getRowspan() {
        return rowspan;
    }

    public String getStyle() {
        return style;
    }

    public String getClazz() {
        return clazz;
    }

    public List<Renderer> getContents() {
        return contents;
    }

    private <V> String asString(V value) {
        if (value != null) {
            return value.toString();
        }
        return null;
    }

    @Override
    public String render() {
        return new Node.Builder(tag).attributes(new Attributes().add("colspan", asString(colspan)).add("rowspan", asString(rowspan)).add("style", style).add("class", clazz)).content(contents).build().render();
    }
    
}
