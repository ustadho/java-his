/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ustasoft.his.dao;

import com.ustasoft.his.model.master.HrProfile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author faheem
 */
public interface HrProfileDao extends PagingAndSortingRepository<HrProfile, String>{
    @Query("from HrProfile h where upper(h.nama) like upper(:search) ")
    public Page<HrProfile> filter(@Param("search") String search, Pageable pageable);
    
    public HrProfile findByNama(String nama);
    
    @Query("from HrProfile h where aktif=:aktif and upper(h.nama) like upper(:search) order by h.nama")
    public Iterable<HrProfile> filterByActive(@Param("aktif") Boolean aktif, @Param("search") String search);
    
}
