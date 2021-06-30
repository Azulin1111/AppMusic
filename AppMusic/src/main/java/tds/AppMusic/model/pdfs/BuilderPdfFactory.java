/*
 * Proyecto AppMusic desarrollado para la asignatura de Tecnologías de Desarrollo de Software,
 * curso 2020-2021. Proyecto desarrollado por Ekam Puri Nieto y Sergio Requena Martínez.
 */

package tds.AppMusic.model.pdfs;

/**
 * Factoría de builders PDF.
 * @author Ekam Puri Nieto
 * @author Sergio Requena Martínez
 * @author ekam.purin@um.es
 * @author sergio.requenam@um.es
 */
public enum BuilderPdfFactory {
    INSTANCE;

    /**
     * Devuelve un builder PDF en función del tipo solicitado.
     * @param tipo El tipo de builder.
     * @return Una instancia de {@link BuilderPDFfromUser}.
     */
    public BuilderPDFfromUser getBuilderPdf(Builders tipo){
        return tipo.getBuilder();
    }
}
