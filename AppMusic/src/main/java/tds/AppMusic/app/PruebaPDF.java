package tds.AppMusic.app;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

public class PruebaPDF {
    public static void main(String[] args) throws IOException, DocumentException {

        String ruta = "c:/Users/Usuario/Desktop/pruebaPDF.pdf";
        File file = new File(ruta);
        file.createNewFile();
        System.out.println(file.getPath());

        FileOutputStream archivo = new FileOutputStream(ruta);
        Document documento = new Document();
        PdfWriter.getInstance(documento, archivo);
        documento.open();
        documento.add(new Paragraph("Hola Mundo!"));
        documento.add(new Paragraph("SoloInformaticaYAlgoMas.blogspot.com"));
        documento.close();
        archivo.close();


    }
}
