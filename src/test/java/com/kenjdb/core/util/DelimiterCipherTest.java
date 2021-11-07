package com.kenjdb.core.util;

import com.kenjerdb.core.exception.ValidationException;
import com.kenjerdb.core.util.DelimiterCipher;
import org.junit.jupiter.api.Test;

import static com.kenjerdb.core.constant.KenjerDatabaseConstant.DEFAULT_RECORD_DELIMITER;
import static org.junit.jupiter.api.Assertions.fail;

public class DelimiterCipherTest {

    private static final String DELIMITER = DEFAULT_RECORD_DELIMITER.value();

    private static final String ENCRYPTED_DELIMITER = "1[1-1|1|1-1]1";

    @Test
    public void testDecryptDelimiter() {
        System.out.println(DelimiterCipher.decryptDelimiter(ENCRYPTED_DELIMITER));
    }

    @Test
    public void testEncryptDelimiter() {
        try {
            System.out.println(DelimiterCipher.encryptDelimiter(DELIMITER));
        } catch (ValidationException e) {
            e.printStackTrace();
            fail();
        }
    }
}
