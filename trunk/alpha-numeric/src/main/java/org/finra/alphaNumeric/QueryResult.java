/*
 * The MIT License
 *
 * Copyright 2016 gax.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package org.finra.alphaNumeric;

import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlList;
import javax.xml.bind.annotation.XmlRootElement;
//import java.util.List;
//import javax.json.Json;
//import javax.json.stream.JsonGenerator;
//import javax.ws.rs.WebApplicationException;
//import javax.ws.rs.core.StreamingOutput;
//import com.google.common.collect.Lists;

/**
 * Web service result wrapper.
 * @author gax
 */
@XmlRootElement
public class QueryResult implements Serializable {
    private Symbol source;
    private int limit;

    /**
     * 
     * @param symbole source of symbols.
     * @param limit number of entries to list/write.
     */
    public QueryResult(Symbol symbole, int limit) {
        this.source = symbole;
        this.limit = limit < 0 ? symbole.getSize() : limit;
    }
    
    public QueryResult() {
        // no-op
    }
    
    /**
     * @return total possible results.
     */
    @XmlAttribute
    public int getTotalCount() {
        return source.getSize();
    }
    
    /**
     * @return possible results.
     */
    @XmlElement(name = "entry")
    public List<String> getList() {
        List<String> l = new ArrayList<>(limit);
        for(String s : source) {
            l.add(s);
            if (l.size() >= limit) {
                break;
            }
        }
        return l;
    }

}
