package tds.AppMusic;

import beans.Entidad;
import tds.AppMusic.GUI.TestForm;
import tds.driver.FactoriaServicioPersistencia;
import tds.driver.ServicioPersistencia;

import java.util.List;

public class AppMusic {
    public static void main(String[] args) {
        System.out.println("No functionality yet!");
        // TODO app launch, GUI launch, etc.

        // TESTING

        TestForm tf = new TestForm();
        tf.setVisible(true);

        ServicioPersistencia sp = FactoriaServicioPersistencia.getInstance().getServicioPersistencia();
        Entidad e = new Entidad();
        e.setNombre("test");
        sp.registrarEntidad(e);

        System.out.println("Cosa insertada. Recuperandola...");

        List<Entidad> l = sp.recuperarEntidades();
        l.forEach(en -> System.out.println(en.getNombre()));
        
        System.out.println("EXITO");
    }
}
