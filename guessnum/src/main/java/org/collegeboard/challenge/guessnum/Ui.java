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
package org.collegeboard.challenge.guessnum;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.Writer;
import java.math.BigDecimal;
import java.util.Arrays;

/**
 *
 * @author gax
 */
public class Ui {

    private final BufferedReader in;
    private final PrintWriter out;

    public static enum Choice {
        higher(BigDecimal.ONE), lower(BigDecimal.ONE.negate()), yes(null), end(null);

        public final BigDecimal dir;

        private Choice(BigDecimal dir) {
            this.dir = dir;
        }

    }

    public Ui(BufferedReader in, PrintWriter out) {
        this.in = in;
        this.out = out;
    }

    public Ui(Reader in, Writer out) {
        this(new BufferedReader(in), new PrintWriter(out));
    }

    public Ui(InputStream in, OutputStream out) {
        this(new InputStreamReader(in), new PrintWriter(out));
    }

    public Ui() {
        this(System.in, System.out);
    }
    
    public void prompt(BigDecimal promptNumber) {
        prompt(String.format("Is the number %s?", promptNumber));
    }

    public void prompt(String msg) {
        out.println(msg);
        out.flush();
    }
    
    /**
     * Read user's answer to the game's guess
     */
    public Choice read() {
        String choice;
        try {
            while ((choice = in.readLine()) != null) {
                try {
                    return Choice.valueOf(choice.toLowerCase());
                } catch (IllegalArgumentException bad) {
                    prompt("Your input options are : " + Arrays.toString(Choice.values()));
                }
            }
        } catch (IOException ioe) {
            throw new Error(ioe);
        }
        return null;
    }
}
