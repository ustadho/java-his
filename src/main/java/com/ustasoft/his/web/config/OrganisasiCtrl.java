/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ustasoft.his.web.config;

import com.ustasoft.his.dao.OrganisasiDao;
import com.ustasoft.his.model.config.Organisasi;
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
@RequestMapping("/config")
public class OrganisasiCtrl {
    @Autowired
    OrganisasiDao dao;
    private Logger logger=LoggerFactory.getLogger(OrganisasiCtrl.class);
    
    @RequestMapping(value = "/organisasi", method = RequestMethod.GET)
    @ResponseBody
    public Page<Organisasi> listAll(
            @RequestParam(required = false) String search, 
            Pageable pageable, 
            HttpServletResponse response){
        PageRequest pr = new PageRequest(pageable.getPageNumber(), pageable.getPageSize(), Sort.Direction.ASC, "nama");
        logger.debug("pageNumber [{}], pageSize: [{}]", pageable.getPageNumber(), pageable.getPageSize());
        return dao.findAll(pr);
    }
    
    @RequestMapping(value = "/organisasi/get/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Organisasi findOne(@PathVariable String id, 
            HttpServletResponse response){
        return dao.findOne(id);
    }
    
    @RequestMapping(value = "/organisasi/cek-nama/{nama}", method = RequestMethod.GET)
    @ResponseBody
    public Organisasi cekNama(@PathVariable String nama, 
            HttpServletResponse response){
        return dao.findByNama(nama);
    }
    
    @RequestMapping(value = "/organisasi/all", method = RequestMethod.GET)
    @ResponseBody
    public Iterable<Organisasi> findAll(HttpServletResponse response){
        return dao.findAll();
    }
    
    @RequestMapping(value = "/organisasi/{nama}", method = RequestMethod.GET)
    @ResponseBody
    public Page<Organisasi> findByName(@PathVariable String nama, 
            Pageable pageable, 
            HttpServletResponse response){
        return dao.filter("%"+nama+"%", pageable);
    }
    
    @RequestMapping(value = "/organisasi", method = RequestMethod.POST)
    public void save(@RequestBody Organisasi ik, 
            HttpServletResponse response){
        dao.save(ik);
    }
    
    @RequestMapping(value = "/organisasi", method = RequestMethod.PUT)
    public void update(@RequestBody Organisasi ik, 
            HttpServletResponse response){
        dao.save(ik);
    }
    
    
    @RequestMapping(value = "/organisasi/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable String id, 
            HttpServletResponse response){
        dao.delete(id);
    }
    
    
}
