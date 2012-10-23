/*
s * TesteDeGerarUmaClasse.java criado em 22/10/2012
 * 
 * Propriedade de Objectos Fábrica de Software LTDA.
 * Reprodução parcial ou total proibida.
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

import com.sun.codemodel.internal.JClassAlreadyExistsException;
import com.sun.codemodel.internal.JCodeModel;
import com.sun.codemodel.internal.JDefinedClass;
import com.sun.codemodel.internal.JPackage;

/**
 * @author ricardo.murad@objectos.com.br (Ricardo Murad)
 */
@Test
public class TesteDeGerarUmaInterface {

  private BufferedReader res;

  public void deve_gerar_uma_arquivo_enum() throws JClassAlreadyExistsException, IOException {

    JCodeModel codeModel = new JCodeModel();

    String pacote = "br.teste";
    String nomeInterface = "EntidadeTeste";
    String nomeMetodo1 = "getNome";
    String nomeMetodo2 = "getData";
    String nomeMetodo3 = "getIdade";

    String destino = "./target/classes";

    JPackage _package = codeModel._package(pacote);

    JDefinedClass _interface = _package._interface(1, nomeInterface);

    _interface.method(0, String.class, nomeMetodo1);
    _interface.method(0, Calendar.class, nomeMetodo2);
    _interface.method(0, String.class, nomeMetodo3);

    File file = new File(destino);
    codeModel.build(file);

    File lerDoDisco = new File(destino + "/" + nomeInterface + ".java");
    FileReader arquivo = new FileReader(lerDoDisco);
    res = new BufferedReader(arquivo);
    String saida = new String();
    while (res.ready()) {
      saida += res.readLine();
    }

    assertThat(saida.isEmpty(), equalTo(false));
    assertThat(saida.contains(nomeInterface), equalTo(true));
    assertThat(saida.contains(nomeMetodo1), equalTo(true));
    assertThat(saida.contains(nomeMetodo2), equalTo(true));
    assertThat(saida.contains(nomeMetodo3), equalTo(true));

  }
}
