package com.tlf.basic.http;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
        String key_one ="./gradlew clean build bintrayUpload -PbintrayUser=vic-tan -PbintrayKey=8970dbd317d720d9b1176877f90c9bd6a431e650 -PdryRun=false";

    }
}