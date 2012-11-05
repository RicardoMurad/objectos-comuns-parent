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

import java.util.Calendar;

import org.joda.time.LocalDate;

import br.com.caelum.stella.boleto.Boleto;
import br.com.caelum.stella.boleto.Datas;
import br.com.caelum.stella.boleto.Emissor;
import br.com.caelum.stella.boleto.Sacado;
import br.com.caelum.stella.boleto.transformer.BoletoGenerator;
import br.com.objectos.comuns.base.br.Cep;
import br.com.objectos.comuns.base.br.Cpf;
import br.com.objectos.comuns.matematica.financeira.ValorFinanceiro;

/**
 * @author ricardo.murad@objectos.com.br (Ricardo Murad)
 */
public class BoletoBancario {

  private final Boleto boleto;
  private final Datas datas;
  private final Sacado sacado;
  private final Emissor emissor;

  BoletoBancario() {
    this.datas = Datas.newDatas();
    this.boleto = Boleto.newBoleto();
    this.sacado = Sacado.newSacado();
    this.emissor = Emissor.newEmissor();
  }

  public BoletoBancario nomeCedente(String nome) {
    emissor.withCedente(nome);
    return this;
  }

  public BoletoBancario agencia(int agencia) {
    emissor.withAgencia(agencia);
    return this;
  }

  public BoletoBancario digitoAgencia(char digito) {
    emissor.withDigitoAgencia(digito);
    return this;
  }

  public BoletoBancario contaCorrente(long conta) {
    emissor.withContaCorrente(conta);
    return this;
  }

  public BoletoBancario digitoContaCorrente(char digito) {
    emissor.withDigitoContaCorrente(digito);
    return this;
  }

  public BoletoBancario codigoAgencia(int codigo) {
    emissor.withCodigoFornecidoPelaAgencia(codigo);
    return this;
  }

  public BoletoBancario codigoOperacao(int codigo) {
    emissor.withCodigoOperacao(codigo);
    return this;
  }

  public BoletoBancario carteira(int carteira) {
    emissor.withCarteira(carteira);
    return this;
  }

  public BoletoBancario nossoNumero(long numero) {
    emissor.withNossoNumero(numero);
    return this;
  }
  public BoletoBancario digitoNossoNumero(char digito) {
    emissor.withDigitoNossoNumero(digito);
    return this;
  }

  public BoletoBancario numeroConvenio(long convenio) {
    emissor.withNumeroConvenio(convenio);
    return this;
  }

  public BoletoBancario nomeSacado(String nome) {
    sacado.withNome(nome);
    return this;
  }

  public BoletoBancario cpfSacado(Cpf cpf) {
    long longValue = cpf.longValue();
    sacado.withCpf(Long.toString(longValue));
    return this;
  }

  public BoletoBancario enderecoSacado(String endereco) {
    sacado.withEndereco(endereco);
    return this;
  }

  public BoletoBancario bairroSacado(String bairro) {
    sacado.withBairro(bairro);
    return this;
  }

  public BoletoBancario cepSacado(Cep cep) {
    int prefixo = cep.getPrefixo();
    int sufixo = cep.getSufixo();
    sacado.withCep(String.format("%05d%03d", prefixo, sufixo));
    return this;
  }
  public BoletoBancario cidadeSacado(String cidade) {
    sacado.withCidade(cidade);
    return this;
  }

  public BoletoBancario estadoSacado(String estado) {
    sacado.withUf(estado);
    return this;
  }

  public BoletoBancario dataVencimento(LocalDate data) {
    datas.withVencimento(toCalendar(data));
    return this;
  }

  public BoletoBancario dataProcessamento(LocalDate data) {
    datas.withProcessamento(toCalendar(data));
    return this;
  }

  public BoletoBancario dataDocumento(LocalDate data) {
    datas.withDocumento(toCalendar(data));
    return this;
  }

  private Calendar toCalendar(LocalDate data) {
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(data.toDate());
    return calendar;
  }

  public BoletoBancario valorDoBoleto(double valor) {
    boleto.withValorBoleto(valor);
    return this;
  }

  public BoletoBancario valorDoBoleto(ValorFinanceiro valor) {
    double val = valor.doubleValue();
    boleto.withValorBoleto(val);
    return this;
  }

  public BoletoBancario descricao(String descricao) {
    boleto.withDescricoes(descricao);
    return this;
  }

  public BoletoBancario aceite(boolean aceite) {
    boleto.withAceite(aceite);
    return this;
  }

  public BoletoBancario instrucoes(String instrucoes) {
    boleto.withInstrucoes(instrucoes);
    return this;
  }

  public BoletoBancario locaisDepagamento(String locaisDePagamento) {
    boleto.withLocaisDePagamento(locaisDePagamento);
    return this;
  }

  public BoletoBancario numeroDocumento(String numero) {
    boleto.withNumeroDoDocumento(numero);
    return this;
  }

  public BoletoBancario especieDocumento(String especie) {
    boleto.withEspecieDocumento(especie);
    return this;
  }

  public BoletoBancario paraBanco(BoletoBanco enumBanco) {
    boleto.withBanco(enumBanco.getbanco());
    return this;
  }

  public byte[] toPdf() {
    join();
    return new BoletoGenerator(boleto)
        .toPDF();
  }

  public void toPdf(String filename) {
    join();
    new BoletoGenerator(boleto)
        .toPDF(filename);
  }

  public static BoletoBancario newBoleto() {
    return new BoletoBancario();
  }

  private void join() {
    boleto.withDatas(datas);
    boleto.withEmissor(emissor);
    boleto.withSacado(sacado);
  }

}