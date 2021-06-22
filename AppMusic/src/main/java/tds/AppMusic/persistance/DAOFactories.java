package tds.AppMusic.persistance;

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
