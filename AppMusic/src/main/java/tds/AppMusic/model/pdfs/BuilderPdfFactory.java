/*
 * Proyecto AppMusic desarrollado para la asignatura de Tecnologías de Desarrollo de Software,
 * curso 2020-2021. Proyecto desarrollado por Ekam Puri Nieto y Sergio Requena Martínez.
 */

package tds.AppMusic.model.pdfs;

public enum BuilderPdfFactory {
    INSTANCE;

    public BuilderPDFfromUser getBuilderPdf(Builders tipo){
        return tipo.getBuilder();
    }
}
