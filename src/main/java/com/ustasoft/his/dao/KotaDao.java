/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ustasoft.his.dao;

import com.ustasoft.his.model.master.Kota;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author faheem
 */

public interface KotaDao extends PagingAndSortingRepository<Kota, String>{
    @Query("from Kota k where upper(k.nama) like upper(:search) ")
    public Page<Kota> filter(@Param("search") String search, Pageable pageable);
    
    public Kota findByNama(String nama);
}
