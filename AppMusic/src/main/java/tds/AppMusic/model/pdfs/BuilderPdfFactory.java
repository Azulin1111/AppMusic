package tds.AppMusic.model.pdfs;

public enum BuilderPdfFactory {
    INSTANCE;
    public BuilderPDFfromUser getBuilderPdf(String tipo){
        if (tipo == "iText")
            return new BuilderItext();
        else
            return null;
    }
}
