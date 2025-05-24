/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.org.fbc.tag.render.renderer;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author artur
 */
public class Attributes implements Renderer {
    
    private final Map<String, String> maps = new HashMap<>();

    public Attributes add(String key, String value) {
        maps.put(key, value);
        return this;
    }

    @Override
    public String render() {
        StringBuilder sb = new StringBuilder();
        maps.forEach((java.lang.String t, java.lang.String u) -> {
            if (u != null) {
                sb.append(String.format(" %s=\"%s\"", t, u));
            }
        });
        return sb.toString();
    }
    
}
