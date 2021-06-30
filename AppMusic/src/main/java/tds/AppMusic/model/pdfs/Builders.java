/*
 * Proyecto AppMusic desarrollado para la asignatura de Tecnologías de Desarrollo de Software,
 * curso 2020-2021. Proyecto desarrollado por Ekam Puri Nieto y Sergio Requena Martínez.
 */

package tds.AppMusic.model.pdfs;

public enum Builders {
    ITEXT;

    public BuilderPDFfromUser getBuilder() {
        switch (this) {
            case ITEXT:
                return new BuilderItext();
        }
        return null;
    }
}
