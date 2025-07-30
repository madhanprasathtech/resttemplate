package com.example.Myweb;

import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class Resttemplate_1 {
	@Autowired
	RestTemplate rt;
	
	@GetMapping(value="/getthis")
	public String gets() {
		String url="http://localhost:8080/getAccess?username=madhan&password=madhan123";
		ResponseEntity<String> r=rt.exchange(url, HttpMethod.GET,null,String.class);
		String x1=r.getBody();
		return x1;
		
	}
	
	@GetMapping("/getMaxUsingMyWeb/{a}/{b}")
	public Integer getMethodName(@PathVariable int a, @PathVariable int b) {
		
		ResponseEntity<Integer> max = rt.exchange("http://localhost:8080/getmax/"+a+"/"+b, HttpMethod.GET,null,int.class);
		Integer maximum = max.getBody();
		
		return maximum;
	}
	
	@GetMapping("/getlength/{a},{b}")
	public String getname(@PathVariable String a, @PathVariable String b) {
		ResponseEntity<String> x=rt.exchange("http://localhost:8080/getstringlength/"+a+"/"+b,HttpMethod.GET,null,String.class);
		String len=x.getBody();
		return len;
	}
	
	@GetMapping("/getobject")
	public ResponseEntity<List<PojoMobile>> getmobi() {
		ResponseEntity<List<PojoMobile>> m=rt.exchange("http://localhost:8080/api/mob/getalls",HttpMethod.GET, null,new ParameterizedTypeReference<List<PojoMobile>>() {});
	   
		List<PojoMobile> mo = m.getBody(); 
		return m;
	}
	
	@GetMapping("/getmaxim")
	public PojoMobile getmax(){
		List<PojoMobile> x=rt.exchange("http://localhost:8080/api/mob/getalls",HttpMethod.GET ,null,new ParameterizedTypeReference<List<PojoMobile>>() {}).getBody();
		PojoMobile maxprice = x.stream()
			    .max(Comparator.comparing(PojoMobile::getPrice))
			    .get(); 
		return maxprice;
	
	}
	
}
