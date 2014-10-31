/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ustasoft.his.web.master;

import com.ustasoft.his.dao.PegawaiDao;
import com.ustasoft.his.model.master.Pegawai;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author faheem
 */
@Controller
@RequestMapping("/hr/pegawai")
public class PegawaiCtrl {
    @Autowired
    PegawaiDao dao;
    private Logger logger=LoggerFactory.getLogger(PegawaiCtrl.class);
    
    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public Page<Pegawai> listAll(
            Pageable pageable, 
            HttpServletResponse response){
        PageRequest pr = new PageRequest(pageable.getPageNumber(), pageable.getPageSize(), Sort.Direction.ASC, "nama");
        logger.debug("pageNumber [{}], pageSize: [{}]", pageable.getPageNumber(), pageable.getPageSize());
        Page<Pegawai> hasil = dao.findAll(pr);
        for(Pegawai x: hasil){
            x.setSpesialisasiSet(null);
        }
        return hasil;
    }
    
    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Pegawai findOne(@PathVariable String id, 
            HttpServletResponse response){
        Pegawai hasil= dao.findOne(id);
        hasil.setSpesialisasiSet(null);
        return hasil;
    }
    
    @RequestMapping(value = "/cek-nip/{nip}", method = RequestMethod.GET)
    @ResponseBody
    public Pegawai cekNama(@PathVariable String nip, 
            HttpServletResponse response){
        Pegawai x= dao.findByNip(nip);
        x.setSpesialisasiSet(null);
        return x;
    }
    
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    @ResponseBody
    public Iterable<Pegawai> findAll(HttpServletResponse response){
        return dao.findAll();
    }
    
    @RequestMapping(value = "/{nama}", method = RequestMethod.GET)
    @ResponseBody
    public Page<Pegawai> findByName(@PathVariable String nama, 
            Pageable pageable, 
            HttpServletResponse response){
        return dao.filter("%"+nama+"%", pageable);
    }
    @RequestMapping(value = "/aktif", method = RequestMethod.GET)
    @ResponseBody
    public Iterable<Pegawai> listAktifAll(@PathVariable String nama, 
            Pageable pageable, 
            HttpServletResponse response){
        Iterable<Pegawai> hasil=dao.filterByActive(Boolean.TRUE, nama);
        fixLie(hasil);
        return hasil;
    }
    
    @RequestMapping(method = RequestMethod.POST)
    public void save(@RequestBody Pegawai ik, 
            HttpServletResponse response){
        dao.save(ik);
    }
    
    @RequestMapping(method = RequestMethod.PUT)
    public void update(@RequestBody Pegawai ik, 
            HttpServletResponse response){
        dao.save(ik);
    }
    
    
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable String id, 
            HttpServletResponse response){
        dao.delete(id);
    }
    
    private void fixLie(Iterable<Pegawai> lst){
        for(Pegawai x: lst){
            x.setSpesialisasiSet(null);
        }
    }
}
