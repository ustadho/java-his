/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ustasoft.his.dao;

import com.ustasoft.his.model.master.ItemSatuan;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author faheem
 */
public interface ItemSatuanDao extends PagingAndSortingRepository<ItemSatuan, String>{
    @Query("from ItemBrand b where upper(b.nama) like upper(:search) ")
    public Page<ItemSatuan> filter(@Param("search") String search, Pageable pageable);
    
    public ItemSatuan findByNama(String nama);
    
    
}
