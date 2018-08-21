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

import com.lukasrosz.vaccheckeronline.service.SuspectService;
import com.lukasrosz.vaccheckeronline.suspects.entity.Suspect;


@Controller
@RequestMapping("/suspects")
public class SuspectsController {
	
	@Autowired
	private SuspectService suspectService;
	
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
	public String saveSuspect(@Valid @ModelAttribute("suspect") Suspect suspect, 
							BindingResult bindingResult, Model model) {
		
		if(bindingResult.hasErrors())
			return "form-suspects";	
		

		if(suspectService.saveSuspect(suspect) == false) {
			String onListError = "Url already on list";
			model.addAttribute("onListError", onListError);
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
	
	public static String getHTMLSource(String url) {
		if(url == null) 
			return "Couldn't connect.";
		
		String source = "";
		
		//TODO: This will be replaced by JSON
		
		
//		try {		
//			URL urlConnection = new URL(url);
//			source = Jsoup.connect(url).get().html();
//			source = urlConnection.get
//		} catch (IOException | IllegalArgumentException e) {
//			source = "Couldn't connect.";
//			System.out.println("=====================================" + 
//								"====================================" + 
//								"============================>>> getHTMLSource Exception");
//			e.printStackTrace();
//		}
		
		return source;
	}
	
	public static boolean checkVacStatus(String url) {
		String source = getHTMLSource(url);
		if(source.contains("profile_ban_status")) {
			return true;
		} else {
			return false;
		}
	}

}
