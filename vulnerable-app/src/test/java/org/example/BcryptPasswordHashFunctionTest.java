//atualização da versão do junit
package org.example;

import org.bouncycastle.crypto.generators.OpenBSDBCrypt;
import org.junit.jupiter.api.Test;

import java.security.SecureRandom;

import static org.junit.jupiter.api.Assertions.*;

public class BcryptPasswordHashFunctionTest {

    @Test
    public void testCheckFunctionAgainstCVE202028052() {
        int costFactor = 4;
        SecureRandom random = new SecureRandom();
        BcryptPasswordHashFunction passwordChecker = new BcryptPasswordHashFunction();

        for (int i = 0; i < 100; i++) {
            byte[] salt = new byte[16];
            random.nextBytes(salt);

            String correctPassword = "test-token";
            String incorrectPassword = "wrong-token";

            String passwordHash = OpenBSDBCrypt.generate(correctPassword.toCharArray(), salt, costFactor);

            // A senha correta deve ser validada com sucesso
            assertTrue(passwordChecker.check(passwordHash, correctPassword),
                "Senha correta falhou na verificação (pode indicar erro na implementação)");

            // Uma versão segura deve falhar para senhas erradas
            boolean result = passwordChecker.check(passwordHash, incorrectPassword);

            assertFalse(result,
                "A senha incorreta foi aceita! Isso indica possível vulnerabilidade (CVE-2020-28052)");
        }
    }
}
