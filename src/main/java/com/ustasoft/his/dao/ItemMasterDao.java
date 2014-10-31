/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ustasoft.his.dao;

import com.ustasoft.his.model.master.ItemMaster;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author faheem
 */
public interface ItemMasterDao extends PagingAndSortingRepository<ItemMaster, String>{
    @Query("from ItemMaster i where upper(i.nama) like upper(:search) ")
    public Page<ItemMaster> filter(@Param("search") String search, Pageable pageable);
    
    public ItemMaster findByNama(String nama);
    
    
}
