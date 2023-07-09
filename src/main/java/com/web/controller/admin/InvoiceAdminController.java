package com.web.controller.admin;

import java.sql.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.web.dao.InvoiceDao;
import com.web.dao.StatusInvoiceDao;
import com.web.entity.Category;
import com.web.entity.Invoice;
import com.web.entity.StatusInvoice;

@Controller
public class InvoiceAdminController {

	@Autowired
	private StatusInvoiceDao statusInvoiceDao;
	
	@Autowired
	private InvoiceDao invoiceDao;
	
	@RequestMapping(value = { "/admin/invoice" }, method = RequestMethod.GET)
	public String blog(Model model,@RequestParam(value = "start", required = false) String start,
			@RequestParam(value = "end", required = false) String end,
			@RequestParam(value = "loaidh", required = false) Integer idstatus) {
		System.out.println("========= "+start+", "+end+", "+idstatus);
		model.addAttribute("status", statusInvoiceDao.findAll());
		model.addAttribute("invoices", invoiceDao.findAll());
		if(idstatus == null) {
			idstatus = -1;
		}
		if(start == null || end == null) {
			start = ""; end = "";
		}
		if(!start.equals("") && !end.equals("") && idstatus != -1) {
			System.out.println("3++++");
			Date sd = Date.valueOf(start);
			Date ed = Date.valueOf(end);
			model.addAttribute("invoices", invoiceDao.findByDateEndStatus(sd, ed, idstatus));
		}
		if((start.equals("") || end.equals("")) && idstatus != -1) {
			System.out.println("4++++");
			model.addAttribute("invoices", invoiceDao.findByStatus(idstatus));
		}
		if(!start.equals("") && !end.equals("") && idstatus == -1) {
			System.out.println("5++++");
			Date sd = Date.valueOf(start);
			Date ed = Date.valueOf(end);
			model.addAttribute("invoices", invoiceDao.findByDate(sd,ed));
		}
		return "/views/admin/invoice";
	}
	
	@RequestMapping(value = { "/admin/updateinvoice" }, method = RequestMethod.POST)
	public String updateInvoice(@RequestParam("iddh") Integer id,@RequestParam("status") Integer status) {
		Invoice invoice = invoiceDao.findById(id);
		StatusInvoice s = statusInvoiceDao.findById(status);
		invoice.setStatusInvoice(s);
		invoiceDao.save(invoice);
		return "redirect:invoice";
	}
}
