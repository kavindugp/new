package lk.group1.auth.server;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;

import org.junit.Test;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.test.web.servlet.MockMvc;

import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import org.springframework.web.context.WebApplicationContext;

public class TestAuthServer extends OauthApplicationTests{
	
	@Autowired
	private WebApplicationContext webApplicationContext;
	
	
	private MockMvc mockMvc;
	
	@Before
	public void setup() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

	@Test
	public void AddUser() throws Exception {
		
		mockMvc.perform(get("/user")).andExpect(status().isOk())
		
				.andExpect(content().contentType("application/json;charset=UTF-8"))
				
				.andExpect(jsonPath("$.id").value("002")).andExpect(jsonPath("$.username").value("Kavindu"))
				
				.andExpect(jsonPath("$.password").value("1234567890")).andExpect(jsonPath("$.email").value("kavindugp@hotmail.com"));

	}

	
	
	@Test
	public void testUpdateUser() throws Exception {
		
		mockMvc.perform(get("/updateUser/{id}")).andExpect(status().isOk())
		
				.andExpect(content().contentType("application/json;charset=UTF-8"))
				
				.andExpect(jsonPath("$.id").value("001")).andExpect(jsonPath("$.username").value("Tharindu"))
				
				.andExpect(jsonPath("$.password").value("1234")).andExpect(jsonPath("$.email").value("kavindugp@gmail.com"));

	}

	
	@Test
	public void deleteUser() throws Exception {
		mockMvc.perform(get("//deleteUser/{id}")).andExpect(status().isOk())
		
				.andExpect(content().contentType("application/json;charset=UTF-8"))
				
				.andExpect(jsonPath("$.id").value("001"));

	}
}
