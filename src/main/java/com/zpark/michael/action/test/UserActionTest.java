package com.zpark.michael.action.test;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/back1")
public class UserActionTest {
	//请求http:ip:port/应用/back1/test1
   @RequestMapping(value="/test1")
   public  String test1(){
	   return "/index";//forward
   }
//   //请求http:ip:port/应用/back1/test2?name=张三&id=1
//   @RequestMapping(value="/test2")
//   public  String test2(String name,int id){
//	   System.out.println("name:"+name);
//	   System.out.println("id:"+id);
//	   return "index";
//   }
//   //请求http:ip:port/应用/back1/test2?pname=张三&id=1
//   @RequestMapping(value="/test3")
//   public  String test3(@RequestParam(value="pname",required=false,defaultValue="lisi")String name,int id){
//	   System.out.println("name:"+name);
//	   System.out.println("id:"+id);
//	   return "index";
//   }
// //请求http:ip:port/应用/back1/test4/1/zhangsan  restful url
//   @RequestMapping(value="/test4/{id}/{name}")
//   public  String test4(@PathVariable("name")String name,@PathVariable("id")int id){
//	   System.out.println("name:"+name);
//	   System.out.println("id:"+id);
//	   return "index";
//   }
//   @RequestMapping(value="/test5")
//   public  String test5(){
//	   return "redirect:/index.jsp";
//   }
}
