package ex.org.project.entityservice.controller;

import ex.org.project.entityservice.model.DTO.EntityDTO;
import ex.org.project.entityservice.model.DTO.PropsDTO;
import ex.org.project.entityservice.model.DTO.VariableDTO;
import ex.org.project.entityservice.service.EntityService;
import ex.org.project.entityservice.service.VariableService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/search")
@RequiredArgsConstructor
public class SearchController {

    private final EntityService entityService;

    private final VariableService variableService;

    @GetMapping("/getFacets")
    // Calls the entityService to retrieve the display settings based on facet type.
    public ResponseEntity<List<EntityDTO>> getFacets(@RequestParam(value = "type", defaultValue = "study") String type ) {
        List<EntityDTO> displaySettingsMap = entityService.getDisplaySettingsByEntityType(type) ;
        return new ResponseEntity<>(displaySettingsMap, HttpStatus.OK);
    }

    @GetMapping("/getProps")
    // Calls the entityService to retrieve the display settings for properties
    public ResponseEntity<Map<String, List<PropsDTO>>> getProps() {
        Map<String, List<PropsDTO>> displaySettingsMap = entityService.getProps();
        return new ResponseEntity<>(displaySettingsMap, HttpStatus.OK);
    }

	@GetMapping("/variables")
	public ResponseEntity<List<VariableDTO>> getVariables(@RequestParam Optional<Integer> studyId) {
		if (studyId.isEmpty()) {
			return ResponseEntity.ok(variableService.getAllVariables());
		} else {
			return ResponseEntity.ok(variableService.getVariables(studyId.get()));
		}
	}

}
