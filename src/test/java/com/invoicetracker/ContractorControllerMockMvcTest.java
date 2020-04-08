package com.invoicetracker;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.invoicetracker.controllers.ContractorController;
import com.invoicetracker.models.Contractor;
import com.invoicetracker.models.Invoice;
import com.invoicetracker.repositories.ContractorRepository;
import com.invoicetracker.repositories.InvoiceRepository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;


@RunWith(SpringRunner.class)
@WebMvcTest(ContractorController.class)
public class ContractorControllerMockMvcTest {

	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private ContractorRepository contractorRepo;

	@MockBean
	private InvoiceRepository invoiceRepo;
	
	@Mock
	private Contractor contractorOne;
	
	@Mock
	private Invoice invoiceOne;

	@Mock
	private Invoice invoiceTwo;
	
	@Test
	public void shouldGetStatusOfOkWhenNavigatingToCreateInvoice() throws Exception {
		this.mockMvc.perform(get("/contractor/create-new-invoice")).andExpect(status().isOk())
		.andExpect(view().name("create-invoice"));
	}

	@Test
	public void shouldGetStatusOfOkWhenNavigatingToViewInvoice() throws Exception {
		this.mockMvc.perform(get("/contractor/view-existing-invoice")).andExpect(status().isOk())
		.andExpect(view().name("view-invoice"));
	}

	
	@Test
	public void shouldGetStatusOfOkWhenNavigatingToSearchInvoiceList() throws Exception {
		long contractorId = 1;
		when(contractorRepo.findById(contractorId)).thenReturn(Optional.of(contractorOne));
		this.mockMvc.perform(get("/contractor/search-invoice-list/" + contractorId)).andExpect(status().isOk())
		.andExpect(view().name("search-invoice-list"));
	}
	

}
