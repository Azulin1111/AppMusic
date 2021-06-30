/*
 * Proyecto AppMusic desarrollado para la asignatura de Tecnologías de Desarrollo de Software,
 * curso 2020-2021. Proyecto desarrollado por Ekam Puri Nieto y Sergio Requena Martínez.
 */

package tds.appMusic.model.pdfs;

/**
 * Colección de builders PDF disponibles en la aplicación.
 * @author Ekam Puri Nieto
 * @author Sergio Requena Martínez
 * @author ekam.purin@um.es
 * @author sergio.requenam@um.es
 */
public enum Builders {
    ITEXT;

    /**
     * Devuelve el builder asociado al tipo de builder seleccionado.
     * @return Una instancia de {@link BuilderPDFfromUser}.
     */
    BuilderPDFfromUser getBuilder() {
        switch (this) {
            case ITEXT:
                return new BuilderItext();
        }
        return null;
    }
}
