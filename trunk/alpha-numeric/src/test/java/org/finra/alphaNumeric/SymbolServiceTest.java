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

import javax.ejb.EJB;
import static org.hamcrest.core.Is.*;
import static org.hamcrest.core.IsEqual.equalTo;
//import org.jboss.arquillian.container.test.api.Deployment;
//import org.jboss.arquillian.core.api.annotation.Inject;
//import org.jboss.arquillian.junit.Arquillian;
//import org.jboss.shrinkwrap.api.ShrinkWrap;
//import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Test;
import static org.junit.Assert.*;
//import org.junit.runner.RunWith;
//import org.wildfly.swarm.jaxrs.JAXRSArchive;

/**
 *
 * @author gax
 */
//@RunWith(Arquillian.class)
public class SymbolServiceTest {

//    @Inject
    private SymbolService service = new SymbolService();

//    @Deployment
//    public static JAXRSArchive createDeployment() throws Exception {
//        return ShrinkWrap.create(JAXRSArchive.class)
//                .addClass(SymbolService.class)
//                .addAllDependencies(true);
//
//    }
    // TODO : test in container with javax.validation of parameters.

    @Test
    public void testList() throws Exception {
        QueryResult result = service.list("1234567", 0, 10);
        assertThat(result.getTotalCount(), is(equalTo(5120)));
        assertThat(result.getList().size(), is(equalTo(10)));
    }

    @Test
    public void testGet() throws Exception {
        QueryResult result = service.list("0000000000", 0, 1);
        assertThat(result.getTotalCount(), is(equalTo(1)));
        assertThat(result.getList().size(), is(equalTo(1)));
    }

}
