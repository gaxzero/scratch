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

import java.util.Iterator;

/**
 * One of many alpha-numeric representation of a phone number. It is uniquely
 * identified by it's position (index) in the tree defined by a phone number.
 * e.g. the symbol "1AD" in at position 0 * 4 * 4 + 1 * 4 + 1 * 1 = 5 in a
 * number system where the variable-base is [[1], [2,A,B,C], [3,D,E,F]] defined
 * by a phone number's key sequence 123.
 *
 * @author gax
 */
public class Symbol implements Iterable<String> {

    /**
     * Maps a digit to the set of symbols it identifies.
     */
    private static final char[][] KEYPADE = {
        {'0'},
        {'1'},
        {'2', 'A', 'B', 'C'},
        {'3', 'D', 'E', 'F'},
        {'4', 'G', 'H', 'I'},
        {'5', 'J', 'K', 'L'},
        {'6', 'M', 'N', 'O'},
        {'7', 'P', 'Q', 'R', 'S'},
        {'8', 'T', 'U', 'V'},
        {'9', 'W', 'X', 'Y', 'Z'}
    };

    private int[] base;
    private int[] significance;
    private int symbolPosition;
    private int size; // size of the list.

    public Symbol(int[] baseNumber) {
        if (baseNumber == null || baseNumber.length == 0) {
            throw new IllegalArgumentException("The phone number must have at least on digit");
        }
        for (int i : baseNumber) {
            if (i >= KEYPADE.length || i < 0) {
                throw new IllegalArgumentException("Digit " + i + " not fount on keypad");
            }
        }

        this.base = baseNumber;
        this.significance = new int[baseNumber.length];
        significance[baseNumber.length - 1] = 1;
        for (int i = baseNumber.length - 2; i >= 0; i--) {
            significance[i] = significance[i + 1] * KEYPADE[base[i + 1]].length;
        }
        this.size = significance[0] * KEYPADE[base[0]].length;
    }

    private Symbol(Symbol s) {
        this.base = s.base;
        this.significance = s.significance;
        this.size = s.size;
    }

    /**
     * @return The size of the list of symbols (which is the number of symbols
     * in the space defined by the base number).
     */
    public int getSize() {
        return size;
    }

    /**
     * @return Position of this symbol in the list of symbols defined by the
     * base.
     */
    public int getSymbolPosition() {
        return symbolPosition;
    }

    /**
     * Places this symbol at the specified position in the list of symbols.
     *
     * @param symbolPosition position of symbol.
     * @throws IndexOutOfBoundsException When the position is negative or beyond
     * the size.
     */
    public void setSymbolPosition(int symbolPosition) throws IndexOutOfBoundsException {
        if (symbolPosition < 0 || symbolPosition >= size) {
            throw new IllegalArgumentException("position cannot be negative");
        }
        this.symbolPosition = symbolPosition;
    }

    /**
     * Increment this symbol's position by one.
     *
     * @return The newly incremented position.
     */
    public int inc() {
        this.setSymbolPosition(symbolPosition + 1);
        return symbolPosition;
    }

    /**
     * @see #toSymbolChars() .
     * @return
     */
    @Override
    public String toString() {
        return new String(toSymbolChars());
    }

    public char[] toSymbolChars() {
        char[] symbolChars = new char[base.length];
        int remainder = this.symbolPosition;
        for (int i = 0; i < base.length; i++) {
            int mostSignificantDigit = remainder / this.significance[i];
            remainder = remainder % this.significance[i];
            symbolChars[i] = KEYPADE[this.base[i]][mostSignificantDigit];
        }
        return symbolChars;
    }

    /**
     * Iterate through the list starting from the current position.
     *
     * @return Iterator.
     */
    @Override
    public Iterator<String> iterator() {

        return new Iterator<String>() {
            private final Symbol internal = new Symbol(Symbol.this);

            {
                internal.symbolPosition = Symbol.this.symbolPosition - 1;
            }

            @Override
            public boolean hasNext() {
                return internal.symbolPosition < internal.size - 1;
            }

            @Override
            public String next() {
                internal.inc();
                return internal.toString();
            }
        };
    }

}
