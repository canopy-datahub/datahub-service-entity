package ex.org.project.entityservice.repository;

import ex.org.project.entityservice.model.StudyDocument;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class StudyDocumentRepositoryTest {


    @Test
    public void testFindByStudyIdOrderByDisplayOrder() {
        // Create sample data
        Integer studyId = 1;
        List<StudyDocument> expectedStudyDocuments = new ArrayList<>();
        expectedStudyDocuments.add(new StudyDocument());
        expectedStudyDocuments.add(new StudyDocument());
        StudyDocumentRepository repository = mock(StudyDocumentRepository.class);

        // Mock the repository method
        when(repository
                .findByStudyIdAndLookupDataFileCategoryCategoryGroupOrderByDisplayOrder(studyId, "document"))
                .thenReturn(expectedStudyDocuments);

        // Call the method under test
        List<StudyDocument> actualStudyDocuments = repository
                .findByStudyIdAndLookupDataFileCategoryCategoryGroupOrderByDisplayOrder(studyId, "document");

        // Verify the results
        assertNotNull(actualStudyDocuments);
        assertEquals(expectedStudyDocuments.size(), actualStudyDocuments.size());
        // You can add more assertions if needed
    }

    @Test
    public void testFindById_DocumentNotFound() {
        Integer documentId = 1;
        // Mock repository and data
        StudyDocumentRepository repository = mock(StudyDocumentRepository.class);
        when(repository.findById(documentId)).thenReturn(Optional.empty());
        Optional<StudyDocument> result = repository.findById(documentId);

        // Assertions
        assertEquals(Optional.empty(), result);
    }

}