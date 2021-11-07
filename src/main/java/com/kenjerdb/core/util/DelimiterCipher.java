package com.kenjerdb.core.util;

import com.kenjerdb.core.exception.ValidationException;
import com.kenjerdb.core.validator.StringValidator;

import static com.kenjerdb.core.constant.KenjerDatabaseConstant.CIPHER_SIGN;
import static com.kenjerdb.core.validator.ValidatorValuesType.DELIMITER_REGEX;

public class DelimiterCipher {

    public static String encryptDelimiter(String delimiter) throws ValidationException {
        StringValidator validator = new StringValidator(DELIMITER_REGEX.value());
        if (!validator.isValid(delimiter)) {
            throw new ValidationException(delimiter);
        }
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < delimiter.length(); i++) {
            result.append(CIPHER_SIGN.value());
            result.append(delimiter.charAt(i));
        }
        result.append(CIPHER_SIGN.value());
        return result.toString();
    }

    public static String decryptDelimiter(String encryptedDelimiter) {
        StringBuilder result = new StringBuilder(encryptedDelimiter);
        for (int i = 0; i < encryptedDelimiter.length() - 2; i++) {
            if (i > result.length()) {
                break;
            }
            result.deleteCharAt(i);
        }
        return result.toString();
    }
}
