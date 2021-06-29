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
