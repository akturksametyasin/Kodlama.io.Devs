package kodlama.io.Kodlama.io.Devs.webApi.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kodlama.io.Kodlama.io.Devs.business.abtracts.ProgrammingLanguageSubTechnologyService;
import kodlama.io.Kodlama.io.Devs.business.requests.programmingLanguageSubTechnologyRequests.CreateProgrammingLanguageSubTechnologyRequest;
import kodlama.io.Kodlama.io.Devs.business.requests.programmingLanguageSubTechnologyRequests.DeleteProgrammingLanguageSubTechnologyRequest;
import kodlama.io.Kodlama.io.Devs.business.requests.programmingLanguageSubTechnologyRequests.UpdateProgrammingLanguageSubTechnologyRequest;
import kodlama.io.Kodlama.io.Devs.business.responses.programmingLanguageSubTechnologyResponses.GetAllProgrammingLanguageSubTechnologiesResponse;
import kodlama.io.Kodlama.io.Devs.business.responses.programmingLanguageSubTechnologyResponses.GetByProgrammingLanguageIdSubTechnologiesResponse;
import kodlama.io.Kodlama.io.Devs.business.responses.programmingLanguageSubTechnologyResponses.GetProgrammingLanguageSubTechnologyResponse;

@RestController
@RequestMapping("/api/programminglanguagesubtechnologies")
public class ProgrammingLanguageSubTechnologiesController {
	private ProgrammingLanguageSubTechnologyService programmingLanguageSubTechnologyService;

	@Autowired
	public ProgrammingLanguageSubTechnologiesController(ProgrammingLanguageSubTechnologyService programmingLanguageSubTechnologyService) {
		this.programmingLanguageSubTechnologyService = programmingLanguageSubTechnologyService;
	}
	
	@GetMapping("/getall")
	public List<GetAllProgrammingLanguageSubTechnologiesResponse> getall(){
		return programmingLanguageSubTechnologyService.getall();
	}
	
	@PostMapping("/add")
	public void add(int programmingLanguageId, CreateProgrammingLanguageSubTechnologyRequest createProgrammingLanguageSubTechnologyRequest) throws Exception {
		this.programmingLanguageSubTechnologyService.add(programmingLanguageId, createProgrammingLanguageSubTechnologyRequest);
	}
	
	@DeleteMapping("/delete")
	public void delete(DeleteProgrammingLanguageSubTechnologyRequest deleteProgrammingLanguageSubTechnologyRequest) throws Exception {
		this.programmingLanguageSubTechnologyService.delete(deleteProgrammingLanguageSubTechnologyRequest);
	}
	
	@PutMapping("/update")
	public void update(int id, UpdateProgrammingLanguageSubTechnologyRequest updateProgrammingLanguageSubTechnologyRequest) throws Exception {
		this.programmingLanguageSubTechnologyService.update(id, updateProgrammingLanguageSubTechnologyRequest);
	}
	
	@GetMapping("/getbyprogramminglanguageid")
	public List<GetByProgrammingLanguageIdSubTechnologiesResponse> getByProgrammingLanguageId(int programmingLanguageId) throws Exception{
		return programmingLanguageSubTechnologyService.getByProgrammingLanguageId(programmingLanguageId);
	}
	
	@GetMapping("/getbyid")
	public List<GetProgrammingLanguageSubTechnologyResponse> get(int id) throws Exception{
		return programmingLanguageSubTechnologyService.getById(id);
	}
}
