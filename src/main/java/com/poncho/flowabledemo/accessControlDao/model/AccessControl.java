package com.poncho.flowabledemo.accessControlDao.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class AccessControl {

    @Id
    @GeneratedValue
    private Long id;

    private String nombre;

    private String apellido;

    private String dni;

    private String empresa;

    @OneToMany(mappedBy="accessControl")
    private Set<Material> materialesIngresa = new HashSet<>();

    private String notebook;

    private String autoriza;

    private String racks;

    private boolean terminos;

    public AccessControl() {
    }


    protected boolean canEqual(Object other) {
        return other instanceof AccessControl;
    }

    public Long getId() {
        return this.id;
    }

    public String getNombre() {
        return this.nombre;
    }

    public String getApellido() {
        return this.apellido;
    }

    public String getDni() {
        return this.dni;
    }

    public String getEmpresa() {
        return this.empresa;
    }

    public Set<Material> getMaterialesIngresa() {
        return this.materialesIngresa;
    }

    public String getNotebook() {
        return this.notebook;
    }

    public String getAutoriza() {
        return this.autoriza;
    }

    public String getRacks() {
        return this.racks;
    }

    public boolean isTerminos() {
        return this.terminos;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    public void setMaterialesIngresa(Set<Material> materialesIngresa) {
        this.materialesIngresa = materialesIngresa;
    }

    public void setNotebook(String notebook) {
        this.notebook = notebook;
    }

    public void setAutoriza(String autoriza) {
        this.autoriza = autoriza;
    }

    public void setRacks(String racks) {
        this.racks = racks;
    }

    public void setTerminos(boolean terminos) {
        this.terminos = terminos;
    }

    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof AccessControl)) return false;
        final AccessControl other = (AccessControl) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$id = this.getId();
        final Object other$id = other.getId();
        if (this$id == null ? other$id != null : !this$id.equals(other$id)) return false;
        final Object this$nombre = this.getNombre();
        final Object other$nombre = other.getNombre();
        if (this$nombre == null ? other$nombre != null : !this$nombre.equals(other$nombre)) return false;
        final Object this$apellido = this.getApellido();
        final Object other$apellido = other.getApellido();
        if (this$apellido == null ? other$apellido != null : !this$apellido.equals(other$apellido)) return false;
        final Object this$dni = this.getDni();
        final Object other$dni = other.getDni();
        if (this$dni == null ? other$dni != null : !this$dni.equals(other$dni)) return false;
        final Object this$empresa = this.getEmpresa();
        final Object other$empresa = other.getEmpresa();
        if (this$empresa == null ? other$empresa != null : !this$empresa.equals(other$empresa)) return false;
        final Object this$materialesIngresa = this.getMaterialesIngresa();
        final Object other$materialesIngresa = other.getMaterialesIngresa();
        if (this$materialesIngresa == null ? other$materialesIngresa != null : !this$materialesIngresa.equals(other$materialesIngresa))
            return false;
        final Object this$notebook = this.getNotebook();
        final Object other$notebook = other.getNotebook();
        if (this$notebook == null ? other$notebook != null : !this$notebook.equals(other$notebook)) return false;
        final Object this$autoriza = this.getAutoriza();
        final Object other$autoriza = other.getAutoriza();
        if (this$autoriza == null ? other$autoriza != null : !this$autoriza.equals(other$autoriza)) return false;
        final Object this$racks = this.getRacks();
        final Object other$racks = other.getRacks();
        if (this$racks == null ? other$racks != null : !this$racks.equals(other$racks)) return false;
        if (this.isTerminos() != other.isTerminos()) return false;
        return true;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $id = this.getId();
        result = result * PRIME + ($id == null ? 43 : $id.hashCode());
        final Object $nombre = this.getNombre();
        result = result * PRIME + ($nombre == null ? 43 : $nombre.hashCode());
        final Object $apellido = this.getApellido();
        result = result * PRIME + ($apellido == null ? 43 : $apellido.hashCode());
        final Object $dni = this.getDni();
        result = result * PRIME + ($dni == null ? 43 : $dni.hashCode());
        final Object $empresa = this.getEmpresa();
        result = result * PRIME + ($empresa == null ? 43 : $empresa.hashCode());
        final Object $materialesIngresa = this.getMaterialesIngresa();
        result = result * PRIME + ($materialesIngresa == null ? 43 : $materialesIngresa.hashCode());
        final Object $notebook = this.getNotebook();
        result = result * PRIME + ($notebook == null ? 43 : $notebook.hashCode());
        final Object $autoriza = this.getAutoriza();
        result = result * PRIME + ($autoriza == null ? 43 : $autoriza.hashCode());
        final Object $racks = this.getRacks();
        result = result * PRIME + ($racks == null ? 43 : $racks.hashCode());
        result = result * PRIME + (this.isTerminos() ? 79 : 97);
        return result;
    }

    public String toString() {
        return "AccessControl(id=" + this.getId() + ", nombre=" + this.getNombre() + ", apellido=" + this.getApellido() + ", dni=" + this.getDni() + ", empresa=" + this.getEmpresa() + ", materialesIngresa=" + this.getMaterialesIngresa() + ", notebook=" + this.getNotebook() + ", autoriza=" + this.getAutoriza() + ", racks=" + this.getRacks() + ", terminos=" + this.isTerminos() + ")";
    }
}

