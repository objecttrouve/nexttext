/**
 * Released under the terms of the Apache License 2.0.
 * Copyright (c) 2020 objecttrouve.org <un.object.trouve@gmail.com>.
 */
package org.objecttrouve.nexttext;

import org.junit.Test;

@SuppressWarnings("ConstantConditions")
public class NextTextTestJava {

    @Test(expected = IllegalArgumentException.class)
    public void criDistance__null_null(){
        NextText nextText = new NextText.Builder().build();

        nextText.criDistance(null, null);
    }

    @Test(expected = NullPointerException.class)
    public void Builder_withMinCodePoint__null(){
        final Integer nullInt = null;
        new NextText.Builder().withMinCodePoint(nullInt);
    }

    @Test(expected = NullPointerException.class)
    public void Builder_withMaxCodePoint__null(){
        final Integer nullInt = null;
        new NextText.Builder().withMaxCodePoint(nullInt);
    }
}
