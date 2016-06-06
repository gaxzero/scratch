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

import org.collegeboard.challenge.guessnum.straregy.Basic;

/**
 *
 * @author gax
 */
public class Game {
    private Ui input = new Ui();
    private Basic strategy = new Basic();

    public Ui getInput() {
        return input;
    }

    public void setInput(Ui input) {
        this.input = input;
    }

    /**
     * @return number of iterations.
     */
    public int play() {
        int iterations = 0;
        Ui.Choice choice;
        input.prompt(strategy.getPrompt());
        while((choice = input.read()) != null) {
            iterations ++;
            switch(choice) {
                case higher :
                case lower  : {
                    strategy.advance(choice);
                    input.prompt(strategy.getPrompt());
                    break;
                }
                case yes    : // TODO log victory stats
                case end    : // TODO bye bye
                default     : {
                    input.prompt("Game ended after " + iterations + " iterations.");
                    return iterations;
                }
            }
        }
        return iterations;
    }
    
    
}