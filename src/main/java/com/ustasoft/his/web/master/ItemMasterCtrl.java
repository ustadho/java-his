/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ustasoft.his.web.master;

import com.ustasoft.his.dao.ItemMasterDao;
import com.ustasoft.his.model.master.ItemMaster;
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
public class ItemMasterCtrl {
    @Autowired
    ItemMasterDao dao;
    private Logger logger=LoggerFactory.getLogger(ItemMasterCtrl.class);
    
    @RequestMapping(value = "/item", method = RequestMethod.GET)
    @ResponseBody
    public Page<ItemMaster> listAll(
            @RequestParam(required = false) String search, 
            Pageable pageable, 
            HttpServletResponse response){
        PageRequest pr = new PageRequest(pageable.getPageNumber(), pageable.getPageSize(), Sort.Direction.ASC, "nama");
        logger.debug("pageNumber [{}], pageSize: [{}]", pageable.getPageNumber(), pageable.getPageSize());
        return dao.findAll(pr);
    }
    
    @RequestMapping(value = "/item/get/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ItemMaster findOne(@PathVariable String id, 
            HttpServletResponse response){
        return dao.findOne(id);
    }
    
    @RequestMapping(value = "/item/cek-nama/{nama}", method = RequestMethod.GET)
    @ResponseBody
    public ItemMaster cekNama(@PathVariable String nama, 
            HttpServletResponse response){
        return dao.findByNama(nama);
    }
    
    @RequestMapping(value = "/item/all", method = RequestMethod.GET)
    @ResponseBody
    public Iterable<ItemMaster> findAll(HttpServletResponse response){
        return dao.findAll();
    }
    
    @RequestMapping(value = "/item/{nama}", method = RequestMethod.GET)
    @ResponseBody
    public Page<ItemMaster> findByName(@PathVariable String nama, 
            Pageable pageable, 
            HttpServletResponse response){
        return dao.filter("%"+nama+"%", pageable);
    }
    
    @RequestMapping(value = "/item", method = RequestMethod.POST)
    public void save(@RequestBody ItemMaster ik, 
            HttpServletResponse response){
        dao.save(ik);
    }
    
    @RequestMapping(value = "/item", method = RequestMethod.PUT)
    public void update(@RequestBody ItemMaster ik, 
            HttpServletResponse response){
        dao.save(ik);
    }
    
    
    @RequestMapping(value = "/item/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable String id, 
            HttpServletResponse response){
        dao.delete(id);
    }
    
    
    
}
