package Model;

import java.util.Date;

public abstract class Personaje {
    private String nombre;
    private String apodo;
    private Date fechaNacimiento;
    private Integer edad;
    private Integer salud;
    private String imagen;

    private Integer velocidad;
    private Integer destreza;
    private Integer fuerza;
    private Integer nivel;
    private Integer armadura;

    @Override
    public String toString() {
        return "Personaje{" +
                "nombre='" + nombre + '\'' +
                ", apodo='" + apodo + '\'' +
                ", fechaNacimiento=" + fechaNacimiento +
                ", edad=" + edad +
                ", salud=" + salud +
                ", imagen='" + imagen + '\'' +
                ", velocidad=" + velocidad +
                ", destreza=" + destreza +
                ", fuerza=" + fuerza +
                ", nivel=" + nivel +
                ", armadura=" + armadura +
                '}';
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    public abstract Integer atacar(Integer poderDefensaContrincante);

    public Integer poderDefensa() {
        return (this.armadura * this.velocidad);
    }

    public Integer poderDisparo() {
        return (this.destreza * this.fuerza * this.nivel);
    }

    public Boolean hasNullAttribute() {
        return (
                this.nombre == null ||
                        this.apodo == null ||
                        this.edad == null ||
                        this.salud == null ||
                        this.velocidad == null ||
                        this.destreza == null ||
                        this.fuerza == null ||
                        this.nivel == null ||
                        this.armadura== null
                );
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApodo() {
        return apodo;
    }

    public void setApodo(String apodo) {
        this.apodo = apodo;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public Integer getEdad() {
        return edad;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }

    public Integer getSalud() {
        return salud;
    }

    public void setSalud(Integer salud) {
        this.salud = salud;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public Integer getVelocidad() {
        return velocidad;
    }

    public void setVelocidad(Integer velocidad) {
        this.velocidad = velocidad;
    }

    public Integer getDestreza() {
        return destreza;
    }

    public void setDestreza(Integer destreza) {
        this.destreza = destreza;
    }

    public Integer getFuerza() {
        return fuerza;
    }

    public void setFuerza(Integer fuerza) {
        this.fuerza = fuerza;
    }

    public Integer getNivel() {
        return nivel;
    }

    public void setNivel(Integer nivel) {
        this.nivel = nivel;
    }

    public Integer getArmadura() {
        return armadura;
    }

    public void setArmadura(Integer armadura) {
        this.armadura = armadura;
    }
}
