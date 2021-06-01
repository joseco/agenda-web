/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package services;

import dao.ContactoDao;
import dao.UsuarioDao;
import dto.Contacto;
import dto.Usuario;
import factory.FactoryDao;
import java.util.ArrayList;
import java.util.List;
import javax.print.attribute.standard.MediaTray;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author josec
 */
@Path("/contacto")
public class ContactoService {
    
    // api/contacto/usuario/{usuarioId}
    @GET
    @Path("/usuario/{usuarioId}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Contacto> getContactos(@PathParam("usuarioId") int usuarioId){
        if(usuarioId <= 0)
            return null;
        
        UsuarioDao daoUsuario = FactoryDao.getFactoryInstance().getNewUsuarioDao();
        Usuario obj = daoUsuario.get(usuarioId);
        
        if(obj == null)
            return null;
        
        ContactoDao dao = FactoryDao.getFactoryInstance().getNewContactoDao();
        List<Contacto> list= null;
        
        try {
            list = dao.getContactosByUsuarioId(usuarioId);
        } catch (Exception e) {
            list = new ArrayList<>();
            e.printStackTrace();
        }
        
        return list;
    }
    
    @GET
    @Path("/{contactoId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Contacto getContacto(@PathParam("contactoId") int contactoId ){
        ContactoDao dao = FactoryDao.getFactoryInstance().getNewContactoDao();
        Contacto obj= null;
        
        try {
            obj = dao.get(contactoId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return obj;
    }
    
    @POST
    @Path("/")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Respuesta insertContacto(Contacto obj){
        ContactoDao dao = FactoryDao.getFactoryInstance().getNewContactoDao();
        
        try {
            int id = dao.insert(obj);
            
            return new Respuesta(true, Integer.toString(id));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Respuesta(false, "Ocurrió un error al guardar el contacto");
    
    }
        
    
    @PUT
    @Path("/")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Respuesta updateContacto(Contacto obj){
        ContactoDao dao = FactoryDao.getFactoryInstance().getNewContactoDao();
        
        try {
            dao.update(obj);
            
            return new Respuesta(true, "Contacto actualizado");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Respuesta(false, "Ocurrió un error al guardar el contacto");
    }
    
    @DELETE
    @Path("/{contactoId}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Respuesta eliminarContacto(@PathParam("contactoId") int contactoId){
        ContactoDao dao = FactoryDao.getFactoryInstance().getNewContactoDao();
        
        try {
            dao.delete(contactoId);
            return new Respuesta(true, "El contacto fue eliminado correctamente");
        } catch (Exception e) {
            e.printStackTrace();
            
        }
        
        return new Respuesta(false, "No se pudo eliminar el contacto");
    }
    
}
