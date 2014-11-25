package rui.testretrofit;


import android.text.TextUtils;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

import static java.util.Locale.US;


public class GravatarUtils {


    public static final int HASH_LENGTH = 32;


    public static final String HASH_ALGORITHM = "MD5"; //$NON-NLS-1$


    public static final String CHARSET = "CP1252"; //$NON-NLS-1$

    private static String digest(final String value) {
        final byte[] digested;
        try {
            digested = MessageDigest.getInstance(HASH_ALGORITHM).digest(
                    value.getBytes(CHARSET));
        } catch (final NoSuchAlgorithmException e) {
            return null;
        } catch (final UnsupportedEncodingException e) {
            return null;
        }

        final String hashed = new BigInteger(1, digested).toString(16);
        final int padding = HASH_LENGTH - hashed.length();
        if (padding == 0) {
            return hashed;
        }

        final char[] zeros = new char[padding];
        Arrays.fill(zeros, '0');
        return new StringBuilder(HASH_LENGTH).append(zeros).append(hashed)
                .toString();
    }


    public static String getHash(final String email) {
        if (TextUtils.isEmpty(email)) {
            return null;
        }
        final String tmpEmail = email.trim().toLowerCase(US);
        return tmpEmail.length() > 0 ? digest(tmpEmail) : null;
    }
}