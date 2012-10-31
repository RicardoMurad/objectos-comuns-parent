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

import br.com.caelum.stella.boleto.Banco;
import br.com.caelum.stella.boleto.bancos.BancoDoBrasil;
import br.com.caelum.stella.boleto.bancos.Bradesco;
import br.com.caelum.stella.boleto.bancos.Caixa;
import br.com.caelum.stella.boleto.bancos.Itau;

/**
 * @author ricardo.murad@objectos.com.br (Ricardo Murad)
 */
public enum BoletoBanco {
  BANCO_DO_BRASIL("001") {
    @Override
    Banco getbanco() {
      return new BancoDoBrasil();
    }
  },
  BRADESCO("237") {
    @Override
    Banco getbanco() {
      return new Bradesco();
    }
  },
  CAIXA("104") {
    @Override
    Banco getbanco() {
      return new Caixa();
    }
  },
  ITAU("341") {
    @Override
    Banco getbanco() {
      return new Itau();
    }
  };

  private String numero;

  BoletoBanco(String numero) {
    this.numero = numero;
  }

  public static BoletoBanco porNumero(String numero) {
    for (BoletoBanco banco : BoletoBanco.values()) {
      if (banco.numero == numero)
        return banco;
    }
    return null;
  }

  abstract Banco getbanco();
}