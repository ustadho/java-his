/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ustasoft.his.model.master;

import com.fasterxml.jackson.annotation.JacksonAnnotation;
import com.ustasoft.his.model.BaseEntity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author faheem
 */
@Entity
@Table(name = "m_item_master")
public class ItemMaster extends BaseEntity{
    private Boolean medical=Boolean.TRUE;
    
    @NotNull
    @NotEmpty
    @Column(unique = true, nullable = false)
    private String kode;
    
    @ManyToOne
    @JoinColumn(name = "id_brand")
    private ItemBrand brand;
    
    @NotEmpty
    @NotNull
    @Column(unique = true, nullable = false)
    private String nama;
    
    @ManyToOne
    @JoinColumn(name = "id_manufaktur")
    private ItemManufaktur manufaktur;
    
    @ManyToOne
    @JoinColumn(name = "id_satuan")
    private ItemSatuan satuan;
    
    
    @ManyToOne
    @JoinColumn(name = "id_kategori")
    private ItemKategori kategori;
    
    private Integer max;
    private Integer min;
    private Integer reorderQty;
    
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "item_type")
    //generik, non generik
    private ItemTipeProduk itemType;
    
    private String komposisi;
    private String indikasi;
    private String ketDosis;
    private String efekSamping;
    private String kontraIndikasi;
    private String keterangan;
    private Boolean generik=Boolean.FALSE;
    private Boolean aktif=Boolean.TRUE;
    
    public Boolean isMedical() {
        return medical;
    }

    public void setMedical(Boolean medical) {
        this.medical = medical;
    }

    public String getKode() {
        return kode;
    }

    public void setKode(String kode) {
        this.kode = kode;
    }

    public ItemBrand getBrand() {
        return brand;
    }

    public void setBrand(ItemBrand brand) {
        this.brand = brand;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public ItemManufaktur getManufaktur() {
        return manufaktur;
    }

    public void setManufaktur(ItemManufaktur manufaktur) {
        this.manufaktur = manufaktur;
    }

    public ItemSatuan getSatuan() {
        return satuan;
    }

    public void setSatuan(ItemSatuan satuan) {
        this.satuan = satuan;
    }

    public ItemKategori getKategori() {
        return kategori;
    }

    public void setKategori(ItemKategori kategori) {
        this.kategori = kategori;
    }

    public Integer getMax() {
        return max;
    }

    public void setMax(Integer max) {
        this.max = max;
    }

    public Integer getMin() {
        return min;
    }

    public void setMin(Integer min) {
        this.min = min;
    }

    public Integer getReorderQty() {
        return reorderQty;
    }

    public void setReorderQty(Integer reorderQty) {
        this.reorderQty = reorderQty;
    }

    public ItemTipeProduk getItemType() {
        return itemType;
    }

    public void setItemType(ItemTipeProduk itemType) {
        this.itemType = itemType;
    }

    public String getKomposisi() {
        return komposisi;
    }

    public void setKomposisi(String komposisi) {
        this.komposisi = komposisi;
    }

    public String getIndikasi() {
        return indikasi;
    }

    public void setIndikasi(String indikasi) {
        this.indikasi = indikasi;
    }

    public String getKetDosis() {
        return ketDosis;
    }

    public void setKetDosis(String ketDosis) {
        this.ketDosis = ketDosis;
    }

    public String getEfekSamping() {
        return efekSamping;
    }

    public void setEfekSamping(String efekSamping) {
        this.efekSamping = efekSamping;
    }

    public String getKontraIndikasi() {
        return kontraIndikasi;
    }

    public void setKontraIndikasi(String kontraIndikasi) {
        this.kontraIndikasi = kontraIndikasi;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public Boolean isAktif() {
        return aktif;
    }

    public void setAktif(Boolean aktif) {
        this.aktif = aktif;
    }

    public Boolean isGenerik() {
        return generik;
    }

    public void setGenerik(Boolean generik) {
        this.generik = generik;
    }
    
    
    
}
