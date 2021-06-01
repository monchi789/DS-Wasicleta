package Modelo.Reporte;

public class ReporteAlquiler {

    private long idAlquiler;
    private String nombreAlquiler;
    private String apellidoAlquiler;
    private String celularAlquiler;
    private String correoAlquiler;
    private String numeroDocumentoAlquiler;
    private String codigoBicicleta;
    private String codigo;
    private String fechaAlquiler;
    private String montoAlquiler;
    private String nombreCliente;
    private String nombreTrabajador;

    public ReporteAlquiler(long idAlquiler, String nombreAlquiler, String apellidoAlquiler, String celularAlquiler, String correoAlquiler, String numeroDocumentoAlquiler, String codigoBicicleta, String codigo, String fechaAlquiler, String montoAlquiler, String nombreCliente, String nombreTrabajador) {
        this.idAlquiler = idAlquiler;
        this.nombreAlquiler = nombreAlquiler;
        this.apellidoAlquiler = apellidoAlquiler;
        this.celularAlquiler = celularAlquiler;
        this.correoAlquiler = correoAlquiler;
        this.numeroDocumentoAlquiler = numeroDocumentoAlquiler;
        this.codigoBicicleta = codigoBicicleta;
        this.codigo = codigo;
        this.fechaAlquiler = fechaAlquiler;
        this.montoAlquiler = montoAlquiler;
        this.nombreCliente = nombreCliente;
        this.nombreTrabajador = nombreTrabajador;
    }

    public ReporteAlquiler() {
    }

    public long getIdAlquiler() {
        return idAlquiler;
    }

    public void setIdAlquiler(long idAlquiler) {
        this.idAlquiler = idAlquiler;
    }

    public String getNombreAlquiler() {
        return nombreAlquiler;
    }

    public void setNombreAlquiler(String nombreAlquiler) {
        this.nombreAlquiler = nombreAlquiler;
    }

    public String getApellidoAlquiler() {
        return apellidoAlquiler;
    }

    public void setApellidoAlquiler(String apellidoAlquiler) {
        this.apellidoAlquiler = apellidoAlquiler;
    }

    public String getCelularAlquiler() {
        return celularAlquiler;
    }

    public void setCelularAlquiler(String celularAlquiler) {
        this.celularAlquiler = celularAlquiler;
    }

    public String getCorreoAlquiler() {
        return correoAlquiler;
    }

    public void setCorreoAlquiler(String correoAlquiler) {
        this.correoAlquiler = correoAlquiler;
    }

    public String getNumeroDocumentoAlquiler() {
        return numeroDocumentoAlquiler;
    }

    public void setNumeroDocumentoAlquiler(String numeroDocumentoAlquiler) {
        this.numeroDocumentoAlquiler = numeroDocumentoAlquiler;
    }

    public String getCodigoBicicleta() {
        return codigoBicicleta;
    }

    public void setCodigoBicicleta(String codigoBicicleta) {
        this.codigoBicicleta = codigoBicicleta;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getFechaAlquiler() {
        return fechaAlquiler;
    }

    public void setFechaAlquiler(String fechaAlquiler) {
        this.fechaAlquiler = fechaAlquiler;
    }

    public String getMontoAlquiler() {
        return montoAlquiler;
    }

    public void setMontoAlquiler(String montoAlquiler) {
        this.montoAlquiler = montoAlquiler;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public String getNombreTrabajador() {
        return nombreTrabajador;
    }

    public void setNombreTrabajador(String nombreTrabajador) {
        this.nombreTrabajador = nombreTrabajador;
    }
}
