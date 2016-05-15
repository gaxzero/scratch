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

import java.util.ArrayList;
import java.util.List;
import static org.hamcrest.core.IsEqual.equalTo;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.core.Is.is;
import org.hamcrest.core.IsCollectionContaining;
import static org.hamcrest.core.IsCollectionContaining.hasItem;
import static org.hamcrest.core.IsNot.not;

/**
 *
 * @author gax
 */
public class SymbolTest {
    
    private static final int[] DEFAULT_BASE = {3, 4, 6, 7, 2};
    private Symbol instance;
    
    public SymbolTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        instance = new Symbol(DEFAULT_BASE);
    }

    /**
     * Test of getSymbolPosition method, of class Symbol.
     */
    @Test
    public void testGetSymbolPosition() {
        int result = instance.getSymbolPosition();
        assertThat(result, is(equalTo(0)));
    }

    /**
     * Test of setSymbolPosition method, of class Symbol.
     */
    @Test
    public void testSetSymbolPosition() {
        assertThat(instance.getSymbolPosition(), is(not(equalTo(99))));
        instance.setSymbolPosition(99);
        assertThat(instance.getSymbolPosition(), is(equalTo(99)));
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testSetSymbolPositionOutOfRangeLow() {
        instance.setSymbolPosition(-1);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testSetSymbolPositionOutOfRangeHigh() {
        instance.setSymbolPosition(Integer.MAX_VALUE);
    }

    /**
     * Test of inc, getSymbolPosition, and setSymbolPosition methods,.
     */
    @Test
    public void testInc() {
        assertThat(instance.getSymbolPosition(), is(equalTo(0)));
        assertThat(instance.inc(), is(equalTo(1)));
        assertThat(instance.getSymbolPosition(), is(equalTo(1)));
        instance.setSymbolPosition(9);
        assertThat(instance.inc(), is(equalTo(10)));
        assertThat(instance.getSymbolPosition(), is(equalTo(10)));
    }

    /**
     * Test of toString method, of class Symbol.
     */
    @Test
    public void testToString() {
        assertThat(instance.toString(), is(equalTo("34672")));
        instance.inc();
        assertThat(instance.toString(), is(equalTo("3467A")));
    }

    /**
     * Test of toSymbolChars method, of class Symbol.
     */
    @Test
    public void testToSymbolChars() {
        char[] result = instance.toSymbolChars();
        assertArrayEquals("34672".toCharArray(), result);
    }
    
    @Test
    public void testIterator() {
        List<String> all = new ArrayList<>();
        for (String s : instance) {
            all.add(s);
        }
        
        assertThat(all.size(), is(equalTo(instance.getSize())));
        assertThat(all, hasItem("FINRA"));
    }
    
}
