/*
 * TesteDeGerarCodigoDeBarrasCodigo128.java criado em 23/10/2012
 * 
 * Propriedade de Objectos Fábrica de Software LTDA.
 * Reprodução parcial ou total proibida.
 */
package br.com.objectos.comuns.barcode;

import java.io.File;

import net.sourceforge.barbecue.Barcode;
import net.sourceforge.barbecue.BarcodeException;
import net.sourceforge.barbecue.BarcodeFactory;
import net.sourceforge.barbecue.BarcodeImageHandler;
import net.sourceforge.barbecue.output.OutputException;

import org.testng.annotations.Test;

/**
 * @author ricardo.murad@objectos.com.br (Ricardo Murad)
 */
@Test
public class TesteDeGerarCodigoDeBarrasCodigo128 {

  public void gerar_codigo_modelo_128() throws BarcodeException, OutputException {

    String valor = "Objectos";
    String path = "./target/output.png";
    File file = new File(path);

    Barcode barcode = BarcodeFactory.createCode128(valor);
    BarcodeImageHandler.saveGIF(barcode, file);

  }

}