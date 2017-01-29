package com.f1x.mtcdtools.input;

import org.json.JSONArray;
import org.json.JSONException;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static junit.framework.Assert.assertEquals;

/**
 * Created by COMPUTER on 2017-01-24.
 */

public class KeysSequenceConverterTest {
    @Before
    public void init() throws JSONException {
        mKeysSequenceJsonArray = new JSONArray();
        mKeysSequenceJsonArray.put(1);
        mKeysSequenceJsonArray.put(100);
        mKeysSequenceJsonArray.put(1000);
    }

    @Test
    public void test_Conversion() throws JSONException {
        List<Integer> keysSequence = KeysSequenceConverter.fromJsonArray(mKeysSequenceJsonArray);
        assertEquals(mKeysSequenceJsonArray.length(), keysSequence.size());
        for(int i = 0; i < mKeysSequenceJsonArray.length(); ++i) {
            assertEquals(mKeysSequenceJsonArray.get(i), keysSequence.get(i));
        }

        JSONArray keysSequenceJsonArray = KeysSequenceConverter.toJsonArray(keysSequence);
        assertEquals(mKeysSequenceJsonArray.toString(), keysSequenceJsonArray.toString());
    }

    @Test
    public void test_Conversion_From_Array() {
        int array[] = { 1, 2, 3, 4, 5};
        List<Integer> keysSequence = KeysSequenceConverter.fromArray(array);

        assertEquals(array.length, keysSequence.size());

        for(int i = 0; i < array.length; ++i) {
            assertEquals(array[i], (int)keysSequence.get(i));
        }
    }

    private JSONArray mKeysSequenceJsonArray;
}
