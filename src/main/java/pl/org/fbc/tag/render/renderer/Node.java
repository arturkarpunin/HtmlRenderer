/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.org.fbc.tag.render.renderer;

import java.util.List;

/**
 *
 * @author artur
 * @param <T> Typ węzła. 
 */
public class Node<T> implements Renderer {
    
    final String tag;
    final Attributes attributes;
    final T content;
    final Boolean noEndTag;

    private Node(Builder<T> builder) {
        this.tag = builder.tag;
        this.content = builder.content;
        this.attributes = builder.attributes;
        this.noEndTag = builder.noEndTag;
    }

    public static class Builder<T> {

        private final String tag;
        private Attributes attributes = new Attributes();
        private T content = null;
        private boolean noEndTag = false;

        public Builder(String tag) {
            this.tag = tag;
        }

        public Builder() {
            this.tag = null;
        }

        public Builder content(T content) {
            this.content = content;
            return this;
        }

        public Builder attributes(Attributes attributes) {
            this.attributes = attributes;
            return this;
        }

        public Builder noEndTag(boolean flag) {
            this.noEndTag = flag;
            return this;
        }

        public Node build() {
            return new Node(this);
        }
    }

    @Override
    public String render() {
        StringBuilder sb = new StringBuilder();
        if (tag == null) {
            sb.append(renderContent());
        } else {
            sb.append(startTag()).append('\n');
            sb.append(renderContent()).append('\n');
            sb.append(endTag()).append('\n');
        }
        return sb.toString();
    }

    private String startTag() {
        if (noEndTag) {
            return String.format("<%s%s/>", tag, attributes.render());
        }
        return String.format("<%s%s>", tag, attributes.render());
    }

    private String endTag() {
        if (noEndTag) {
            return "";
        }
        return String.format("</%s>", tag);
    }

    private String renderContent() {
        if (content instanceof Node) {
            return ((Renderer) content).render();
        } else if (content instanceof String) {
            return (String) content;
        } else if (content instanceof List) {
            StringBuilder sb = new StringBuilder();
            List objects = (List) content;
            for (int i = 0; i < objects.size(); i++) {
                Renderer node = (Renderer) ((List) content).get(i);
                sb.append(node.render());
            }
            return sb.toString();
        } else {
            return "";
        }
    }
    
}
