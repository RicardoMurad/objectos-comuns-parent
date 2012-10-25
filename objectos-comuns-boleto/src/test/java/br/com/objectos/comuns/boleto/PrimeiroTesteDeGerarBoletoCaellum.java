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
import static org.hamcrest.Matchers.notNullValue;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.testng.annotations.Test;

import br.com.caelum.stella.boleto.Boleto;
import br.com.caelum.stella.boleto.Datas;
import br.com.caelum.stella.boleto.Emissor;
import br.com.caelum.stella.boleto.Sacado;
import br.com.caelum.stella.boleto.bancos.BancoDoBrasil;
import br.com.caelum.stella.boleto.transformer.BoletoGenerator;

/**
 * @author ricardo.murad@objectos.com.br (Ricardo Murad)
 */
@Test
public class PrimeiroTesteDeGerarBoletoCaellum {

  private FileInputStream stream;

  @Test
  public void teste_construcao_de_boleto_com_stella() throws Exception {
    String contra = "src/test/resources/contra.pdf";
    String resultado = "src/test/resources/saida.pdf";

    int diaDocumento = 22;
    int mesDocumento = 7;
    int anoDocumento = 2012;

    int diaProcessamento = 25;
    int mesProcessamento = 8;
    int anoProcessamento = 2013;

    int diaVencimento = 28;
    int mesVecnimento = 9;
    int anoVencimento = 2014;

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
    String descricao1 = "01 Produto XY";
    boolean aceite = false;
    String instruções = "Não receber após a data de vencimento.";
    String locaisPagamento = "Pagável em qualquer agência até a data de vencimento";
    String numeroDocumento = "124365";
    String especie = "EspecieDoc";
    BigDecimal quantidadeMoeda = BigDecimal.valueOf(11);
    BigDecimal valorMoeda = BigDecimal.valueOf(9);

    BancoDoBrasil banco = new BancoDoBrasil();

    Datas datas = Datas.newDatas()
        .withDocumento(diaDocumento, mesDocumento, anoDocumento)
        .withProcessamento(diaProcessamento, mesProcessamento, anoProcessamento)
        .withVencimento(diaVencimento, mesVecnimento, anoVencimento);

    Emissor emissor = Emissor.newEmissor()
        .withCedente(nomeCedente)
        .withAgencia(agencia)
        .withDigitoAgencia(digitoAgencia)
        .withContaCorrente(contaCorrente)
        .withDigitoContaCorrente(digitoContaCorrente)
        .withCodigoFornecidoPelaAgencia(codFornecidoPelaAgencia)
        .withCodigoOperacao(codOperacao)
        .withCarteira(carteira)
        .withNossoNumero(nossoNumero)
        .withDigitoNossoNumero(digitoNossoNumero)
        .withNumeroConvenio(numeroConvenio);

    Sacado sacado = Sacado.newSacado()
        .withNome(nomeSacado)
        .withCpf(cpf)
        .withEndereco(endereco)
        .withBairro(bairro)
        .withCep(cep)
        .withCidade(cidade)
        .withUf(estado);

    Boleto boleto = Boleto.newBoleto()
        .withBanco(banco)
        .withDatas(datas)
        .withEmissor(emissor)
        .withSacado(sacado)
        .withValorBoleto(valorBoleto)
        .withDescricoes(descricao1)
        .withAceite(aceite)
        .withInstrucoes(instruções)
        .withLocaisDePagamento(locaisPagamento)
        .withNumeroDoDocumento(numeroDocumento)
        .withEspecieDocumento(especie)
        .withQuantidadeMoeda(quantidadeMoeda)
        .withValorMoeda(valorMoeda);

    BoletoGenerator generator = new BoletoGenerator(boleto);
    generator.toPDF(resultado);
    generator.toPDF(contra);

    byte[] res = gerarHashSha1(contra);
    byte[] prova = gerarHashSha1(resultado);

    assertThat(prova, notNullValue());
    assertThat(contra, notNullValue());
    assertThat(res.length, equalTo(prova.length));
    assertThat(res, equalTo(prova));
  }

  private byte[] gerarHashSha1(String path) throws NoSuchAlgorithmException, IOException {
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