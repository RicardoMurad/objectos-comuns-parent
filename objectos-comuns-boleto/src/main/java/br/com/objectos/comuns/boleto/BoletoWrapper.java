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

import java.math.BigDecimal;
import java.util.Calendar;

import org.joda.time.LocalDate;

import br.com.caelum.stella.boleto.Boleto;
import br.com.caelum.stella.boleto.Datas;
import br.com.caelum.stella.boleto.Emissor;
import br.com.caelum.stella.boleto.Sacado;
import br.com.caelum.stella.boleto.bancos.BancoDoBrasil;
import br.com.caelum.stella.boleto.transformer.BoletoGenerator;

/**
 * @author ricardo.murad@objectos.com.br (Ricardo Murad)
 */
public class BoletoWrapper {

  private final Boleto boleto;
  private final Datas datas;
  private final Sacado sacado;
  private final Emissor emissor;

  BoletoWrapper() {
    // -- Guice
    this.datas = Datas.newDatas();
    this.boleto = Boleto.newBoleto();
    this.sacado = Sacado.newSacado();
    this.emissor = Emissor.newEmissor();
  }

  public BoletoWrapper nomeCedente(String nome) {
    emissor.withCedente(nome);
    return this;
  }

  public BoletoWrapper agencia(int agencia) {
    emissor.withAgencia(agencia);
    return this;
  }

  public BoletoWrapper digitoAgencia(char digito) {
    emissor.withDigitoAgencia(digito);
    return this;
  }

  public BoletoWrapper contaCorrente(long conta) {
    emissor.withContaCorrente(conta);
    return this;
  }

  public BoletoWrapper digitoContaCorrente(char digito) {
    emissor.withDigitoContaCorrente(digito);
    return this;
  }

  public BoletoWrapper codigoAgencia(int codigo) {
    emissor.withCodigoFornecidoPelaAgencia(codigo);
    return this;
  }

  public BoletoWrapper codigoOperacao(int codigo) {
    emissor.withCodigoOperacao(codigo);
    return this;
  }

  public BoletoWrapper carteira(int carteira) {
    emissor.withCarteira(carteira);
    return this;
  }

  public BoletoWrapper nossoNumero(long numero) {
    emissor.withNossoNumero(numero);
    return this;
  }
  public BoletoWrapper digitoNossoNumero(char digito) {
    emissor.withDigitoNossoNumero(digito);
    return this;
  }

  public BoletoWrapper numeroConvenio(long convenio) {
    emissor.withNumeroConvenio(convenio);
    return this;
  }

  public BoletoWrapper nomeSacado(String nome) {
    sacado.withNome(nome);
    return this;
  }

  public BoletoWrapper cpfSacado(String cpf) {
    sacado.withCpf(cpf);
    return this;
  }

  public BoletoWrapper enderecoSacado(String endereco) {
    sacado.withEndereco(endereco);
    return this;
  }

  public BoletoWrapper bairroSacado(String bairro) {
    sacado.withBairro(bairro);
    return this;
  }

  public BoletoWrapper cepSacado(String cep) {
    sacado.withCep(cep);
    return this;
  }

  public BoletoWrapper cidadeSacado(String cidade) {
    sacado.withCidade(cidade);
    return this;
  }

  public BoletoWrapper estadoSacado(String estado) {
    sacado.withUf(estado);
    return this;
  }

  public BoletoWrapper emissor(Emissor emissor) {
    return this;
  }

  public BoletoWrapper emissor(TipoDeBanco banco) {
    return null;
  }

  public BoletoWrapper dataVencimento(LocalDate data) {
    datas.withVencimento(toCalendar(data));
    return this;
  }

  public BoletoWrapper dataProcessamento(LocalDate data) {
    datas.withProcessamento(toCalendar(data));
    return this;
  }

  public BoletoWrapper dataDocumento(LocalDate data) {
    datas.withDocumento(toCalendar(data));
    return this;
  }

  private Calendar toCalendar(LocalDate data) {
    int dia = data.getDayOfMonth();
    int mes = data.getMonthOfYear();
    int ano = data.getYear();
    Calendar calendar = Calendar.getInstance();
    calendar.set(ano, mes, dia);
    return calendar;
  }

  public BoletoWrapper valorDoBoleto(String valor) {
    boleto.withValorBoleto(valor);
    return this;
  }

  public BoletoWrapper descricao(String descricao) {
    boleto.withDescricoes(descricao);
    return this;
  }

  public BoletoWrapper aceite(boolean aceite) {
    boleto.withAceite(aceite);
    return this;
  }

  public BoletoWrapper instrucoes(String instrucoes) {
    boleto.withInstrucoes(instrucoes);
    return this;
  }

  public BoletoWrapper locaisDepagamento(String locaisDePagamento) {
    boleto.withLocaisDePagamento(locaisDePagamento);
    return this;
  }

  public BoletoWrapper numeroDocumento(String numero) {
    boleto.withNumeroDoDocumento(numero);
    return this;
  }

  public BoletoWrapper especieDocumento(String especie) {
    boleto.withEspecieDocumento(especie);
    return this;
  }

  public BoletoWrapper quantidadeMoeda(BigDecimal quantidade) {
    boleto.withQuantidadeMoeda(quantidade);
    return this;
  }

  public BoletoWrapper valorMoeda(BigDecimal valor) {
    boleto.withValorMoeda(valor);
    return this;
  }

  public BoletoWrapper paraBanco(TipoDeBanco banco) {
    // refac propio enum deve retornar inst
    if (banco == TipoDeBanco.BANCO_DO_BRASIL) {
      BancoDoBrasil bancoDoBrasil = new BancoDoBrasil();
      boleto.withBanco(bancoDoBrasil);
    }
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

  public static BoletoWrapper newBoleto() {
    return new BoletoWrapper();
  }

  private void join() {
    boleto.withDatas(datas);
    boleto.withEmissor(emissor);
    boleto.withSacado(sacado);
  }

}