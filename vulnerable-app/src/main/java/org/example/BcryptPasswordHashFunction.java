//código cliente que chama o método vulnerável

package org.example;

import org.bouncycastle.crypto.generators.OpenBSDBCrypt;

public class BcryptPasswordHashFunction {
    public boolean check(String passwordHash, String password) {
        return OpenBSDBCrypt.checkPassword(passwordHash, password.toCharArray());
    }
}

