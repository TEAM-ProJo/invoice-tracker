package com.invoicetracker.controllers;

import java.util.Collection;
import javax.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.invoicetracker.models.Contractor;
import com.invoicetracker.models.Invoice;
import com.invoicetracker.repositories.ContractorRepository;
import com.invoicetracker.repositories.InvoiceRepository;

@RequestMapping("/contractor")
@Controller
public class ContractorController {

	@Resource
	private ContractorRepository contractorRepo;

	@Resource
	private InvoiceRepository invoiceRepo;

	@GetMapping("/create-new-invoice/{contractorId}")
	private String createInvoice(@PathVariable(value = "contractorId") long contractorId, Model model) {
		Contractor contractor = contractorRepo.findById(contractorId).get();
		model.addAttribute("contractor", contractor);
		return "create-invoice";
	}

	@GetMapping("/update-profile/{contractorId}")
	private String updateProfile(@PathVariable(value = "contractorId") long contractorId, Model model) {
		Contractor contractor = contractorRepo.findById(contractorId).get();
		model.addAttribute("contractor", contractor);
		return "profile";
	}

	@GetMapping("/view-existing-invoice/{contractorId}/{invoiceId}")
	private String viewInvoice(@PathVariable(value = "contractorId") long contractorId,
			@PathVariable(value = "invoiceId") long invoiceId, Model model) {

		Contractor contractor = contractorRepo.findById(contractorId).get();
		Invoice invoice = invoiceRepo.findById(invoiceId).get();

		model.addAttribute("contractor", contractor);
		model.addAttribute("invoice", invoice);

		return "view-invoice";
	}

	@GetMapping("/search-invoice-list/{contractorId}")
	private String viewInvoiceList(@PathVariable(value = "contractorId") long contractorId, Model model) {

		Contractor contractor = contractorRepo.findById(contractorId).get();
		Collection<Invoice> invoices = contractor.getInvoices();
		model.addAttribute("invoices", invoices);
		model.addAttribute("contractor", contractor);

		return "search-invoice-list";
	}

	/*
	 * TODO Needs Code Review: I do not think a @PutMapping is right, but it is
	 * working.
	 */
	@PutMapping("/mark-invoice-paid/{invoiceId}")
	private void markInvoicePaid(@PathVariable(value = "invoiceId") long invoiceId) {

		Invoice invoiceToMarkPaid = invoiceRepo.findById(invoiceId).get();
		invoiceToMarkPaid.setIsPaid(true);
		invoiceRepo.save(invoiceToMarkPaid);
	}

	/*
	 * TODO Needs Code Review: I do not think a @PutMapping is right, but it is
	 * working.
	 */
	@PutMapping("/mark-invoice-sent/{invoiceId}")
	private void markInvoiceSent(@PathVariable(value = "invoiceId") long invoiceId) {

		Invoice invoiceToSend = invoiceRepo.findById(invoiceId).get();
		invoiceToSend.setIsSent(true);
		invoiceRepo.save(invoiceToSend);
	}

}