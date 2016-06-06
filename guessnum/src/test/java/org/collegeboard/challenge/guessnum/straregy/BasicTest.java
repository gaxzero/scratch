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
package org.collegeboard.challenge.guessnum.straregy;

import java.math.BigDecimal;
import org.collegeboard.challenge.guessnum.Ui;
import static org.hamcrest.core.Is.*;
import static org.hamcrest.core.IsEqual.*;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;

/**
 *
 * @author gax
 */
public class BasicTest {
    Basic instance = new Basic();
    
    public BasicTest() {
    }
    

    /**
     * Test of getPrompt method, of class Basic.
     */
    @Test
    public void testGetPrompt() {
        assertThat(instance.getPrompt(), is(equalTo("0")));
    }

    /**
     * Test of advance method, of class Basic.
     */
    @Test
    public void testAdvance() {
        BigDecimal jump = instance.advance(Ui.Choice.higher);
        BigDecimal pivot = instance.getPivot();
        assertThat(jump, is(equalTo(pivot)));
        
        
        jump = instance.advance(Ui.Choice.lower);
        assertThat(instance.getPivot(), is(equalTo(pivot.subtract(jump))));
    }
    
}
