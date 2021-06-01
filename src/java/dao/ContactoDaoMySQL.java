package dao;

import dal.Conexion;
import dto.Contacto;
import dto.Usuario;
import java.util.ArrayList;
import java.sql.ResultSet;

/**
 *
 * @author Jose Carlos Gutierrez
 */
public class ContactoDaoMySQL extends ContactoDao {

    @Override
    public int insert(Contacto obj) throws Exception {
        Conexion objConexion = Conexion.getOrCreate();

        int id = 0;
        StringBuilder query = new StringBuilder("INSERT INTO contacto  ( usuarioId, nombreContacto, telefono, email, imagenFileId) VALUES (");
        
        String strFile = obj.getImagenFileId() == 0 ? "null" : Integer.toString(obj.getImagenFileId());
        
        query.append(obj.getUsuarioId() + ",");
        query.append("'" + obj.getNombreContacto() + "',");
        query.append("'" + obj.getTelefono() + "',");
        query.append("'" + obj.getEmail() + "',");
        query.append(strFile);
        query.append(")");
        id = objConexion.ejecutarInsert(query.toString());
        if (id == 0) {
            throw new Exception("El registro no pudo ser insertado");
        }
        objConexion.desconectar();
        return id;
    }

    @Override
    public void update(Contacto obj) throws Exception {
        Conexion objConexion = Conexion.getOrCreate();
        
        String strFile = obj.getImagenFileId() == 0 ? "null" : Integer.toString(obj.getImagenFileId());
                
        StringBuilder query = new StringBuilder("UPDATE contacto SET ");
        query.append("usuarioId = " + obj.getUsuarioId() + ",");
        query.append("nombreContacto = '" + obj.getNombreContacto() + "',");
        query.append("telefono = '" + obj.getTelefono() + "',");
        query.append("email = '" + obj.getEmail() + "',");
        query.append("imagenFileId = " + strFile);
        query.append(" WHERE contactoId = " + obj.getContactoId());
        
        System.out.println(query.toString());
        int upd = objConexion.ejecutarSimple(query.toString());
        if (upd == 0) {
            throw new Exception("El registro no pudo ser actualizado");
        }

        objConexion.desconectar();
    }

    @Override
    public void delete(int id) {
        Conexion objConexion = Conexion.getOrCreate();
        StringBuffer query = new StringBuffer("DELETE FROM contacto ");
        query.append("WHERE contactoId = " + id);
        objConexion.ejecutarSimple(query.toString());
        objConexion.desconectar();
    }

    @Override
    public Contacto get(int id) {
        try {
            Conexion objConexion = Conexion.getOrCreate();
            String query = "SELECT * FROM contacto WHERE contactoId = " + id;
            ResultSet objResultSet = objConexion.ejecutar(query);
            if (objResultSet.next()) {
                Contacto obj = new Contacto();
                int _contactoId = objResultSet.getInt("contactoId");
                obj.setContactoId(_contactoId);

                int _usuarioId = objResultSet.getInt("usuarioId");
                obj.setUsuarioId(_usuarioId);

                String _nombreContacto = objResultSet.getString("nombreContacto");
                obj.setNombreContacto(_nombreContacto);

                String _telefono = objResultSet.getString("telefono");
                obj.setTelefono(_telefono);

                String _email = objResultSet.getString("email");
                obj.setEmail(_email);

                int _imagenFileId = objResultSet.getInt("imagenFileId");
                obj.setImagenFileId(_imagenFileId);

                return obj;
            }
        } catch (Exception ex) {
            ;
        }
        return null;
    }

    @Override
    public ArrayList<Contacto> getList() {
        ArrayList<Contacto> registros = new ArrayList<Contacto>();
        try {
            Conexion objConexion = Conexion.getOrCreate();
            String query = "SELECT * FROM contacto";
            ResultSet objResultSet = objConexion.ejecutar(query);
            while (objResultSet.next()) {
                Contacto obj = new Contacto();
                int _contactoId = objResultSet.getInt("contactoId");
                obj.setContactoId(_contactoId);

                int _usuarioId = objResultSet.getInt("usuarioId");
                obj.setUsuarioId(_usuarioId);

                String _nombreContacto = objResultSet.getString("nombreContacto");
                obj.setNombreContacto(_nombreContacto);

                String _telefono = objResultSet.getString("telefono");
                obj.setTelefono(_telefono);

                String _email = objResultSet.getString("email");
                obj.setEmail(_email);

                int _imagenFileId = objResultSet.getInt("imagenFileId");
                obj.setImagenFileId(_imagenFileId);

                registros.add(obj);
            }
        } catch (Exception ex) {
            ;
        }
        return registros;
    }

    @Override
    public ArrayList<Contacto> getContactosByUsuarioId(int usuarioId) {
        ArrayList<Contacto> registros = new ArrayList<Contacto>();
        try {
            Conexion objConexion = Conexion.getOrCreate();
            String query = "SELECT * FROM contacto WHERE usuarioId = " + usuarioId ;
            ResultSet objResultSet = objConexion.ejecutar(query);
            while (objResultSet.next()) {
                Contacto obj = new Contacto();
                int _contactoId = objResultSet.getInt("contactoId");
                obj.setContactoId(_contactoId);

                int _usuarioId = objResultSet.getInt("usuarioId");
                obj.setUsuarioId(_usuarioId);

                String _nombreContacto = objResultSet.getString("nombreContacto");
                obj.setNombreContacto(_nombreContacto);

                String _telefono = objResultSet.getString("telefono");
                obj.setTelefono(_telefono);

                String _email = objResultSet.getString("email");
                obj.setEmail(_email);

                int _imagenFileId = objResultSet.getInt("imagenFileId");
                obj.setImagenFileId(_imagenFileId);

                registros.add(obj);
            }
        } catch (Exception ex) {
            ;
        }
        return registros;
    }

}
