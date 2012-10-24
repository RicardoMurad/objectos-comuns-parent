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

import org.joda.time.LocalDate;

import br.com.caelum.stella.boleto.Boleto;
import br.com.caelum.stella.boleto.Datas;
import br.com.caelum.stella.boleto.Emissor;
import br.com.caelum.stella.boleto.Sacado;
import br.com.caelum.stella.boleto.transformer.BoletoGenerator;

/**
 * @author ricardo.murad@objectos.com.br (Ricardo Murad)
 */
public class BoletoWrapper {

  private final Boleto boleto;
  private final Datas datas;

  BoletoWrapper() {
    // Guice
    this.datas = Datas.newDatas();
    this.boleto = Boleto.newBoleto();
  }

  public BoletoWrapper sacado(Sacado sacado) {
    return this;
  }

  public BoletoWrapper emissor(Emissor emissor) {
    return this;
  }

  public BoletoWrapper emissor(TipoDeBanco banco) {
    return null;
  }

  public BoletoWrapper dataVencimento(LocalDate data) {
    int dia = data.getDayOfMonth();
    int mes = data.getMonthOfYear();
    int ano = data.getYear();
    datas.withVencimento(dia, mes, ano);
    return this;
  }

  public BoletoWrapper dataProcessamento(LocalDate data) {
    int dia = data.getDayOfMonth();
    int mes = data.getMonthOfYear();
    int ano = data.getYear();
    datas.withProcessamento(dia, mes, ano);
    return this;
  }

  public BoletoWrapper dataDocumento(LocalDate data) {
    int dia = data.getDayOfMonth();
    int mes = data.getMonthOfYear();
    int ano = data.getYear();
    datas.withDocumento(dia, mes, ano);
    return this;
  }

  public BoletoWrapper numeroDocumento(String numero) {
    boleto.withNumeroDoDocumento(numero);
    return this;
  }

  public BoletoWrapper valor(double valor) {
    boleto.withValorBoleto(BigDecimal.valueOf(valor));
    return this;
  }

  public BoletoWrapper aceite(boolean aceite) {
    boleto.withAceite(aceite);
    return this;
  }

  public BoletoWrapper descricao(String descricao) {
    boleto.withDescricoes(descricao);
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

  public byte[] toPdf() {
    boleto.withDatas(datas);
    return new BoletoGenerator(boleto)
        .toPDF();
  }

  public void toPdf(String filename) {
    boleto.withDatas(datas);
    new BoletoGenerator(boleto)
        .toPDF(filename);
  }

  public static BoletoWrapper newBoleto() {
    return new BoletoWrapper();
  }
}