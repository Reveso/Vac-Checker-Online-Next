package com.lukasrosz.vaccheckeronline.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import com.lukasrosz.vaccheckeronline.service.SuspectService;
import com.lukasrosz.vaccheckeronline.steamapiintegration.urlmaker.SteamApiUrlMaker;
import com.lukasrosz.vaccheckeronline.suspects.entity.Suspect;

@Controller
@RequestMapping("/suspects")
public class SuspectsController {
	
	@Autowired
	private SuspectService suspectService;
	
	@Autowired
	private SteamApiUrlMaker steamApiUrlMaker;
	
	@GetMapping("/showList")
	public String showSuspectList(Model model) {
		List<Suspect> suspects = suspectService.getSuspects();

		model.addAttribute("suspects", suspects);
		return "list-suspects";
	}
	
	@GetMapping("/showFormForAdd")
	public String showFormForAdd(Model model) {
		model.addAttribute("suspect", new Suspect());
		return "form-suspects";
	}
	
	@GetMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("suspectId") int suspectId, 
									Model model) {
		Suspect suspect = suspectService.getSuspect(suspectId);
		model.addAttribute("suspect", suspect);
		return "form-suspects";	
	}
	
	@PostMapping("/saveSuspect")
	public String saveSuspect(@Valid @ModelAttribute("suspect") Suspect suspectDto, 
			BindingResult bindingResult, Model model) {
		
		if (bindingResult.hasErrors()) {
			return "form-suspects";
		}
		
		RestTemplate restTemplate = new RestTemplate();
		
		if(!suspectDto.isSteamAccount(restTemplate, steamApiUrlMaker)) {
			String incorrectIdError = "Incorrect ID";
			model.addAttribute("error", incorrectIdError);
			return "form-suspects";	
		}

		suspectDto.updateVACStatus(restTemplate, steamApiUrlMaker);
		
		if(suspectService.saveSuspect(suspectDto) == false) {
			String onListError = "ID already on list";
			model.addAttribute("error", onListError);
			return "form-suspects";
		}
		
		return "redirect:/suspects/showList";
	}
	
	@GetMapping("/delete")
	public String deleteSuspect(@RequestParam("suspectId") int suspectId) {
		suspectService.deleteSuspect(suspectId);	
		return "redirect:/suspects/showList";
	}
	
	@InitBinder
	public void initBinder(WebDataBinder dataBinder) {
		StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
		dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
	}
	
}
