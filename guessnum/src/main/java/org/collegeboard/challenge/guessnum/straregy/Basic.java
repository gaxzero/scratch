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

/**
 *
 *  
 * @author gax
 */
public class Basic {
    public static final BigDecimal TWO = BigDecimal.ONE.add(BigDecimal.ONE); // 2 = 1 + 1
    public static final BigDecimal FOUR = TWO.add(TWO); // 4 = 2 + 2
    public static final BigDecimal SEVEN = FOUR.add(TWO).add(BigDecimal.ONE); // 7 = 4 + 2 + 1

    private BigDecimal pivot = BigDecimal.ZERO; //SEVEN; // or something popular.

    // why one? should be something related to the standaed deviation of past experiments
    private BigDecimal jump;// = BigDecimal.ONE;
    private BigDecimal lo, hi;

    public BigDecimal getPivot() {
        return pivot;
    }

    public void setPivot(BigDecimal pivot) {
        this.pivot = pivot;
    }

    public BigDecimal getJump() {
        return jump;
    }

    public void setJump(BigDecimal jump) {
        this.jump = jump;
    }

    public BigDecimal getLo() {
        return lo;
    }

    public void setLo(BigDecimal lo) {
        this.lo = lo;
    }

    public BigDecimal getHi() {
        return hi;
    }

    public void setHi(BigDecimal hi) {
        this.hi = hi;
    }

    
    public String getPrompt() {
        return pivot.toPlainString(); // + " (or " + pivot.toEngineeringString() + ")";
    }

    public BigDecimal advance(Ui.Choice choice) {
        switch (choice) {
            case lower: {
                hi = pivot; // last guess is now an upper bound.
                advance(choice, lo);
                break;
            }
            case higher: {
                lo = pivot;  // last guess is now an lover bound.
                advance(choice, hi);
                break;
            }
            default:
                throw new IllegalArgumentException(String.valueOf(choice));
        }
        return pivot;
    }

    private void advance(Ui.Choice choice, BigDecimal boundery) {
        if (boundery == null) {
            widden(choice);
        } else {
            narrow(choice);
        }
    }

    private void narrow(Ui.Choice choice) {
        jump = jump.divide(TWO);
        pivot = pivot.add(jump.multiply(choice.dir));
    }

    private void widden(Ui.Choice choice) {
        jump = (jump == null) ? BigDecimal.ONE : jump.multiply(TWO);
        pivot = pivot.add(jump.multiply(choice.dir));
    }
}
