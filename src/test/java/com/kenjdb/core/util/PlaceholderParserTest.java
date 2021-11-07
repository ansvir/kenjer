package com.kenjdb.core.util;

import com.kenjerdb.core.util.PlaceholderParser;
import org.junit.jupiter.api.Test;

import static com.kenjerdb.core.constant.KenjerDatabaseConstant.DEFAULT_PLACEHOLDER_DELIMITER_CLOSE;
import static com.kenjerdb.core.constant.KenjerDatabaseConstant.DEFAULT_PLACEHOLDER_DELIMITER_OPEN;

public class PlaceholderParserTest {

    private static final String TEST_STRING =
            "some content before placeholder " +
            DEFAULT_PLACEHOLDER_DELIMITER_OPEN.value() +
            "some content inside placeholder to extract" +
            DEFAULT_PLACEHOLDER_DELIMITER_CLOSE.value() +
            "some content after first placeholder" +
            DEFAULT_PLACEHOLDER_DELIMITER_OPEN.value() +
            "I'M HERE" +
            DEFAULT_PLACEHOLDER_DELIMITER_CLOSE.value() +
            "some content after last placeholder";

    @Test
    public void testAllContents() {
        System.out.println(PlaceholderParser.allContents(
                        TEST_STRING,
                        DEFAULT_PLACEHOLDER_DELIMITER_OPEN.value(),
                        DEFAULT_PLACEHOLDER_DELIMITER_CLOSE.value()
                )
        );
    }
}
