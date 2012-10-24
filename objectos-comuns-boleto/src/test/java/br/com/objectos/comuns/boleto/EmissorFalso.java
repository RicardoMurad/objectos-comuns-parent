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

/**
 * @author ricardo.murad@objectos.com.br (Ricardo Murad)
 */
public class EmissorFalso implements EmissorBoleto {

  @Override
  public String getCedente() {
    return null;
  }

  @Override
  public String getAgencia() {
    return null;
  }

  @Override
  public int getContaCorrente() {
    return 0;
  }

  @Override
  public int getCateira() {
    return 0;
  }

  @Override
  public int getNossoNumero() {
    return 0;
  }

}
