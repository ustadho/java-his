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
@Table(name = "m_kota")
public class Kota extends BaseEntity{
    @Column(length = 50, nullable = false)
    private String nama;
    private String propinsi;

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getPropinsi() {
        return propinsi;
    }

    public void setPropinsi(String propinsi) {
        this.propinsi = propinsi;
    }
    
    
}
