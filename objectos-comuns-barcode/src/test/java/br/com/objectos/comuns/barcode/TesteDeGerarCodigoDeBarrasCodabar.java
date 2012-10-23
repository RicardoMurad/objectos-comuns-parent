/*
 * TesteDeGerarCodigoDeBarrasCodabar.java criado em 23/10/2012
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
public class TesteDeGerarCodigoDeBarrasCodabar {

  public void gerar_codigo_modelo_codabar() throws BarcodeException, OutputException {
    String boletoFebraban = "23654781257936541257413968554478558652584155";
    String path = "./target/outputCodabar.png";
    File file = new File(path);

    Barcode codabar = BarcodeFactory.createCodabar(boletoFebraban);
    BarcodeImageHandler.saveJPEG(codabar, file);
  }

}