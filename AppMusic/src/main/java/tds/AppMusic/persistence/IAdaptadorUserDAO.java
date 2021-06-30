/*
 * Proyecto AppMusic desarrollado para la asignatura de Tecnologías de Desarrollo de Software,
 * curso 2020-2021. Proyecto desarrollado por Ekam Puri Nieto y Sergio Requena Martínez.
 */

package tds.AppMusic.persistence;

import tds.AppMusic.model.users.User;

import java.util.*;

/**
 * Adaptador de almacenamiento de usuarios en memoria.
 */
public interface IAdaptadorUserDAO {

    /**
     * Almacena un usuario en persistencia. En caso de existir ya en persistencia, el método no modifica nada.
     * @param user El usuario a almacenar.
     */
    void storeUser(User user);

    /**
     * Elimina un usuario de persistencia. En caso de no existir, el método no modifica nada.
     * @param user El usuario a eliminar.
     */
    void deleteUser(User user);

    /**
     * Modifica un usuario existente en persistencia. En caso de no existir, el método no modifica nada.
     * @param user El usuario a modificar, con los cambios ya realizados.
     */
    void setUser(User user);

    /**
     * Obtiene un usuario a partir de su código.
     * @param code El código del usuario.
     * @return El usuario de persistencia, o {@code null} si no existe el usuario.
     */
    User getUser(int code);

    /**
     * Devuelve todos los usuarios existentes en persistencia.
     * @return Una lista con todos los usuarios.
     */
    List<User> getAllUsers();


}
