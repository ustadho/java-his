/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ustasoft.his.dao;

import com.ustasoft.his.model.master.Spesialisasi;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author faheem
 */
public interface SpesialisasiDao extends PagingAndSortingRepository<Spesialisasi, String>{
    @Query("from Spesialisasi s where upper(s.nama) like upper(:search) ")
    public Page<Spesialisasi> filter(@Param("search") String search, Pageable pageable);
    
    public Spesialisasi findByNama(String nama);
    
    @Query("from Spesialisasi h where aktif=:aktif and upper(h.nama) like upper(:search) order by h.nama")
    public Iterable<Spesialisasi> filterByActive(@Param("aktif") Boolean aktif, @Param("search") String search);
    
}
