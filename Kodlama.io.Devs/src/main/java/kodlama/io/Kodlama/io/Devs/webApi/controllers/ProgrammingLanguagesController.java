package kodlama.io.Kodlama.io.Devs.webApi.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kodlama.io.Kodlama.io.Devs.business.abtracts.ProgrammingLanguageService;
import kodlama.io.Kodlama.io.Devs.business.requests.programmingLanguageRequests.CreateProgrammingLanguageRequest;
import kodlama.io.Kodlama.io.Devs.business.requests.programmingLanguageRequests.DeleteProgrammingLanguageRequest;
import kodlama.io.Kodlama.io.Devs.business.requests.programmingLanguageRequests.UpdateProgrammingLanguageRequest;
import kodlama.io.Kodlama.io.Devs.business.responses.programmingLanguageResponses.GetAllProgrammingLanguagesResponse;
import kodlama.io.Kodlama.io.Devs.business.responses.programmingLanguageResponses.GetProgrammingLanguageResponse;

@RestController
@RequestMapping("/api/programminglanguages")
public class ProgrammingLanguagesController {
	private ProgrammingLanguageService programmingLanguageService;

	@Autowired
	public ProgrammingLanguagesController(ProgrammingLanguageService programmingLanguageService) {
		this.programmingLanguageService = programmingLanguageService;
	}
	
	@GetMapping("/getall")
	public List<GetAllProgrammingLanguagesResponse> getAll(){
		return programmingLanguageService.getAll();
	}
	
	@PostMapping("/add")
	public void add(CreateProgrammingLanguageRequest createProgrammingLanguageRequest) throws Exception {
		this.programmingLanguageService.add(createProgrammingLanguageRequest);
	}
	
	@PutMapping("/update")
	public void update(int id, UpdateProgrammingLanguageRequest updateProgrammingLanguageRequest) throws Exception {
		this.programmingLanguageService.update(id, updateProgrammingLanguageRequest);
	}
	
	@DeleteMapping("/delete")
	public void delete(DeleteProgrammingLanguageRequest deleteProgrammingLanguageRequest) throws Exception {
		this.programmingLanguageService.delete(deleteProgrammingLanguageRequest);
	}
	
	@GetMapping("/get")
	public GetProgrammingLanguageResponse get(int id) throws Exception {
		return programmingLanguageService.get(id);
	}
	
	/*
	 * @PostMapping("/add") public void add(ProgrammingLanguage programmingLanguage)
	 * throws Exception { programmingLanguageService.add(programmingLanguage); }
	 * 
	 * @DeleteMapping("/delete/{id}") public void delete(int id) throws Exception {
	 * programmingLanguageService.delete(id); }
	 * 
	 * @PutMapping("/update/{id}") public void update(ProgrammingLanguage
	 * programmingLanguage) throws Exception{
	 * programmingLanguageService.update(programmingLanguage); }
	 * 
	 * @GetMapping("/get/{id}") public ProgrammingLanguage get(int id) throws
	 * Exception { return programmingLanguageService.get(id); }
	 */
	
}
