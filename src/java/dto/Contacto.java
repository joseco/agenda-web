package dto;

/**
 *
 * @author Jose Carlos Gutierrez
 */
public class Contacto {

    private int contactoId;
    private int usuarioId;
    private String nombreContacto;
    private String telefono;
    private String email;
    private int imagenFileId;

    public Contacto() {
        ;
    }

    public void setContactoId(int contactoId) {
        this.contactoId = contactoId;
    }

    public int getContactoId() {
        return this.contactoId;
    }

    public void setUsuarioId(int usuarioId) {
        this.usuarioId = usuarioId;
    }

    public int getUsuarioId() {
        return this.usuarioId;
    }

    public void setNombreContacto(String nombreContacto) {
        this.nombreContacto = nombreContacto;
    }

    public String getNombreContacto() {
        return this.nombreContacto;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getTelefono() {
        return this.telefono;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return this.email;
    }

    public void setImagenFileId(int imagenFileId) {
        this.imagenFileId = imagenFileId;
    }

    public int getImagenFileId() {
        return this.imagenFileId;
    }

}
