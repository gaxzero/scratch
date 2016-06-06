/*
 * This file was automatically generated by EvoSuite
 * Mon Jun 06 06:39:19 GMT 2016
 */
package org.collegeboard.challenge.guessnum;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.evosuite.runtime.EvoAssertions.*;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.Writer;
import java.math.BigDecimal;
import org.evosuite.runtime.EvoRunner;
import org.evosuite.runtime.EvoRunnerParameters;
import org.evosuite.runtime.util.SystemInUtil;
import org.evosuite.shaded.org.hamcrest.core.IsEqual;
import org.evosuite.shaded.org.mockito.Mock;
import static org.evosuite.shaded.org.mockito.Mockito.*;
import org.evosuite.shaded.org.mockito.MockitoAnnotations;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import org.junit.Before;
import org.junit.runner.RunWith;

@RunWith(EvoRunner.class)
@EvoRunnerParameters(mockJVMNonDeterminism = true, useVFS = true, useVNET = true, resetStaticState = true, separateClassLoader = false)
public class Ui_ESTest extends Ui_ESTest_scaffolding {

    @Mock
    BufferedReader in;
    @Mock
    PrintWriter out;

    @Before
    public void initMocks() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void test00() throws Throwable {
        Ui ui = new Ui(in, out);
        ui.prompt("hello");
        verify(out).println("hello");
    }

    @Test
    public void test01() throws Throwable {
        // SystemInUtil.addInputLine("yes");
        when(in.readLine()).thenReturn("yes");
        Ui ui = new Ui(in, out);
        Ui.Choice choice = ui.read();
        assertThat(choice, is(Ui.Choice.yes));
    }

    @Test
    public void test02() throws Throwable {
        Ui ui0 = new Ui((BufferedReader) null, (PrintWriter) null);
        // Undeclared exception!
        try {
            ui0.read();
            fail("Expecting exception: NullPointerException");

        } catch (NullPointerException e) {
            //
            // no message in exception (getMessage() returned null)
            //
            assertThrownBy("org.collegeboard.challenge.guessnum.Ui", e);
        }
    }

    @Test
    public void test03() throws Throwable {
        SystemInUtil.addInputLine("S97<f/!^I");
        Ui ui0 = new Ui();
        // Undeclared exception!
        try {
            ui0.read();
            fail("Expecting exception: Error");

        } catch (Error e) {
            //
            // java.io.IOException: Simulated exception in System.in
            //
            assertThrownBy("org.collegeboard.challenge.guessnum.Ui", e);
        }
    }

    @Test
    public void test04() throws Throwable {
        Ui ui = new Ui(in, (PrintWriter) null);
        // Undeclared exception!
        try {
            ui.prompt(BigDecimal.ONE);
            fail("Expecting exception: NullPointerException");

        } catch (NullPointerException e) {
            //
            // no message in exception (getMessage() returned null)
            //
            assertThrownBy("org.collegeboard.challenge.guessnum.Ui", e);
        }
    }

    @Test
    public void test05() throws Throwable {
        Ui ui0 = null;
        try {
            ui0 = new Ui((Reader) null, (Writer) null);
            fail("Expecting exception: NullPointerException");

        } catch (NullPointerException e) {
            //
            // no message in exception (getMessage() returned null)
            //
            assertThrownBy("java.io.Reader", e);
        }
    }

    @Test
    public void test06() throws Throwable {
        Ui ui0 = null;
        try {
            ui0 = new Ui((InputStream) null, (OutputStream) null);
            fail("Expecting exception: NullPointerException");

        } catch (NullPointerException e) {
            //
            // no message in exception (getMessage() returned null)
            //
            assertThrownBy("java.io.Reader", e);
        }
    }

    @Test
    public void test07() throws Throwable {
        Ui ui0 = new Ui((BufferedReader) null, (PrintWriter) null);
        // Undeclared exception!
        try {
            ui0.prompt("l4#-");
            fail("Expecting exception: NullPointerException");

        } catch (NullPointerException e) {
            //
            // no message in exception (getMessage() returned null)
            //
            assertThrownBy("org.collegeboard.challenge.guessnum.Ui", e);
        }
    }

    @Test
    public void test08() throws Throwable {
        when(in.readLine()).thenReturn("higher");
        Ui ui = new Ui(in, out);
        Ui.Choice choice = ui.read();
        assertThat(choice.name(), is(equalTo(Ui.Choice.higher.name())));
    }

    @Test
    public void test09() throws Throwable {
        Ui ui = new Ui(in, out);
        ui.read();
        verify(in).readLine();
    }

    @Test
    public void test10() throws Throwable {
        Ui ui = new Ui(in, out);
        BigDecimal prompt = BigDecimal.TEN;
        ui.prompt(prompt);
        verify(out).println("Is the number " + prompt + "?");
    }

}
