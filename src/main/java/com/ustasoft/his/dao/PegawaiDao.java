/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ustasoft.his.dao;

import com.ustasoft.his.model.master.Pegawai;
import com.ustasoft.his.model.master.Spesialisasi;
import javax.persistence.OrderBy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author faheem
 */
public interface PegawaiDao extends PagingAndSortingRepository<Pegawai, String>{
    @Query("from Pegawai p where aktif=true and p.hrProfile.nama='DOKTER'")
    @OrderBy(value = "nama")
    public Iterable<Pegawai> lookupDokterAktif();
    
    public Pegawai findByNip(String nip);
    
    @Query("from Pegawai p where aktif=:aktif and upper(p.nama) like upper(:search) order by p.nama")
    public Iterable<Pegawai> filterByActive(@Param("aktif") Boolean aktif, @Param("search") String search);
    
    @Query("from Pegawai p where upper(p.nama) like upper(:search) ")
    public Page<Pegawai> filter(@Param("search") String search, Pageable pageable);
    
}
