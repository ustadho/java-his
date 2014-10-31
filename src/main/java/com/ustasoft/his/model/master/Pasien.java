/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ustasoft.his.model.master;

import com.ustasoft.his.model.BaseEntity;
import javax.persistence.Column;

/**
 *
 * @author faheem
 */

public class Pasien extends BaseEntity{
    @Column(length = 12)
    private String norm;
    
    private String nama;
    
    
}
