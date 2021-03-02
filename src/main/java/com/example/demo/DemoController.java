package com.example.demo;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class DemoController {

	@Autowired
	@Qualifier("postgresJdbcTemplate")
	private JdbcTemplate postgresTemplate;

	@Autowired
	@Qualifier("mysqlJdbcTemplate")
	private JdbcTemplate mysqlTemplate;

	@RequestMapping(value = "/getPGUser")
	@ResponseBody
	public String getPGUser() {
		Map<String, Object> map = new HashMap<String, Object>();
		String query = " select * from usermaster";
		try {
			map = postgresTemplate.queryForMap(query);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "PostgreSQL Data: " + map.toString();
	}

	@RequestMapping(value = "/getMYUser")
	@ResponseBody
	public String getMYUser() {
		Map<String, Object> map = new HashMap<String, Object>();
		String query = " select * from usermaster";
		String retStr=map.toString();
		try {
			map = mysqlTemplate.queryForMap(query);
			retStr = map.toString();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "MySQL Data: " + retStr;
	}
}
