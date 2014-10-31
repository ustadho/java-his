/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ustasoft.his.web.master;

import com.ustasoft.his.dao.KotaDao;
import com.ustasoft.his.model.master.ItemKategori;
import com.ustasoft.his.model.master.Kota;
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
@RequestMapping("/master")
public class KotaCtrl {
    @Autowired
    KotaDao kotaDao;
    private Logger logger=LoggerFactory.getLogger(KotaCtrl.class);
    
    @RequestMapping(value = "/kota", method = RequestMethod.GET)
    @ResponseBody
    public Page<Kota> listAll(
            @RequestParam(required = false) String search, 
            Pageable pageable, 
            HttpServletResponse response){
        PageRequest pr = new PageRequest(pageable.getPageNumber(), pageable.getPageSize(), Sort.Direction.ASC, "nama");
        logger.debug("pageNumber [{}], pageSize: [{}]", pageable.getPageNumber(), pageable.getPageSize());
        return kotaDao.findAll(pr);
    }
    
    @RequestMapping(value = "/kota/all", method = RequestMethod.GET)
    @ResponseBody
    public Iterable<Kota> findAll(HttpServletResponse response){
        return kotaDao.findAll();
    }
    
    @RequestMapping(value = "/kota/get/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Kota findOne(@PathVariable String id, 
            HttpServletResponse response){
        return kotaDao.findOne(id);
    }
    @RequestMapping(value = "/kota/cek-nama/{nama}", method = RequestMethod.GET)
    @ResponseBody
    public Kota cekNama(@PathVariable String nama, 
            HttpServletResponse response){
        return kotaDao.findByNama(nama);
    }
    @RequestMapping(value = "/kota/{nama}", method = RequestMethod.GET)
    @ResponseBody
    public Page<Kota> findByName(@PathVariable String nama, 
            Pageable pageable, 
            HttpServletResponse response){
        return kotaDao.filter("%"+nama+"%", pageable);
    }
    
    
    @RequestMapping(value = "/kota", method = RequestMethod.POST)
    public void save(@RequestBody Kota ik, 
            HttpServletResponse response){
        kotaDao.save(ik);
    }
    
    @RequestMapping(value = "/kota", method = RequestMethod.PUT)
    public void update(@RequestBody Kota ik, 
            HttpServletResponse response){
        kotaDao.save(ik);
    }
    
    
    @RequestMapping(value = "/kota/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable String id, 
            HttpServletResponse response){
        kotaDao.delete(id);
    }
    
}
