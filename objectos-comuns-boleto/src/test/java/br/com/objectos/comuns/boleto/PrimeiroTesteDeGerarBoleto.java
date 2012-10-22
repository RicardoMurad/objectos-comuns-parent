/*
 * TesteDeGerarBoleto.java criado em 22/10/2012
 * 
 * Propriedade de Objectos Fábrica de Software LTDA.
 * Reprodução parcial ou total proibida.
 */
package br.com.objectos.comuns.boleto;

import org.testng.annotations.Test;

import br.com.caelum.stella.boleto.Banco;
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
public class PrimeiroTesteDeGerarBoleto {

  public void teste_construcao_de_boleto() {
    Datas datas = Datas.newDatas()
        .withDocumento(1, 1, 2012)
        .withProcessamento(1, 11, 2012)
        .withVencimento(12, 12, 2012);

    Emissor emissor = Emissor.newEmissor()
        .withCedente("João da Silva")
        .withAgencia(1824)
        .withContaCorrente(76000)
        .withCarteira(18)
        .withNossoNumero(9000206);

    Sacado sacado = Sacado.newSacado()
        .withNome("Jorge da Silva")
        .withEndereco("Avenida paulista, 100")
        .withCpf("30567362850")
        .withBairro("Bela Vista")
        .withCep("01310100")
        .withCidade("São Paulo")
        .withUf("SP");

    Banco banco = new BancoDoBrasil();

    Boleto boleto = Boleto.newBoleto()
        .withBanco(banco)
        .withDatas(datas)
        .withDescricoes("Descrição")
        .withEmissor(emissor)
        .withSacado(sacado)
        .withValorBoleto("200.0")
        .withDescricoes(" Pagamento do produto 0001")
        .withAceite(false)
        .withInstrucoes("Não receber após a data de vencimento")
        .withLocaisDePagamento("Pagável em qualquer banco até a data de vencimento")
        .withNumeroDoDocumento("12345");

    BoletoGenerator generator = new BoletoGenerator(boleto);

    generator.toPNG("teste.png");
    generator.toPDF("Saida.pdf");

  }
}