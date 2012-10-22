/*
 * TesteDeGerarBoleto.java criado em 22/10/2012
 * 
 * Propriedade de Objectos Fábrica de Software LTDA.
 * Reprodução parcial ou total proibida.
 */
package br.com.objectos.comuns.boleto;

import java.math.BigDecimal;

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
public class TesteDeGerarBoleto {

  public void teste_construcao_de_boleto() {

    Datas datas = Datas.newDatas()
        .withDocumento(22, 10, 2012)
        .withProcessamento(22, 10, 2012)
        .withVencimento(25, 12, 2012);

    Emissor emissor = Emissor.newEmissor()
        .withCedente("João da silva")
        .withAgencia(238)
        .withContaCorrente(10270762)
        .withCarteira(10)
        .withNossoNumero(1)
        .withNossoNumero(9000206);

    Sacado sacado = Sacado.newSacado()
        .withNome("Jorge da Silva")
        .withCpf("30567362850")
        .withBairro("Bela Vista")
        .withCep("01310100")
        .withCidade("São Paulo")
        .withUf("SP");

    Banco banco = new BancoDoBrasil();

    Boleto boleto = Boleto.newBoleto()
        .withBanco(banco)
        .withDatas(datas)
        .withEmissor(emissor)
        .withSacado(sacado)
        .withValorBoleto(BigDecimal.valueOf(100.0))
        .withDescricoes(" Pagamento do produto 0001")
        .withAceite(false)
        .withInstrucoes("Não receber após a data de vencimento")
        .withLocaisDePagamento("Pagável em qualquer banco até a data de vencimento")
        .withNumeroDoDocumento("12345");

    BoletoGenerator generator = new BoletoGenerator(boleto);

    generator.toPDF("saida.pdf");

  }
}
