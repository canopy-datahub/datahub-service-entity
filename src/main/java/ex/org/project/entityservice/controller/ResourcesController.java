package ex.org.project.entityservice.controller;

import java.util.List;
import java.util.Map;

import ex.org.project.entityservice.model.DTO.*;
import ex.org.project.entityservice.util.Validator;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import ex.org.project.entityservice.service.ResourcesService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class ResourcesController {
	private final ResourcesService resourcesService;

	@GetMapping("/getEvents")
	public ResponseEntity<List<EventDTO>> getCurrentEvents() {
		List<EventDTO> returnedEvents = resourcesService.getEvents();
		return new ResponseEntity<>(returnedEvents, HttpStatus.OK);
	}

	@GetMapping("/getAllEvents")
	public ResponseEntity<CombinedEventsDTO> getAllEvents(){
		CombinedEventsDTO allEvents = resourcesService.getAllEvents();
		return new ResponseEntity<>(allEvents, HttpStatus.OK);
	}

	@GetMapping("/getNews")
	public ResponseEntity<List<NewsDTO>> getCurrentNews() {
		List<NewsDTO> returnedNews = resourcesService.getNews();
		return new ResponseEntity<>(returnedNews, HttpStatus.OK);
	}
	
	@GetMapping("/getAllNews")
	public ResponseEntity<List<NewsDTO>> getAllNews() {
		List<NewsDTO> returnedNews = resourcesService.getAllNews();
		return new ResponseEntity<>(returnedNews, HttpStatus.OK);
	}

	@GetMapping("/getNews/{newsSlug}")
	public ResponseEntity<NewsDTO> getNewsEntry(@PathVariable("newsSlug") String newsSlug) {
		Validator.validate(newsSlug);
		NewsDTO newsEntry = resourcesService.getNewsBySlug(newsSlug);
		return new ResponseEntity<>(newsEntry, HttpStatus.OK);
	}

	@GetMapping("/getFunding")
	public ResponseEntity<List<FundingDTO>> getCurrentFunding() {
		List<FundingDTO> currentFundings = resourcesService.getFunding();
		return new ResponseEntity<>(currentFundings, HttpStatus.OK);
	}
	
	@GetMapping("/getAllFunding")
	public ResponseEntity<List<FundingDTO>> getAllFunding(){
		List<FundingDTO> allFundings = resourcesService.getAllFunding();
		return new ResponseEntity<>(allFundings, HttpStatus.OK);
	}
	
	@GetMapping("/getRecentlyApprovedStudies")
	public ResponseEntity<List<StudyViewDTO>> getRecentlyApprovedStudies() {
		List<StudyViewDTO> recentlyApprovedStudies = resourcesService.getRecentlyApprovedStudies();
		return new ResponseEntity<>(recentlyApprovedStudies, HttpStatus.OK);
	}

	@GetMapping("/getDccStats")
	public ResponseEntity<HomepageStatsDTO> getDccStats(){
		return new ResponseEntity<>(resourcesService.getAllDccStats(), HttpStatus.OK);
	}

	@GetMapping("/getHomepageContent")
	public ResponseEntity<HomepageContentDTO> getHomepageContent(){
		return new ResponseEntity<>(resourcesService.getHomepageContent(), HttpStatus.OK);
	}

	@GetMapping("/getNewsletters")
	public ResponseEntity<Map<Integer, List<NewsletterDTO>>> getNewsletters(){
		return ResponseEntity.ok(resourcesService.getNewsletters());
	}

}
