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

import org.joda.time.LocalDate;
import org.testng.annotations.Test;

/**
 * @author ricardo.murad@objectos.com.br (Ricardo Murad)
 */
@Test
public class TesteDeEmissaoDeBoletoPdfWrapper {

  public void gerar_boleto_em_formato_pdf() {

    String path = "boleto.pdf";
    LocalDate data = new LocalDate(2012, 10, 20);

    BoletoWrapper
        .newBoleto()
        .dataDocumento(data)
        .dataProcessamento(data)
        .dataVencimento(data)
        .valor(100.0)
        .aceite(false)
        .numeroDocumento("12345")
        .descricao("Produto XYZ")
        .instrucoes("não receber após o vencimento")
        .locaisDepagamento("pagável em qualquer agência")
        .toPdf(path);

  }

}