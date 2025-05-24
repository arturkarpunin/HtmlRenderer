/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.htmlrend;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author artur
 */
public class NewMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        HeadRenderer headRenderer = new HeadRenderer();
        TableRenderer table = new TableRenderer.Builder().build();
        BodyRenderer body = (BodyRenderer) new BodyRenderer.Builder()
                .addContent(table)
                .build();
        HtmlRenderer html = new HtmlRenderer(headRenderer, body);
        System.out.println(html.render());
    }

    private static interface Renderer {

        public abstract String render();
    }

    private static class Attributes implements Renderer {

        private final Map<String, String> maps = new HashMap<>();

        public Attributes add(String key, String value) {
            maps.put(key, value);
            return this;
        }

        @Override
        public String render() {
            StringBuilder sb = new StringBuilder();
            maps.forEach((t, u) -> {
                if (u != null) {
                    sb.append(String.format(" %s=\"%s\"", t, u));
                }
            });
            return sb.toString();
        }

    }

    private static class Node<T> implements Renderer {

        private final String tag;
        private final Attributes attributes;
        private final T content;
        private final Boolean noEndTag;

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

            private Builder() {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
            sb.append(startTag()).append('\n');
            sb.append(renderContent()).append('\n');
            sb.append(endTag()).append('\n');
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
                List objects = ((List) content);
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

    private static class HtmlRenderer implements Renderer {

        private final List<Renderer> contents = new ArrayList<>();

        public HtmlRenderer(HeadRenderer head, BodyRenderer body) {
            this.contents.add(head);
            this.contents.add(body);
        }

        @Override
        public String render() {
            return new Node.Builder("html")
                    .attributes(new Attributes().add("lang", "pl"))
                    .content(contents)
                    .build()
                    .render();
        }

    }

    private static class HeadRenderer implements Renderer {
        private final List<Renderer> contents = new ArrayList<>();
        
        public HeadRenderer() {
            this.contents.add(new MetaRenderer("utf-8"));
            this.contents.add(new StyleRenderer());
            this.contents.add(new TitleRenderer("Tytuł"));
        }

        @Override
        public String render() {
            return new Node.Builder("head")
                    .content(contents)
                    .build()
                    .render();
        }

    }

    private static class MetaRenderer implements Renderer {

        private final String charset;

        public MetaRenderer(String charset) {
            this.charset = charset;
        }

        @Override
        public String render() {
            return new Node.Builder("meta")
                    .noEndTag(true)
                    .attributes(new Attributes().add("charset", charset))
                    .build()
                    .render();
        }
    }

    private static class TitleRenderer implements Renderer {

        private final String title;

        public TitleRenderer(String title) {
            this.title = title;
        }

        @Override
        public String render() {
            return new Node.Builder("title")
                    .content(title)
                    .build()
                    .render();
        }

    }

    private static class StyleRenderer implements Renderer {

        private static final String TABLE_CSS = "table {width: 100%;border-collapse: collapse;}";
        private static final String TH_TD_CSS = "th, td {\n"
                + "      border: 1px solid #C3C1C1;\n"
                + "      padding: 8px;\n"
                + "      background: #fff;\n"
                + "      color: #000;\n"
                + "      text-align: center;\n"
                + "    }";

        private static final String DOT_UI_ICON_CSS = ".ui-icon {\n"
                + "      width: 16px;\n"
                + "      height: 16px;\n"
                + "      display: inline-block;\n"
                + "    }";
        private static final String DOT_UI_ICON_CHECK_CSS = ".ui-icon-check {\n"
                + "      background-color: green;\n"
                + "    }";

        private static final String DOT_UI_ICON_CLOSE_CSS = ".ui-icon-close {\n"
                + "      background-color: red;\n"
                + "    }";

        private static final String DOT_IU_STATE_DISABLED_CSS = ".ui-state-disabled {\n"
                + "      color: #999;\n"
                + "    }";

        private final Node<String> style = new Node.Builder("style")
                .content(
                        new StringBuilder()
                                .append(TABLE_CSS).append('\n')
                                .append(TH_TD_CSS).append('\n')
                                .append(DOT_UI_ICON_CSS).append('\n')
                                .append(DOT_UI_ICON_CHECK_CSS).append('\n')
                                .append(DOT_UI_ICON_CLOSE_CSS).append('\n')
                                .append(DOT_IU_STATE_DISABLED_CSS).append('\n')
                                .toString()
                )
                .build();

        @Override
        public String render() {
            return style.render();
        }

    }
    
    // *************************************************************************
    
    private static class Element implements Renderer {

        private final String tag;
        private final Integer colspan;
        private final Integer rowspan;
        private final String style;
        private final String clazz;
        private final List<Renderer> contents;

        private Element(Builder builder) {
            this.tag = builder.tag;
            this.colspan = builder.colspan;
            this.rowspan = builder.rowspan;
            this.style = builder.style;
            this.contents = builder.contents;
            this.clazz = builder.clazz;
        }

        public static class Builder {

            private final String tag;
            private Integer colspan = null;
            private Integer rowspan = null;
            private String style = null;
            private String clazz = null;
            private List<Renderer> contents = new ArrayList<>();

            public Builder colspan(Integer colspan) {
                this.colspan = colspan;
                return this;
            }

            public Builder rowspan(Integer rowspan) {
                this.rowspan = rowspan;
                return this;
            }

            public Builder style(String style) {
                this.style = style;
                return this;
            }

            public Builder clazz(String clazz) {
                this.clazz = clazz;
                return this;
            }

            public Builder contents(List<Renderer> contents) {
                this.contents.clear();
                this.contents.addAll(contents);
                return this;
            }

            public Builder addContent(Renderer content) {
                this.contents.add(content);
                return this;
            }

            public Builder(String tag) {
                this.tag = tag;
            }

            public Element build() {
                return new Element(this);
            }

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

        private <T> String asString(T value) {
            if ((value != null)) {
                return value.toString();
            }
            return null;
        }

        @Override
        public String render() {
            return new Node.Builder(tag)
                    .attributes(new Attributes()
                            .add("colspan", asString(colspan))
                            .add("rowspan", asString(rowspan))
                            .add("style", style)
                            .add("class", clazz)
                    )
                    .content(contents)
                    .build()
                    .render();
        }
    }

    private static class THRenderer extends Element {

        private THRenderer(Builder builder) {
            super(builder);
        }

        public static class Builder extends Element.Builder {

            public Builder() {
                super("th");
            }

            @Override
            public THRenderer build() {
                return new THRenderer(this);
            }
        }
    }

    private static class THeadRenderer extends Element {

        private THeadRenderer(Builder builder) {
            super(builder);
        }

        public static class Builder extends Element.Builder {

            public Builder() {
                super("thead");
            }

            @Override
            public THeadRenderer build() {
                return new THeadRenderer(this);
            }
        }
    }

    private static class TBodyRenderer extends Element {

        private TBodyRenderer(Builder builder) {
            super(builder);
        }

        public static class Builder extends Element.Builder {

            public Builder() {
                super("tbody");
            }
            
            @Override
            public TBodyRenderer build() {
                return new TBodyRenderer(this);
            }
        }
    }

    private static class TFootRenderer extends Element {

        private TFootRenderer(Builder builder) {
            super(builder);
        }

        public static class Builder extends Element.Builder {

            public Builder() {
                super("tfoot");
            }

            @Override
            public TFootRenderer build() {
                return new TFootRenderer(this);
            }
        }
    }

    private static class TableRenderer extends Element {

        private TableRenderer(Builder builder) {
            super(builder);
        }

        public static class Builder extends Element.Builder {

            public Builder() {
                super("table");
            }

            @Override
            public TableRenderer build() {
                return new TableRenderer(this);
            }
        }
    }

    private static class BodyRenderer extends Element {

        private BodyRenderer(Builder builder) {
            super(builder);
        }

        public static class Builder extends Element.Builder {

            public Builder() {
                super("body");
            }

            @Override
            public BodyRenderer build() {
                return new BodyRenderer(this);
            }
        }
    }
}
