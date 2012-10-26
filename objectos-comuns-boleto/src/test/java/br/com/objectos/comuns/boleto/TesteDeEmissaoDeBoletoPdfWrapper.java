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

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import java.math.BigDecimal;

import org.joda.time.LocalDate;
import org.testng.annotations.Test;

/**
 * @author ricardo.murad@objectos.com.br (Ricardo Murad)
 */
@Test
public class TesteDeEmissaoDeBoletoPdfWrapper {

  public void gerar_boleto_em_formato_pdf() throws Exception {
    String contra = "src/test/resources/contra.pdf";
    String resultado = "src/test/resources/saida.pdf";

    LocalDate dataDocumento = new LocalDate(2012, 7, 22);
    LocalDate dataProcessamento = new LocalDate(2013, 8, 25);
    LocalDate dataVecimento = new LocalDate(2014, 9, 28);

    String nomeCedente = "Paulo da Silva";
    int agencia = 238;
    char digitoAgencia = '3';
    int contaCorrente = 127076;
    char digitoContaCorrente = '2';
    int codFornecidoPelaAgencia = 987;
    int codOperacao = 9;
    int carteira = 18;
    int nossoNumero = 966691;
    char digitoNossoNumero = 'z';
    int numeroConvenio = 456;

    String nomeSacado = "Marina Santos";
    String cpf = "30567362850";
    String endereco = "Avenida Paulista, 1000";
    String bairro = "Bela Vista";
    String cep = "01310100";
    String cidade = "São Paulo";
    String estado = "SP";

    String valorBoleto = "177.40";
    String descricao = "01 Produto XY";
    boolean aceite = false;
    String instruções = "Não receber após a data de vencimento.";
    String locaisPagamento = "Pagável em qualquer agência até a data de vencimento";
    String numeroDocumento = "124365";
    String especie = "EspecieDoc";
    BigDecimal quantidadeMoeda = BigDecimal.valueOf(11);
    BigDecimal valorMoeda = BigDecimal.valueOf(9);

    BoletoWrapper.newBoleto()
        .dataDocumento(dataDocumento)
        .dataProcessamento(dataProcessamento)
        .dataVencimento(dataVecimento)

        .nomeCedente(nomeCedente)
        .agencia(agencia)
        .digitoAgencia(digitoAgencia)
        .contaCorrente(contaCorrente)
        .digitoContaCorrente(digitoContaCorrente)
        .codigoAgencia(codFornecidoPelaAgencia)
        .codigoOperacao(codOperacao)
        .carteira(carteira)
        .nossoNumero(nossoNumero)
        .digitoNossoNumero(digitoNossoNumero)
        .numeroConvenio(numeroConvenio)

        .nomeSacado(nomeSacado)
        .cpfSacado(cpf)
        .enderecoSacado(endereco)
        .bairroSacado(bairro)
        .cepSacado(cep)
        .cidadeSacado(cidade)
        .estadoSacado(estado)

        .valorDoBoleto(valorBoleto)
        .descricao(descricao)
        .aceite(aceite)
        .instrucoes(instruções)
        .locaisDepagamento(locaisPagamento)
        .numeroDocumento(numeroDocumento)
        .especieDocumento(especie)
        .quantidadeMoeda(quantidadeMoeda)
        .valorMoeda(valorMoeda)
        .paraBanco(TipoDeBanco.BANCO_DO_BRASIL)
        .toPdf(resultado);

    String c1 = PdfToText.fromFile(contra);
    String c2 = PdfToText.fromFile(resultado);

    assertThat(c1, equalTo(c2));
  }

}