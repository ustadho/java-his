/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ustasoft.his.model.master;

import com.ustasoft.his.model.BaseEntity;
import com.ustasoft.his.model.config.Permission;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author faheem
 */
@Entity
@Table(name = "hr_pegawai")
public class Pegawai extends BaseEntity{
    @NotNull
    @NotEmpty
    @Column(unique = true, nullable = false, length = 15)
    private String nip;
    
    @NotNull
    @NotEmpty
    @Column(nullable = false, length = 100)
    private String nama;
    
    //L, P
    @Enumerated(EnumType.STRING)
    @Column(name = "sex")
    private JenisKelamin sex;
    
    private String gelar;
    
    @ManyToOne
    @JoinColumn(name = "id_kota_lahir")
    private Kota kotaLahir;
    
    @Temporal(TemporalType.DATE)
    private Date tglLahir;
    
    @Temporal(TemporalType.DATE)
    private Date tglMasuk;
    
    @ManyToOne
    @JoinColumn(name = "id_hr_profile")
    private HrProfile hrProfile;
    
    private Boolean aktif;
    
    @ManyToMany
    @JoinTable(
        name="hr_pegawai_spesialisasi", 
        joinColumns=@JoinColumn(name="id_pegawai", nullable=false),
        inverseJoinColumns=@JoinColumn(name="id_spesialisasi", nullable=false)
    )
    private Set<Spesialisasi> spesialisasiSet = new HashSet<Spesialisasi>();

    public String getNip() {
        return nip;
    }

    public void setNip(String nip) {
        this.nip = nip;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getGelar() {
        return gelar;
    }

    public void setGelar(String gelar) {
        this.gelar = gelar;
    }

    public Kota getKotaLahir() {
        return kotaLahir;
    }

    public void setKotaLahir(Kota kotaLahir) {
        this.kotaLahir = kotaLahir;
    }

    public Date getTglLahir() {
        return tglLahir;
    }

    public void setTglLahir(Date tglLahir) {
        this.tglLahir = tglLahir;
    }

    public Date getTglMasuk() {
        return tglMasuk;
    }

    public void setTglMasuk(Date tglMasuk) {
        this.tglMasuk = tglMasuk;
    }

    public HrProfile getHrProfile() {
        return hrProfile;
    }

    public void setHrProfile(HrProfile hrProfile) {
        this.hrProfile = hrProfile;
    }

    public Boolean isAktif() {
        return aktif;
    }

    public void setAktif(Boolean aktif) {
        this.aktif = aktif;
    }

    public Set<Spesialisasi> getSpesialisasiSet() {
        return spesialisasiSet;
    }

    public void setSpesialisasiSet(Set<Spesialisasi> spesialisasiSet) {
        this.spesialisasiSet = spesialisasiSet;
    }

    public JenisKelamin getSex() {
        return sex;
    }

    public void setSex(JenisKelamin sex) {
        this.sex = sex;
    }

    
}
