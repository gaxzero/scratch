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

import java.util.Arrays;
import javax.ejb.Singleton;

/**
 *
 * @author gax
 */
@Singleton
public class SpellBean {

  private static final char[][] KEYPADE = {
      {'0'}, 
      {'1'},
      {'2','A','B','C'},
      {'3','D','E','F'},
      {'4','G','H','I'},
      {'5','J','K','L'},
      {'6','M','N','O'},
      {'7','P','Q','R','S'},
      {'8','T','U','V'},
      {'9','W','X','Y','Z'}
  };
  
  public int conbinationCount(int[] digits) {
      if (digits == null || (digits.length != 7 && digits.length != 10)) {
          throw new IllegalArgumentException("invalid length");
      }
      return Arrays.stream(digits)
              .map(d -> KEYPADE[d].length)
              .reduce(1, Math::multiplyExact);
  }
}
