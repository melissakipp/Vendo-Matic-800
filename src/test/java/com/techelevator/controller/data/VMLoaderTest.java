package com.techelevator.controller.data;

import junitparams.FileParameters;
import junitparams.JUnitParamsRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.BufferedInputStream;
import java.util.Objects;

import static org.junit.Assert.assertTrue;

@RunWith(JUnitParamsRunner.class)
public class VMLoaderTest {



    static BufferedInputStream buffer = null;

    @Before
    public void setup() {
        buffer = new BufferedInputStream(Objects.requireNonNull(getClass().getResourceAsStream(
                "src\\test\\java\\resources\\vendingmachineTest.csv"))
        );
        System.out.println("SETUP!");
    }

    @Test
    @FileParameters("src\\test\\java\\resources\\vendingmachineTest.csv")
    public void loadParamsFromFileWithIdentityMapper(int age, String name) {
        assertTrue(age > 0);
    }

    // https://stackoverflow.com/questions/36406422/mockito-and-unit-test-for-a-csv-reader

// Example
//    @Rule
//    public MockitoRule mockito = MockitoJUnit.rule().strictness(STRICT_STUBS);
//
//    @Mock
//    Foo foo;
//    @Mock Bar bar;
//
//    @Before public void before() {
//        when(foo.foo()).thenReturn("ok");
//
//        // it is better to configure the stubbing to be lenient:
//        // lenient().when(foo.foo()).thenReturn("ok");
//
//        // or the entire mock to be lenient:
//        // foo = mock(Foo.class, withSettings().lenient());
//    }
//
//    @Test public void test1() {
//        foo.foo();
//    }
//
//    @Test public void test2() {
//        foo.foo();
//    }
//
//    @Test public void test3() {
//        bar.bar();
//    }

}
