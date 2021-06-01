package dao;

import dto.Contacto;
import dto.Usuario;
import java.util.ArrayList;

/**
 *
 * @author Jose Carlos Gutierrez
 */
public abstract class ContactoDao {

    public abstract int insert(Contacto obj) throws Exception;

    public abstract void update(Contacto obj) throws Exception;
	
    public abstract ArrayList<Contacto> getContactosByUsuarioId(int usuarioId);

    public abstract void delete(int id);

    public abstract ArrayList<Contacto> getList();

    public abstract Contacto get(int id);

}
