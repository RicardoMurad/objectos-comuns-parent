package br.com.objectos.comuns.barcode;

import java.io.File;

import net.sourceforge.barbecue.Barcode;
import net.sourceforge.barbecue.BarcodeException;
import net.sourceforge.barbecue.BarcodeFactory;
import net.sourceforge.barbecue.BarcodeImageHandler;
import net.sourceforge.barbecue.output.OutputException;

import org.testng.annotations.Test;

/*
 * TesteDeGerarCodigoDeBarrasUpcEaN.java criado em 23/10/2012
 * 
 * Propriedade de Objectos Fábrica de Software LTDA.
 * Reprodução parcial ou total proibida.
 */

/**
 * @author ricardo.murad@objectos.com.br (Ricardo Murad)
 */
@Test
public class TesteDeGerarCodigoDeBarrasUpcEaN {

  public void teste_de_gerar_codigo_EaN128() throws OutputException, BarcodeException {
    String produto = "Objectos";
    String path = "./target/outputEAN128.png";
    File file = new File(path);

    Barcode barcode = BarcodeFactory.createEAN128(produto);
    BarcodeImageHandler.savePNG(barcode, file);
  }

}