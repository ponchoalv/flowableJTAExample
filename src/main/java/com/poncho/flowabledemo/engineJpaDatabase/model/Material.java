package com.poncho.flowabledemo.engineJpaDatabase.model;



import javax.persistence.*;

@Entity
public class Material {

    public Material(String nombre){
        this.nombre = nombre;
    }

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name="access_control_id", nullable=false)
    private AccessControl accessControl;

    private String nombre;

    public Material() {
    }

    public Long getId() {
        return this.id;
    }

    public AccessControl getAccessControl() {
        return this.accessControl;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setAccessControl(AccessControl accessControl) {
        this.accessControl = accessControl;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof Material)) return false;
        final Material other = (Material) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$id = this.getId();
        final Object other$id = other.getId();
        if (this$id == null ? other$id != null : !this$id.equals(other$id)) return false;
        final Object this$accessControl = this.getAccessControl();
        final Object other$accessControl = other.getAccessControl();
        if (this$accessControl == null ? other$accessControl != null : !this$accessControl.equals(other$accessControl))
            return false;
        final Object this$nombre = this.getNombre();
        final Object other$nombre = other.getNombre();
        if (this$nombre == null ? other$nombre != null : !this$nombre.equals(other$nombre)) return false;
        return true;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $id = this.getId();
        result = result * PRIME + ($id == null ? 43 : $id.hashCode());
        final Object $nombre = this.getNombre();
        result = result * PRIME + ($nombre == null ? 43 : $nombre.hashCode());
        return result;
    }

    protected boolean canEqual(Object other) {
        return other instanceof Material;
    }

    public String toString() {
        return this.getNombre();
    }
}
