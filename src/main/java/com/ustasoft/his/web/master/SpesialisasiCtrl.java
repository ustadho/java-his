/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ustasoft.his.web.master;

import com.ustasoft.his.dao.SpesialisasiDao;
import com.ustasoft.his.model.master.Spesialisasi;
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
@RequestMapping("/master/spesialisasi")
public class SpesialisasiCtrl {
    @Autowired
    SpesialisasiDao dao;
    private Logger logger=LoggerFactory.getLogger(SpesialisasiCtrl.class);
    
    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public Page<Spesialisasi> listAll(
            @RequestParam(required = false) String search, 
            Pageable pageable, 
            HttpServletResponse response){
        PageRequest pr = new PageRequest(pageable.getPageNumber(), pageable.getPageSize(), Sort.Direction.ASC, "nama");
        logger.debug("pageNumber [{}], pageSize: [{}]", pageable.getPageNumber(), pageable.getPageSize());
        return dao.findAll(pr);
    }
    
    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Spesialisasi findOne(@PathVariable String id, 
            HttpServletResponse response){
        return dao.findOne(id);
    }
    
    @RequestMapping(value = "/cek-nama/{nama}", method = RequestMethod.GET)
    @ResponseBody
    public Spesialisasi cekNama(@PathVariable String nama, 
            HttpServletResponse response){
        return dao.findByNama(nama);
    }
    
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    @ResponseBody
    public Iterable<Spesialisasi> findAll(HttpServletResponse response){
        return dao.findAll();
    }
    
    @RequestMapping(value = "/{nama}", method = RequestMethod.GET)
    @ResponseBody
    public Page<Spesialisasi> findByName(@PathVariable String nama, 
            Pageable pageable, 
            HttpServletResponse response){
        return dao.filter("%"+nama+"%", pageable);
    }
    @RequestMapping(value = "/aktif", method = RequestMethod.GET)
    @ResponseBody
    public Iterable<Spesialisasi> listAktifAll(@PathVariable String nama, 
            Pageable pageable, 
            HttpServletResponse response){
        return dao.filterByActive(Boolean.TRUE, nama);
    }
    
    @RequestMapping(method = RequestMethod.POST)
    public void save(@RequestBody Spesialisasi ik, 
            HttpServletResponse response){
        dao.save(ik);
    }
    
    @RequestMapping(method = RequestMethod.PUT)
    public void update(@RequestBody Spesialisasi ik, 
            HttpServletResponse response){
        dao.save(ik);
    }
    
    
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable String id, 
            HttpServletResponse response){
        dao.delete(id);
    }
}
