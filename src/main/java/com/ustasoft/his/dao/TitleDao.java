/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ustasoft.his.dao;

import com.ustasoft.his.model.master.Title;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author faheem
 */
public interface TitleDao extends PagingAndSortingRepository<Title, String>{
    @Query("from Title t where upper(t.singkatan) like upper(:search) or upper(t.keterangan) like upper(:search) ")
    public Page<Title> filter(@Param("search") String search, Pageable pageable);
    
    public Title findBySingkatan(String singkatan);
    public Title findByKeterangan(String keterangan);
}
