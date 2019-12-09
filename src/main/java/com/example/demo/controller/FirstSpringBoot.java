package com.example.demo.controller;


import com.example.demo.domain.Degree;
import com.example.demo.service.DegreeService;
import com.example.demo.util.JSONChange;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class FirstSpringBoot {
    @RequestMapping(value = "/hello.ctl" , method = RequestMethod.GET)
    public String hello() throws Exception{
        return "hello world";
    }

    @RequestMapping(value = "/degree.ctl" , method = RequestMethod.GET)
    public Object getDegree(
            @RequestParam(value = "id" ,required = false)String id
            ) throws Exception{
            if(id==null){
                return DegreeService.getInstance().findAll();
            }else {
                return DegreeService.getInstance().find(Integer.parseInt(id));
            }
    }

    @RequestMapping(value = "/degree.ctl" , method = RequestMethod.PUT)
    public Object updateDegree(
            @RequestBody String degreeToUpdate
    ) throws Exception{
        Object obj = JSONChange.jsonToObj(new Degree(),degreeToUpdate);
        Degree degree = (Degree) obj;
        Map<String,String> map = new HashMap<String, String>();
        if(DegreeService.getInstance().update(degree)){
            map.put("message","OK");
            return map;
        }else {
            map.put("message","The database has not changed");
            return map;
        }
    }

    @RequestMapping(value = "/degree.ctl" , method = RequestMethod.POST)
    public Object addDegree(
            @RequestBody String degreeToAdd
    ) throws Exception{
        Object obj = JSONChange.jsonToObj(new Degree(),degreeToAdd);
        Degree degree = (Degree) obj;
        Map<String,String> map = new HashMap<String, String>();
        if(DegreeService.getInstance().add(degree)){
            map.put("message","OK");
            return map;
        }else {
            map.put("message","The database has not changed");
            return map;
        }
    }

    @RequestMapping(value = "/degree.ctl" , method = RequestMethod.DELETE)
    public Object deleteDegree(
            @RequestParam(value = "id")String id
    ) throws Exception{
        Map<String,String> map = new HashMap<String, String>();
        if(DegreeService.getInstance().delete(Integer.parseInt(id))){
            map.put("message","OK");
            return map;
        }else {
            map.put("message","The database has not changed");
            return map;
        }
    }

}
