package Modelo.Reporte;

public class ReporteTrabajador {

    private long idTrabajador;
    private String nombreTrabajador;
    private String apellidosTrabajador;
    private String usuarioTrabajador;
    private String correoTrabajador;
    private String nroDocumentoTrabajador;
    private String celularTrabajador;
    private String tipoRol;



    public ReporteTrabajador(long idTrabajador, String nombreTrabajador, String apellidosTrabajador, String usuarioTrabajador, String correoTrabajador, String nroDocumentoTrabajador, String celularTrabajador, String tipoRol) {
        this.idTrabajador = idTrabajador;
        this.nombreTrabajador = nombreTrabajador;
        this.apellidosTrabajador = apellidosTrabajador;
        this.usuarioTrabajador = usuarioTrabajador;
        this.correoTrabajador = correoTrabajador;
        this.nroDocumentoTrabajador = nroDocumentoTrabajador;
        this.celularTrabajador = celularTrabajador;
        this.tipoRol = tipoRol;
    }

    public ReporteTrabajador() {
    }

    public long getIdTrabajador() {
        return idTrabajador;
    }

    public void setIdTrabajador(long idTrabajador) {
        this.idTrabajador = idTrabajador;
    }

    public String getNombreTrabajador() {
        return nombreTrabajador;
    }

    public void setNombreTrabajador(String nombreTrabajador) {
        this.nombreTrabajador = nombreTrabajador;
    }

    public String getApellidosTrabajador() {
        return apellidosTrabajador;
    }

    public void setApellidosTrabajador(String apellidosTrabajador) {
        this.apellidosTrabajador = apellidosTrabajador;
    }

    public String getUsuarioTrabajador() {
        return usuarioTrabajador;
    }

    public void setUsuarioTrabajador(String usuarioTrabajador) {
        this.usuarioTrabajador = usuarioTrabajador;
    }

    public String getCorreoTrabajador() {
        return correoTrabajador;
    }

    public void setCorreoTrabajador(String correoTrabajador) {
        this.correoTrabajador = correoTrabajador;
    }

    public String getNroDocumentoTrabajador() {
        return nroDocumentoTrabajador;
    }

    public void setNroDocumentoTrabajador(String nroDocumentoTrabajador) {
        this.nroDocumentoTrabajador = nroDocumentoTrabajador;
    }

    public String getCelularTrabajador() {
        return celularTrabajador;
    }

    public void setCelularTrabajador(String celularTrabajador) {
        this.celularTrabajador = celularTrabajador;
    }

    public String getTipoRol() {
        return tipoRol;
    }

    public void setTipoRol(String tipoRol) {
        this.tipoRol = tipoRol;
    }
}
