package tds.AppMusic.persistence;

public enum DAOFactories {
    TDS;

    FactoryDAO getInstance() {
        switch (this) {
            case TDS:
                return new TDSFactoryDAO();
        }
        return null;
    }
}
