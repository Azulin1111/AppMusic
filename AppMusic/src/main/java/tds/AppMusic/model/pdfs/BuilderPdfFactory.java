package tds.AppMusic.model.pdfs;

public enum BuilderPdfFactory {
    INSTANCE;

    public BuilderPDFfromUser getBuilderPdf(Builders tipo){
        return tipo.getBuilder();
    }
}
