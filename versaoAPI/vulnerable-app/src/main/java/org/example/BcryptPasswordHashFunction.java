//código cliente que chama o método vulnerável

package org.example;

import org.bouncycastle.crypto.generators.OpenBSDBCrypt;

public class BcryptPasswordHashFunction {
    // O método abaixo chama a API vulnerável, portanto pode ser afetado por CVE − 2020 − 28052.
    public boolean check(String passwordHash, String password) {
        // a chamada à API vulnerável
        return OpenBSDBCrypt.checkPassword(passwordHash, password.toCharArray());
    }
}

