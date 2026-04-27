package org.canopyplatform.canopy.entityservice.repository;

import org.canopyplatform.canopy.entityservice.model.StudyPropertyValue;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class StudyPropertyValueRepositoryTest {

    @Mock
    private StudyPropertyValueRepository repository;

    @Test
    public void testFindByStudyIdAndEntityPropertyDisplaySettingsPage_OrderByEntityPropertyDisplayOrder_NonExistentStudyId() {
        Integer studyId = 999;
        String page = "study_search";
        when(repository.findByStudyIdAndPageOrderByDisplayOrder(studyId, page))
                .thenReturn(Collections.emptyList());

        // Call the repository method
        List<StudyPropertyValue> result = repository.findByStudyIdAndPageOrderByDisplayOrder(studyId, page);
        assertTrue(result.isEmpty());
    }

    @Test
    public void testFindByStudyIdAndEntityPropertyDisplaySettingsPage_OrderByEntityPropertyDisplayOrder_NonExistentPage() {
        Integer studyId = 1;
        String page = "nonexistent_page";
        when(repository.findByStudyIdAndPageOrderByDisplayOrder(studyId, page))
                .thenReturn(Collections.emptyList());

        // Call the repository method
        List<StudyPropertyValue> result = repository.findByStudyIdAndPageOrderByDisplayOrder(studyId, page);
        assertTrue(result.isEmpty());
    }
}
