package org.example;

import org.bouncycastle.crypto.generators.OpenBSDBCrypt;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.*;

public class BcryptPasswordHashFunctionTest {

    private BcryptPasswordHashFunction bcryptPasswordHashFunction;

    @BeforeEach
    public void setUp() {
        bcryptPasswordHashFunction = new BcryptPasswordHashFunction();
    }

    @Test
    public void testCheckPasswordSecureImplementation() {
        // Mocking the OpenBSDBCrypt class to simulate secure behavior
        OpenBSDBCrypt openBSDBCryptMock = Mockito.mock(OpenBSDBCrypt.class);
        when(openBSDBCryptMock.checkPassword(anyString(), any(char[].class))).thenAnswer(invocation -> {
            String passwordHash = invocation.getArgument(0);
            char[] password = invocation.getArgument(1);
            return passwordHash.equals(new String(password)); // Simulate secure check
        });

        // Simulate a secure hash for "test-token"
        String passwordHash = OpenBSDBCrypt.generate("test-token".toCharArray(), new byte[16], 4);

        // Test valid password
        assertTrue(bcryptPasswordHashFunction.check(passwordHash, "test-token"));
        // Test invalid password
        assertFalse(bcryptPasswordHashFunction.check(passwordHash, "wrong-token"));
    }

    @Test
    public void testCheckPasswordVulnerableImplementation() {
        // Mocking the OpenBSDBCrypt class to simulate vulnerable behavior
        OpenBSDBCrypt openBSDBCryptMock = Mockito.mock(OpenBSDBCrypt.class);
        when(openBSDBCryptMock.checkPassword(anyString(), any(char[].class))).thenReturn(true); // Vulnerable behavior

        // Simulate a hash for "test-token"
        String passwordHash = OpenBSDBCrypt.generate("test-token".toCharArray(), new byte[16], 4);

        // Test valid password
        assertTrue(bcryptPasswordHashFunction.check(passwordHash, "test-token"));
        // Test invalid password, which should incorrectly return true due to vulnerability
        assertTrue(bcryptPasswordHashFunction.check(passwordHash, "wrong-token")); // This should fail in a secure implementation
    }
}