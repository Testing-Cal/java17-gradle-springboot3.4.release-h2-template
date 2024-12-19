package com.template.demo.impl;

import com.template.demo.bean.ComponentDetailsBean;
import com.template.demo.bean.PairedComponentDetailsBean;
import com.template.demo.entity.ComponentDetailsEntity;
import com.template.demo.repository.ComponentDetailsRepository;
import com.template.demo.service.impl.ComponentDetailsServiceImpl;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class ComponentDetailsServiceImplTests {

	private Gson gson;

	@Mock
	private ComponentDetailsRepository componentDetailsRepository;

	@InjectMocks
	private ComponentDetailsServiceImpl componentDetailsService;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
		gson = new GsonBuilder().serializeNulls().create();
	}

	private ComponentDetailsBean mockComponentDetails() {
		final ComponentDetailsBean componentDetails = new ComponentDetailsBean("componentName", "componentIdentifier");
		return componentDetails;
	}

	private List<ComponentDetailsBean> mockPairedComponentDetails() {
		final ComponentDetailsBean pairedComponentDetails = new ComponentDetailsBean("pairedComponentName", "pairedComponentIdentifier");
		return Arrays.asList(pairedComponentDetails);
	}

	private PairedComponentDetailsBean mockPairedComponentDetailsResponse() {
		final List<ComponentDetailsBean> componentDetails = new ArrayList<ComponentDetailsBean>(1);
		componentDetails.add(new ComponentDetailsBean("pairedComponentName", "pairedComponentIdentifier"));
		final PairedComponentDetailsBean pairedComponentDetails = new PairedComponentDetailsBean("componentName", "componentIdentifier");
		pairedComponentDetails.setPairedComponentDetails(componentDetails);
		return pairedComponentDetails;
	}

	@Test
	public void findAll_Success() throws Exception {
		Mockito.when(this.componentDetailsRepository.getByComponentName(Mockito.any())).thenReturn(this.mockComponentDetails());
		Mockito.when(this.componentDetailsRepository.getByComponentNameNotIn(Mockito.any())).thenReturn(this.mockPairedComponentDetails());
		JSONAssert.assertEquals(this.gson.toJson(this.mockPairedComponentDetailsResponse()), this.gson.toJson(this.componentDetailsService.findAll(Mockito.any())), true);
	}

	@Test
	public void createComponentDetails_Success() throws Exception {
		Mockito.when(this.componentDetailsRepository.findByComponentName(Mockito.any())).thenReturn(null);
		Mockito.when(this.componentDetailsRepository.save(Mockito.any())).thenReturn(new ComponentDetailsEntity());
		assertEquals(true, this.componentDetailsService.createComponentDetails(Mockito.any()));
	}

	@Test
	public void createExistingComponentDetails_Success() throws Exception {
		Mockito.when(this.componentDetailsRepository.findByComponentName(Mockito.any())).thenReturn(new ComponentDetailsEntity());
		Mockito.when(this.componentDetailsRepository.save(Mockito.any())).thenReturn(new ComponentDetailsEntity());
		assertEquals(true, this.componentDetailsService.createComponentDetails(Mockito.any()));
	}

	@Test
	public void regenerateComponentIdentifier_Success() throws Exception {
		Mockito.when(this.componentDetailsRepository.findByComponentName(Mockito.any())).thenReturn(new ComponentDetailsEntity());
		Mockito.when(this.componentDetailsRepository.save(Mockito.any())).thenReturn(new ComponentDetailsEntity());
		this.componentDetailsService.regenerateComponentIdentifier();
		assertTrue(true);
	}

}
