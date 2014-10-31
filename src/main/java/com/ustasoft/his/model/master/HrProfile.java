/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ustasoft.his.model.master;

import com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type;
import com.ustasoft.his.model.BaseEntity;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 *
 * @author faheem
 */
@Entity
@Table(name = "hr_profile")
public class HrProfile extends BaseEntity{
    private String nama;
    private Boolean aktif=Boolean.TRUE;

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public Boolean isAktif() {
        return aktif;
    }

    public void setAktif(Boolean aktif) {
        this.aktif = aktif;
    }

    
    
}
