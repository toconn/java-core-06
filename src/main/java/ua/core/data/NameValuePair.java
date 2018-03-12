/*
 * Created on May 26, 2004
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package ua.core.data;

import java.util.Objects;

/**
 * Created with CodeCrank.io
 */
public class NameValuePair {

    private String name;
    private String value;

    public NameValuePair() {
    }

    public NameValuePair(String name, String value) {

        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public String getValue() {
        return value;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object object) {
        if (object == null) return false;
        if (object == this) return true;
        if (!(object instanceof NameValuePair)) return false;
        NameValuePair item = (NameValuePair)object;
        return Objects.equals(name, item.name) &&
                Objects.equals(value, item.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, value);
    }

    @Override
    public String toString() {
        return "NameValuePair [" +
                "name=" + ((name != null) ? "\"" + name + "\"" : "null") + ", " +
                "value=" + ((value != null) ? "\"" + value + "\"" : "null") +
                "]";
    }

}