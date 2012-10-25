/*
 * Copyright 2012 Objectos, Fábrica de Software LTDA.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package br.com.objectos.comuns.boleto;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

class GerarHash {

  public static byte[] fileToSha1(String path) throws NoSuchAlgorithmException, IOException {
    FileInputStream stream;
    File file = new File(path);
    MessageDigest digest = MessageDigest.getInstance("SHA1");
    stream = new FileInputStream(file);
    byte[] buffer = new byte[1024];
    int n = 0;

    while (n != -1) {
      n = stream.read();
      if (n > 0) {
        digest.update(buffer, 0, n);
      }
    }

    stream.close();
    return digest.digest();
  }

}