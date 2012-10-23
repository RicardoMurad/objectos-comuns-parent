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
package br.com.objectos.comuns.geradorcodigo;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Calendar;

import org.testng.annotations.Test;

import com.sun.codemodel.internal.ClassType;
import com.sun.codemodel.internal.JBlock;
import com.sun.codemodel.internal.JClassAlreadyExistsException;
import com.sun.codemodel.internal.JCodeModel;
import com.sun.codemodel.internal.JDefinedClass;
import com.sun.codemodel.internal.JMethod;

/**
 * @author ricardo.murad@objectos.com.br (Ricardo Murad)
 */
@Test
public class TesteDeGerarClasseJava {

  private BufferedReader res;

  public void gerarArquivo() throws JClassAlreadyExistsException, IOException,
      ClassNotFoundException {

    JCodeModel cm = new JCodeModel();

    String nomeClasse = "EntidadePessoa";
    String nomeMetodo1 = "getNome";
    String nomeMetodo2 = "getData";
    String nomeMetodo3 = "getIdade";
    String metodoSet = "setname";
    String destino = "./";

    JDefinedClass entidade = cm._class(nomeClasse, ClassType.CLASS);
    entidade.method(0, String.class, nomeMetodo1);
    entidade.method(0, Calendar.class, nomeMetodo2);
    JMethod method = entidade.method(0, Calendar.class, nomeMetodo3);
    method.param(String.class, metodoSet);
    JBlock body = method.body();
    body._return();

    File gravarArquivo = new File(destino);
    cm.build(gravarArquivo);

    File lerDoDisco = new File(destino + "/" + nomeClasse + ".java");
    FileReader arquivo = new FileReader(lerDoDisco);
    res = new BufferedReader(arquivo);
    String saida = new String();
    while (res.ready()) {
      saida += res.readLine();
    }

    assertThat(saida.isEmpty(), equalTo(false));
    assertThat(saida.contains(nomeClasse), equalTo(true));
    assertThat(saida.contains(nomeMetodo1), equalTo(true));
    assertThat(saida.contains(nomeMetodo2), equalTo(true));
    assertThat(saida.contains(nomeMetodo3), equalTo(true));
  }

}