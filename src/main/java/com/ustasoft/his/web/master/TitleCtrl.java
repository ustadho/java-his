/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ustasoft.his.web.master;

import com.ustasoft.his.dao.TitleDao;
import com.ustasoft.his.model.master.Title;
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
public class TitleCtrl {
    @Autowired
    TitleDao dao;
    private Logger logger=LoggerFactory.getLogger(TitleCtrl.class);
    
    @RequestMapping(value = "/title", method = RequestMethod.GET)
    @ResponseBody
    public Page<Title> listAll(
            @RequestParam(required = false) String search, 
            Pageable pageable, 
            HttpServletResponse response){
        PageRequest pr = new PageRequest(pageable.getPageNumber(), pageable.getPageSize(), Sort.Direction.ASC, "nama");
        logger.debug("pageNumber [{}], pageSize: [{}]", pageable.getPageNumber(), pageable.getPageSize());
        return dao.findAll(pr);
    }
    
    @RequestMapping(value = "/title/get/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Title findOne(@PathVariable String id, 
            HttpServletResponse response){
        return dao.findOne(id);
    }
    
    @RequestMapping(value = "/title/cek-singkatan/{singkatan}", method = RequestMethod.GET)
    @ResponseBody
    public Title cekNama(@PathVariable String singkatan, 
            HttpServletResponse response){
        return dao.findBySingkatan(singkatan);
    }
    
    @RequestMapping(value = "/title/all", method = RequestMethod.GET)
    @ResponseBody
    public Iterable<Title> findAll(HttpServletResponse response){
        return dao.findAll();
    }
    
    @RequestMapping(value = "/title/{nama}", method = RequestMethod.GET)
    @ResponseBody
    public Page<Title> findByName(@PathVariable String nama, 
            Pageable pageable, 
            HttpServletResponse response){
        return dao.filter("%"+nama+"%", pageable);
    }
    
    @RequestMapping(value = "/title", method = RequestMethod.POST)
    public void save(@RequestBody Title ik, 
            HttpServletResponse response){
        dao.save(ik);
    }
    
    @RequestMapping(value = "/title", method = RequestMethod.PUT)
    public void update(@RequestBody Title ik, 
            HttpServletResponse response){
        dao.save(ik);
    }
    
    
    @RequestMapping(value = "/title/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable String id, 
            HttpServletResponse response){
        dao.delete(id);
    }
    
    
    
}
