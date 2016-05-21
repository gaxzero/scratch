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

import javax.ejb.Singleton;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

/**
 *
 * @author gax
 */
@Singleton
@Path("{base}")
public class SymbolService {
    
    @Context
    private UriInfo context;
    

    /**
     * Retrieves the entries starting at the desired entry offset. If the limit
     * is not specified, all are returned.
     *
     * @param base the base phone number from the URI.
     * @param offset the offset to start from. Defaults to 0.
     * @param limit number of entries desired. Defaults to all if negative or
     * unspecified.
     * @return requested list of symbols.
     * @see #get(java.lang.String, int, int)
     */
    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public QueryResult list(
            @PathParam("base") @NotNull @Size(min = 1) String base,
            @QueryParam("offset") @DefaultValue("0") int offset,
            @QueryParam("limit") @DefaultValue("-1") int limit) {
        return listSymbols(base, offset, limit);
    }

    /**
     * Retrieves the entries starting at the specified entry index/offset/id. If
     * the limit is not specified, only one is returned.
     *
     * @param base the base phone number from the URI.
     * @param offset the offset/index/id from the URI.
     * @param limit number of entries desired. Defaults to one when not
     * specified, or all if negative.
     * @return requested list of symbols.
     * @see #list(java.lang.String, int, int)
     *
     */
    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Path("/{id}")
    public QueryResult get(
            @PathParam("base") @NotNull @Size(min = 1) String base,
            @PathParam("id") int offset,
            @DefaultValue("1") @QueryParam("limit") int limit) {
        return list(base, offset, limit);
    }

    public QueryResult listSymbols(int[] base, int offset, int limit) throws IndexOutOfBoundsException {
        Symbol symbol = new Symbol(base);
        symbol.setSymbolPosition(offset);
        if (limit < 0) {
            limit = symbol.getSize();
        }
        return new QueryResult(symbol, limit);
    }

    public QueryResult listSymbols(String baseString, int offset, int limit) throws IndexOutOfBoundsException {
        int[] base = baseString.chars().map(d -> d - '0').toArray();
        return listSymbols(base, offset, limit);
    }
}
