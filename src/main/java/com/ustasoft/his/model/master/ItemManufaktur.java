/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ustasoft.his.model.master;

import com.ustasoft.his.model.BaseEntity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 *
 * @author faheem
 */
@Entity
@Table(name = "m_item_manufaktur")
public class ItemManufaktur extends BaseEntity{
    
    @Column(length = 100, nullable = false)
    private String nama;
    private String keterangan;
    
    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }
    
    
}
