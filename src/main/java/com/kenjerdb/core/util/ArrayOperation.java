package com.kenjerdb.core.util;

public class ArrayOperation {

    /**
     * Shift array left for one element and add symbol to the end of the array
     * @param delimiterCharSeq
     * @param addable
     * @return shifted array
     */
    public static char[] shiftLeft(char[] delimiterCharSeq, char addable) {

        for (int i = 0 ; i < delimiterCharSeq.length; i++) {
            if (i + 1 == delimiterCharSeq.length) break;
            delimiterCharSeq[i] = delimiterCharSeq[i + 1];
        }

        delimiterCharSeq[delimiterCharSeq.length - 1] = addable;

        return delimiterCharSeq;
    }
}
